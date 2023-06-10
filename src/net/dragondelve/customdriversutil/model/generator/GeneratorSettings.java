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

package net.dragondelve.customdriversutil.model.generator;

import javafx.beans.property.*;
import net.dragondelve.customdriversutil.model.VehicleClass;

/**
 * Encapsulates generator settings that are necessary to generate a new driver grid.
 */
public class GeneratorSettings {
    /**
     * Vehicle class of the desired generated grid.
     */
    private VehicleClass vehicleClass;

    /**
     * Flag that determines whether NAMeS files should be used in order to generate driver names.
     */
    private final BooleanProperty useNAMeS = new SimpleBooleanProperty();

    /**
     * Flag that determines whether livery names should be used in order to generate driver names.
     */
    private final BooleanProperty fromLiveryNames = new SimpleBooleanProperty();

    /**
     * Flag that determines if Qualification skill and race skill should be bound together or not.
     */
    private final BooleanProperty bindQualiAndRaceSkills = new SimpleBooleanProperty();

    /**
     * Flag that determines if TrackOverrides for ovals with reduced gap ar going to be generated or not.
     */
    private final BooleanProperty reduceGapsOnOvals = new SimpleBooleanProperty();

    /**
     * Flag that determines whether aggression is limited or not.
     */
    private final BooleanProperty limitAggression = new SimpleBooleanProperty();

    /**
     * Number of drivers that determines how many drivers should be generated.
     */
    private final IntegerProperty nDrivers = new SimpleIntegerProperty();

    /**
     * Aggression limit determines the maximum value that driver's aggression can have.
     */
    private final DoubleProperty aggressionLimit = new SimpleDoubleProperty();

    /**
     * Gap between Driver's qualification skill and racing skill if those values are bound.
     */
    private final DoubleProperty boundSkillsGap = new SimpleDoubleProperty();

    /**
     *
     * @return Vehicle class of the desired generated grid.
     */
    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    /**
     * Lightweight accessor method.
     * @param vehicleClass Vehicle class of the desired generated grid.
     */
    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether NAMeS files should be used in order to generate driver names.
     */
    public boolean isUseNAMeS() {
        return useNAMeS.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether NAMeS files should be used in order to generate driver names as a property.
     */
    public BooleanProperty useNAMeSProperty() {
        return useNAMeS;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if Qualification skill and race skill should be bound together or not.
     */
    public boolean isFromLiveryNames() {
        return fromLiveryNames.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if Qualification skill and race skill should be bound together or not as a property.
     */
    public BooleanProperty fromLiveryNamesProperty() {
        return fromLiveryNames;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if Qualification skill and race skill should be bound together or not.
     */
    public boolean isBindQualiAndRaceSkills() {
        return bindQualiAndRaceSkills.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if Qualification skill and race skill should be bound together or not as a property.
     */
    public BooleanProperty bindQualiAndRaceSkillsProperty() {
        return bindQualiAndRaceSkills;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if TrackOverrides for ovals with reduced gap ar going to be generated or not.
     */
    public boolean isReduceGapsOnOvals() {
        return reduceGapsOnOvals.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if TrackOverrides for ovals with reduced gap ar going to be generated or not as a property.
     */
    public BooleanProperty reduceGapsOnOvalsProperty() {
        return reduceGapsOnOvals;
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether aggression is limited or not.
     */
    public boolean isLimitAggression() {
        return limitAggression.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether aggression is limited or not as a property.
     */
    public BooleanProperty limitAggressionProperty() {
        return limitAggression;
    }

    /**
     * Lightweight accessor method.
     * @return Number of drivers that determines how many drivers should be generated.
     */
    public int getnDrivers() {
        return nDrivers.get();
    }

    /**
     * Lightweight accessor method.
     * @return Number of drivers that determines how many drivers should be generated as a property.
     */
    public IntegerProperty nDriversProperty() {
        return nDrivers;
    }

    /**
     * Lightweight accessor method.
     * @return Aggression limit determines the maximum value that driver's aggression can have.
     */
    public double getAggressionLimit() {
        return aggressionLimit.get();
    }

    /**
     * Lightweight accessor method.
     * @return Aggression limit determines the maximum value that driver's aggression can have as a property.
     */
    public DoubleProperty aggressionLimitProperty() {
        return aggressionLimit;
    }

    /**
     * Lightweight accessor method.
     * @return Gap between Driver's qualification skill and racing skill if those values are bound.
     */
    public double getBoundSkillsGap() {
        return boundSkillsGap.get();
    }

    /**
     * Lightweight accessor method.
     * @return Gap between Driver's qualification skill and racing skill if those values are bound as a property.
     */
    public DoubleProperty boundSkillsGapProperty() {
        return boundSkillsGap;
    }
}