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

import javafx.beans.property.*;

/**
 * Represents a base model of a driver described by AMS2 Developers here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
abstract class DriverBase {
    /**
     * Driver name.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * Flag that determines whether you should override driver name or not.
     */
    private final BooleanProperty overrideName = new SimpleBooleanProperty();

    /**
     * 3 letter country code. This is used for displaying the country flag.
     */
    private final StringProperty country = new SimpleStringProperty();

    /**
     * Flag that determines whether you should override driver's country or not.
     */
    private final BooleanProperty overrideCountry = new SimpleBooleanProperty();

    /**
     * Race session driver skill. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting. Example:
     * For example, at 90% "Opponent Skill Level" slider setting: it builds a range from 85% to 95% (just example, the exact numbers vary for each vehicle model),
     * so a 1.0 race skill driver would be 95% skill and a 0.0 race skill driver would be 85% skill in this example.
     */
    private final DoubleProperty raceSkill = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's race skill or not.
     */
    private final BooleanProperty overrideRaceSkill = new SimpleBooleanProperty();

    /**
     * Qualification Session driver skill. Its completely independent of the race_skill.
     * One detail is that a lower qualifying_skill value increases the likelihood of AI programmed mistakes during qualifying hotlaps.
     */
    private final DoubleProperty qualifyingSkill = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's qualifying skill or not.
     */
    private final BooleanProperty overrideQualifyingSkill = new SimpleBooleanProperty();

    /**
     * Driver aggression. It is scaled by the "Opponent Aggression" setting:
     * At Low "Opponent Aggression" setting, the 0.0-1.0 aggression value is mapped into a 0.0-0.8 range.
     * At Medium "Opponent Aggression" setting, the 0-1 aggression value is mapped into a 0.2-1.0 range.
     * At High "Opponent Aggression" setting, the 0-1 aggression value is mapped into a 0.6-1.0 range.
     * At Max "Opponent Aggression" setting, all drivers have 1.0 aggression.
     */
    private final DoubleProperty aggression = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's aggression or not.
     */
    private final BooleanProperty overrideAggression = new SimpleBooleanProperty();

    /**
     * How much the driver will try to defend his position. Is also scaled by the "Opponent Aggression" slider setting.
     * One detail is that a lower defending value increases the likelihood of AI programmed mistakes when under pressure.
     */
    private final DoubleProperty defending = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's defending or not.
     */
    private final BooleanProperty overrideDefending = new SimpleBooleanProperty();

    /**
     * Lower stamina value means the driver loses more of his skill during the session,
     * and also makes the driver to become tired earlier (which increases the likelihood of AI programmed mistakes)
     */
    private final DoubleProperty stamina = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's stamina or not.
     */
    private final BooleanProperty overrideStamina = new SimpleBooleanProperty();

    /**
     * Lower consistency value means the driver skill can be randomly reduced more (basically, the lower the consistency
     * value of a driver, the more skill he can lose from the consistency logic). The randomness is biased towards not losing too much skill,
     * so there is a higher probability for the drivers to lose few skill than to lose much skill. Its has a per-weekend effect
     * (determined upon loading of a track) and also a per-lap effect (determined every new lap or so). One detail is that
     * a lower consistency value slightly increases the likelihood of AI programmed mistakes during the session.
     */
    private final DoubleProperty consistency = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's consistency or not.
     */
    private final BooleanProperty overrideConsistency = new SimpleBooleanProperty();

    /**
     * Lower start_reactions value means the driver will take more time to react to the race green flag and is more likely to make race start
     * programmed mistakes at the moment of the race green flag (like losing rear grip with some smoke).
     */
    private final DoubleProperty startReactions = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's start reactions or not.
     */
    private final BooleanProperty overrideStartReactions = new SimpleBooleanProperty();

    /**
     * How good he is on a wet track. Controls how much he will slow down in curves as the track gets wet
     * (lower wet_skill values means he will slow down more), and how likely he will make programmed mistakes related to wetness,
     * like losing grip in puddles or in wet surface (lower wet_skill values increases the likelihood of those mistakes).
     */
    private final DoubleProperty wetSkill = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's wet skill or not.
     */
    private final BooleanProperty overrideWetSkill = new SimpleBooleanProperty();

    /**
     * How good he is in preventing tyre wear (higher values means he will have less tyre wear and consequently
     * he will be able do keep doing good laptimes for a longer period and pit later due to that).
     * This doesn't change the behavior of the driver (i.e. he won't drive differently to try to save tires), just the tyre wear.
     */
    private final DoubleProperty tyreManagement = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's tyre management or not.
     */
    private final BooleanProperty overrideTyreManagement = new SimpleBooleanProperty();

    /**
     * For now this parameter works in oval tracks only (but in future it will be extended to all track types);
     * The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing,
     * like when it sees it can maybe save a pitstop, it will try to stay in draft and coasting instead of overtaking
     * while at the same time trying to not lose much distance to the race leader. It doesnt mean high value is better
     * than low value, its just a characteristic of the driver.
     */
    private final DoubleProperty fuelManagement = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's fuel management or not.
     */
    private final BooleanProperty overrideFuelManagement = new SimpleBooleanProperty();

    /**
     * Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag.
     */
    private final DoubleProperty blueFlagConceding = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's blue flag conceding or not.
     */
    private final BooleanProperty overrideBlueFlagConceding = new SimpleBooleanProperty();

    /**
     * Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes during the session in general
     * (like understeer, oversteer, recoverable and non-recoverable mistakes).
     */
    private final DoubleProperty weatherTyreChange = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's weather tyre change or not.
     */
    private final BooleanProperty overrideWeatherTyreChange = new SimpleBooleanProperty();

    /**
     * Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes during the session in general
     * (like understeer, oversteer, recoverable and non-recoverable mistakes).
     */
    private final DoubleProperty avoidanceOfMistakes = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's avoidance of mistakes or not.
     */
    private final BooleanProperty overrideAvoidanceOfMistakes = new SimpleBooleanProperty();

    /**
     *  Drivers with 1.0 value for avoidance_of_forced_mistakes won't have their chances of mistakes increased when under pressure (when defending position).
     *  Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure (compared to their chances
     *  of mistakes when not under pressure).
     */
    private final DoubleProperty avoidanceOfForcedMistakes = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's avoidance of forced mistakes or not.
     */
    private final BooleanProperty overrideAvoidanceOfForcedMistakes = new SimpleBooleanProperty();

    /**
     * Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    private final DoubleProperty vehicleReliability = new SimpleDoubleProperty();

    /**
     * Flag that determines whether you should override driver's vehicle reliability or not.
     */
    private final BooleanProperty overrideVehicleReliability = new SimpleBooleanProperty();

    /**
     * Lightweight accessor method.
     * @return Driver name.
     */
    public String getName() {
        return name.get();
    }

    /**
     * Lightweight accessor method.
     * @return Driver name as a property.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver name or not.
     */
    public boolean isOverrideName() {
        return overrideName.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver name or not as a property.
     */
    public BooleanProperty overrideNameProperty() {
        return overrideName;
    }

    /**
     * Lightweight accessor method.
     * @return 3 letter country code as a property. This is used for displaying the country flag.
     */
    public StringProperty countryProperty() {
        return country;
    }

    /**
     /**
     * Lightweight accessor method.
     * @return 3 letter country code. This is used for displaying the country flag.
     */
    public String getCountry() {
        return country.get();
    }
    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's country or not.
     */
    public boolean isOverrideCountry() {
        return overrideCountry.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's country or not.
     */
    public BooleanProperty overrideCountryProperty() {
        return overrideCountry;
    }

    /**
     * Lightweight accessor method.
     * @return Race session driver skill. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting.
     */
    public double getRaceSkill() {
        return raceSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Race session driver skill as a property. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting.
     */
    public DoubleProperty raceSkillProperty() {
        return raceSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Qualification Session driver skill. Its completely independent from the race_skill.
     */
    public double getQualifyingSkill() {
        return qualifyingSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Qualification Session driver skill as a property. Its completely independent from the race_skill.
     */
    public DoubleProperty qualifyingSkillProperty() {
        return qualifyingSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Driver aggression. It is scaled by the "Opponent Aggression".
     */
    public double getAggression() {
        return aggression.get();
    }

    /**
     * Lightweight accessor method.
     * @return Driver aggression as a property. It is scaled by the "Opponent Aggression".
     */
    public DoubleProperty aggressionProperty() {
        return aggression;
    }

    /**
     * Lightweight accessor method.
     * @return How much the driver will try to defend his position.
     */
    public double getDefending() {
        return defending.get();
    }

    /**
     * Lightweight accessor method.
     * @return How much the driver will try to defend his position as a property.
     */
    public DoubleProperty defendingProperty() {
        return defending;
    }

    /**
     * Lightweight accessor method.
     * @return Lower stamina value means the driver loses more of his skill during the session.
     */
    public double getStamina() {
        return stamina.get();
    }

    /**
     * Lightweight accessor method.
     * @return Lower stamina value means the driver loses more of his skill during the session.
     */
    public DoubleProperty staminaProperty() {
        return stamina;
    }

    /**
     * Lightweight accessor method.
     * @return Lower consistency value means the driver skill can be randomly reduced more.
     */
    public double getConsistency() {
        return consistency.get();
    }

    /**
     * Lightweight accessor method.
     * @return Lower consistency value means the driver skill can be randomly reduced more.
     */
    public DoubleProperty consistencyProperty() {
        return consistency;
    }

    /**
     * Lightweight accessor method.
     * @return Lower start_reactions value means the driver will take more time to react to the race green flag
     */
    public double getStartReactions() {
        return startReactions.get();
    }

    /**
     * Lightweight accessor method.
     * @return Lower start_reactions value means the driver will take more time to react to the race green flag
     */
    public DoubleProperty startReactionsProperty() {
        return startReactions;
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is on a wet track.
     */
    public double getWetSkill() {
        return wetSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is on a wet track as a property.
     */
    public DoubleProperty wetSkillProperty() {
        return wetSkill;
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is in preventing tyre wear.
     */
    public double getTyreManagement() {
        return tyreManagement.get();
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is in preventing tyre wear as a proeprty.
     */
    public DoubleProperty tyreManagementProperty() {
        return tyreManagement;
    }

    /**
     * Lightweight accessor method.
     * @return The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing.
     */
    public double getFuelManagement() {
        return fuelManagement.get();
    }

    /**
     * Lightweight accessor method.
     * @return The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing.
     */
    public DoubleProperty fuelManagementProperty() {
        return fuelManagement;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag.
     */
    public double getBlueFlagConceding() {
        return blueFlagConceding.get();
    }

    public DoubleProperty blueFlagConcedingProperty() {
        return blueFlagConceding;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with high weather_tyre_changes are more likely to make pitstops for changing tyres when the track wetness state changes.
     */
    public double getWeatherTyreChange() {
        return weatherTyreChange.get();
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with high weather_tyre_changes are more likely to make pitstops for changing tyres when the track wetness state changes.
     */
    public DoubleProperty weatherTyreChangeProperty() {
        return weatherTyreChange;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes
     */
    public double getAvoidanceOfMistakes() {
        return avoidanceOfMistakes.get();
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes
     */
    public DoubleProperty avoidanceOfMistakesProperty() {
        return avoidanceOfMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure.
     */
    public double getAvoidanceOfForcedMistakes() {
        return avoidanceOfForcedMistakes.get();
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure.
     */
    public DoubleProperty avoidanceOfForcedMistakesProperty() {
        return avoidanceOfForcedMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    public double getVehicleReliability() {
        return vehicleReliability.get();
    }

    /**
     * Lightweight accessor method.
     * @return Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    public DoubleProperty vehicleReliabilityProperty() {
        return vehicleReliability;
    }

    public boolean isOverrideRaceSkill() {
        return overrideRaceSkill.get();
    }

    public BooleanProperty overrideRaceSkillProperty() {
        return overrideRaceSkill;
    }

    public boolean isOverrideQualifyingSkill() {
        return overrideQualifyingSkill.get();
    }

    public BooleanProperty overrideQualifyingSkillProperty() {
        return overrideQualifyingSkill;
    }

    public boolean isOverrideAggression() {
        return overrideAggression.get();
    }

    public BooleanProperty overrideAggressionProperty() {
        return overrideAggression;
    }

    public boolean isOverrideDefending() {
        return overrideDefending.get();
    }

    public BooleanProperty overrideDefendingProperty() {
        return overrideDefending;
    }

    public boolean isOverrideStamina() {
        return overrideStamina.get();
    }

    public BooleanProperty overrideStaminaProperty() {
        return overrideStamina;
    }

    public boolean isOverrideConsistency() {
        return overrideConsistency.get();
    }

    public BooleanProperty overrideConsistencyProperty() {
        return overrideConsistency;
    }

    public boolean isOverrideStartReactions() {
        return overrideStartReactions.get();
    }

    public BooleanProperty overrideStartReactionsProperty() {
        return overrideStartReactions;
    }

    public boolean isOverrideWetSkill() {
        return overrideWetSkill.get();
    }

    public BooleanProperty overrideWetSkillProperty() {
        return overrideWetSkill;
    }

    public boolean isOverrideTyreManagement() {
        return overrideTyreManagement.get();
    }

    public BooleanProperty overrideTyreManagementProperty() {
        return overrideTyreManagement;
    }

    public boolean isOverrideFuelManagement() {
        return overrideFuelManagement.get();
    }

    public BooleanProperty overrideFuelManagementProperty() {
        return overrideFuelManagement;
    }

    public boolean isOverrideBlueFlagConceding() {
        return overrideBlueFlagConceding.get();
    }

    public BooleanProperty overrideBlueFlagConcedingProperty() {
        return overrideBlueFlagConceding;
    }

    public boolean isOverrideWeatherTyreChange() {
        return overrideWeatherTyreChange.get();
    }

    public BooleanProperty overrideWeatherTyreChangeProperty() {
        return overrideWeatherTyreChange;
    }

    public boolean isOverrideAvoidanceOfMistakes() {
        return overrideAvoidanceOfMistakes.get();
    }

    public BooleanProperty overrideAvoidanceOfMistakesProperty() {
        return overrideAvoidanceOfMistakes;
    }

    public boolean isOverrideAvoidanceOfForcedMistakes() {
        return overrideAvoidanceOfForcedMistakes.get();
    }

    public BooleanProperty overrideAvoidanceOfForcedMistakesProperty() {
        return overrideAvoidanceOfForcedMistakes;
    }

    public boolean isOverrideVehicleReliability() {
        return overrideVehicleReliability.get();
    }

    public BooleanProperty overrideVehicleReliabilityProperty() {
        return overrideVehicleReliability;
    }
}
