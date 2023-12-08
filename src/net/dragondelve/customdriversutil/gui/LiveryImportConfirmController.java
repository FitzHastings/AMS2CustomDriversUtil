// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License";
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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.xml.XMLOverridesImporter;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.customdriversutil.util.TooltipUtil;
import net.dragondelve.mabelfx.StageController;

import java.io.File;


/**
 * Controller for the LiveryImportConfirm.fxml file.
 * This controller is responsible for displaying the list of liveries that are going to be imported and for allowing the user to confirm the import.
 */
public class LiveryImportConfirmController implements StageController {
    /**
     * ListView for displaying the names of liveries.
     */
    @FXML
    private ListView<String> liveryNameListView;

    /**
     * Represents a cancel button in a JavaFX scene.
     * The cancel button is typically used to cancel or close a form or dialog.
     * This button can be used to trigger an action when clicked.
     */
    @FXML
    private Button cancelButton;

    /**
     * Root Pane of the main screen. Is used to display everything inside the main window.
     * The main css style is applied to the rootPane.
     */
    @FXML
    private VBox rootPane;

    /**
     * A JavaFX TextField for entering a name.
     *
     * The nameTextField is a JavaFX TextField component that allows the user to input a name.
     * It is typically used in forms or dialogs where the user needs to enter a name.
     */
    @FXML
    private TextField nameTextField;

    /**
     * Performs the importAction on action.
     */
    @FXML
    private Button importButton;

    /**
     * Represents a Label that displays the number of liveries.
     */
    @FXML
    private Label nLiveriesLabel;

    /**
     *
     */
    @FXML
    private Button moreButton;

    /**
     *
     */
    @FXML
    private ChoiceBox<VehicleClass> modForChoiceBox;

    /**
     * Represents the stage on which the controller is displayed.
     */
    private Stage stage;

    /**
     * Represents an in-game vehicle class.
     * This class is fully annotated with JAXB for easy XML conversion.
     */
    private VehicleClass vehicleClass;

    /**
     * Creates a new instance of
     * @param vehicleClass Vehicle class
     */
    public LiveryImportConfirmController(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * Initializes the controller.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        liveryNameListView.setItems(vehicleClass.getLiveryNames());
        vehicleClass.nameProperty().bindBidirectional(nameTextField.textProperty());

        importButton.setOnAction(e -> importAction());
        cancelButton.setOnAction(e -> stage.close());
        moreButton.setOnAction(e -> moreAction());
        importButton.disableProperty().bind(vehicleClass.nameProperty().isEmpty());
        modForChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());

        nLiveriesLabel.setText(Integer.toString(vehicleClass.getLiveryNames().size()));

        initTooltips();
    }

    /**
     * Sets the stage on which this controller is going to be displayed.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Imports a vehicle class and exports the vehicle class library.
     * This method adds the specified vehicle class to the vehicle class library and exports the updated library to a specified pathname.
     * This action is performed by importButton
     *
     * @throws NullPointerException if LibraryManager or Configurator instances are not initialized.
     */
    private void importAction() {
        this.vehicleClass.setModded(true);
        if (modForChoiceBox.getSelectionModel().selectedItemProperty() != null)
            this.vehicleClass.xmlNameProperty().set(modForChoiceBox.getSelectionModel().selectedItemProperty().get().getXmlName());

        LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(vehicleClass);
        LibraryManager.getInstance().exportVehicleClassLibrary(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
        this.stage.close();
    }

    /**
     * Initializes tooltips for the elements in the UI.
     * Adds tooltips to the nameTextField, importButton, and cancelButton elements.
     * Tooltips provide additional information when the user hovers over the elements.
     */
    private void initTooltips() {
        nameTextField.setTooltip(TooltipUtil.VEHICLE_CLASS_NAME_TOOLTIP);
        importButton.setTooltip(TooltipUtil.CONFIRM_IMPORT);
        cancelButton.setTooltip(TooltipUtil.CANCEL_IMPORT);
    }

    private void moreAction() {
        File selectedFile = LibraryManager.createLibraryFileChooser("Import Additional Livery Overrides", "/").showOpenDialog(stage);
        if (selectedFile != null) {
            VehicleClass vehicleClass = new XMLOverridesImporter().importFromFile(selectedFile);
            if (vehicleClass == null)
                return;
            this.vehicleClass.getLiveryNames().addAll(vehicleClass.getLiveryNames());
            nLiveriesLabel.setText(Integer.toString(Integer.parseInt(nLiveriesLabel.getText()) + vehicleClass.getLiveryNames().size()));
        }
    }
}
