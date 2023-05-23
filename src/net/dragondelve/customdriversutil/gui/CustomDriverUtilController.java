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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.DriverEditor;
import net.dragondelve.customdriversutil.gui.editor.Editor;
import net.dragondelve.customdriversutil.gui.editor.TrackLibraryEditor;
import net.dragondelve.customdriversutil.gui.editor.VehicleClassLibraryEditor;
import net.dragondelve.customdriversutil.model.*;
import net.dragondelve.customdriversutil.model.xml.XMLGridExporter;
import net.dragondelve.customdriversutil.model.xml.XMLGridImporter;
import net.dragondelve.customdriversutil.util.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controls the main screen of the CustomDriverUtility.
 * Controls the top menu and what is shown inside the rootPane
 */
public class CustomDriverUtilController implements StageController {
    /**
     * Button that performs addDriverAction on action.
     */
    @FXML
    private Button addDriverButton;

    /**
     * Button that performs removeDriverAction on action.
     */
    @FXML
    private Button removeDriverButton;

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
    private MenuItem importGridWithClassItem;

    /**
     * Imports a Grid.
     * Imports the grid without the vehicle class.
     */
    @FXML
    private MenuItem importGridItem;

    /**
     * Exports a Grid
     * Exports a grid to XML.
     */
    @FXML
    private MenuItem exportGridItem;

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
     * Shows a configuration screen allowing  the user to directly edit the configuration file. Performs configurationAction
     * on action.
     */
    @FXML
    private MenuItem configurationMenuItem;


    /**
     * Tableview That displays the drivers from the grid that is being edited.
     */
    @FXML
    private TableView<Driver> driversTableView;

    /**
     * TableView that displays the trackOverrides for the driver selected int the driversTableView
     */
    @FXML
    private TableView<TrackOverride> trackOverrideTableView;

    /**
     * TableColumn in trackOverrideTableView that displays the track name for the given override.
     */
    @FXML
    private TableColumn<TrackOverride, String> trackNameColumn;

    /**
     * TableColumn in driversTableView that displays the name of a driver
     */
    @FXML
    private TableColumn<Driver, String> driverNameColumn;

    /**
     * TableColumn in driver driversTableView that displays the three letter code for the driver's country.
     */
    @FXML
    private TableColumn<Driver, String> driverCountryColumn;

    /**
     * Button that performs addTrackOverrideAction on action.
     */
    @FXML
    private Button addTrackOverrideButton;

    /**
     * Button that performs removeTrackOverrideAction on action.
     */
    @FXML
    private Button removeTrackOverrideButton;

    /**
     * Button that performs editTrackOverrideAction on action.
     */
    @FXML
    private Button editTrackOverrideButton;

    /**
     * Stage on which this controller is displayed.
     * This is also the primaryStage in the Application's main method.
     */
    private Stage stage = new Stage();

    /**
     * Grid that is being edited by the editor.
     */
    private final Grid editedGrid = new Grid();

    /**
     * an instance of DriverEditor that controls the driver editor that will edit a driver selected in driverTableView.
     */
    private final DriverEditor driverEditor = new DriverEditor();

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        trackNameColumn.setCellValueFactory(e-> e.getValue().getTrack().get(0).nameProperty());
//        GridGenerator gridGenerator = new GridGenerator();
//        VehicleClass vehicleClass = LibraryManager.getInstance().getVehicleClassLibrary().findVehicleClassWithXmlName("F-Inter");
//        if (vehicleClass != null) {
//            gridGenerator.setVehicleClass(vehicleClass);
//            editedGrid = gridGenerator.generateNewGrid();
//        }

