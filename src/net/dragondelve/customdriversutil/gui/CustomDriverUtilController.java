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

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.Editor;
import net.dragondelve.customdriversutil.gui.editor.TrackLibraryEditor;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class CustomDriverUtilController implements StageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private AnchorPane centralAnchorPane;

    @FXML
    private MenuItem editTracksItem;

    @FXML
    private MenuItem exportTracksItem;

    @FXML
    private MenuItem importTracksItem;

    Stage stage = new Stage();

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:"+DDUtil.DRIVER_EDITOR_FXML_PATHNAME));
            Node center = loader.load();
            centralAnchorPane.getChildren().add(center);
            AnchorPane.setTopAnchor(center, 0.0);
            AnchorPane.setBottomAnchor(center, 0.0);
            AnchorPane.setLeftAnchor(center, 0.0);
            AnchorPane.setRightAnchor(center, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        editTracksItem.setOnAction(e-> editTracksAction());
        exportTracksItem.setOnAction(e->exportTracksAction());
        importTracksItem.setOnAction(e->importTracksAction());
    }

    private void editTracksAction() {
        Editor<Track> controller = new TrackLibraryEditor();
        controller.setItems(LibraryManager.getInstance().getTrackLibrary().getTracks());
        try {
            URL editorFXMLURL = new URL("file:"+DDUtil.TRACK_EDITOR_FXML_PATHNAME);
            Stage stage = new Stage();
            try {
                FXMLLoader loader = new FXMLLoader(editorFXMLURL);
                loader.setController(controller);
                controller.setStage(stage);
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.initOwner(this.stage);
                stage.setTitle("Track Library");
                stage.showAndWait();
                LibraryManager.getInstance().getTrackLibrary().setTracks(FXCollections.observableArrayList(controller.getItems()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void exportTracksAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Track Library");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml library file","*.xml"));
        fileChooser.setInitialDirectory(new File("library/tracks"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if(selectedFile != null)
            LibraryManager.getInstance().exportTrackLibrary(selectedFile.getPath());
    }

    private void importTracksAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Track Library");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml library file","*.xml"));
        fileChooser.setInitialDirectory(new File("library/tracks"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null)
            if(LibraryManager.getInstance().importTrackLibrary(selectedFile.getPath())) {
                Configurator.getInstance().getConfiguration().setTrackLibraryPathname(selectedFile.getPath());
                Configurator.getInstance().saveConfiguration();
            }
    }

    /**
     * Creates a stage on which an editor controlled by a StageController can be displayed.
     * Loads an FXML File from the URL, sets the controller and finally displays the stage.
     * @param editorFXMLURL URL to a FXML file that contains the editor's gui information.
     * @param controller controller to be used for the new Stage.
     * @param title text title to be displayed on the new Stage.
     */
    private void openEditor(URL editorFXMLURL, StageController controller, String title) {
        Stage stage = new Stage();
        try {
            FXMLLoader loader = new FXMLLoader(editorFXMLURL);
            loader.setController(controller);
            controller.setStage(stage);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initOwner(this.stage);
            stage.setTitle(title);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
