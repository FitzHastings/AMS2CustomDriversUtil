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

package net.dragondelve.customdriversutil.gui.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.DDUtil;

public class VehicleClassLibraryEditor implements Editor<VehicleClass> {
    @FXML
    private Button addLiveryNameButton;

    @FXML
    private Button addVehicleClassButton;

    @FXML
    private ListView<String> liveryNameListView;

    @FXML
    private Button removeLiveryNameButton;

    @FXML
    private Button removeVehicleClassButton;

    @FXML
    private SplitPane rootPane;

    @FXML
    private TableColumn<VehicleClass, String> vehicleClassNameTableColumn;

    @FXML
    private TextField vehicleClassNameTextField;

    @FXML
    private TableView<VehicleClass> vehicleClassTableView;

    @FXML
    private TextField vehicleClassXMLNameTextField;

    private boolean controlsAreDisabled = false;

    /**
     * Stage on which this editor is displayed.
     */
    private Stage stage;

    /**
     * An observable list of items that are being edited by this editor.
     */
    private ObservableList<VehicleClass> items = FXCollections.observableArrayList();

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        setDisableControls(true);

        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        vehicleClassTableView.setItems(items);
        vehicleClassTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null)
                unbindVehicleCLass(oldValue);

            if(newValue != null) {
                bindVehicleClass(newValue);
                if(controlsAreDisabled)
                    setDisableControls(false);
            } else if (!controlsAreDisabled)
                setDisableControls(true);
        });

        vehicleClassNameTableColumn.setCellValueFactory(e->e.getValue().nameProperty());

        addVehicleClassButton.setOnAction(e->addVehicleClassAction());
        removeVehicleClassButton.setOnAction(e->removeVehicleClassAction());

        liveryNameListView.setEditable(true);
        liveryNameListView.setCellFactory(TextFieldListCell.forListView());

        addLiveryNameButton.setOnAction(e->addLiveryNameAction());
        removeLiveryNameButton.setOnAction(e->setRemoveLiveryNameAction());

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
     * Lightweight accessor method.
     * @return Observable list of Vehicle Classes that are edited by this editor.
     */
    @Override
    public ObservableList<VehicleClass> getItems() {
        return items;
    }

    /**
     * Lightweight mutator method.
     * Should be called before initialize method is called by the JavaFX.
     * @param items Observable list of Vehicle Classes that are edited by this editor.
     */
    @Override
    public void setItems(ObservableList<VehicleClass> items) {
        this.items = items;
    }

    private void addVehicleClassAction() {
       VehicleClass vehicleClass =  new VehicleClass();
       vehicleClass.setName("New Class");
       vehicleClass.setXmlName("New Class");
       items.add(vehicleClass);
    }

    private void removeVehicleClassAction() {
        VehicleClass selectedVehicleClass = vehicleClassTableView.getSelectionModel().getSelectedItem();
        if (selectedVehicleClass != null) {
            if (!vehicleClassTableView.getSelectionModel().isSelected(vehicleClassTableView.getItems().size()))
                vehicleClassTableView.getSelectionModel().selectNext();
            else
                vehicleClassTableView.getSelectionModel().selectPrevious();
            items.remove(selectedVehicleClass);
        }
    }

    private void addLiveryNameAction() {
        liveryNameListView.getItems().add("New Livery");
    }

    private void setRemoveLiveryNameAction() {
        String selectedLiveryName = liveryNameListView.getSelectionModel().getSelectedItem();
        if (selectedLiveryName != null) {
            if(!liveryNameListView.getSelectionModel().isSelected(liveryNameListView.getItems().size()))
                liveryNameListView.getSelectionModel().selectNext();
            else
                liveryNameListView.getSelectionModel().selectPrevious();
            liveryNameListView.getItems().remove(selectedLiveryName);
        }
    }

    /**
     * Method used to disable all editing controls if no selection in the tableView is made.
     * @param disable True if you want to disable all controls, false if you want to enable them.
     */
    private void setDisableControls(boolean disable) {
        vehicleClassNameTextField.setDisable(disable);
        vehicleClassXMLNameTextField.setDisable(disable);
        addLiveryNameButton.setDisable(disable);
        removeLiveryNameButton.setDisable(disable);
        liveryNameListView.setDisable(disable);
        controlsAreDisabled = disable;
    }

    /**
     * Method that binds properties of a given vehicleClass to the controls to allow user to edit them.
     * The bind is bidirectional and should be unbound with unbindVehicleClass when it's no longer needed.
     * @param vehicleClass Vehicle Class whose properties are to be bound to the controls
     */
    private void bindVehicleClass(VehicleClass vehicleClass) {
        vehicleClassNameTextField.textProperty().bindBidirectional(vehicleClass.nameProperty());
        vehicleClassXMLNameTextField.textProperty().bindBidirectional(vehicleClass.xmlNameProperty());

        liveryNameListView.setItems(vehicleClass.getLiveryNames());
    }

    /**
     * Method that unbinds properties of a given vehicleClass from the control.
     * If a vehicleClass is bound with bindVehicleClass it should be unbound with unbindVehicleClass
     * when it's no longer needed.
     * @param vehicleClass Vehicle Class whose properties are to be bound to the controls
     */
    private void unbindVehicleCLass(VehicleClass vehicleClass) {
        vehicleClassNameTextField.textProperty().unbindBidirectional(vehicleClass.nameProperty());
        vehicleClassXMLNameTextField.textProperty().unbindBidirectional(vehicleClass.xmlNameProperty());

        liveryNameListView.setItems(FXCollections.emptyObservableList());
    }
}
