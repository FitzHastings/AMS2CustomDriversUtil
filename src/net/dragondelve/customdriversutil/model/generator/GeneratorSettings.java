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
    private VehicleClass vehicleClass;

    private ValueGenerator valueGenerator;

    private final BooleanProperty useNAMeS = new SimpleBooleanProperty();

    private final BooleanProperty fromLiveryNames = new SimpleBooleanProperty();

    private final BooleanProperty bindQualiAndRaceSkills = new SimpleBooleanProperty();
    private final BooleanProperty reduceGapsOnOvals = new SimpleBooleanProperty();
    private final BooleanProperty limitAggression = new SimpleBooleanProperty();
    private final IntegerProperty nDrivers = new SimpleIntegerProperty();
    private final DoubleProperty minValue = new SimpleDoubleProperty();
    private final DoubleProperty maxValue = new SimpleDoubleProperty();
    private final DoubleProperty aggressionLimit = new SimpleDoubleProperty();
    private final DoubleProperty boundSkillsGap = new SimpleDoubleProperty();

    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public ValueGenerator getValueGenerator() {
        return valueGenerator;
    }

    public void setValueGenerator(ValueGenerator valueGenerator) {
        this.valueGenerator = valueGenerator;
    }

    public boolean isUseNAMeS() {
        return useNAMeS.get();
    }

    public BooleanProperty useNAMeSProperty() {
        return useNAMeS;
    }

    public boolean isFromLiveryNames() {
        return fromLiveryNames.get();
    }

    public BooleanProperty fromLiveryNamesProperty() {
        return fromLiveryNames;
    }

    public boolean isBindQualiAndRaceSkills() {
        return bindQualiAndRaceSkills.get();
    }

    public BooleanProperty bindQualiAndRaceSkillsProperty() {
        return bindQualiAndRaceSkills;
    }

    public boolean isReduceGapsOnOvals() {
        return reduceGapsOnOvals.get();
    }

    public BooleanProperty reduceGapsOnOvalsProperty() {
        return reduceGapsOnOvals;
    }

    public boolean isLimitAggression() {
        return limitAggression.get();
    }

    public BooleanProperty limitAggressionProperty() {
        return limitAggression;
    }

    public int getnDrivers() {
        return nDrivers.get();
    }

    public IntegerProperty nDriversProperty() {
        return nDrivers;
    }

    public double getMinValue() {
        return minValue.get();
    }

    public DoubleProperty minValueProperty() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue.get();
    }

    public DoubleProperty maxValueProperty() {
        return maxValue;
    }

    public double getAggressionLimit() {
        return aggressionLimit.get();
    }

    public DoubleProperty aggressionLimitProperty() {
        return aggressionLimit;
    }

    public double getBoundSkillsGap() {
        return boundSkillsGap.get();
    }

    public DoubleProperty boundSkillsGapProperty() {
        return boundSkillsGap;
    }
}
