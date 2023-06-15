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

package net.dragondelve.customdriversutil.tools.modifier;

import net.dragondelve.customdriversutil.model.DriverBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Performs modifications of the grid based on the settings passed and the actionPassed.
 */
public class GridModifier {
    /**
     * Settings that encapsulate the grid and properties that have to be modified with the ModifierAction given.
     */
    private final ModifierSettings settings;

    /**
     * Action that will be performed on all properties when performModifications is called.
     */
    private final ModifierAction modifierAction;

    /**
     * Creates a new instance of GridModifier
     * @param settings Settings that encapsulate the grid and properties that have to be modified with the ModifierAction given.
     * @param modifierAction Action that will be performed on all properties when performModifications is called.
     */
    public GridModifier(ModifierSettings settings, ModifierAction modifierAction) {
        this.settings = settings;
        this.modifierAction = modifierAction;
    }

    /**
     * Performs modifications on grid's properties based on the settings and modifierActions provided
     */
    public void performModifications() {
        if (settings == null || modifierAction == null)
            return;
        List<? extends DriverBase> grid = settings.getGrid();

        if (settings.isOverrideRaceSkill()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getRaceSkill()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).raceSkillProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideQualifyingSkill()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getQualifyingSkill()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).qualifyingSkillProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideAggression()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getAggression()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).aggressionProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideDefending()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getDefending()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).defendingProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideStamina()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getStamina()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).staminaProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideConsistency()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getConsistency()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).consistencyProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideStartReactions()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getStartReactions()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).startReactionsProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideWetSkill()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getWetSkill()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).wetSkillProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideTyreManagement()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getTyreManagement()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).tyreManagementProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideFuelManagement()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getFuelManagement()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).fuelManagementProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideBlueFlagConceding()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getBlueFlagConceding()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).blueFlagConcedingProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideWeatherTyreChange()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getWeatherTyreChange()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).weatherTyreChangeProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideAvoidanceOfMistakes()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getAvoidanceOfMistakes()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).avoidanceOfMistakesProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideAvoidanceOfForcedMistakes()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getAvoidanceOfForcedMistakes()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).avoidanceOfForcedMistakesProperty().set(modifierAction.performAction(values.get(i)));
            }
        }

        if (settings.isOverrideVehicleReliability()) {
            List<Double> values = new ArrayList<>();
            grid.forEach(driver -> values.add(driver.getVehicleReliability()));
            if (SmartAction.class.isAssignableFrom(modifierAction.getClass()))
                ((SmartAction) modifierAction).analyzeValues(values);
            for(int i = 0; i < values.size(); i++) {
                grid.get(i).vehicleReliabilityProperty().set(modifierAction.performAction(values.get(i)));
            }
        }
    }
}
