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
                driver.randomize();
                driver.getOverrideFlags().setOverrideAll(true);
                grid.getDrivers().add(driver);
        });

        return grid;
    }
}
