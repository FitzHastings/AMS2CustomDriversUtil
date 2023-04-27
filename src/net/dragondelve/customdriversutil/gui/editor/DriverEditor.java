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
    private CheckBox consistencyCheckBox;

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
    private TextField forcedMistakeAvoidanceTextField;

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
    private CheckBox overrideDefendingCheckBox;

    @FXML
    private CheckBox overrideDriverNameCheckBox;

    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideForcedMistakesCheckBox;

    @FXML
    private CheckBox overrideFuelManagementCheckBox;

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
        driverNameTextField.textProperty()      .bindBidirectional(driver.liveryNameProperty());
        driverNameTextField.textProperty()      .bindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()   .bindBidirectional(driver.countryProperty());
    }

    private void unbindDriver(Driver driver) {
        driverNameTextField.textProperty()      .unbindBidirectional(driver.liveryNameProperty());
        driverNameTextField.textProperty()      .unbindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()   .unbindBidirectional(driver.countryProperty());
    }
}
