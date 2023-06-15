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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.tools.modifier.*;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.TooltipUtil;

import java.util.List;

/**
 * Controller for a tool that allows the user to modify the Grid of drivers and their property in mass way.
 */
public class MassModifyToolController implements StageController{

    /**
     * CheckBox that determines if blueFlagConceding property should be modified.
     */
    @FXML
    private CheckBox blueFlagConcedingCheckBox;

    /**
     * RadioButton that determines if DecreasePercentAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton decreasePercentRadioButton;

    /**
     * RadioButton that determines if DecreasePercentAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton increaseDistanceCeilingRadioButton;

    /**
     * CheckBox that determines if aggression property should be modified.
     */
    @FXML
    private CheckBox aggressionCheckBox;

    /**
     * Slider that determines the modificationValue that will be passed to the ModifierAction.
     */
    @FXML
    private Slider modificationValueSlider;

    /**
     * CheckBox that determines if tyreManagement property should be modified.
     */
    @FXML
    private CheckBox tyreManagementCheckBox;

    /**
     * RadioButton that determines if IncreaseFlatAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton increaseFlatRadioButton;

    /**
     * RadioButton that determines if BringCloserFloorAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton bringCloserFloorRadioButton;

    /**
     * Button that performs cancelAction() on action,
     * Closes the stage.
     */
    @FXML
    private Button cancelButton;

    /**
     * RadioButton that determines if BringCloserCeiling should be passed to the GridModifier.
     */
    @FXML
    private RadioButton bringCloserCeilingRadioButton;

    /**
     * Button that performs modifyAction() on action.
     * Performs modifications, then closes the stage.
     */
    @FXML
    private Button modifyButton;

    /**
     * CheckBox that determines if qualificationSkill property should be modified.
     */
    @FXML
    private CheckBox qualiSkillCheckBox;

    /**
     * CheckBox that determines if fuelManagement property should be modified.
     */
    @FXML
    private CheckBox fuelManagementCheckBox;

    /**
     * CheckBox that determines if startReactions property should be modified.
     */
    @FXML
    private CheckBox startReactionsCheckBox;

    /**
     * CheckBox that determines if mistakeAvoidance property should be modified.
     */
    @FXML
    private CheckBox mistakeAvoidanceCheckBox;

    /**
     * RadioButton that determines if IncreasePercentAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton increasePercentRadioButton;

    /**
     * CheckBox that determines if racingSkill property should be modified.
     */
    @FXML
    private CheckBox racingSkillCheckBox;

    /**
     * CheckBox that determines if stamina property should be modified.
     */
    @FXML
    private CheckBox staminaCheckBox;

    /**
     * The root pane of this window, used to apply the CSS resource to this entire scene.
     */
    @FXML
    private VBox rootPane;

    /**
     * RadioButton that determines if decreaseFlatAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton decreaseFlatRadioButton;

    /**
     * TextField that displays the exact value set in the modificationValueSlider
     */
    @FXML
    private TextField modificationValueTextField;

    /**
     * RadioButton that determines if IncreaseDistanceFloorAction should be passed to the GridModifier.
     */
    @FXML
    private RadioButton increaseDistanceFloorRadioButton;

    /**
     * CheckBox that determines if consistency property should be modified.
     */
    @FXML
    private CheckBox consistencyCheckBox;

    /**
     * CheckBox that determines if defending property should be modified.
     */
    @FXML
    private CheckBox defendingCheckBox;

    /**
     * CheckBox that determines if weatherPit property should be modified.
     */
    @FXML
    private CheckBox weatherPitCheckBox;

    /**
     * CheckBox that determines if wetSkill property should be modified.
     */
    @FXML
    private CheckBox wetSkillCheckBox;

    /**
     * CheckBox that determines if forcedMistakeAvoidance property should be modified.
     */
    @FXML
    private CheckBox forcedMistakeAvoidanceCheckbox;

    /**
     * CheckBox that determines if vehicleReliability property should be modified.
     */
    @FXML
    private CheckBox vehicleReliabilityCheckBox;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    /**
     * Settings that are going to be used to modify the grid of drivers.
     */
    private final ModifierSettings modifierSettings;

    /**
     * Creates a new instance of MassModifyToolController.
     * @param grid List of drivers to be modified.
     */
    public MassModifyToolController(List<Driver> grid) {
        this.modifierSettings = new ModifierSettings(grid);
    }

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    private void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        ToggleGroup actionGroup = new ToggleGroup();

        increaseFlatRadioButton.setToggleGroup(actionGroup);
        decreaseFlatRadioButton.setToggleGroup(actionGroup);
        increasePercentRadioButton.setToggleGroup(actionGroup);
        decreasePercentRadioButton.setToggleGroup(actionGroup);

        increaseDistanceFloorRadioButton.setToggleGroup(actionGroup);
        bringCloserFloorRadioButton.setToggleGroup(actionGroup);
        increaseDistanceCeilingRadioButton.setToggleGroup(actionGroup);
        bringCloserCeilingRadioButton.setToggleGroup(actionGroup);

        increaseFlatRadioButton.selectedProperty().set(true);

        cancelButton.setOnAction(e -> cancelAction());
        modifyButton.setOnAction(e -> modifyAction());

