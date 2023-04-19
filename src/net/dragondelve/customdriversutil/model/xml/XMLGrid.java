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

import net.dragondelve.customdriversutil.model.VehicleClass;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is fully annotated for use with JAXB.
 * Represents an entire grid of drivers and track overrides as described by AMS2 Developers here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
@XmlRootElement (name = "custom_ai_drivers")
final class XMLGrid {
    /**
     * List of all drivers and track overrides in the grid in their XML form.
     */
    private List<XMLDriver> xmlDrivers = new ArrayList<>();

    /**
     * Lightweight Accessor Method.
     * @return List of all drivers and track overrides in the grid in their XML form.
     */
    @XmlElement(name="driver")
    public List<XMLDriver> getXmlDrivers() {
        return xmlDrivers;
    }

    /**
     * Lightweight Mutator Method.
     * @param xmlDrivers List of all drivers and track overrides in the grid in their XML form.
     */
    public void setXmlDrivers(List<XMLDriver> xmlDrivers) {
        this.xmlDrivers = xmlDrivers;
    }

    /**
     * Generates a new VehicleClass for this XMLGrid. It includes all
     * @param name new VehicleClass's name.
     * @param xmlName new VehicleCLass's xmlName.
     * @return new VehicleClass with liveries from this grid.
     */
    public VehicleClass generateVehicleClass(String name, String xmlName) {
        VehicleClass vehicleClass = new VehicleClass();
        vehicleClass.setName(name);
        vehicleClass.setXmlName(xmlName);
        xmlDrivers.forEach(e->vehicleClass.getLiveryNames().add(e.getLiveryName()));
        return vehicleClass;
    }
}
