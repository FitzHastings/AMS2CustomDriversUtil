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
 * Represents a base model of a driver or a track override  as described by AMS2 Developers here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
@XmlRootElement(name = "driver")
public class Driver {
    private String liveryName;
    private String name;
    private String country;
    private Double raceSkill;
    private Double qualifyingSkill;
    private Double aggression;
    private Double defending;
    private Double stamina;
    private Double consistency;
    private Double startReactions;
    private Double wetSkill;
    private Double tyreManagement;
    private Double fuelManagement;
    private Double blueFlagConceding;
    private Double weatherTyreChanges;
    private Double avoidanceOfMistakes;
    private Double avoidanceOfForcedMistakes;
    private Double vehicleReliability;
    private String tracks;

    @XmlAttribute(name = "livery_name")
    public String getLiveryName() {
        return liveryName;
    }

    public void setLiveryName(String liveryName) {
        this.liveryName = liveryName;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @XmlElement(name = "race_skill")
    public Double getRaceSkill() {
        return raceSkill;
    }
    
    public void setRaceSkill(Double raceSkill) {
        this.raceSkill = raceSkill;
    }

    @XmlElement(name = "qualifying_skill")
    public Double getQualifyingSkill() {
        return qualifyingSkill;
    }
    
    public void setQualifyingSkill(Double qualifyingSkill) {
        this.qualifyingSkill = qualifyingSkill;
    }

    @XmlElement(name = "aggression")
    public Double getAggression() {
        return aggression;
    }
    
    public void setAggression(Double aggression) {
        this.aggression = aggression;
    }

    @XmlElement(name = "defending")
    public Double getDefending() {
        return defending;
    }
    
    public void setDefending(Double defending) {
        this.defending = defending;
    }

    @XmlElement(name = "stamina")
    public Double getStamina() {
        return stamina;
    }

    public void setStamina(Double stamina) {
        this.stamina = stamina;
    }

    @XmlElement(name = "consistency")
    public Double getConsistency() {
        return consistency;
    }
    
    public void setConsistency(Double consistency) {
        this.consistency = consistency;
    }

    @XmlElement(name = "start_reactions")
    public Double getStartReactions() {
        return startReactions;
    }
    
    public void setStartReactions(Double startReactions) {
        this.startReactions = startReactions;
    }

    @XmlElement(name = "wet_skill")
    public Double getWetSkill() {
        return wetSkill;
    }
    
    public void setWetSkill(Double wetSkill) {
        this.wetSkill = wetSkill;
    }

    @XmlElement(name = "tyre_management")
    public Double getTyreManagement() {
        return tyreManagement;
    }
    
    public void setTyreManagement(Double tyreManagement) {
        this.tyreManagement = tyreManagement;
    }

    @XmlElement(name = "fuel_management")
    public Double getFuelManagement() {
        return fuelManagement;
    }

    public void setFuelManagement(Double fuelManagement) {
        this.fuelManagement = fuelManagement;
    }

    @XmlElement (name = "blue_flag_conceding")
    public Double getBlueFlagConceding() {
        return blueFlagConceding;
    }

    public void setBlueFlagConceding(Double blueFlagConceding) {
        this.blueFlagConceding = blueFlagConceding;
    }

    @XmlElement (name = "weather_tyre_changes")
    public Double getWeatherTyreChanges() {
        return weatherTyreChanges;
    }

    public void setWeatherTyreChanges(Double weatherTyreChanges) {
        this.weatherTyreChanges = weatherTyreChanges;
    }

    @XmlElement (name = "avoidance_of_mistakes")
    public Double getAvoidanceOfMistakes() {
        return avoidanceOfMistakes;
    }

    public void setAvoidanceOfMistakes(Double avoidanceOfMistakes) {
        this.avoidanceOfMistakes = avoidanceOfMistakes;
    }

    @XmlElement (name = "avoidance_of_forced_mistakes")
    public Double getAvoidanceOfForcedMistakes() {
        return avoidanceOfForcedMistakes;
    }

    public void setAvoidanceOfForcedMistakes(Double avoidanceOfForcedMistakes) {
        this.avoidanceOfForcedMistakes = avoidanceOfForcedMistakes;
    }

    @XmlElement (name = "vehicle_reliability")
    public Double getVehicleReliability() {
        return vehicleReliability;
    }

    public void setVehicleReliability(Double vehicleReliability) {
        this.vehicleReliability = vehicleReliability;
    }

    @XmlAttribute (name = "tracks")
    public String getTracks() {
        return tracks;
    }

    public void setTracks(String tracks) {
        this.tracks = tracks;
    }
}
