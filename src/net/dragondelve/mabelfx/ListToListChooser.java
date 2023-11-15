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

package net.dragondelve.mabelfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import net.dragondelve.mabelfx.util.MabelUtil;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Displays two listviews that can be dragged or dropped from one to another the left list is labeled as a "library" list view,
 * the right list is labeled as a "selected" list view. They are labeled by their respective labels.
 *
 * @param <T> Type that will be contained by each List within ListToList
 */
public class ListToListChooser<T> extends HBox {
    /**
     * ListView that displays the items selected by the user.
     */
    @FXML
    private ListView<T> selectedListView;

    /**
     * ListView that displays all tracks in the current track library, minus the items that are already selected in the
     * selectedListView
     */
    @FXML
    private ListView<T> libraryListView;

    /**
     * Label that labels the selectedListView
     */
    @FXML
    private Label selectedLabel;

    /**
     * Label that labels the libraryListView
     */
    @FXML
    private Label libraryLabel;

    /**
     * rootPane of this element. It is used to apply the stylesheet to this element
     */
    @FXML
    private HBox rootPane;

    /**
     * Default Constructor.
     */
    public ListToListChooser() {
        this(MabelUtil.DEFAULT_STYLESHEET);
    }

    /**
     * Creates a new instance of ListToListChooser
     *
     * @param stylesheet path to a css resource to be used for styling this element.
     */
    public ListToListChooser(String stylesheet) {
        FXMLLoader loader = new FXMLLoader(MabelUtil.LIST_TO_LIST_FXML_URL);
        loader.setController(this);
        try {
            this.getChildren().add(loader.load());
        } catch (IOException e) {
            MabelUtil.DEFAULT_LOGGER.log(Level.SEVERE, "Could not load List to List FXML");
        }

        rootPane.getStylesheets().add(stylesheet);
    }

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {

        HBox.setHgrow(rootPane, Priority.ALWAYS);
        //Handling Double Clicks
        libraryListView.setOnMouseClicked(e -> {
            T selectedItem = libraryListView.getSelectionModel().getSelectedItem();
            if (e.getClickCount() == 2 && selectedItem != null) {
                selectedListView.getItems().add(selectedItem);
                libraryListView.getItems().remove(selectedItem);
            }
        });

        selectedListView.setOnMouseClicked(e -> {
            T selectedItem = selectedListView.getSelectionModel().getSelectedItem();
            if (e.getClickCount() >= 2 && selectedItem != null) {
                libraryListView.getItems().add(selectedItem);
                selectedListView.getItems().remove(selectedItem);
            }
        });

        //Handle Drag and drop from libraryListView into selectedListView.
        libraryListView.setOnDragDetected(event -> {
            if (libraryListView.getSelectionModel().getSelectedItem() != null) {
                Dragboard db = libraryListView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(libraryListView.getSelectionModel().getSelectedItem().toString());
                db.setContent(content);
            }
            event.consume();
        });

        libraryListView.setOnMouseDragged(event -> event.setDragDetect(true));
        selectedListView.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != selectedListView && event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        selectedListView.setOnDragDropped((DragEvent event) -> {
            T selectedItem = libraryListView.getSelectionModel().getSelectedItem();
            if (libraryListView.getSelectionModel().getSelectedItem() != null) {
                selectedListView.getItems().add(selectedItem);
                libraryListView.getItems().remove(selectedItem);
            }
            event.consume();

        });

        //Handle Drag and drop from selectedListView into libraryListView
        selectedListView.setOnDragDetected(event -> {
            if (selectedListView.getSelectionModel().getSelectedItem() != null) {
                Dragboard db = selectedListView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedListView.getSelectionModel().getSelectedItem().toString());
                db.setContent(content);
            }

            event.consume();
        });

        selectedListView.setOnMouseDragged(event -> event.setDragDetect(true));
        libraryListView.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != libraryListView && event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        libraryListView.setOnDragDropped((DragEvent event) -> {
            T selectedItem = selectedListView.getSelectionModel().getSelectedItem();
            if (selectedListView.getSelectionModel().getSelectedItem() != null) {
                libraryListView.getItems().add(selectedItem);
                selectedListView.getItems().remove(selectedItem);
            }
            event.consume();
        });
    }

    /**
     * Lightweight accessor method.
     *
     * @return Label that labels the selectedListView
     */
    public ListView<T> getSelectedListView() {
        return selectedListView;
    }

    /**
     * Lightweight accessor method.
     *
     * @return ListView that displays all tracks in the current track library
     */
    public ListView<T> getLibraryListView() {
        return libraryListView;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Label that labels the selectedListView
     */
    public Label getSelectedLabel() {
        return selectedLabel;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Label that labels the libraryListView
     */
    public Label getLibraryLabel() {
        return libraryLabel;
    }
}
