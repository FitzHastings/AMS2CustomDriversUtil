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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.util.DDUtil;


/**
 *
 */
public class MassModifyToolController implements StageController{

    @FXML
    private CheckBox blueFlagConcedingCheckBox;

    @FXML
    private RadioButton decreasePercentRadioButton;

    @FXML
    private RadioButton IncreaseDistanceCeilingRadioButton;

    @FXML
    private CheckBox aggressionCheckBox;

    @FXML
    private Slider modificationValueSlider;

    @FXML
    private CheckBox tyreManagementCheckBox;

    @FXML
    private RadioButton incraseFlatRadioButton;

    @FXML
    private RadioButton bringCloserFloorRadioButton;

    @FXML
    private Button cancelButton;

    @FXML
    private RadioButton bringCloserCielingRadioButton;

    @FXML
    private Button modifyButton;

    @FXML
    private CheckBox qualiSkillCheckBox;

    @FXML
    private CheckBox fuelManagementCheckBox;

    @FXML
    private CheckBox startReactionsCheckBox;

    @FXML
    private CheckBox mistakeAvoidanceCheckBox;

    @FXML
    private RadioButton increasePercentRadioButton;

    @FXML
    private CheckBox racingSkillCheckBox;

    @FXML
    private CheckBox staminaCheckBox;

    @FXML
    private VBox rootPane;

    @FXML
    private RadioButton decreaseFlatRadioButton;

    @FXML
    private TextField modificationValueTextField;

    @FXML
    private RadioButton increaseDistanceFloorRadioButton;

    @FXML
    private CheckBox consistencyCheckBox;

    @FXML
    private CheckBox defendingCheckBox;

    @FXML
    private CheckBox weatherPitCheckBox;

    @FXML
    private CheckBox wetSkillCheckBox;

    @FXML
    private CheckBox forcedMistakeAvoidanceCheckbox;

    @FXML
    private CheckBox vehicleReliabilityCheckBox;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    private void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
