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

package net.dragondelve.customdriversutil.gui.generator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.LibraryManager;

public class TableGridGeneratorController {
    @FXML
    private Slider maxRaceSkillSlider;

    @FXML
    private TextField maxRaceSkillTextField;

    @FXML
    private CheckBox limitAgressionCheckBox;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;

    @FXML
    private TextField limitToTextFIeld;

    @FXML
    private Slider noizeSlider;

    @FXML
    private CheckBox qualiExceedsRaceCheckbox;

    @FXML
    private TableView<Driver> driversTable;

    @FXML
    private TextField exceedsByTextField;

    @FXML
    private TableColumn<Driver, String> nameColumn;

    @FXML
    private CheckBox bindQualiCheckBox;

    @FXML
    private HBox mainHBox;

    @FXML
    private TableColumn<Driver, Integer> pointsColumn;

    @FXML
    private Button removeButton;

    @FXML
    private TextField noizeTextField;

    private Grid pregeneratedGrid = new Grid();

    @FXML
    public void initialize() {

        removeButton.setDisable(true);
        driversTable.setItems(pregeneratedGrid.getDrivers());
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pointsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pointsColumn.setCellValueFactory(cellData -> cellData.getValue().pointsProperty().asObject());

        addButton.setOnAction(e -> addDriverAction());

        driversTable.setEditable(true);
        nameColumn.setEditable(true);
        pointsColumn.setEditable(true);
        removeButton.setOnAction(e -> removeDriverAction());

        driversTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> removeButton.setDisable(newValue == null)
        );

        vehicleClassChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
        vehicleClassChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        pregeneratedGrid.setVehicleClass(newValue);
                        mainHBox.setDisable(false);
                    } else {
                        mainHBox.setDisable(true);
                    }
                }
        );

        mainHBox.setDisable(true);
    }

    private void addDriverAction() {
        Driver driver = new Driver();
        driver.nameProperty().set("");
        driver.liveryNameProperty().set("Not Assigned");
        driver.pointsProperty().set(0);
        pregeneratedGrid.getDrivers().add(driver);
    }

    private void removeDriverAction() {
        if (driversTable.getSelectionModel().getSelectedItem() != null)
            pregeneratedGrid.getDrivers().remove(driversTable.getSelectionModel().getSelectedItem());
    }
}
