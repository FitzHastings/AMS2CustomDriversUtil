// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License";
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

package net.dragondelve.customdriversutil.model.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is fully annotated for use with JAXB.
 * Represents a base model of a driver or a track override as described by AMS2 Developers here:
 * <a href="https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/">AMS2 Reiza Forums</a>
 */
@XmlRootElement(name = "driver")
final class XMLDriver {
    /**
     * In game name of the livery. Ths value determines to which car the custom AI values are going to be applied.
     * If the Livery Name is shared between different drivers than it's likely that some of them are track specific overrides
     * of the same driver.
     */
    private String liveryName;

    /**
     * Driver name.
     */
    private String name;

    /**
     * 3-letter country code. This is used for displaying the country flag.
     */
    private String country;

    /**
     * Race session driver skill. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting. Example:
     * For example, at 90% "Opponent Skill Level" slider setting: it builds a range from 85% to 95% (just example, the exact numbers vary for each vehicle model),
     * so a 1.0 race skill driver would be 95% skill and a 0.0 race skill driver would be 85% skill in this example.
     */
    private Double raceSkill;

    /**
     * Qualification Session driver skill. Its completely independent of the race_skill.
     * One detail is that a lower qualifying_skill value increases the likelihood of AI programmed mistakes during qualifying hotlaps.
     */
    private Double qualifyingSkill;

    /**
     * Driver aggression. It is scaled by the "Opponent Aggression" setting:
     * At Low "Opponent Aggression" setting, the 0.0-1.0 aggression value is mapped into a 0.0-0.8 range.
     * At Medium "Opponent Aggression" setting, the 0-1 aggression value is mapped into a 0.2-1.0 range.
     * At High "Opponent Aggression" setting, the 0-1 aggression value is mapped into a 0.6-1.0 range.
     * At Max "Opponent Aggression" setting, all drivers have 1.0 aggression.
     */
    private Double aggression;

    /**
     * How much the driver will try to defend his position. Is also scaled by the "Opponent Aggression" slider setting.
     * One detail is that a lower defending value increases the likelihood of AI programmed mistakes when under pressure.
     */
    private Double defending;

    /**
     * Lower stamina value means the driver loses more of his skill during the session,
     * and also makes the driver to become tired earlier (which increases the likelihood of AI programmed mistakes)
     */
    private Double stamina;

    /**
     * Lower consistency value means the driver skill can be randomly reduced more (basically, the lower the consistency
     * value of a driver, the more skill he can lose from the consistency logic). The randomness is biased towards not losing too much skill,
     * so there is a higher probability for the drivers to lose few skill than to lose much skill. Its has a per-weekend effect
     * (determined upon loading of a track) and also a per-lap effect (determined every new lap or so). One detail is that
     * a lower consistency value slightly increases the likelihood of AI programmed mistakes during the session.
     */
    private Double consistency;

    /**
     * Lower start_reactions value means the driver will take more time to react to the race green flag and is more likely to make race start
     * programmed mistakes at the moment of the race green flag (like losing rear grip with some smoke).
     */
    private Double startReactions;

    /**
     * How good he is on a wet track. Controls how much he will slow down in curves as the track gets wet
     * (lower wet_skill values means he will slow down more), and how likely he will make programmed mistakes related to wetness,
     * like losing grip in puddles or in wet surface (lower wet_skill values increases the likelihood of those mistakes).
     */
    private Double wetSkill;

    /**
     * How good he is in preventing tyre wear (higher values means he will have less tyre wear, and consequently
     * he will be able to do keep doing good lap times for a longer period and pit later due to that).
     * This doesn't change the behavior of the driver (i.e. he won't drive differently to try to save tires), just the tyre wear.
     */
    private Double tyreManagement;

    /**
     * For now this parameter works in oval tracks only (but in future it will be extended to all track types);
     * The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing,
     * like when it sees it can maybe save a pit stop, it will try to stay in draft and coasting instead of overtaking
     * while at the same time trying to not lose much distance to the race leader. It doesn't mean high value is better
     * than low value, it's just a characteristic of the driver.
     */
    private Double fuelManagement;

    /**
     * Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag.
     */
    private Double blueFlagConceding;