        racingSkillCheckBox             .selectedProperty().bindBidirectional(modifierSettings.overrideRaceSkillProperty());
        qualiSkillCheckBox              .selectedProperty().bindBidirectional(modifierSettings.overrideQualifyingSkillProperty());
        aggressionCheckBox              .selectedProperty().bindBidirectional(modifierSettings.overrideAggressionProperty());
        defendingCheckBox               .selectedProperty().bindBidirectional(modifierSettings.overrideDefendingProperty());
        staminaCheckBox                 .selectedProperty().bindBidirectional(modifierSettings.overrideStaminaProperty());
        consistencyCheckBox             .selectedProperty().bindBidirectional(modifierSettings.overrideConsistencyProperty());
        startReactionsCheckBox          .selectedProperty().bindBidirectional(modifierSettings.overrideStartReactionsProperty());
        wetSkillCheckBox                .selectedProperty().bindBidirectional(modifierSettings.overrideWetSkillProperty());
        tyreManagementCheckBox          .selectedProperty().bindBidirectional(modifierSettings.overrideTyreManagementProperty());
        fuelManagementCheckBox          .selectedProperty().bindBidirectional(modifierSettings.overrideFuelManagementProperty());
        blueFlagConcedingCheckBox       .selectedProperty().bindBidirectional(modifierSettings.overrideBlueFlagConcedingProperty());
        weatherPitCheckBox              .selectedProperty().bindBidirectional(modifierSettings.overrideWeatherTyreChangeProperty());
        mistakeAvoidanceCheckBox        .selectedProperty().bindBidirectional(modifierSettings.overrideAvoidanceOfMistakesProperty());
        forcedMistakeAvoidanceCheckbox  .selectedProperty().bindBidirectional(modifierSettings.overrideAvoidanceOfForcedMistakesProperty());
        vehicleReliabilityCheckBox      .selectedProperty().bindBidirectional(modifierSettings.overrideVehicleReliabilityProperty());

        modificationValueTextField.textProperty().bind(modificationValueSlider.valueProperty().asString("%.2f"));
        modificationValueTextField.setEditable(false);

        initTooltips();
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Performed by the cancelButton. Closes the current stage.
     */
    private void cancelAction() {
        this.stage.close();
    }

    /**
     * Performed by the modifyButton. Passes the modifier settings to the GridModifier with the action based on the user's
     * selection.
     */
    private void modifyAction() {
        ModifierAction action = null;
        if (increaseFlatRadioButton.isSelected()) {
            action = new IncreaseFlatAction(modificationValueSlider.getValue());
        } else if (decreaseFlatRadioButton.isSelected()) {
            action = new DecreaseFlatAction(modificationValueSlider.getValue());
        } else if (increasePercentRadioButton.isSelected()) {
            action = new IncreasePercentAction(modificationValueSlider.getValue());
        } else if (decreasePercentRadioButton.isSelected()) {
            action = new DecreasePercentAmount(modificationValueSlider.getValue());
        } else if (bringCloserFloorRadioButton.isSelected()) {
            action = new BringCloserFloorAction(modificationValueSlider.getValue());
        } else if (bringCloserCeilingRadioButton.isSelected()) {
            action = new BringCloserCeilingAction(modificationValueSlider.getValue());
        } else if (increaseDistanceFloorRadioButton.isSelected()) {
            action = new IncreaseDistanceFloorAction(modificationValueSlider.getValue());
        } else if (increaseDistanceCeilingRadioButton.isSelected()) {
            action = new IncreaseDistanceCeilingAction(modificationValueSlider.getValue());
        }

        GridModifier modifier = new GridModifier(modifierSettings, action);
        modifier.performModifications();
        stage.close();
    }

    /**
     * Initializes all tooltips for the control elements
     */
    private void initTooltips() {
        racingSkillCheckBox             .setTooltip(TooltipUtil.raceSkillTooltip);
        qualiSkillCheckBox              .setTooltip(TooltipUtil.qualiSkillTooltip);
        aggressionCheckBox              .setTooltip(TooltipUtil.aggressionTooltip);
        defendingCheckBox               .setTooltip(TooltipUtil.defendingTooltip);
        staminaCheckBox                 .setTooltip(TooltipUtil.staminaTooltip);
        consistencyCheckBox             .setTooltip(TooltipUtil.consistencyTooltip);
        startReactionsCheckBox          .setTooltip(TooltipUtil.startReactionsTooltip);
        wetSkillCheckBox                .setTooltip(TooltipUtil.wetSkillTooltip);
        tyreManagementCheckBox          .setTooltip(TooltipUtil.tyreManagementTooltip);
        fuelManagementCheckBox          .setTooltip(TooltipUtil.fuelManagementTooltip);
        blueFlagConcedingCheckBox       .setTooltip(TooltipUtil.blueFlagConcedingTooltip);
        weatherPitCheckBox              .setTooltip(TooltipUtil.weatherPitTooltip);
        mistakeAvoidanceCheckBox        .setTooltip(TooltipUtil.mistakeAvoidanceTooltip);
        forcedMistakeAvoidanceCheckbox  .setTooltip(TooltipUtil.forcedMistakeAvoidanceTooltip);
        vehicleReliabilityCheckBox      .setTooltip(TooltipUtil.vehicleReliabilityTooltip);
    }
}