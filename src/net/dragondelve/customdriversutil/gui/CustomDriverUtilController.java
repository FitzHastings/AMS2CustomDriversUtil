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

import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
import net.dragondelve.customdriversutil.model.xml.XMLOverridesImporter;
import net.dragondelve.customdriversutil.util.*;
import net.dragondelve.mabelfx.FXObjectChooser;
import net.dragondelve.mabelfx.StageController;
import net.dragondelve.mabelfx.util.FXTableRefresher;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Controls the main screen of the CustomDriverUtility.
 * Controls the top menu and what is shown inside the rootPane
 */
public class CustomDriverUtilController implements StageController {
    /**
     * an instance of DriverEditor that controls the driver editor that will edit a driver selected in driverTableView.
     */
    private final DriverEditor driverEditor = new DriverEditor();
    /**
     * Button that performs addDriverAction on action.
     */
    @FXML
    private Button addDriverButton;
    /**
     * Button that performs addLibraryDriverAction on action;
     */
    @FXML
    private Button addLibraryDriverButton;
    /**
     * TableView that displays the Drivers in the currently loaded  DriverLibrary.
     */
    @FXML
    private TableView<Driver> libraryDriverTableView;
    /**
     * Table column in the libraryDriverTableView that displays the nameProperty of the Drivers in the table.
     */
    @FXML
    private TableColumn<Driver, String> libraryDriverNameColumn;
    /**
     * Table column in the libraryDriverTableView that displays the countryProperty  of the Drivers in the table.
     */
    @FXML
    private TableColumn<Driver, String> libraryDriverCountryColumn;
    /**
     * Button that performs removeLibraryDriverAction on action.
     */
    @FXML
    private Button removeLibraryDriverButton;
    /**
     * Button that performs the exportDriverLibraryAction() on action.
     */
    @FXML
    private Button saveDriverLibraryButton;
    /**
     * Opens the NewGridWizard.
     * Performs newGridAction on action.
     */
    @FXML
    private MenuItem newGridItem;
    /**
     * Button that performs removeDriverAction on action.
     */
    @FXML
    private Button removeDriverButton;
    /**
     * Button that performs exportGridCation on action
     */
    @FXML
    private Button saveGridButton;
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
     * Export Vehicle Class Library Menu Item.  Shows a FileChooser and if a selection is made attempts to export the
     * currently loaded vehicle class library to this file.
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
     * Shows a FileChooser and if a selection is made attempts to export a driver library from the file chosen.
     * Performs exportDriverLibraryAction on action.
     */
    @FXML
    private MenuItem exportDriverLibraryItem;
    /**
     * Opens the MassModifyTool Window and allows the user to modify the grid.
     * Performs massModifyAction on action.
     */
    @FXML
    private MenuItem massModifyItem;
    /**
     * Opens the DefineDrivers Step, which then opens the  MassModifyTool Window and allows the user to modify drivers selected..
     * Performs customModifyAction on action.
     */
    @FXML
    private MenuItem customModifyItem;
    /**
     * Shows a FileChooser and if a selection is made attempts to import a driver library form the file chosen.
     * Performs importDriverLibraryAction on action.
     */
    @FXML
    private MenuItem importDriverLibraryItem;
    /**
     * Tableview That displays the drivers from the grid that is being edited.
     */
    @FXML
    private TableView<Driver> driversTableView;
    /**
     * TableView that displays the trackOverrides for the driver selected in the driversTableView.
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
     * TableColumn in driver driversTableView that displays the three-letter code for the driver's country.
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
     * Menu Item that performs fromLiveryOverrideAction on action.
     */
    @FXML
    private MenuItem fromLiveryOverrideItem;
    /**
     * Menu Item that performs switchVehicleClassOnAction on action.
     */
    @FXML
    private MenuItem switchVehicleClassItem;
    /**
     * Stage on which this controller is displayed.
     * This is also the primaryStage in the Application's main method.
     */
    private Stage stage = new Stage();
    /**
     * Grid that is being edited by the editor.
     */
    private Grid editedGrid = new Grid();

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

