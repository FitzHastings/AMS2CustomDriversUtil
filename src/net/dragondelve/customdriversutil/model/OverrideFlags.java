package net.dragondelve.customdriversutil.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A class that encapsulates all flags that determine whether you should override the DriverBase's properties or
 * not. All flags are stored as boolean properties and you can access their value by calling a corresponding accessor
 * method or you can access them as properties. All properties within this class are final.
 */
public class OverrideFlags {
    /**
     * Flag that determines whether you should override driver name or not.
     */
    private final BooleanProperty overrideName = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's country or not.
     */
    private final BooleanProperty overrideCountry = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's race skill or not.
     */
    private final BooleanProperty overrideRaceSkill = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's qualifying skill or not.
     */
    private final BooleanProperty overrideQualifyingSkill = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's aggression or not.
     */
    private final BooleanProperty overrideAggression = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's defending or not.
     */
    private final BooleanProperty overrideDefending = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's stamina or not.
     */
    private final BooleanProperty overrideStamina = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's consistency or not.
     */
    private final BooleanProperty overrideConsistency = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's start reactions or not.
     */
    private final BooleanProperty overrideStartReactions = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's wet skill or not.
     */
    private final BooleanProperty overrideWetSkill = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's tyre management or not.
     */
    private final BooleanProperty overrideTyreManagement = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's fuel management or not.
     */
    private final BooleanProperty overrideFuelManagement = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's blue flag conceding or not.
     */
    private final BooleanProperty overrideBlueFlagConceding = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's weather tyre change or not.
     */
    private final BooleanProperty overrideWeatherTyreChange = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's avoidance of mistakes or not.
     */
    private final BooleanProperty overrideAvoidanceOfMistakes = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's avoidance of forced mistakes or not.
     */
    private final BooleanProperty overrideAvoidanceOfForcedMistakes = new SimpleBooleanProperty();

    /**
     * Flag that determines whether you should override driver's vehicle reliability or not.
     */
    private final BooleanProperty overrideVehicleReliability = new SimpleBooleanProperty();

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver name or not.
     */
    public final boolean isOverrideName() {
        return overrideName.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver name or not as a property.
     */
    public final BooleanProperty overrideNameProperty() {
        return overrideName;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's country or not.
     */
    public final boolean isOverrideCountry() {
        return overrideCountry.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's country or not.
     */
    public final BooleanProperty overrideCountryProperty() {
        return overrideCountry;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's race skill or not.
     */
    public final boolean isOverrideRaceSkill() {
        return overrideRaceSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's race skill or not as a property.
     */
    public final BooleanProperty overrideRaceSkillProperty() {
        return overrideRaceSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's qualifying skill or not.
     */
    public final boolean isOverrideQualifyingSkill() {
        return overrideQualifyingSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's qualifying skill or not as a property..
     */
    public final BooleanProperty overrideQualifyingSkillProperty() {
        return overrideQualifyingSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's aggression or not.
     */
    public final boolean isOverrideAggression() {
        return overrideAggression.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's aggression or not as a property.
     */
    public final BooleanProperty overrideAggressionProperty() {
        return overrideAggression;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's defending or not.
     */
    public final boolean isOverrideDefending() {
        return overrideDefending.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's defending or not as property.
     */
    public final BooleanProperty overrideDefendingProperty() {
        return overrideDefending;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's stamina or not.
     */
    public final boolean isOverrideStamina() {
        return overrideStamina.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's stamina or not as property.
     */
    public final BooleanProperty overrideStaminaProperty() {
        return overrideStamina;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's consistency or not.
     */
    public final boolean isOverrideConsistency() {
        return overrideConsistency.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's consistency or not as a property.
     */
    public final BooleanProperty overrideConsistencyProperty() {
        return overrideConsistency;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's start reactions or not.
     */
    public final boolean isOverrideStartReactions() {
        return overrideStartReactions.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's start reactions or not as a property.
     */
    public final BooleanProperty overrideStartReactionsProperty() {
        return overrideStartReactions;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's wet skill or not.
     */
    public final boolean isOverrideWetSkill() {
        return overrideWetSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's wet skill or not as a property.
     */
    public final BooleanProperty overrideWetSkillProperty() {
        return overrideWetSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's tyre management or not.
     */
    public final boolean isOverrideTyreManagement() {
        return overrideTyreManagement.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's tyre management or not as a property.
     */
    public final BooleanProperty overrideTyreManagementProperty() {
        return overrideTyreManagement;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's fuel management or not.
     */
    public final boolean isOverrideFuelManagement() {
        return overrideFuelManagement.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's fuel management or not as a property.
     */
    public final BooleanProperty overrideFuelManagementProperty() {
        return overrideFuelManagement;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's blue flag conceding or not.
     */
    public final boolean isOverrideBlueFlagConceding() {
        return overrideBlueFlagConceding.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's blue flag conceding or not as a property.
     */
    public final BooleanProperty overrideBlueFlagConcedingProperty() {
        return overrideBlueFlagConceding;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's weather tyre change or not.
     */
    public final boolean isOverrideWeatherTyreChange() {
        return overrideWeatherTyreChange.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's weather tyre change or not as a property.
     */
    public final BooleanProperty overrideWeatherTyreChangeProperty() {
        return overrideWeatherTyreChange;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of mistakes or not.
     */
    public final boolean isOverrideAvoidanceOfMistakes() {
        return overrideAvoidanceOfMistakes.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of mistakes or not as a property.
     */
    public final BooleanProperty overrideAvoidanceOfMistakesProperty() {
        return overrideAvoidanceOfMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of forced mistakes or not.
     */
    public final boolean isOverrideAvoidanceOfForcedMistakes() {
        return overrideAvoidanceOfForcedMistakes.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of forced mistakes or not as a property.
     */
    public final BooleanProperty overrideAvoidanceOfForcedMistakesProperty() {
        return overrideAvoidanceOfForcedMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's vehicle reliability or not.
     */
    public final boolean isOverrideVehicleReliability() {
        return overrideVehicleReliability.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's vehicle reliability or not as a property.
     */
    public final BooleanProperty overrideVehicleReliabilityProperty() {
        return overrideVehicleReliability;
    }

    public void setOverrideAll(boolean overrideAll) {
       overrideNameProperty().set(overrideAll);
       overrideCountryProperty().set(overrideAll);
       overrideRaceSkillProperty().set(overrideAll);
       overrideQualifyingSkillProperty().set(overrideAll);
       overrideAggressionProperty().set(overrideAll);
       overrideDefendingProperty().set(overrideAll);
       overrideStaminaProperty().set(overrideAll);
       overrideConsistencyProperty().set(overrideAll);
       overrideStartReactionsProperty().set(overrideAll);
       overrideWetSkillProperty().set(overrideAll);
       overrideTyreManagementProperty().set(overrideAll);
       overrideFuelManagementProperty().set(overrideAll);
       overrideBlueFlagConcedingProperty().set(overrideAll);
       overrideWeatherTyreChangeProperty().set(overrideAll);
       overrideAvoidanceOfMistakesProperty().set(overrideAll);
       overrideAvoidanceOfForcedMistakesProperty().set(overrideAll);
       overrideVehicleReliabilityProperty().set(overrideAll);
    }
}
