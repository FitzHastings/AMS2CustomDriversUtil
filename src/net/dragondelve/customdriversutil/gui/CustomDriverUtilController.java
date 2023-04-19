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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.Editor;
import net.dragondelve.customdriversutil.gui.editor.TrackLibraryEditor;
import net.dragondelve.customdriversutil.gui.editor.VehicleClassLibraryEditor;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.VehicleClassLibrary;
import net.dragondelve.customdriversutil.model.xml.XMLGridImporter;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.customdriversutil.util.PathRelativisor;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controls the main screen of the CustomDriverUtility.
 * Controls the top menu and what is shown inside the rootPane
 */
public class CustomDriverUtilController implements StageController {

    /**
     * Root Pane of the main screen. Is used to display everything inside the main window.
     * The main css style is applied to the rootPane.
     */
    @FXML
    private BorderPane rootPane;

    /**
     * Central Anchor Pane that is used to display the Driver Editor.
     */
    @FXML
    private AnchorPane centralAnchorPane;

    /**
     * Import Grid with a Vehicle Class Item.
     * Imports the Grid with a Vehicle class, adding the vehicle class to the current VehicleClassLibrary.
     */
    @FXML
    private MenuItem importGridITemWithClassItem;

    /**
     * Edit Tracks Menu Item. Displays the TrackLibraryEditor
     */
    @FXML
    private MenuItem editTracksItem;

    /**
     * Export Track Library Menu Item. Shows a FileChooser and if a selection is made attempts to export the currently
     * loaded track library to this file.
     */
    @FXML
    private MenuItem exportTracksItem;

    /**
     * Import Track Library Menu Item. Shows a FileChooser and if a selection is made attempts to import a track library.
     * from the File chosen.
     */
    @FXML
    private MenuItem importTracksItem;

    /**
     * Edit Vehicle Class Library Menu Item.  Displays the VehicleClassLibraryEditor
     */
    @FXML
    private MenuItem editVehicleClassesItem;

    /**
     *  Export Vehicle Class Library Menu Item.  Shows a FileChooser and if a selection is made attempts to export the
     *  currently loaded vehicle class library to this file.
     */
    @FXML
    private MenuItem exportVehicleClassesItem;

    /**
     * Import Vehicle Class Library Menu Item. Shows a FileChooser and if a selection is made attempts to import a
     * vehicle class library from the File chosen.
     */
    @FXML
    private MenuItem importVehicleClassesItem;

    /**
     * Stage on which this controller is displayed.
     * This is also the primaryStage in the Application's main method.
     */
    Stage stage = new Stage();

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        try {
            FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DRIVER_EDITOR_FXML_URL);
            Node center = loader.load();
            centralAnchorPane.getChildren().add(center);
            AnchorPane.setTopAnchor(center, 0.0);
            AnchorPane.setBottomAnchor(center, 0.0);
            AnchorPane.setLeftAnchor(center, 0.0);
            AnchorPane.setRightAnchor(center, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        importGridITemWithClassItem.setOnAction(e->importGridITemWithClassAction());

        editTracksItem.setOnAction(e-> editTracksAction());
        exportTracksItem.setOnAction(e-> exportTracksAction());
        importTracksItem.setOnAction(e-> importTracksAction());

        editVehicleClassesItem.setOnAction(e-> editVehicleClassesAction());
        exportVehicleClassesItem.setOnAction(e-> exportVehicleClassesAction());
        importVehicleClassesItem.setOnAction(e-> importVehicleClassesAction());
    }

    /**
     * Action that is performed by importGridITemWithClassItem.
     */
    private void importGridITemWithClassAction() {
        File file = chooseFileToOpen("Choose XML Grid File", "grids");
        VehicleClass vehicleClass = XMLGridImporter.importVehicleClassFromXMLGrid(chooseFileToOpen("Choose XML Grid File", "grids"));
        if(vehicleClass != null)
            LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(vehicleClass);
    }

    /**
     * Action that is performed by editTracksItem.
     * Opens a new Stage with a TrackLibraryEditor, and waits until the stage is closed by the user.
     */
    private void editTracksAction() {
        Editor<Track> controller = new TrackLibraryEditor();
        controller.setItems(LibraryManager.getInstance().getTrackLibrary().getTracks());
        openEditor(DDUtil.getInstance().TRACK_EDITOR_FXML_URL, controller, "Track Library");
    }