        driverEditor.setDriverTableRefresher(new FXTableRefresher<>(driversTableView));
        driverEditor.setLiveryValidator(new LiveryValidator());

        newGridItem.setOnAction(e -> newGridAction());
        exportGridItem.setOnAction(e -> exportGridAction());
        importGridItem.setOnAction(e -> importGridAction());
        importGridWithClassItem.setOnAction(e -> importGridWithClassAction());

        editTracksItem.setOnAction(e -> editTracksAction());
        exportTracksItem.setOnAction(e -> exportTracksAction());
        importTracksItem.setOnAction(e -> importTracksAction());

        editVehicleClassesItem.setOnAction(e -> editVehicleClassesAction());
        exportVehicleClassesItem.setOnAction(e -> exportVehicleClassesAction());
        importVehicleClassesItem.setOnAction(e -> importVehicleClassesAction());
        fromLiveryOverrideItem.setOnAction(e -> fromLiveryOverrideAction());

        exportDriverLibraryItem.setOnAction(e -> exportDriverLibraryAction());
        importDriverLibraryItem.setOnAction(e -> importDriverLibraryAction());

        massModifyItem.setOnAction(e -> massModifyAction());
        customModifyItem.setOnAction(e -> customModifyAction());
        switchVehicleClassItem.setOnAction(e -> switchVehicleClassAction());

        configurationMenuItem.setOnAction(e -> configurationAction());

