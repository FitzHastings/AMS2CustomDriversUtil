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

/**
 * Full Grid of Drivers stores the specific class that has been assigned to the grid.
 */
public final class Grid extends DriverLibrary {
    /**
     * Class of cars that are driven by the grid.
     */
    private VehicleClass vehicleClass = new VehicleClass();

    /**
     * Lightweight accessor method.
     * @return Class of cars that are driven by the grid.
     */
    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    /**
     * Lightweight mutator method.
     * @param vehicleClass Class of cars that are driven by the grid.
     */
    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }
}
