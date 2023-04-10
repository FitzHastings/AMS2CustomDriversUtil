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

package net.dragondelve.customdriversutil.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a base model of a driver described by AMS2 Developers here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
abstract class DriverBase {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty country = new SimpleStringProperty();
    private final DoubleProperty raceSkill = new SimpleDoubleProperty();
    private final DoubleProperty qualifyingSkill = new SimpleDoubleProperty();
    private final DoubleProperty aggression = new SimpleDoubleProperty();
    private final DoubleProperty defending = new SimpleDoubleProperty();
    private final DoubleProperty stamina = new SimpleDoubleProperty();
    private final DoubleProperty consistency = new SimpleDoubleProperty();
    private final DoubleProperty startReactions = new SimpleDoubleProperty();
    private final DoubleProperty wetSkill = new SimpleDoubleProperty();
    private final DoubleProperty tyreManagement = new SimpleDoubleProperty();
    private final DoubleProperty fuelManagement = new SimpleDoubleProperty();
    private final DoubleProperty blueFlagConceding = new SimpleDoubleProperty();
    private final DoubleProperty avoidanceOfMistakes = new SimpleDoubleProperty();
    private final DoubleProperty avoidanceOfForcedMistakes = new SimpleDoubleProperty();
    private final DoubleProperty vehicleReliability = new SimpleDoubleProperty();


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public double getRaceSkill() {
        return raceSkill.get();
    }

    public DoubleProperty raceSkillProperty() {
        return raceSkill;
    }

    public double getQualifyingSkill() {
        return qualifyingSkill.get();
    }

    public DoubleProperty qualifyingSkillProperty() {
        return qualifyingSkill;
    }

    public double getAggression() {
        return aggression.get();
    }

    public DoubleProperty aggressionProperty() {
        return aggression;
    }

    public double getDefending() {
        return defending.get();
    }

    public DoubleProperty defendingProperty() {
        return defending;
    }

    public double getStamina() {
        return stamina.get();
    }

    public DoubleProperty staminaProperty() {
        return stamina;
    }

    public double getConsistency() {
        return consistency.get();
    }

    public DoubleProperty consistencyProperty() {
        return consistency;
    }

    public double getStartReactions() {
        return startReactions.get();
    }

    public DoubleProperty startReactionsProperty() {
        return startReactions;
    }

    public double getWetSkill() {
        return wetSkill.get();
    }

    public DoubleProperty wetSkillProperty() {
        return wetSkill;
    }

    public double getTyreManagement() {
        return tyreManagement.get();
    }

    public DoubleProperty tyreManagementProperty() {
        return tyreManagement;
    }

    public double getFuelManagement() {
        return fuelManagement.get();
    }

    public DoubleProperty fuelManagementProperty() {
        return fuelManagement;
    }

    public double getBlueFlagConceding() {
        return blueFlagConceding.get();
    }

    public DoubleProperty blueFlagConcedingProperty() {
        return blueFlagConceding;
    }

    public double getAvoidanceOfMistakes() {
        return avoidanceOfMistakes.get();
    }

    public DoubleProperty avoidanceOfMistakesProperty() {
        return avoidanceOfMistakes;
    }

    public double getAvoidanceOfForcedMistakes() {
        return avoidanceOfForcedMistakes.get();
    }

    public DoubleProperty avoidanceOfForcedMistakesProperty() {
        return avoidanceOfForcedMistakes;
    }

    public double getVehicleReliability() {
        return vehicleReliability.get();
    }

    public DoubleProperty vehicleReliabilityProperty() {
        return vehicleReliability;
    }
}
