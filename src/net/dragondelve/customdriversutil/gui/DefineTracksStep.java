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

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.DriverEditor;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.model.TrackOverride;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.mabelfx.ListToListChooser;
import net.dragondelve.mabelfx.StageController;

import java.io.IOException;
import java.util.logging.Level;

/**
 * DefineTracksStep is an intermediate step between pressing the addTrackOverrideButton and the Driver Editor opening
 * in the Override mode to allow the user to set all the tracks on which the newly created override will be overriding
 * the base driver properties. This is a controller class for  fxml/DefineStep.fxml.
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
     * List to List chooser for user choice
     */
    @FXML
    private ListToListChooser<Track> listToListChooser;

    /**
     * The root pane of the entire scene. Used to assign the css style to the controls and containers.
     */
    @FXML
    private VBox rootPane;

    /**
     * Stage on which this editor is displayed.
     */
    private Stage stage;

    /**
     * Currently edited or newly created track override.
     */
    private TrackOverride trackOverride;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        stage.setTitle("Choose Tracks");

        if (trackOverride == null) {
            DDUtil.DEFAULT_LOGGER.log(Level.SEVERE, "Define Track Step initialized without calling setTrackOverride() first.");
            return;
        }

        listToListChooser.getSelectedListView().setItems(trackOverride.getTrack());
        listToListChooser.getLibraryListView().setItems(LibraryManager.getInstance().getTrackLibrary().getTracks());

        listToListChooser.getSelectedListView().getItems().addListener((ListChangeListener<? super Track>) c -> nextButton.setDisable(c.getList().isEmpty()));

        //setting up the buttons
        nextButton.setOnAction(e -> nextAction());
        cancelButton.setOnAction(e -> cancelAction());

        nextButton.setDisable(trackOverride == null || trackOverride.getTrack().isEmpty());
    }

    /**
     * Lightweight mutator method. Used when editing a trackOverride.
     *
     * @param trackOverride trackOverride that is being edited if this editor is performing the edit action
     */
    @FXML
    public void setTrackOverride(TrackOverride trackOverride) {
        this.trackOverride = trackOverride;
        if (listToListChooser != null)
            listToListChooser.getSelectedListView().getItems().addAll(this.trackOverride.getTrack());
    }

    /**
     * Lightweight mutator method.
     * Should be called before the editor is initialized by JavaFX.
     *
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
        trackOverride.getTrack().clear();
        stage.close();
    }

    /**
     * Action that is performed by the nextButton. Opens a driver editor in a new window
     */
    private void nextAction() {
        if (listToListChooser.getSelectedListView().getItems().isEmpty())
            return;
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DRIVER_EDITOR_FXML_URL);
        DriverEditor editor = new DriverEditor();
        loader.setController(editor);
        try {
            Stage editorStage = new Stage();
            editorStage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
            editorStage.setTitle(stage.getTitle());
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(loader.load());
            ListView<Track> listView = new ListView<>();
            listView.setItems(listToListChooser.getSelectedListView().getItems());
            listView.getStyleClass().add("inactive-list-view");
            borderPane.setLeft(listView);
            borderPane.getStylesheets().clear();
            borderPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
            Scene scene = new Scene(borderPane);
            editorStage.setScene(scene);
            editorStage.setTitle("Track Override Editor");
            stage.close();
            editor.setEditedDriver(trackOverride);
            editorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
