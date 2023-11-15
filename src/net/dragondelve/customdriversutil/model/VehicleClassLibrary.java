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
import net.dragondelve.customdriversutil.util.DDUtil;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.NoSuchElementException;
import java.util.logging.Level;

/**
 * Represents a reference Library of Vehicle Classes.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "vehicle_class_library")
public class VehicleClassLibrary {
    /**
     * List of vehicle classes contained in the library.
     */
    private ObservableList<VehicleClass> vehicleClasses = FXCollections.observableArrayList();

    /**
     * Lightweight Accessor Method
     *
     * @return List of vehicle classes contained in the library.
     */
    @XmlElementWrapper(name = "vehicle_classes")
    @XmlElement(name = "vehicle_class")
    public ObservableList<VehicleClass> getVehicleClasses() {
        return vehicleClasses;
    }

    /**
     * Lightweight Mutator Method
     *
     * @param vehicleClasses List of vehicle classes contained in the library.
     */
    public void setVehicleClasses(ObservableList<VehicleClass> vehicleClasses) {
        this.vehicleClasses = vehicleClasses;
    }

    /**
     * Finds an unmodded grid with the XMLName that matches the xmlName provided.
     *
     * @param xmlName Name of the xmlFile of the vehicle class without the .xml
     * @return Vanilla vehicle class with xmlName that matches the xmlName provided if it's found or null if it isn't found.
     */
    public VehicleClass findVanillaVehicleClass(String xmlName) {
        try {
            return vehicleClasses.stream().filter(track -> track.getXmlName().equals(xmlName) && !track.isModded()).findFirst().get();
        } catch (NoSuchElementException e) {
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Trying to find vehicle class with xml name '" + xmlName + "' in the library but no such track found");
            return null;
        }
    }
}