    /**
     * Drivers with high weather_tyre_changes are more likely to make pit stops for changing tyres when the track wetness state changes.
     * It doesn't mean 1.0 is better than 0.0, it's just a characteristic of the driver, not really a skill.
     * Sometimes staying longer on the track with the wrong tyres is better, sometimes its worse.
     */
    private Double weatherTyreChanges;

    /**
     * Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes during the session in general
     * (like understeer, oversteer, recoverable and non-recoverable mistakes).
     */
    private Double avoidanceOfMistakes;

    /**
     *  Drivers with 1.0 value for avoidance_of_forced_mistakes won't have their chances of mistakes increased when under pressure (when defending position).
     *  Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure (compared to their chances
     *  of mistakes when not under pressure).
     */
    private Double avoidanceOfForcedMistakes;

    /**
     * Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    private Double vehicleReliability;

    /**
     * Comma separated list of track names. If this XML attribute is present in the XML it indicates that this driver is a track specific override of another driver.
     */
    private String tracks;

    /**
     * Lightweight accessor method.
     * @return In game name of the livery.
     */
    @XmlAttribute(name = "livery_name")
    public String getLiveryName() {
        return liveryName;
    }

    /**
     * Lightweight mutator method.
     * @param liveryName In game name of the livery.
     */
    public void setLiveryName(String liveryName) {
        this.liveryName = liveryName;
    }

    /**
     * Lightweight accessor method.
     * @return Driver name.
     */
    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    /**
     * Lightweight mutator method.
     * @param name Driver name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Lightweight accessor method.
     * @return 3-letter country code. This is used for displaying the country flag.
     */
    @XmlElement(name = "country")
    public String getCountry() {
        return country;
    }

    /**
     * Lightweight mutator method.
     * @param country 3-letter country code. This is used for displaying the country flag.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Lightweight accessor method.
     * @return Race session driver skill. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting.
     */
    @XmlElement(name = "race_skill")
    public Double getRaceSkill() {
        return raceSkill;
    }