        driversTableView.setEditable(true);
        driversTableView.setItems(editedGrid.getDrivers());
        driverNameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        driverNameColumn.setCellFactory(col -> new TextFieldTableCell<Driver, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setText(item);
                    if (hasValidLivery((Driver) this.getTableRow().getItem())) {
                        this.getStylesheets().clear();
                    } else {
                        this.getStylesheets().clear();
                        this.getStylesheets().add(DDUtil.WARNING_CSS_RESOURCE);
                    }
                }
            }
        });

        driversTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> driversTableView.refresh());

        driverCountryColumn.setCellValueFactory(e -> e.getValue().countryProperty());
        driverCountryColumn.setCellFactory(col -> new TextFieldTableCell<Driver, String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    this.setText(item);
                    if (hasValidLivery((Driver) this.getTableRow().getItem())) {
                        this.getStylesheets().clear();
                    } else {
                        this.getStylesheets().clear();
                        this.getStylesheets().add(DDUtil.WARNING_CSS_RESOURCE);
                    }
                }
            }
        });

        driversTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                driverEditor.setEditedDriver(newValue);
                trackOverrideTableView.setItems(newValue.getTrackOverrides());
                addLibraryDriverButton.setDisable(false);
                addTrackOverrideButton.setDisable(false);
                removeDriverButton.setDisable(false);
            } else {
                driverEditor.setEditedDriver(null);
                addLibraryDriverButton.setDisable(true);
                addTrackOverrideButton.setDisable(true);
                editTrackOverrideButton.setDisable(true);
                removeTrackOverrideButton.setDisable(true);
                removeDriverButton.setDisable(true);
            }
        });

        trackOverrideTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                editTrackOverrideButton.setDisable(false);
                removeTrackOverrideButton.setDisable(false);
            } else {
                editTrackOverrideButton.setDisable(true);
                removeTrackOverrideButton.setDisable(true);
            }
        });

        libraryDriverTableView.setItems(LibraryManager.getInstance().getDriverLibrary().getDrivers());
        libraryDriverNameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        libraryDriverCountryColumn.setCellValueFactory(e -> e.getValue().countryProperty());
        libraryDriverTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> removeLibraryDriverButton.setDisable(newValue == null));

        trackNameColumn.setCellValueFactory(e -> e.getValue().getTrack().get(0).nameProperty());

        removeDriverButton.setDisable(true);
        addLibraryDriverButton.setDisable(true);
        removeLibraryDriverButton.setDisable(true);
        addTrackOverrideButton.setDisable(true);
        editTrackOverrideButton.setDisable(true);
        removeTrackOverrideButton.setDisable(true);

        addDriverButton.setOnAction(e -> addDriverAction());
        removeDriverButton.setOnAction(e -> removeDriverAction());
        saveGridButton.setOnAction(e -> exportGridAction());
        addTrackOverrideButton.setOnAction(e -> addTrackOverrideAction());
        removeTrackOverrideButton.setOnAction(e -> removeTrackOverrideAction());
        editTrackOverrideButton.setOnAction(e -> editTrackOverrideAction());
        addLibraryDriverButton.setOnAction(e -> addLibraryDriverAction());
        removeLibraryDriverButton.setOnAction(e -> removeLibraryDriverAction());
        saveDriverLibraryButton.setOnAction(e -> exportDriverLibraryAction());

        //Handle Drag and drop from libraryDriverTableView to driversTableView
        libraryDriverTableView.setOnDragDetected(event -> {
            if (libraryDriverTableView.getSelectionModel().getSelectedItem() != null) {
                Dragboard db = libraryDriverTableView.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putString(libraryDriverTableView.getSelectionModel().getSelectedItem().getName());
                db.setContent(content);
            }
            event.consume();
        });

        driversTableView.setOnMouseDragged(event -> event.setDragDetect(true));
        driversTableView.setOnDragOver((DragEvent event) -> {
            if (event.getGestureSource() != driversTableView && event.getDragboard().hasString())
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            event.consume();
        });

        driversTableView.setOnDragDropped((DragEvent event) -> {
            Driver selectedDriver = libraryDriverTableView.getSelectionModel().getSelectedItem();
            if (libraryDriverTableView.getSelectionModel().getSelectedItem() != null)
                driversTableView.getItems().add(selectedDriver);
            event.consume();
        });

        if (!editedGrid.getVehicleClass().getLiveryNames().isEmpty())
            driverEditor.setVehicleClass(editedGrid.getVehicleClass());

        initTooltips();
    }

    /**
     * Lightweight mutator method.
     * Should be called before this class is displayed to the user.
     *
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Lightweight mutator method.
     * Should be called before the initialize() method is called.
     *
     * @param grid Grid to be edited in the Custom Driver Utility.
     */
    public void setEditedGrid(Grid grid) {
        this.editedGrid = grid;
    }

    /**
     * Adds a new driver to the editedGrid.
     */
    private void addDriverAction() {
        Driver driver = new Driver();
        driver.nameProperty().set("New Driver");
        driver.countryProperty().set("GBR");
        driver.setOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultDriverFlags());
        editedGrid.getDrivers().add(driver);
    }

    /**
     * If a driver is selected in the driversTableView, removes the selected driver form the editedGrid.
     */
    private void removeDriverAction() {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        if (editedDriver != null)
            driversTableView.getItems().remove(editedDriver);
    }

    /**
     * Adds the driver currently selected in driversTableView to the Driver Library
     * Performed by addLibraryDriverButton on action.
     */
    private void addLibraryDriverAction() {
        LibraryManager.getInstance().getDriverLibrary().getDrivers().add(driversTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Removes the driver currently selected in the libraryTableView
     */
    public void removeLibraryDriverAction() {
        LibraryManager.getInstance().getDriverLibrary().getDrivers().remove(libraryDriverTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Opens the DefineTrack step window. If any tracks are chosen by the user it adds a new TrackOverride for the tracks
     * selected..
     */
    private void addTrackOverrideAction() {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        TrackOverride override = new TrackOverride();
        override.setOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultTrackOverrideFlags());
        initTrackOverrideEditor(override);
        if (!override.getTrack().isEmpty())
            editedDriver.getTrackOverrides().add(override);
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
     * if a track override is chosen in the trackOverrideTableView removes the selected track.
     */
    private void removeTrackOverrideAction() {
        TrackOverride selectedTrackOverride = trackOverrideTableView.getSelectionModel().getSelectedItem();
        if (selectedTrackOverride != null)
            trackOverrideTableView.getItems().remove(selectedTrackOverride);
    }

    /**
     * Initializes the Track Override Editor by opening the defineTrackStep and passing it the trackOverride that
     * is passed as an argument.
     *
     * @param override Track specific override that is going to be edited by the trackOverrideEditor.
     */
    private void initTrackOverrideEditor(TrackOverride override) {
        Driver editedDriver = driversTableView.getSelectionModel().getSelectedItem();
        if (editedDriver == null)
            return;
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DEFINE_TRACKS_STEP_FXML_URL);
        DefineTracksStep stageController = new DefineTracksStep();
        stageController.setTrackOverride(override);
        loader.setController(stageController);
        Stage stage = new Stage();
        stage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);

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
     * Loads the New Grid Wizard FXML and shows the New Grid wizard in this window by creating a new scene and setting
     * this StageController's stage scene to the new scene that contains the new grid wizard.
     * Performed by NewGridItem.
     */
    private void newGridAction() {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().NEW_GRID_WIZARD_FXML_URL);
        NewGridWizardController controller = new NewGridWizardController();
        controller.setPreviousScene(stage.getScene());
        controller.setStage(stage);
        loader.setController(controller);

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
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
        FileChooser fileChooser = LibraryManager.createLibraryFileChooser("Choose XML Grid File", "grids");
        if (editedGrid.getVehicleClass().getXmlName() != null)
            fileChooser.setInitialFileName(editedGrid.getVehicleClass().getXmlName() + ".xml");
        File file = fileChooser.showSaveDialog(stage);
        GridExporter exporter = new XMLGridExporter();
        if (file != null)
            exporter.exportToFile(editedGrid, file);

    }

    /**
     * Action that is performed by importGridITemWithClassItem. Displays the file chooser with extension filters set for
     * xml files only in the grids folder by default. If a selection is made it will attempt to import the grid.
     */
    private void importGridWithClassAction() {
        File file = chooseFileToOpen("Choose XML Grid File", "grids");
        VehicleClass vehicleClass = null;
        if (file != null)
            vehicleClass = XMLGridImporter.importVehicleClassFromXMLGrid(file);
        if (vehicleClass != null) {
            LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(vehicleClass);
            driverEditor.setVehicleClass(vehicleClass);
        }
        GridImporter importer = new XMLGridImporter();
        Grid importedGrid = importer.importFromFile(file);
        if (importedGrid != null) {
            editedGrid.getDrivers().clear();
            editedGrid.getDrivers().addAll(importedGrid.getDrivers());
            editedGrid.setVehicleClass(vehicleClass);
        }
    }

    /**
     * Action that is performed by ImportGridItem. Displays the file chooser with extension filters set for
     * xml files only in the grids folder by default. If a selection is made it will attempt to import the grid.
     */
    private void importGridAction() {
        File file = chooseFileToOpen("Choose XML Grid File", "grids");
        XMLGridImporter importer = new XMLGridImporter();
        if (file != null) {
            Grid importedGrid = importer.importFromFile(file);
            if (importedGrid != null) {
                VehicleClass vehicleClass = LibraryManager.getInstance().getVehicleClassLibrary().findVanillaVehicleClass(file.getName().substring(0, file.getName().length() - 4));
                if (vehicleClass != null) {
                    driverEditor.setVehicleClass(vehicleClass);
                    editedGrid.setVehicleClass(vehicleClass);
                } else
                    driverEditor.setVehicleClass(new VehicleClass());
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
        if (selectedFile != null)
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
        if (selectedFile != null && LibraryManager.getInstance().importTrackLibrary(selectedFile.getPath())) {
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
        File selectedFile = chooseFileToSave("Export Vehicle Class Library", "library/vehicles");
        if (selectedFile != null) {
            LibraryManager.getInstance().exportVehicleClassLibrary(selectedFile.getPath());
        }
    }

    /**
     * Action that is performed by fromLiveryOverrideItem.
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to import a VehicleClass from
     * the chosen file.
     */
    private void fromLiveryOverrideAction() {
        File selectedFile = chooseFileToOpen("Import Livery Overrides", "/");
        if (selectedFile != null) {
            VehicleClass vehicleClass = new XMLOverridesImporter().importFromFile(selectedFile);
            if (vehicleClass == null)
                return;
            openEditor(DDUtil.getInstance().LIVERY_IMPORT_CONFIRM_FXML_URL, new LiveryImportConfirmController(vehicleClass), "Confirm Vehicle Class");
        }
    }

    /**
     * Creates a new stage on which the MassModifyTool scene is going to be displayed.
     * performed by massModifyItem on action.
     */
    private void massModifyAction() {
        Stage stage = new Stage();
        stage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
        MassModifyToolController controller = new MassModifyToolController(editedGrid.getDrivers());
        try {
            FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MASS_MODIFY_TOOL_FXML_URL);
            loader.setController(controller);
            controller.setStage(stage);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initOwner(this.stage);
            stage.setTitle("Mass Modify Tool");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Action that is performed by the customModifyItem
     */
    private void customModifyAction() {
        Stage stage = new Stage();
        stage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
        DefineDriversStep controller = new DefineDriversStep(editedGrid.getDrivers());
        try {
            FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().DEFINE_TRACKS_STEP_FXML_URL);
            loader.setController(controller);
            controller.setStage(stage);
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initOwner(this.stage);
            stage.setTitle("Select Drivers");
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     */
    private void switchVehicleClassAction() {
        FXObjectChooser<VehicleClass> vehicleClassFXObjectChooser = new FXObjectChooser<>();
        TableColumn<VehicleClass, String> nameColumn = new TableColumn<>();
        nameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        vehicleClassFXObjectChooser.getTableView().getColumns().add(nameColumn);
        vehicleClassFXObjectChooser.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
        VehicleClass newVehicleClass = vehicleClassFXObjectChooser.showChooseDialog();

        if (newVehicleClass != null) {
            editedGrid.setVehicleClass(newVehicleClass);
            driverEditor.setVehicleClass(newVehicleClass);

            for (Driver driver : editedGrid.getDrivers())
                driver.liveryNameProperty().set(null);
        }
    }

    /**
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to import a VehicleClassLibrary from
     * the chosen file.
     */
    private void importVehicleClassesAction() {
        File selectedFile = chooseFileToOpen("Import Vehicle Class Library", "library/vehicles");
        if (selectedFile != null && LibraryManager.getInstance().importVehicleClassLibrary(selectedFile.getPath())) {
            PathRelativisor relativisor = new PathRelativisor(selectedFile.toPath());
            Configurator.getInstance().getConfiguration().setVehicleClassLibraryPathname(relativisor.relativize());
            Configurator.getInstance().saveConfiguration();
        }
    }

    /**
     * Action that is performed by exportDriverLibraryItem.
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to export the currently loaded
     * DriverLibrary to the chosen file.
     */
    private void exportDriverLibraryAction() {
        File selectedFile = chooseFileToSave("Export Driver Library", "library/drivers");
        if (selectedFile != null && LibraryManager.getInstance().exportDriverLibrary(selectedFile.getPath())) {
            PathRelativisor relativisor = new PathRelativisor(selectedFile.toPath());
            Configurator.getInstance().getConfiguration().setDriverLibraryPathname(relativisor.relativize());
            Configurator.getInstance().saveConfiguration();
        }
    }

    /**
     * Opens a FileChooser with the *.xml extension filter, displays the FileChooser to the user and allows them to make
     * a selection. When the FileChooser is closed if a file was selected it attempts to import a DriverLibrary from
     * the chosen file.
     */
    private void importDriverLibraryAction() {
        File selectedFile = chooseFileToSave("Import Driver Library", "library/drivers");
        if (selectedFile != null && LibraryManager.getInstance().importDriverLibrary(selectedFile.getPath())) {
            PathRelativisor relativisor = new PathRelativisor(selectedFile.toPath());
            Configurator.getInstance().getConfiguration().setDriverLibraryPathname(relativisor.relativize());
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
        screenStage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
        screenStage.setTitle("Configuration");
        controller.setStage(screenStage);
        loader.setController(controller);
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
     *
     * @param editorFXMLURL URL to a FXML file that contains the editor's gui information.
     * @param controller    Controller to be used for the new Stage.
     * @param title         Text title to be displayed on the new Stage.
     */
    private void openEditor(URL editorFXMLURL, StageController controller, String title) {
        Stage stage = new Stage();
        stage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
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
     * Creates a new FileChooser, sets its extension filter to *.xml and sets its initial directory to the pathname provided.
     * and the title of its Stage to the title provided. Displays the FileChooser to the user with showOpenDialog, waits
     * for a selection to be made and returns the File selected.
     *
     * @param title            Title of the Stage on which the FileChooser is going to be displayed.
     * @param initialDirectory Pathname to an initial directory for the FileChooser.
     * @return File Chosen by the user. returns null if no file was chosen.
     */
    private File chooseFileToOpen(String title, String initialDirectory) {
        return LibraryManager.createLibraryFileChooser(title, initialDirectory).showOpenDialog(stage);
    }

    /**
     * Filters the editedFrid to drivers whose livery matches driver's livery name, and checks if it is unique;
     *
     * @param driver Driver whose livery is to be checked for uniqueness
     * @return true if driver has a unique livery name, false if the driver does not
     */
    private boolean hasValidLivery(Driver driver) {
        if (driver == null)
            return true;
        if (driver.getLiveryName() == null || driver.getLiveryName().equals(""))
            return false;
        FilteredList<Driver> drivers = editedGrid.getDrivers().filtered(d -> driver.getLiveryName().equals(d.getLiveryName()));
        return drivers.size() == 1;
    }

    /**
     * Creates a new FileChooser, sets its extension filter to *.xml and sets its initial directory to the pathname provided.
     * and the title of its Stage to the title provided. Displays the FileChooser to the user with showSaveDialog, waits
     * for a selection to be made and returns the File selected.
     *
     * @param title            Title of the Stage on which the FileChooser is going to be displayed.
     * @param initialDirectory Pathname to an initial directory for the FileChooser.
     * @return File Chosen by the user. returns null if no file was chosen.
     */
    private File chooseFileToSave(String title, String initialDirectory) {
        return LibraryManager.createLibraryFileChooser(title, initialDirectory).showSaveDialog(stage);
    }

    /**
     * Initializes all tooltips for the control elements
     */
    private void initTooltips() {
        addDriverButton.setTooltip(TooltipUtil.ADD_DRIVER_TOOLTIP);
        removeDriverButton.setTooltip(TooltipUtil.REMOVE_DRIVER_TOOLTIP);
        saveGridButton.setTooltip(TooltipUtil.SAVE_GRID_TOOLTIP);

        addTrackOverrideButton.setTooltip(TooltipUtil.ADD_TRACK_OVERRIDE_TOOLTIP);
        removeTrackOverrideButton.setTooltip(TooltipUtil.REMOVE_TRACK_OVERRIDE_TOOLTIP);
        editTrackOverrideButton.setTooltip(TooltipUtil.EDIT_TRACK_OVERRIDE_TOOLTIP);

        addLibraryDriverButton.setTooltip(TooltipUtil.ADD_LIBRARY_DRIVER_TOOLTIP);
        removeLibraryDriverButton.setTooltip(TooltipUtil.REMOVE_LIBRARY_DRIVER_TOOLTIP);
        saveDriverLibraryButton.setTooltip(TooltipUtil.SAVE_DRIVER_LIBRARY_TOOLTIP);
    }

    public class LiveryValidator {
        public boolean validate(String liveryName) {
            if (liveryName == null)
                return false;
            FilteredList<Driver> filteredList = editedGrid.getDrivers().filtered(driver -> liveryName.equals(driver.getLiveryName()));
            return filteredList == null || filteredList.size() == 0;
        }
    }
}