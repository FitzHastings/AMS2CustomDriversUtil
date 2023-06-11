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
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.xml.XMLGridImporter;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.GridImporter;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Controls the Welcome Screen where a user has a limited selection of features where he can choose how he wants to get
 * started on working with the Custom Driver Utility.
 * Controller for fxml/CustomGridWelcome.fxml
 */
public class CustomGridWelcomeController implements StageController {
    /**
     * Button that performs loadExistingVanillaGridAction on action.
     * Shows a file chooser and imports the grid normally, if successful, it shows the main window.
     */
    @FXML
    private Button loadExistingVanillaGrid;

    /**
     * Button that performs newGeneratedGridAction on action.
     * Shows the NewGridWizard to the user.
     */
    @FXML
    private Button newGeneratedGridButton;

    /**
     * Button that performs the newEmptyGridAction on action.
     * Shows the user the main window with the empty grid, same as if he just skipped this screen.
     */
    @FXML
    private Button newEmptyGridButton;

    /**
     * Button that opens a URL for manual update in the user's default internet browser.
     */
    @FXML
    private Button manualUpdateButton;

    /**
     * Button that performs the loadModdedGridAction on action.
     * Shows a file chooser and if the file is chosen loads it as a grid and also loads its vehicleClass.
     */
    @FXML
    private Button loadModdedGridButton;

    /**
     * The root pane of this window, used to apply the CSS resource to this entire screen.
     */
    @FXML
    private VBox rootPane;

    /**
     * Checkbox that determines if the welcome screen is going to be skipped next time this program launches.
     */
    @FXML
    private CheckBox skipWelcomeScreenCheckBox;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    Stage stage;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        newEmptyGridButton.setOnAction(e -> newEmptyGridAction());
        newGeneratedGridButton.setOnAction(e -> newGeneratedGridAction());
        loadExistingVanillaGrid.setOnAction(e -> loadExistingVanillaGridAction());
        loadModdedGridButton.setOnAction(e -> loadModdedGridAction());
        manualUpdateButton.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL(Configurator.getInstance().getConfiguration().getUpdateURL()).toURI());
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Action that is performed by the newEmptyGridButton.
     * Shows the main window to the user in the same stage as the welcome screen.
     */
    private void newEmptyGridAction() {
        nextScene(new CustomDriverUtilController(), DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
    }

    /**
     * Action that is performed by the newGeneratedGridButton.
     * Shows the new grid wizard to the user in the same stage as the welcome screen.
     */
    private void newGeneratedGridAction() {
        nextScene(new NewGridWizardController(), DDUtil.getInstance().NEW_GRID_WIZARD_FXML_URL);
    }

    /**
     * Action that is performed by the loadExistingVanillaGridButton.
     * Opens a file chooser. IF a selection is made attempts to load the file chosen as a grid. If it's successful
     * shows the user the main window in the same stage as the welcome screen with the grid already loaded.
     */
    private void loadExistingVanillaGridAction() {
        CustomDriverUtilController controller = new CustomDriverUtilController();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Vanilla Grid");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml library file", "*.xml"));
        fileChooser.setInitialDirectory(new File("grids"));
        File file = fileChooser.showOpenDialog(stage);

        XMLGridImporter importer = new XMLGridImporter();
        if (file != null) {
            Grid importedGrid = importer.importFromFile(file);
            if (importedGrid != null) {
                VehicleClass vehicleClass = LibraryManager.getInstance().getVehicleClassLibrary().findVanillaVehicleClass(file.getName().substring(0, file.getName().length()-4));
                if (vehicleClass != null) {
                    importedGrid.setVehicleClass(vehicleClass);
                } else
                    importedGrid.setVehicleClass(new VehicleClass());
                controller.setEditedGrid(importedGrid);
                nextScene(controller, DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
            }
        }
    }

    /**
     * Action that is performed by the loadModdedGridButton.
     * Opens a file chooser. IF a selection is made attempts to load the file chosen as a grid. If it's successful
     * shows the user the main window in the same stage as the welcome screen with the grid already loaded.
     * Also creates a new vehicleClass and assigns this vehicleClass to this grid, and adds it to the VehicleClassLibrary.
     */
    private void loadModdedGridAction() {
        CustomDriverUtilController controller = new CustomDriverUtilController();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Modded Grid");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml library file", "*.xml"));
        fileChooser.setInitialDirectory(new File("grids"));
        File file = fileChooser.showOpenDialog(stage);
        if (file == null)
            return;
        VehicleClass vehicleClass = XMLGridImporter.importVehicleClassFromXMLGrid(file);
        if (vehicleClass == null)
            return;

        LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(vehicleClass);
        GridImporter importer = new XMLGridImporter();
        Grid importedGrid = importer.importFromFile(file);
        if (importedGrid == null)
            return;

        importedGrid.setVehicleClass(vehicleClass);
        controller.setEditedGrid(importedGrid);
        nextScene(controller, DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
    }

    /**
     * Shows the next scene in the same stage as this window.
     * @param controller StageController that is going to be controlling this stage after the fxml resource was loaded.
     * @param fxmlURL URL to an fxml resource to be loaded.
     */
    private void nextScene(StageController controller, URL fxmlURL) {
        FXMLLoader loader = new FXMLLoader(fxmlURL);
        controller.setStage(stage);
        loader.setController(controller);

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (skipWelcomeScreenCheckBox.isSelected()) {
            Configurator.getInstance().getConfiguration().setSkipWelcomeScreen(true);
            Configurator.getInstance().saveConfiguration();
        }
    }
}
