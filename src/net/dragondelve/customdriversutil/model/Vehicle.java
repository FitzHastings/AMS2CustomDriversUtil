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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a vehicle in the game
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement (name = "vehicle")
public class Vehicle {

    /**
     * Human-readable name of the vehicle
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * Xml-name of the vehicle used in livery overrides
     */
    private final StringProperty xmlName = new SimpleStringProperty();

    /**
     * Lightweight accessor method.
     * @return Human-readable name of the vehicle
     */
    @XmlAttribute (name = "name")
    public String getName() {
        return name.get();
    }

    /**
     * Lightweight accessor method.
     * @return Human-readable name of the vehicle as a property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Lightweight mutator method.
     * @param name Human-readable name of the vehicle
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Lightweight accessor method.
     * @return Xml-name of the vehicle used in livery overrides
     */
    @XmlAttribute (name = "xml_name")
    public String getXmlName() {
        return xmlName.get();
    }

    /**
     * Lightweight accessor method.
     * @return Xml-name of the vehicle used in livery overrides as a property
     */
    public StringProperty xmlNameProperty() {
        return xmlName;
    }

    /**
     * Lightweight mutator method.
     * @param xmlName Xml-name of the vehicle used in livery overrides
     */
    public void setXmlName(String xmlName) {
        this.xmlName.set(xmlName);
    }
}
