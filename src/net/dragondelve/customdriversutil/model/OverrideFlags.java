package net.dragondelve.customdriversutil.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A class that encapsulates all flags that determine whether you should override the DriverBase's properties or
 * not. All flags are stored as boolean properties and you can access their value by calling a corresponding accessor
 * method or you can access them as properties. All properties within this class are final. This class is fully annotated
 * to be used with JAXB for exporting to and importing from an xml file. It is exported as part of the configuration file.
 */
@XmlRootElement(name = "override_flags")
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
    @XmlElement(name = "override_name")
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
     * Lightweight mutator method.
     * @param overrideName Flag that determines whether you should override driver name or not.
     */
    public final void setOverrideName(boolean overrideName) {
        this.overrideNameProperty().set(overrideName);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's country or not.
     */
    @XmlElement (name = "override_country")
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
     * Lightweight mutator method.
     * @param overrideCountryProperty Flag that determines whether you should override driver's country or not.
     */
    public final void setOverrideCountry(boolean overrideCountryProperty) {
        this.overrideCountry.set(overrideCountryProperty);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's race skill or not.
     */
    @XmlElement (name = "override_race_skill")
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
     * Lightweight mutator method.
     * @param overrideRaceSkill Flag that determines whether you should override driver's race skill or not.
     */
    public final void setOverrideRaceSkill(boolean overrideRaceSkill) {
        this.overrideRaceSkill.set(overrideRaceSkill);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's qualifying skill or not.
     */
    @XmlElement (name = "override_quali_skill")
    public final boolean isOverrideQualifyingSkill() {
        return overrideQualifyingSkill.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's qualifying skill or not as a property.
     */
    public final BooleanProperty overrideQualifyingSkillProperty() {
        return overrideQualifyingSkill;
    }

    /**
     * Lightweight mutator method.
     * @param overrideQualifyingSkill Flag that determines whether you should override driver's qualifying skill or not.
     */
    public void setOverrideQualifyingSkill(boolean overrideQualifyingSkill) {
        this.overrideQualifyingSkill.set(overrideQualifyingSkill);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's aggression or not.
     */
    @XmlElement (name = "override_aggression")
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
     * Lightweight mutator method.
     * @param overrideAggression Flag that determines whether you should override driver's aggression or not.
     */
    public void setOverrideAggression(boolean overrideAggression) {
        this.overrideAggression.set(overrideAggression);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's defending or not.
     */
    @XmlElement (name = "override_defending")
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
     * Lightweight mutator method.
     * @param overrideDefending Flag that determines whether you should override driver's defending or not.
     */
    public final void setOverrideDefending(boolean overrideDefending) {
        this.overrideDefending.set(overrideDefending);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's stamina or not.
     */
    @XmlElement (name = "override_stamina")
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
     * Lightweight mutator method.
     * @param overrideStamina Flag that determines whether you should override driver's stamina or not.
     */
    public final void setOverrideStamina(boolean overrideStamina) {
        this.overrideStamina.set(overrideStamina);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's consistency or not.
     */
    @XmlElement (name = "override_consistency")
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
     * Lightweight mutator method.
     * @param overrideConsistency Flag that determines whether you should override driver's consistency or not.
     */
    public final void setOverrideConsistency(boolean overrideConsistency) {
        this.overrideConsistency.set(overrideConsistency);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's start reactions or not.
     */
    @XmlElement (name = "override_start_reactions")
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
     * Lightweight mutator method.
     * @param overrideStartReactions Flag that determines whether you should override driver's start reactions or not.
     */
    public final void setOverrideStartReactions(boolean overrideStartReactions) {
        this.overrideStartReactions.set(overrideStartReactions);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's wet skill or not.
     */
    @XmlElement (name =  "override_wet_skill")
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
     * Lightweight mutator method.
     * @param overrideWetSkill Flag that determines whether you should override driver's wet skill or not.
     */
    public final void setOverrideWetSkill(boolean overrideWetSkill) {
        this.overrideWetSkill.set(overrideWetSkill);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's tyre management or not.
     */
    @XmlElement (name = "override_tyre_management")
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
     * Lightweight mutator method.
     * @param overrideTyreManagement Flag that determines whether you should override driver's tyre management or not.
     */
    public final void setOverrideTyreManagement(boolean overrideTyreManagement) {
        this.overrideTyreManagement.set(overrideTyreManagement);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's fuel management or not.
     */
    @XmlElement (name = "override_fuel_management")
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
     * Lightweight mutator method.
     * @param overrideFuelManagement Flag that determines whether you should override driver's fuel management or not.
     */
    public final void setOverrideFuelManagement(boolean overrideFuelManagement) {
        this.overrideFuelManagement.set(overrideFuelManagement);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's blue flag conceding or not.
     */
    @XmlElement (name = "override_blue_flag_conceding")
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
     * Lightweight mutator method.
     * @param overrideBlueFlagConceding Flag that determines whether you should override driver's blue flag conceding or not.
     */
    public final void setOverrideBlueFlagConceding(boolean overrideBlueFlagConceding) {
        this.overrideBlueFlagConceding.set(overrideBlueFlagConceding);
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
     * Lightweight mutator method.
     * @param overrideWeatherTyreChange Flag that determines whether you should override driver's weather tyre change or not.
     */
    public final void setOverrideWeatherTyreChange(boolean overrideWeatherTyreChange) {
        this.overrideWeatherTyreChange.set(overrideWeatherTyreChange);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of mistakes or not.
     */
    @XmlElement (name = "override_avoidance_of_mistakes")
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
     * Lightweight mutator method.
     * @param overrideAvoidanceOfMistakes Flag that determines whether you should override driver's avoidance of mistakes or not.
     */
    public final void setOverrideAvoidanceOfMistakes(boolean overrideAvoidanceOfMistakes) {
        this.overrideAvoidanceOfMistakes.set(overrideAvoidanceOfMistakes);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether you should override driver's avoidance of forced mistakes or not.
     */
    @XmlElement (name = "override_avoidance_of_forced_mistakes")
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
     * Lightweight mutator method.
     * @param overrideAvoidanceOfForcedMistakes Flag that determines whether you should override driver's avoidance of forced mistakes or not.
     */
    public final void setOverrideAvoidanceOfForcedMistakes(boolean overrideAvoidanceOfForcedMistakes) {
        this.overrideAvoidanceOfForcedMistakes.set(overrideAvoidanceOfForcedMistakes);
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

    /**
     * Lightweight mutator method.
     * @param overrideVehicleReliability Flag that determines whether you should override driver's vehicle reliability or not.
     */
    public final void setOverrideVehicleReliability(boolean overrideVehicleReliability) {
        this.overrideVehicleReliability.set(overrideVehicleReliability);
    }

    /**
     * Sets the given value to all boolean properties of overrideAll to a given boolean value.
     * @param overrideAll flag that will be set to all properties of overrideFlags.
     */
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
