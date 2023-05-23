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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.util.DDUtil;

public class ConfigurationScreenController implements StageController {
    @FXML
    private CheckBox tOverrideBlueFlagCheckBox;

    @FXML
    private CheckBox overrideAggressionCheckBox;

    @FXML
    private TextField trackLibraryTextField;

    @FXML
    private CheckBox tOverrideMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideDefendingCheckBox;

    @FXML
    private CheckBox tOverrideAggressionCheckBox;

    @FXML
    private Button vehicleClassFileChooserButton;

    @FXML
    private CheckBox tOverrideConsistencyCheckBox;

    @FXML
    private CheckBox tOverrideWetSkillCheckBox;

    @FXML
    private CheckBox tOverrideNameCheckBox;

    @FXML
    private CheckBox overrideNameCheckBox;

    @FXML
    private TextField driverLibraryTextField;

    @FXML
    private CheckBox tOverrideStartReactionsCheckBox;

    @FXML
    private CheckBox tOverrideRacingSkillCheckBox;

    @FXML
    private Button trackLibraryFileChooserButton;

    @FXML
    private CheckBox overrideVehicleReliabilityCheckBox;

    @FXML
    private CheckBox overrideStaminaCheckBox;

    @FXML
    private CheckBox overrideFuelManagementCheckBox;

    @FXML
    private TextField vehicleClassLibraryTextField;

    @FXML
    private CheckBox overrideStartReactionsCheckBox;

    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox tOverrideDefendingCheckBox;

    @FXML
    private CheckBox tOverrideFuelManagementCheckBox;

    @FXML
    private CheckBox overrideWeatherPitCheckBox;

    @FXML
    private CheckBox tOverrideTyreManagementCheckBox;

    @FXML
    private CheckBox tOverrideVehicleReliabilityCheckBox;

    @FXML
    private CheckBox overrideWetSkillCheckBox;

    @FXML
    private CheckBox overrideMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideConsistencyCheckBox;

    @FXML
    private Button driverLibraryFileChooserButton;

    @FXML
    private CheckBox tOverrideStaminaCheckBox;

    @FXML
    private CheckBox overrideRacingSkillCheckBox;

    @FXML
    private CheckBox overrideQualifyingSkillCheckBox;

    @FXML
    private CheckBox overrideBlueFlagCheckBox;

    @FXML
    private CheckBox overrideTyreManagemnetCheckBox;

    @FXML
    private CheckBox chooseLiveryNameCheckBox;

    @FXML
    private CheckBox overrideCountryCheckBox;

    @FXML
    private CheckBox tOverrideWeatherPitCheckBox;

    @FXML
    private CheckBox tOverrideCountry;

    @FXML
    private CheckBox tOverrideForcedMistakeCheckBox;

    @FXML
    private CheckBox tOverrideQualifyingSkillCheckBox;

    @FXML
    private VBox rootPane;

    Stage stage;

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
