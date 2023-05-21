// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package net.dragondelve.customdriversutil.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.DriverEditor;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.model.TrackOverride;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.io.IOException;
import java.util.logging.Level;

/**
 * DefineTracksStep is an intermediate step between pressing the addTrackOverrideButton and the Driver Editor opening
 * in the Override mode to allow the user to set all the tracks on which the newly created override will be overriding
 * the base driver properties. This is a controller class for  fxml/DefineTracksStep.fxml.
 */
public class DefineTracksStep implements StageController {

    /**
     * Button that performs nextAction on action.
     */
    @FXML
    private Button nextButton;

    /**
     * Button that performs the cancelAction on action.
     */
    @FXML
    private Button cancelButton;

    /**
     * The root pane of the entire scene. Used to assign the css style to the controls and containers.
     */
    @FXML
    private VBox rootPane;

    /**
     * ListView that displays the tracks selected by the user. Items of this list are going to be passed forward to the
     * DriverEditor to edit the override for the chosen tracks
     */
    @FXML
    private ListView<Track> selectedListView;

    /**
     * ListView that displays all tracks in the current track library, minus the tracks that are already selected in the
     * selectedListView
     */
    @FXML
    private ListView<Track> trackLibraryListView;

    /**
     * Stage on which this editor is displayed.
     */
    private Stage stage;

    private TrackOverride trackOverride;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        if(trackOverride == null) {
            DDUtil.DEFAULT_LOGGER.log(Level.SEVERE, "Define Track Step initialized without calling setTrackOverride() first.");
            return;
        }

        selectedListView.setItems(trackOverride.getTrack());
        trackLibraryListView.setItems(LibraryManager.getInstance().getTrackLibrary().getTracks());
        //Remove everything that is already selected in the Selected List view.
        trackLibraryListView.getItems().removeAll(selectedListView.getItems());

        //Handling Double Clicks
        trackLibraryListView.setOnMouseClicked(e-> {
            Track selectedTrack = trackLibraryListView.getSelectionModel().getSelectedItem();
            if(e.getClickCount() == 2 && selectedTrack != null) {
                selectedListView.getItems().add(selectedTrack);
                trackLibraryListView.getItems().remove(selectedTrack);
            }
        });

        selectedListView.setOnMouseClicked(e-> {
            Track selectedTrack = selectedListView.getSelectionModel().getSelectedItem();
            if(e.getClickCount() >= 2 && selectedTrack != null) {
                trackLibraryListView.getItems().add(selectedTrack);
                selectedListView.getItems().remove(selectedTrack);
            }
        });

        //Handle Drag and drop from trackLibraryListView into selectedListView.
        trackLibraryListView.setOnDragDetected(event -> {
            if(trackLibraryListView.getSelectionModel().getSelectedItem() != null) {
                Dragboard db = trackLibraryListView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(trackLibraryListView.getSelectionModel().getSelectedItem().getName());
                db.setContent(content);
            }
            event.consume();
        });

        trackLibraryListView.setOnMouseDragged(event -> event.setDragDetect(true));
        selectedListView.setOnDragOver((DragEvent event) ->  {
                if (event.getGestureSource() != selectedListView && event.getDragboard().hasString())
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                event.consume();
        });

        selectedListView.setOnDragDropped((DragEvent event) -> {
            Track selectedTrack = trackLibraryListView.getSelectionModel().getSelectedItem();
            if(trackLibraryListView.getSelectionModel().getSelectedItem() != null) {
                selectedListView.getItems().add(selectedTrack);
                trackLibraryListView.getItems().remove(selectedTrack);
            }
            event.consume();

        });

        //Handle Drag and drop from selectedListView into trackLibraryListView.
        selectedListView.setOnDragDetected(event -> {
            if(selectedListView.getSelectionModel().getSelectedItem() != null) {
                Dragboard db = selectedListView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedListView.getSelectionModel().getSelectedItem().getName());
                db.setContent(content);
            }

            event.consume();
        });

        selectedListView.setOnMouseDragged(event -> event.setDragDetect(true));
        trackLibraryListView.setOnDragOver((DragEvent event) ->  {
            if (event.getGestureSource() != trackLibraryListView && event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        trackLibraryListView.setOnDragDropped((DragEvent event) -> {
            Track selectedTrack = selectedListView.getSelectionModel().getSelectedItem();
            if(selectedListView.getSelectionModel().getSelectedItem() != null) {
                trackLibraryListView.getItems().add(selectedTrack);
                selectedListView.getItems().remove(selectedTrack);
            }
            event.consume();
        });

        //setting up the buttons
        nextButton.setOnAction(e -> nextAction());
        cancelButton.setOnAction(e -> cancelAction());
    }

    /**
     * Lightweight mutator method. Used when editing a trackOverride.
     * @param trackOverride trackOverride that is being edited if this editor is performing the edit action
     */
    @FXML
    public void setTrackOverride(TrackOverride trackOverride) {
        this.trackOverride = trackOverride;
        if(selectedListView != null)
            selectedListView.getItems().addAll(this.trackOverride.getTrack());
    }

    /**
     * Lightweight mutator method.
     * Should be called before the editor is initialized by JavaFX.
     * @param stage stage on which this editor is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * Action that is performed by the cancelButton. Closes this editor's Stage.
     */
    private void cancelAction() {
        stage.close();
    }

    /**
     * Action that is performed by the nextButton. Opens a driver editor in a new window
     */
    private void nextAction() {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DRIVER_EDITOR_FXML_URL);
        DriverEditor editor = new DriverEditor();
        loader.setController(editor);
        try {
            Stage editorStage = new Stage();
            editorStage.setTitle(stage.getTitle());
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(loader.load());
            borderPane.setLeft(selectedListView);
            selectedListView.setDisable(true);
            borderPane.getStylesheets().clear();
            borderPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
            Scene scene = new Scene(borderPane);
            editorStage.setScene(scene);
            stage.close();
            editor.setEditedDriver(trackOverride);
            editorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
