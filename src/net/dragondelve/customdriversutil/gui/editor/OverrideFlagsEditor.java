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

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.OverrideFlags;
import net.dragondelve.customdriversutil.util.DDUtil;

import java.util.logging.Level;

public class OverrideFlagsEditor implements Editor<OverrideFlags> {
    @FXML
    private CheckBox overrideWetSkillCheckBox;

    @FXML
    private CheckBox overrideAggressionCheckBox;

    @FXML
    private CheckBox overrideDefendingCheckBox;

    @FXML
    private CheckBox overrideMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideConsistencyCheckBox;

    @FXML
    private CheckBox overrideNameCheckBox;

    @FXML
    private CheckBox overrideRacingSkillCheckBox;

    @FXML
    private CheckBox overrideQualifyingSkillCheckBox;

    @FXML
    private CheckBox overrideBlueFlagCheckBox;

    @FXML
    private CheckBox overrideTyreManagemnetCheckBox;

    @FXML
    private CheckBox overrideVehicleReliabilityCheckBox;

    @FXML
    private CheckBox overrideCountryCheckBox;

    @FXML
    private CheckBox overrideStaminaCheckBox;

    @FXML
    private CheckBox overrideFuelManagementCheckBox;

    @FXML
    private CheckBox overrideStartReactionsCheckBox;

    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    @FXML
    private CheckBox overrideWeatherPitCheckBox;

    Stage stage;

    OverrideFlags overrideFlags;

    @FXML
    public void initialize() {
        overrideNameCheckBox.selectedProperty()                     .bindBidirectional(overrideFlags.overrideNameProperty());
        overrideCountryCheckBox.selectedProperty()                  .bindBidirectional(overrideFlags.overrideCountryProperty());
        overrideRacingSkillCheckBox.selectedProperty()              .bindBidirectional(overrideFlags.overrideRaceSkillProperty());
        overrideQualifyingSkillCheckBox.selectedProperty()          .bindBidirectional(overrideFlags.overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty()               .bindBidirectional(overrideFlags.overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty()                .bindBidirectional(overrideFlags.overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty()                  .bindBidirectional(overrideFlags.overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty()              .bindBidirectional(overrideFlags.overrideConsistencyProperty());
        overrideStartReactionsCheckBox.selectedProperty()           .bindBidirectional(overrideFlags.overrideStartReactionsProperty());
        overrideWetSkillCheckBox.selectedProperty()                 .bindBidirectional(overrideFlags.overrideWetSkillProperty());
        overrideTyreManagemnetCheckBox.selectedProperty()           .bindBidirectional(overrideFlags.overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty()           .bindBidirectional(overrideFlags.overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty()                 .bindBidirectional(overrideFlags.overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty()               .bindBidirectional(overrideFlags.overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty()         .bindBidirectional(overrideFlags.overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty()   .bindBidirectional(overrideFlags.overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty()       .bindBidirectional(overrideFlags.overrideVehicleReliabilityProperty());
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public ObservableList<OverrideFlags> getItems() {
        return new ImmutableObservableList<>(overrideFlags);
    }

    @Override
    public void setItems(ObservableList<OverrideFlags> items) {
        if(items != null || items.size() == 1)
            overrideFlags = items.get(0);
        else
            DDUtil.DEFAULT_LOGGER.log(Level.SEVERE, "ERROR: OverrideFlagsEditor received items incorrectly.");
    }
}