    /**
     * Lightweight mutator method.
     * @param raceSkill  Race session driver skill. It is mapped into a smaller range based on the "Opponent Skill Level" slider setting.
     */
    public void setRaceSkill(Double raceSkill) {
        this.raceSkill = raceSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Qualification Session driver skill. Its completely independent of the race_skill.
     */
    @XmlElement(name = "qualifying_skill")
    public Double getQualifyingSkill() {
        return qualifyingSkill;
    }

    /**
     * Lightweight mutator method.
     * @param qualifyingSkill Qualification Session driver skill. Its completely independent of the race_skill.
     */
    public void setQualifyingSkill(Double qualifyingSkill) {
        this.qualifyingSkill = qualifyingSkill;
    }

    /**
     * Lightweight accessor method.
     * @return Driver aggression. It is scaled by the "Opponent Aggression".
     */
    @XmlElement(name = "aggression")
    public Double getAggression() {
        return aggression;
    }

    /**
     * Lightweight mutator method.
     * @param aggression Driver aggression. It is scaled by the "Opponent Aggression".
     */
    public void setAggression(Double aggression) {
        this.aggression = aggression;
    }

    /**
     * Lightweight accessor method.
     * @return How much the driver will try to defend his position.
     */
    @XmlElement(name = "defending")
    public Double getDefending() {
        return defending;
    }

    /**
     * Lightweight mutator method.
     * @param defending How much the driver will try to defend his position.
     */
    public void setDefending(Double defending) {
        this.defending = defending;
    }

    /**
     * Lightweight accessor method.
     * @return Lower stamina value means the driver loses more of his skill during the session.
     */
    @XmlElement(name = "stamina")
    public Double getStamina() {
        return stamina;
    }

    /**
     * Lightweight mutator method.
     * @param stamina Lower stamina value means the driver loses more of his skill during the session.
     */
    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    /**
     * Lightweight accessor method.
     * @return Lower consistency value means the driver skill can be randomly reduced more.
     */
    @XmlElement(name = "consistency")
    public Double getConsistency() {
        return consistency;
    }

    /**
     * Lightweight mutator method.
     * @param consistency Lower consistency value means the driver skill can be randomly reduced more.
     */
    public void setConsistency(Double consistency) {
        this.consistency = consistency;
    }

    /**
     * Lightweight accessor method.
     * @return Lower start_reactions value means the driver will take more time to react to the race green flag
     */
    @XmlElement(name = "start_reactions")
    public Double getStartReactions() {
        return startReactions;
    }

    /**
     * Lightweight mutator method.
     * @param startReactions Lower start_reactions value means the driver will take more time to react to the race green flag
     */
    public void setStartReactions(Double startReactions) {
        this.startReactions = startReactions;
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is on a wet track.
     */
    @XmlElement(name = "wet_skill")
    public Double getWetSkill() {
        return wetSkill;
    }

    /**
     * Lightweight mutator method.
     * @param wetSkill How good the driver is on a wet track.
     */
    public void setWetSkill(Double wetSkill) {
        this.wetSkill = wetSkill;
    }

    /**
     * Lightweight accessor method.
     * @return How good the driver is in preventing tyre wear.
     */
    @XmlElement(name = "tyre_management")
    public Double getTyreManagement() {
        return tyreManagement;
    }

    /**
     * Lightweight mutator method.
     * @param tyreManagement How good the driver is in preventing tyre wear.
     */
    public void setTyreManagement(Double tyreManagement) {
        this.tyreManagement = tyreManagement;
    }

    /**
     * Lightweight accessor method.
     * @return The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing.
     */
    @XmlElement(name = "fuel_management")
    public Double getFuelManagement() {
        return fuelManagement;
    }

    /**
     * Lightweight mutator method.
     * @param fuelManagement The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing.
     */
    public void setFuelManagement(Double fuelManagement) {
        this.fuelManagement = fuelManagement;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag.
     */
    @XmlElement (name = "blue_flag_conceding")
    public Double getBlueFlagConceding() {
        return blueFlagConceding;
    }

    /**
     * Lightweight mutator method.
     * @param blueFlagConceding Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag.
     */
    public void setBlueFlagConceding(Double blueFlagConceding) {
        this.blueFlagConceding = blueFlagConceding;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with high weather_tyre_changes are more likely to make pitstops for changing tyres when the track wetness state changes.
     */
    @XmlElement (name = "weather_tyre_changes")
    public Double getWeatherTyreChanges() {
        return weatherTyreChanges;
    }

    /**
     * Lightweight mutator method.
     * @param weatherTyreChanges Drivers with high weather_tyre_changes are more likely to make pitstops for changing tyres when the track wetness state changes.
     */
    public void setWeatherTyreChanges(Double weatherTyreChanges) {
        this.weatherTyreChanges = weatherTyreChanges;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes
     */
    @XmlElement (name = "avoidance_of_mistakes")
    public Double getAvoidanceOfMistakes() {
        return avoidanceOfMistakes;
    }

    /**
     * Lightweight mutator method.
     * @param avoidanceOfMistakes Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes
     */
    public void setAvoidanceOfMistakes(Double avoidanceOfMistakes) {
        this.avoidanceOfMistakes = avoidanceOfMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure.
     */
    @XmlElement (name = "avoidance_of_forced_mistakes")
    public Double getAvoidanceOfForcedMistakes() {
        return avoidanceOfForcedMistakes;
    }

    /**
     * Lightweight mutator method.
     * @param avoidanceOfForcedMistakes  Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure.
     */
    public void setAvoidanceOfForcedMistakes(Double avoidanceOfForcedMistakes) {
        this.avoidanceOfForcedMistakes = avoidanceOfForcedMistakes;
    }

    /**
     * Lightweight accessor method.
     * @return Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    @XmlElement (name = "vehicle_reliability")
    public Double getVehicleReliability() {
        return vehicleReliability;
    }

    /**
     * Lightweight mutator method.
     * @param vehicleReliability Ratio between the lowest and highest possible reliability for the car/class in question.
     */
    public void setVehicleReliability(Double vehicleReliability) {
        this.vehicleReliability = vehicleReliability;
    }

    /**
     * Lightweight accessor method.
     * @return Comma separated list of track names.
     */
    @XmlAttribute (name = "tracks")
    public String getTracks() {
        return tracks;
    }

    /**
     * Lightweight mutator method.
     * @param tracks Comma separated list of track names.
     */
    public void setTracks(String tracks) {
        this.tracks = tracks;
    }
}
