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

public class ListToListChooser<T> extends HBox {
    /**
     * ListView that displays the tracks selected by the user. Items of this list are going to be passed forward to the
     * DriverEditor to edit the override for the chosen tracks
     */
    @FXML
    private ListView<T> selectedListView;

    /**
     * ListView that displays all tracks in the current track library, minus the tracks that are already selected in the
     * selectedListView
     */
    @FXML
    private ListView<T> libraryListView;

    @FXML
    private HBox rootPane;

    public ListToListChooser() {
        FXMLLoader loader = new FXMLLoader(MabelUtil.LIST_TO_LIST_FXML_URL);
        loader.setController(this);
        try {
            this.getChildren().add(loader.load());
        } catch (IOException e) {
            MabelUtil.DEFAULT_LOGGER.log(Level.SEVERE, "Could not load List to List FXML");
        }
    }

    @FXML
    public void initialize() {

        HBox.setHgrow(rootPane, Priority.ALWAYS);
        //Handling Double Clicks
        libraryListView.setOnMouseClicked(e-> {
            T selectedItem = libraryListView.getSelectionModel().getSelectedItem();
            if (e.getClickCount() == 2 && selectedItem != null) {
                selectedListView.getItems().add(selectedItem);
                libraryListView.getItems().remove(selectedItem);
            }
        });

        selectedListView.setOnMouseClicked(e-> {
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
        selectedListView.setOnDragOver((DragEvent event) ->  {
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
        libraryListView.setOnDragOver((DragEvent event) ->  {
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

    public ListView<T> getSelectedListView() {
        return selectedListView;
    }

    public ListView<T> getLibraryListView() {
        return libraryListView;
    }
}
