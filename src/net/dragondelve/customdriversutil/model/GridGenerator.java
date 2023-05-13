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

import java.util.Random;

public class GridGenerator {
    VehicleClass vehicleClass;

    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public Grid generateNewGrid() {
        if (vehicleClass == null)
            return null;
        Grid grid = new Grid();
        vehicleClass.getLiveryNames().forEach(liveryName->{
                Driver driver = new Driver();
                driver.nameProperty().set("drv2 "+liveryName.substring(liveryName.length()-8));
                driver.countryProperty().set("USA");
                driver.liveryNameProperty().set(liveryName);
                GridGenerator.randomizeDriver(driver);
                GridGenerator.setOverrideAll(driver, true);
                grid.getDrivers().add(driver);
        });

        return grid;
    }

    public static void setOverrideAll(Driver driver, boolean overrideAll) {
        driver.overrideNameProperty().set(overrideAll);
        driver.overrideCountryProperty().set(overrideAll);
        driver.overrideRaceSkillProperty().set(overrideAll);
        driver.overrideQualifyingSkillProperty().set(overrideAll);
        driver.overrideAggressionProperty().set(overrideAll);
        driver.overrideDefendingProperty().set(overrideAll);
        driver.overrideStaminaProperty().set(overrideAll);
        driver.overrideConsistencyProperty().set(overrideAll);
        driver.overrideStartReactionsProperty().set(overrideAll);
        driver.overrideWetSkillProperty().set(overrideAll);
        driver.overrideTyreManagementProperty().set(overrideAll);
        driver.overrideFuelManagementProperty().set(overrideAll);
        driver.overrideBlueFlagConcedingProperty().set(overrideAll);
        driver.overrideWeatherTyreChangeProperty().set(overrideAll);
        driver.overrideAvoidanceOfMistakesProperty().set(overrideAll);
        driver.overrideAvoidanceOfForcedMistakesProperty().set(overrideAll);
        driver.overrideVehicleReliabilityProperty().set(overrideAll);
    }
    public static void randomizeDriver(DriverBase driver) {
        Random random = new Random();
        driver.raceSkillProperty().set(random.nextDouble());
        driver.qualifyingSkillProperty().set(random.nextDouble());
        driver.aggressionProperty().set(random.nextDouble());
        driver.defendingProperty().set(random.nextDouble());
        driver.staminaProperty().set(random.nextDouble());
        driver.consistencyProperty().set(random.nextDouble());
        driver.startReactionsProperty().set(random.nextDouble());
        driver.wetSkillProperty().set(random.nextDouble());
        driver.tyreManagementProperty().set(random.nextDouble());
        driver.fuelManagementProperty().set(random.nextDouble());
        driver.blueFlagConcedingProperty().set(random.nextDouble());
        driver.weatherTyreChangeProperty().set(random.nextDouble());
        driver.avoidanceOfMistakesProperty().set(random.nextDouble());
        driver.avoidanceOfForcedMistakesProperty().set(random.nextDouble());
        driver.vehicleReliabilityProperty().set(random.nextDouble());
    }
}