        try {
            FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DRIVER_EDITOR_FXML_URL);
            loader.setController(driverEditor);
            Node center = loader.load();
            centralAnchorPane.getChildren().add(center);
            AnchorPane.setTopAnchor(center, 0.0);
            AnchorPane.setBottomAnchor(center, 0.0);
            AnchorPane.setLeftAnchor(center, 0.0);
            AnchorPane.setRightAnchor(center, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        exportGridItem.setOnAction(e -> exportGridAction());
        importGridItem.setOnAction(e -> importGridAction());
        importGridWithClassItem.setOnAction(e -> importGridWithClassAction());

        editTracksItem.setOnAction(e -> editTracksAction());
        exportTracksItem.setOnAction(e -> exportTracksAction());
        importTracksItem.setOnAction(e -> importTracksAction());

        editVehicleClassesItem.setOnAction(e -> editVehicleClassesAction());
        exportVehicleClassesItem.setOnAction(e -> exportVehicleClassesAction());
        importVehicleClassesItem.setOnAction(e -> importVehicleClassesAction());

        configurationMenuItem.setOnAction(e -> configurationAction());

        driversTableView.setItems(editedGrid.getDrivers());
        driverNameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        driverCountryColumn.setCellValueFactory(e -> e.getValue().countryProperty());
        driversTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                driverEditor.setEditedDriver(newValue);
                trackOverrideTableView.setItems(newValue.getTrackOverrides());
            }
        });

        addDriverButton.setOnAction(e -> addDriverAction());
        removeDriverButton.setOnAction(e -> removeDriverAction());
        addTrackOverrideButton.setOnAction(e -> addTrackOverrideAction());
        removeTrackOverrideButton.setOnAction(e -> removeTrackOverrideAction());
        editTrackOverrideButton.setOnAction(e -> editTrackOverrideAction());
    }

    /**
     * Adds a new driver to the editedGrid.
     */
    private void addDriverAction() {
        Driver driver = new Driver();
        //TODO: Assign default values.
        editedGrid.getDrivers().add(driver);
    }

    /**
     * If a driver is selected in the driversTableView, removes the selected driver form the editedGrid.
     */
    private void removeDriverAction() {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        if(editedDriver != null)
            driversTableView.getItems().remove(editedDriver);
    }

    /**
     * Opens the DefineTrack step window. If any tracks are chosen by the user it adds a new TrackOverride for the tracks
     * selected..
     */
    private void addTrackOverrideAction() {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        TrackOverride override = new TrackOverride();
        initTrackOverrideEditor(override);
        if (override.getTrack().size() > 0)
            editedDriver.getTrackOverrides().add(override);
    }

    /**
     * if a track override is chosen in the trackOverrideTableView removes the selected track.
     */
    private void removeTrackOverrideAction() {
        TrackOverride selectedTrackOverride = trackOverrideTableView.getSelectionModel().getSelectedItem();
        if(selectedTrackOverride != null)
            trackOverrideTableView.getItems().remove(selectedTrackOverride);
    }

    /**
     * if a track override is chosen in the trackOverrideTalbeView it opens the defineTrackStep window and passes it the
     * currently selected track override for editing.
     */
    private void editTrackOverrideAction() {
        TrackOverride selectedTrackOverride = trackOverrideTableView.getSelectionModel().getSelectedItem();
        if (selectedTrackOverride != null)
            initTrackOverrideEditor(selectedTrackOverride);
    }

    /**
     * Initializes the Track Override Editor by opening the defineTrackStep and passing it the trackOverride that
     * is passed as an argument.
     * @param override Track specific override that is going to be edited by the trackOverrideEditor.
     */
    private void initTrackOverrideEditor(TrackOverride override) {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        if(editedDriver == null)
            return;
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DEFINE_TRACKS_STEP_FXML_URL);
        DefineTracksStep stageController = new DefineTracksStep();
        stageController.setTrackOverride(override);
        loader.setController(stageController);
        Stage stage = new Stage();
        stageController.setStage(stage);
        stage.initOwner(this.stage);
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action that is performed by ExportGridItem. Exports the currently edited grid to an XML file chosen by the
     * FileChooser that is displayed to the user. If the selection is made exports the currently edited grid to the file
     * selected.
     */
    private void exportGridAction() {
        File file = chooseFileToSave("Choose XML Grid File", "grids");
        GridExporter exporter = new XMLGridExporter();
        if(file != null)
            exporter.exportToFile(editedGrid, file);

    }

    /**
     * Action that is performed by importGridITemWithClassItem. Displays the file chooser with extension filters set for
     * xml files only in the grids folder by default. If a selection is made it will attempt to import the grid.
     */
    private void importGridWithClassAction() {
        File file = chooseFileToOpen("Choose XML Grid File", "grids");
        VehicleClass vehicleClass = XMLGridImporter.importVehicleClassFromXMLGrid(chooseFileToOpen("Choose XML Grid File", "grids"));
        if(vehicleClass != null)
            LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(vehicleClass);
        GridImporter importer = new XMLGridImporter();
        Grid importedGrid = importer.importFromFile(file);
         if(importedGrid != null) {
            editedGrid.getDrivers().clear();
            editedGrid.getDrivers().addAll(importedGrid.getDrivers());
            editedGrid.setVehicleClass(vehicleClass);
        }
        //TODO:Add this new class to the library and save it. Preferably allow user to rename it.

    }

    /**
     * Action that is performed by ImportGridItem. Displays the file chooser with extension filters set for
     * xml files only in the grids folder by default. If a selection is made it will attempt to import the grid.
     */
    private void importGridAction() {
        File file = chooseFileToOpen("Choose XML Grid File", "grids");
        XMLGridImporter importer = new XMLGridImporter();
        if(file != null) {
            Grid importedGrid = importer.importFromFile(file);
            if (importedGrid != null) {
                //TODO:Check for vehicleClass here
                editedGrid.getDrivers().clear();
                editedGrid.getDrivers().addAll(importedGrid.getDrivers());
            }
        }
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
     * Shows the ConfigurationScreen on a separate stage. This action is performed by the configurationMenuItem.
     */
    private void configurationAction() {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().CONFIGURATION_SCREEN_FXML_URL);
        StageController controller = new ConfigurationScreenController();
        Stage screenStage = new Stage();
        controller.setStage(screenStage);
        loader.setController(controller);;
        try {
            Scene scene = new Scene(loader.load());
            screenStage.setScene(scene);
            screenStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
