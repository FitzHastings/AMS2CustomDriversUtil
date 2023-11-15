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

/**
 * Edits a list of a single element (I know) of OverrideFlags.
 * Controller for fxml/editor/OverrideFlagsEditor.fxml
 */
public class OverrideFlagsEditor implements Editor<OverrideFlags> {
    /**
     * CheckBox that determines the overrideWetSkill property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideWetSkillCheckBox;

    /**
     * CheckBox that determines the overrideAggression property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideAggressionCheckBox;

    /**
     * CheckBox that determines the overrideDefending property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideDefendingCheckBox;

    /**
     * CheckBox that determines the overrideMistakeAvoidance property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideMistakeAvoidanceCheckBox;

    /**
     * CheckBox that determines the overrideConsistency property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideConsistencyCheckBox;

    /**
     * CheckBox that determines the overrideName property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideNameCheckBox;

    /**
     * CheckBox that determines the overrideRacingSkill property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideRacingSkillCheckBox;

    /**
     * CheckBox that determines the overrideQualifyingSkill property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideQualifyingSkillCheckBox;

    /**
     * CheckBox that determines the overrideBlueFlagConceding property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideBlueFlagCheckBox;

    /**
     * CheckBox that determines the overrideTyreManagement property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideTyreManagemnetCheckBox;

    /**
     * CheckBox that determines the overrideVehicleReliability property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideVehicleReliabilityCheckBox;

    /**
     * CheckBox that determines the overrideCountry property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideCountryCheckBox;

    /**
     * CheckBox that determines the overrideStamina property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideStaminaCheckBox;

    /**
     * CheckBox that determines the overrideFuelManagement property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideFuelManagementCheckBox;

    /**
     * CheckBox that determines the overrideStartReactions property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideStartReactionsCheckBox;

    /**
     * CheckBox that determines the overrideForcedMistakeAvoidance property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    /**
     * CheckBox that determines the overrideWeatherPit property of the edited OverrideFlags.
     */
    @FXML
    private CheckBox overrideWeatherPitCheckBox;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    /**
     * Edited OverrideFlags.
     */
    private OverrideFlags overrideFlags;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        overrideNameCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideNameProperty());
        overrideCountryCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideCountryProperty());
        overrideRacingSkillCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideRaceSkillProperty());
        overrideQualifyingSkillCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideConsistencyProperty());
        overrideStartReactionsCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideStartReactionsProperty());
        overrideWetSkillCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideWetSkillProperty());
        overrideTyreManagemnetCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty().bindBidirectional(overrideFlags.overrideVehicleReliabilityProperty());
    }

    /**
     * Lightweight mutator method.
     *
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Lightweight accessor method.
     *
     * @return an ImmutableObservableList of OverrideFlags that contains only one element.
     */
    @Override
    public ObservableList<OverrideFlags> getItems() {
        return new ImmutableObservableList<>(overrideFlags);
    }

    /**
     * Semi-lightweight mutator method. Has to check for size to make sure that the amount of elements in the list passed
     * to this method equals to one.
     *
     * @param items an observableList of OverrideFlags that should contain only one element.
     */
    @Override
    public void setItems(ObservableList<OverrideFlags> items) {
        if (items != null && items.size() == 1)
            overrideFlags = items.get(0);
        else
            DDUtil.DEFAULT_LOGGER.log(Level.SEVERE, "ERROR: OverrideFlagsEditor received items incorrectly.");
    }
}
