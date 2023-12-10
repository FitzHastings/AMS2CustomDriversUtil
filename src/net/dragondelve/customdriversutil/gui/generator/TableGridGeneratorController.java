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
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.tools.generator.GeneratorSettings;
import net.dragondelve.customdriversutil.tools.generator.GridGenerator;
import net.dragondelve.customdriversutil.tools.generator.TableGenerator;
import net.dragondelve.customdriversutil.tools.generator.ValueGenerator;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.customdriversutil.util.TooltipUtil;

/**
 * Controller for fxml/TableGridGenerator.fxml
 */
public class TableGridGeneratorController implements GeneratorController {
    private final Grid pregeneratedGrid = new Grid();
    private final GeneratorSettings settings = new GeneratorSettings();
    private final Button generateButton;
    @FXML
    private Slider minRaceSkillSlider;
    @FXML
    private TextField minRaceSkillTextField;
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

    /**
     * Constructor for TableGridGeneratorController. Creates a new instance of TableGridGeneratorController.
     *
     * @param generateButton Button that is used to actuate generation of the grid..
     */
    public TableGridGeneratorController(Button generateButton) {
        this.generateButton = generateButton;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
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

        qualiExceedsRaceCheckbox.disableProperty().bind(bindQualiCheckBox.selectedProperty().not());
        exceedsByTextField.disableProperty().bind(qualiExceedsRaceCheckbox.selectedProperty().not());
        limitToTextFIeld.disableProperty().bind(limitAgressionCheckBox.selectedProperty().not());

        limitToTextFIeld.textProperty().bindBidirectional(settings.aggressionLimitProperty(), new NumberStringConverter());
        limitAgressionCheckBox.selectedProperty().bindBidirectional(settings.limitAggressionProperty());
        bindQualiCheckBox.selectedProperty().bindBidirectional(settings.bindQualiAndRaceSkillsProperty());
        bindQualiCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.FALSE.equals(newValue)) qualiExceedsRaceCheckbox.selectedProperty().set(false);
        });
        exceedsByTextField.textProperty().bindBidirectional(settings.boundSkillsGapProperty(), new NumberStringConverter());

        noizeTextField.textProperty().bind(noizeSlider.valueProperty().asString("%.2f"));
        minRaceSkillTextField.textProperty().bind(minRaceSkillSlider.valueProperty().asString("%.2f"));
        minRaceSkillTextField.setEditable(false);
        noizeTextField.setEditable(false);

        vehicleClassChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
        vehicleClassChoiceBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    boolean newValueIsNull = newValue == null;
                    if (!newValueIsNull)
                        pregeneratedGrid.setVehicleClass(newValue);
                    mainHBox.setDisable(newValueIsNull);
                    generateButton.setDisable(newValueIsNull);
                }
        );
        settings.aggressionLimitProperty().set(0.9);
        settings.boundSkillsGapProperty().set(0.1);
        minRaceSkillSlider.setValue(0.4);
        noizeSlider.setValue(0.2);
        limitAgressionCheckBox.selectedProperty().set(true);
        bindQualiCheckBox.selectedProperty().set(true);
        qualiExceedsRaceCheckbox.selectedProperty().set(true);

        mainHBox.setDisable(true);

        initTooltips();
    }

    /**
     * Creates a new instance of GridGenerator with the settings.
     *
     * @return GridGenerator that is going to be used to generate a new grid.
     */
    @Override
    public GridGenerator createGridGenerator() {
        settings.setVehicleClass(vehicleClassChoiceBox.valueProperty().get());
        settings.nDriversProperty().set(driversTable.getItems().size());
        settings.useNAMeSProperty().set(false);
        settings.fromLiveryNamesProperty().set(false);

        if (!qualiExceedsRaceCheckbox.isSelected())
            settings.boundSkillsGapProperty().set(0.0);

        if (settings.getnDrivers() > settings.getVehicleClass().getLiveryNames().size())
            settings.nDriversProperty().set(settings.getVehicleClass().getLiveryNames().size());

        ValueGenerator generator = new TableGenerator(pregeneratedGrid, noizeSlider.getValue());
        generator.setLimits(minRaceSkillSlider.getValue(), 1.0);
        return new GridGenerator(settings, generator);
    }

    /**
     * Checks if the current settings can be used to generate a valid grid.
     *
     * @return true if the settings are good to generate a new grid, false otherwise.
     */
    @Override
    public boolean isGoodToGenerate() {
        return vehicleClassChoiceBox.getSelectionModel().getSelectedItem() != null && !pregeneratedGrid.getDrivers().isEmpty();
    }

    /**
     * Action that is performed by the addButton. Adds a new driver to the grid.
     */
    private void addDriverAction() {
        Driver driver = new Driver();
        driver.nameProperty().set("New Driver");
        driver.liveryNameProperty().set("Not Assigned");
        driver.pointsProperty().set(0);
        pregeneratedGrid.getDrivers().add(driver);
        addButton.setDisable(pregeneratedGrid.getDrivers().size() >= vehicleClassChoiceBox.getSelectionModel().getSelectedItem().getLiveryNames().size());
    }

    /**
     * Action that is performed by the removeButton. Removes the selected driver from the grid.
     */
    private void removeDriverAction() {
        if (driversTable.getSelectionModel().getSelectedItem() != null)
            pregeneratedGrid.getDrivers().remove(driversTable.getSelectionModel().getSelectedItem());
        addButton.setDisable(pregeneratedGrid.getDrivers().size() >= vehicleClassChoiceBox.getSelectionModel().getSelectedItem().getLiveryNames().size());
    }

    /**
     * Initializes the tooltips for the controls.
     */
    private void initTooltips() {
        vehicleClassChoiceBox.setTooltip(TooltipUtil.CHOOSE_VEHICLE_CLASS_TOOLTIP);
        noizeSlider.setTooltip(TooltipUtil.NOISE_TOOLTIP);
        noizeTextField.setTooltip(TooltipUtil.NOISE_TOOLTIP);

        minRaceSkillSlider.setTooltip(TooltipUtil.MIN_GENERATED_VALUE_TOOLTIP);
        minRaceSkillSlider.setTooltip(TooltipUtil.MAX_GENERATED_VALUE_TOOLTIP);
        limitAgressionCheckBox.setTooltip(TooltipUtil.LIMIT_AGGRESSION_TOOLTIP);
        limitToTextFIeld.setTooltip(TooltipUtil.LIMIT_AGGRESSION_TO_TOOLTIP);

        bindQualiCheckBox.setTooltip(TooltipUtil.BIND_QUALI_SKILL_TOOLTIP);
        qualiExceedsRaceCheckbox.setTooltip(TooltipUtil.QUALI_SKILL_EXCEEDS_TOOLTIP);
        exceedsByTextField.setTooltip(TooltipUtil.EXCEEDS_BY_AMOUNT_TOOLTIP);

        addButton.setTooltip(TooltipUtil.ADD_DRIVER_TOOLTIP);
        removeButton.setTooltip(TooltipUtil.REMOVE_DRIVER_TOOLTIP);
    }
}
