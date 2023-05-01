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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import net.dragondelve.customdriversutil.model.Driver;

public class DriverEditor {

    @FXML
    private Slider aggressionSlider;

    @FXML
    private TextField aggressionTextField;

    @FXML
    private Slider blueFlagSlider;

    @FXML
    private TextField blueFlagTextField;

    @FXML
    private CheckBox chooseLiveryCheckBox;

    @FXML
    private Slider consistencySlider;

    @FXML
    private TextField consistencyTextField;

    @FXML
    private Slider defendingSlider;

    @FXML
    private TextField defendingTextField;

    @FXML
    private TextField driverCountryTextField;

    @FXML
    private TextField driverLiveryNameTextField;

    @FXML
    private TextField driverNameTextField;

    @FXML
    private TextField mistakeAvoidanceTextField;

    @FXML
    private Slider forcedMistakeSlider;

    @FXML
    private TextField forcedMistakeTextField;

    @FXML
    private Slider fuelManagementSlider;

    @FXML
    private TextField fuelManagementTextField;

    @FXML
    private Slider mistakeAvoidanceSlider;

    @FXML
    private CheckBox overrideAggressionCheckBox;

    @FXML
    private CheckBox overrideBlueFlagCheckBox;

    @FXML
    private CheckBox overrideCountryCheckBox;

    @FXML
    private CheckBox overrideConsistencyCheckBox;

    @FXML
    private CheckBox overrideDefendingCheckBox;

    @FXML
    private CheckBox overrideDriverNameCheckBox;

    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideFuelManagementCheckBox;

    @FXML
    private CheckBox overrideMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideQualiSkillCheckBox;

    @FXML
    private CheckBox overrideRacingSkillCheckBox;

    @FXML
    private CheckBox overrideStaminaCheckBox;

    @FXML
    private CheckBox overrideStartReactions;

    @FXML
    private CheckBox overrideTyreManagementCheckBox;

    @FXML
    private CheckBox overrideVehicleReliabilityCheckBox;
    @FXML
    private CheckBox overrideWeatherPitCheckBox;

    @FXML
    private CheckBox overrideWetSkillCheckbox;

    @FXML
    private Slider qualiSkillSlider;

    @FXML
    private TextField qualiSkillTextField;

    @FXML
    private Slider racingSkillSlider;

    @FXML
    private TextField racingSkillTextField;

    @FXML
    private Button randomizeButton;

    @FXML
    private VBox rootPane;

    @FXML
    private Slider staminaSlider;

    @FXML
    private TextField staminaTextField;

    @FXML
    private Slider startReactionsSlider;

    @FXML
    private TextField startReactionsTextField;

    @FXML
    private Slider tyreManagementSlider;

    @FXML
    private TextField tyreManagementTextField;

    @FXML
    private CheckBox vehicleReliabilityCheckBox;

    @FXML
    private TextField vehicleReliabilityTextField;

    @FXML
    private Slider vehicleReliabilitySlider;

    @FXML
    private Slider weatherPitSlider;

    @FXML
    private TextField weatherPitTextField;

    @FXML
    private Slider wetSkillSlider;

    @FXML
    private TextField wetSkillTextField;

    private Driver editedDriver;

    private boolean overrideMode = false;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        driverNameTextField.disableProperty().bind(overrideDriverNameCheckBox.selectedProperty().not());
        driverCountryTextField.disableProperty().bind(overrideCountryCheckBox.selectedProperty().not());
        racingSkillSlider.disableProperty().bind(overrideRacingSkillCheckBox.selectedProperty().not());
        qualiSkillSlider.disableProperty().bind(overrideQualiSkillCheckBox.selectedProperty().not());
        aggressionSlider.disableProperty().bind(overrideAggressionCheckBox.selectedProperty().not());
        defendingSlider.disableProperty().bind(overrideDefendingCheckBox.selectedProperty().not());
        staminaSlider.disableProperty().bind(overrideStaminaCheckBox.selectedProperty().not());
        consistencySlider.disableProperty().bind(overrideConsistencyCheckBox.selectedProperty().not());
        startReactionsSlider.disableProperty().bind(overrideStartReactions.selectedProperty().not());
        wetSkillSlider.disableProperty().bind(overrideWetSkillCheckbox.selectedProperty().not());
        tyreManagementSlider.disableProperty().bind(overrideTyreManagementCheckBox.selectedProperty().not());
        fuelManagementSlider.disableProperty().bind(overrideFuelManagementCheckBox.selectedProperty().not());
        blueFlagSlider.disableProperty().bind(overrideBlueFlagCheckBox.selectedProperty().not());
        weatherPitSlider.disableProperty().bind(overrideWeatherPitCheckBox.selectedProperty().not());
        mistakeAvoidanceSlider.disableProperty().bind(overrideMistakeAvoidanceCheckBox.selectedProperty().not());
        forcedMistakeSlider.disableProperty().bind(overrideForcedMistakeAvoidanceCheckBox.selectedProperty().not());
        vehicleReliabilitySlider.disableProperty().bind(overrideVehicleReliabilityCheckBox.selectedProperty().not());

