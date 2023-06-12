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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an in game vehicle class.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "vehicle_class")
public final class VehicleClass {
    /**
     * Vehicle Class name that is human-readable, it's used for display purposes.
     */
    private final StringProperty name = new SimpleStringProperty();

    /**
     * Vehicle Class name that is used in the XML when exporting.
     */
    private final StringProperty xmlName = new SimpleStringProperty();

    /**
     * Determines whether this vehicle class is a vanilla vehicle class or a modded vehicle class.
     */
    private final BooleanProperty isModded = new SimpleBooleanProperty();

    /**
     * Full list of liveries available for this class.
     */
    private ObservableList<String> liveryNames = FXCollections.observableArrayList();

    /**
     * Lightweight accessor method.
     * @return Vehicle Class name that is human-readable.
     */
    @XmlAttribute(name = "name")
    public String getName() {
        return name.get();
    }

    /**
     * Lightweight accessor method.
     * @return Vehicle Class name that is human-readable as a property
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Lightweight mutator method.
     * @param name Vehicle Class name that is human-readable
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Lightweight accessor method.
     * @return Vehicle Class name that is used in the XML when exporting.
     */
    @XmlAttribute(name = "xml_name")
    public String getXmlName() {
        return xmlName.get();
    }

    /**
     * Lightweight accessor method.
     * @return Vehicle Class name that is used in the XML when exporting as a property.
     */
    public StringProperty xmlNameProperty() {
        return xmlName;
    }

    /**
     * Lightweight mutator method.
     * @param xmlName Vehicle Class name that is used in the XML when exporting.
     */
    public void setXmlName(String xmlName) {
        this.xmlName.set(xmlName);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether this vehicle class is a vanilla vehicle class or a modded vehicle class.
     */
    @XmlElement(name = "is_modded")
    public boolean isModded() {
        return isModded.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines whether this vehicle class is a vanilla vehicle class or a modded vehicle class, as a property.
     */
    public BooleanProperty isModdedProperty() {
        return this.isModded;
    }

    /**
     * Lightweight accessor method.
     * @param modded Flag that determines whether this vehicle class is a vanilla vehicle class or a modded vehicle class.
     */
    public void setModded(boolean modded) {
        this.isModded.set(modded);
    }

    /**
     * Lightweight accessor method.
     * @return Full list of liveries available for this class.
     */
    @XmlElementWrapper(name = "liveries")
    @XmlElement(name = "livery")
    public ObservableList<String> getLiveryNames() {
        return liveryNames;
    }

    /**
     * Lightweight mutator method.
     * @param liveryNames Full list of liveries available for this class.
     */
    public void setLiveryNames(ObservableList<String> liveryNames) {
        this.liveryNames = liveryNames;
    }

    /**
     * Converts vehicle class to String for visual representation of the object.
     * @return Human-readable name of the vehicle class.
     */
    @Override
    public String toString() {
        return name.get();
    }
}