    /**
     * Action that is performed by the exportTracksItem.
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to export the currently loaded
     * TrackLibrary to the chosen file.
     */
    private void exportTracksAction() {
        File selectedFile = chooseFileToSave("Export Track Library", "library/tracks");
        if(selectedFile != null)
            LibraryManager.getInstance().exportTrackLibrary(selectedFile.getPath());
    }

    /**
     * Action that is performed by importTracksItem.
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to import a TrackLibrary from a
     * chosen file.
     */
    private void importTracksAction() {
        File selectedFile = chooseFileToOpen("Import Track Library", "library/tracks");
        if(selectedFile != null && LibraryManager.getInstance().importTrackLibrary(selectedFile.getPath())) {
            PathRelativisor relativisor = new PathRelativisor(selectedFile.toPath());
            Configurator.getInstance().getConfiguration().setTrackLibraryPathname(relativisor.relativize());
            Configurator.getInstance().saveConfiguration();
        }
    }

    /**
     * Action that is performed by editVehicleClassesItem.
     * Opens a new Stage with a VehicleClassLibraryEditor, and waits until the stage is closed by the user.
     */
    private void editVehicleClassesAction() {
        Editor<VehicleClass> controller = new VehicleClassLibraryEditor();
        controller.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
        openEditor(DDUtil.getInstance().VEHICLE_CLASS_EDITOR_FXML_URL, controller, "Vehicle Classes");
    }

    /**
     * Action that is performed by importVehicleClassesItem.
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to export the currently loaded
     * VehicleClassLibrary to the chosen file.
     */
    private void exportVehicleClassesAction() {
        File selectedFile = chooseFileToSave("Import Vehicle Class Library", "library/vehicles");
        if(selectedFile != null) {
            LibraryManager.getInstance().exportVehicleClassLibrary(selectedFile.getPath());
        }
    }

    /**
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to import a VehicleClassLibrary from
     * the chosen file.
     */
    private void importVehicleClassesAction() {
        File selectedFile = chooseFileToOpen("Export Vehicle Class Library", "library/vehicles");
        if(selectedFile != null && LibraryManager.getInstance().importVehicleClassLibrary(selectedFile.getPath())) {
            PathRelativisor relativisor = new PathRelativisor(selectedFile.toPath());
            Configurator.getInstance().getConfiguration().setVehicleClassLibraryPathname(relativisor.relativize());
            Configurator.getInstance().saveConfiguration();
        }
    }

    /**
     * Creates a stage on which an editor controlled by a StageController can be displayed.
     * Loads an FXML File from the URL, sets the controller and finally displays the stage.
     * @param editorFXMLURL URL to a FXML file that contains the editor's gui information.
     * @param controller Controller to be used for the new Stage.
     * @param title Text title to be displayed on the new Stage.
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

    /**
     * Creates a new FileChooser, sets its extension filter to *.xml and sets its initial directory to the pathname provided,
     * and the title of its Stage to the title provided.
     * @param title Title of the Stage on which the FileChooser is going to be displayed.
     * @param initialDirectory Pathname to an initial directory for the FileChooser.
     * @return A new FileChooser that is ready to be displayed.
     */
    private FileChooser createLibraryFileChooser(String title, String initialDirectory) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml library file", "*.xml"));
        fileChooser.setInitialDirectory(new File(initialDirectory));
        return fileChooser;
    }

    /**
     * Creates a new FileChooser, sets its extension filter to *.xml and sets its initial directory to the pathname provided.
     * and the title of its Stage to the title provided. Displays the FileChooser to the user with showOpenDialog, waits
     * for a selection to be made and returns the File selected.
     * @param title Title of the Stage on which the FileChooser is going to be displayed.
     * @param initialDirectory Pathname to an initial directory for the FileChooser.
     * @return File Chosen by the user. returns null if no file was chosen.
     */
    private File chooseFileToOpen(String title, String initialDirectory) {
        return createLibraryFileChooser(title, initialDirectory).showOpenDialog(stage);
    }

    /**
     * Creates a new FileChooser, sets its extension filter to *.xml and sets its initial directory to the pathname provided.
     * and the title of its Stage to the title provided. Displays the FileChooser to the user with showSaveDialog, waits
     * for a selection to be made and returns the File selected.
     * @param title Title of the Stage on which the FileChooser is going to be displayed.
     * @param initialDirectory Pathname to an initial directory for the FileChooser.
     * @return File Chosen by the user. returns null if no file was chosen.
     */
    private File chooseFileToSave(String title, String initialDirectory) {
        return  createLibraryFileChooser(title, initialDirectory).showSaveDialog(stage);
    }

    /**
     * Lightweight mutator method.
     * Should be called before this class is displayed to the user.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