        racingSkillTextField.textProperty().bind(racingSkillSlider.valueProperty().asString("%.2f"));
        qualiSkillTextField.textProperty().bind(qualiSkillSlider.valueProperty().asString("%.2f"));
        aggressionTextField.textProperty().bind(aggressionSlider.valueProperty().asString("%.2f"));
        defendingTextField.textProperty().bind(defendingSlider.valueProperty().asString("%.2f"));
        staminaTextField.textProperty().bind(staminaSlider.valueProperty().asString("%.2f"));
        consistencyTextField.textProperty().bind(consistencySlider.valueProperty().asString("%.2f"));
        startReactionsTextField.textProperty().bind(startReactionsSlider.valueProperty().asString("%.2f"));
        wetSkillTextField.textProperty().bind(wetSkillSlider.valueProperty().asString("%.2f"));
        tyreManagementTextField.textProperty().bind(tyreManagementSlider.valueProperty().asString("%.2f"));
        fuelManagementTextField.textProperty().bind(fuelManagementSlider.valueProperty().asString("%.2f"));
        blueFlagTextField.textProperty().bind(blueFlagSlider.valueProperty().asString("%.2f"));
        weatherPitTextField.textProperty().bind(weatherPitSlider.valueProperty().asString("%.2f"));
        mistakeAvoidanceTextField.textProperty().bind(mistakeAvoidanceSlider.valueProperty().asString("%.2f"));
        forcedMistakeTextField.textProperty().bind(forcedMistakeSlider.valueProperty().asString("%.2f"));
        vehicleReliabilityTextField.textProperty().bind(vehicleReliabilitySlider.valueProperty().asString("%.2f"));

    }

    /**
     * NOT a lightweight mutator method, unbinds the previous driver's properties and binds the new driver to the control
     * elements of this editor.
     * @param driver Driver to be edited.
     */
    public void setEditedDriver(Driver driver) {
        if(this.editedDriver != null) {
            unbindDriver(this.editedDriver);
        }
        this.editedDriver = driver;
        bindDriver(editedDriver);
    }

    public void setOverrideMode(boolean overrideMode) {
        this.overrideMode = overrideMode;
    }

    /**
     * Binds the given driver's properties to the control elements that are supposed to edit them. The bind will be done
     * bidirectionally, and you should call unbindDriver on the same driver after it is no longer being edited.
     * @param driver a driver whose properties are to be bind to the control elements of this editor.
     */
    private void bindDriver(Driver driver) {
        driverLiveryNameTextField.textProperty()    .bindBidirectional(driver.liveryNameProperty());
        driverNameTextField.textProperty()          .bindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()       .bindBidirectional(driver.countryProperty());
        racingSkillSlider.valueProperty()           .bindBidirectional(driver.raceSkillProperty());
        qualiSkillSlider.valueProperty()            .bindBidirectional(driver.qualifyingSkillProperty());
        aggressionSlider.valueProperty()            .bindBidirectional(driver.aggressionProperty());
        defendingSlider.valueProperty()             .bindBidirectional(driver.defendingProperty());
        staminaSlider.valueProperty()               .bindBidirectional(driver.staminaProperty());
        consistencySlider.valueProperty()           .bindBidirectional(driver.consistencyProperty());
        startReactionsSlider.valueProperty()        .bindBidirectional(driver.startReactionsProperty());
        wetSkillSlider.valueProperty()              .bindBidirectional(driver.wetSkillProperty());
        tyreManagementSlider.valueProperty()        .bindBidirectional(driver.tyreManagementProperty());
        fuelManagementSlider.valueProperty()        .bindBidirectional(driver.fuelManagementProperty());
        blueFlagSlider.valueProperty()              .bindBidirectional(driver.blueFlagConcedingProperty());
        weatherPitSlider.valueProperty()            .bindBidirectional(driver.weatherTyreChangeProperty());
        mistakeAvoidanceSlider.valueProperty()      .bindBidirectional(driver.avoidanceOfMistakesProperty());
        forcedMistakeSlider.valueProperty()         .bindBidirectional(driver.avoidanceOfForcedMistakesProperty());
        vehicleReliabilitySlider.valueProperty()    .bindBidirectional(driver.vehicleReliabilityProperty());


        overrideRacingSkillCheckBox.selectedProperty()              .bindBidirectional(driver.overrideRaceSkillProperty());
        overrideQualiSkillCheckBox.selectedProperty()               .bindBidirectional(driver.overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty()               .bindBidirectional(driver.overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty()                .bindBidirectional(driver.overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty()                  .bindBidirectional(driver.overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty()              .bindBidirectional(driver.overrideConsistencyProperty());
        overrideStartReactions.selectedProperty()                   .bindBidirectional(driver.overrideStartReactionsProperty());
        overrideWetSkillCheckbox.selectedProperty()                 .bindBidirectional(driver.overrideWetSkillProperty());
        overrideTyreManagementCheckBox.selectedProperty()           .bindBidirectional(driver.overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty()           .bindBidirectional(driver.overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty()                 .bindBidirectional(driver.overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty()               .bindBidirectional(driver.overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty()         .bindBidirectional(driver.overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty()   .bindBidirectional(driver.overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty()       .bindBidirectional(driver.overrideVehicleReliabilityProperty());
    }

    private void unbindDriver(Driver driver) {
        driverLiveryNameTextField.textProperty()    .unbindBidirectional(driver.liveryNameProperty());
        driverNameTextField.textProperty()          .unbindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()       .unbindBidirectional(driver.countryProperty());
        racingSkillSlider.valueProperty()           .unbindBidirectional(driver.raceSkillProperty());
        qualiSkillSlider.valueProperty()            .unbindBidirectional(driver.qualifyingSkillProperty());
        aggressionSlider.valueProperty()            .unbindBidirectional(driver.aggressionProperty());
        defendingSlider.valueProperty()             .unbindBidirectional(driver.defendingProperty());
        staminaSlider.valueProperty()               .unbindBidirectional(driver.staminaProperty());
        consistencySlider.valueProperty()           .unbindBidirectional(driver.consistencyProperty());
        startReactionsSlider.valueProperty()        .unbindBidirectional(driver.startReactionsProperty());
        wetSkillSlider.valueProperty()              .unbindBidirectional(driver.wetSkillProperty());
        tyreManagementSlider.valueProperty()        .unbindBidirectional(driver.tyreManagementProperty());
        fuelManagementSlider.valueProperty()        .unbindBidirectional(driver.fuelManagementProperty());
        blueFlagSlider.valueProperty()              .unbindBidirectional(driver.blueFlagConcedingProperty());
        weatherPitSlider.valueProperty()            .unbindBidirectional(driver.weatherTyreChangeProperty());
        mistakeAvoidanceSlider.valueProperty()      .unbindBidirectional(driver.avoidanceOfMistakesProperty());
        forcedMistakeSlider.valueProperty()         .unbindBidirectional(driver.avoidanceOfForcedMistakesProperty());
        vehicleReliabilitySlider.valueProperty()    .unbindBidirectional(driver.vehicleReliabilityProperty());

        overrideRacingSkillCheckBox.selectedProperty()              .unbindBidirectional(driver.overrideRaceSkillProperty());
        overrideQualiSkillCheckBox.selectedProperty()               .unbindBidirectional(driver.overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty()               .unbindBidirectional(driver.overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty()                .unbindBidirectional(driver.overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty()                  .unbindBidirectional(driver.overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty()              .unbindBidirectional(driver.overrideConsistencyProperty());
        overrideStartReactions.selectedProperty()                   .unbindBidirectional(driver.overrideStartReactionsProperty());
        overrideWetSkillCheckbox.selectedProperty()                 .unbindBidirectional(driver.overrideWetSkillProperty());
        overrideTyreManagementCheckBox.selectedProperty()           .unbindBidirectional(driver.overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty()           .unbindBidirectional(driver.overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty()                 .unbindBidirectional(driver.overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty()               .unbindBidirectional(driver.overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty()         .unbindBidirectional(driver.overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty()   .unbindBidirectional(driver.overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty()       .unbindBidirectional(driver.overrideVehicleReliabilityProperty());
    }
}
