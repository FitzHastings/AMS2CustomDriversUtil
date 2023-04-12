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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Represents a reference Library of Vehicle Classes.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
public class VehicleClassLibrary {
    /**
     * List of vehicle classes contained in the library.
     */
    private ObservableList<VehicleClass> vehicleClasses = FXCollections.observableArrayList();

    /**
     * Lightweight Accessor Method
     * @return List of vehicle classes contained in the library.
     */
    @XmlElementWrapper(name = "vehicle_classes")
    public ObservableList<VehicleClass> getVehicleClasses() {
        return vehicleClasses;
    }

    /**
     * Lightweight Mutator Method
     * @param vehicleClasses List of vehicle classes contained in the library.
     */
    public void setVehicleClasses(ObservableList<VehicleClass> vehicleClasses) {
        this.vehicleClasses = vehicleClasses;
    }
}
