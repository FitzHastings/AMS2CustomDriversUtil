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

package net.dragondelve.customdriversutil.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents an in game track.
 * Stores all properties to allow an automatic generation of track specific overrides.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "track")
public final class Track {
    /**
     * Track name that is human-readable, it's used for display purposes.
     */
    private final StringProperty name = new SimpleStringProperty();
    /**
     * Track name that is used in the XML when exporting.
     */
    private final StringProperty xmlName = new SimpleStringProperty();
    /**
     * Flag that determines if the track is an oval track or a road course.
     * If set to true the track is an oval track.
     * If set to false the track is a road course.
     */
    private final BooleanProperty isOval = new SimpleBooleanProperty();

    /**
     * Default constructor does not provide default values
     */
    public Track() {
        super();
    }

    /**
     *
     * @param name Track name that is human-readable, it's used for display purposes.
     * @param xmlName Track name that is used in the XML when exporting.
     */
    public Track(String name, String xmlName) {
        this.name.set(name);
        this.xmlName.set(xmlName);
    }

    /**
     * Lightweight accessor method.
     * @return Track name that is human-readable, it's used for display purposes.
     */
    @XmlAttribute(name = "name")
    public String getName() {
        return name.get();
    }

    /**
     * Lightweight accessor method.
     * @return Track name that is human-readable as a property, it's used for display purposes.
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     * Lightweight mutator method.
     * @param name Track name that is human-readable, it's used for display purposes.
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * Lightweight accessor method.
     * @return Track name that is used in the XML when exporting.
     */
    @XmlAttribute(name = "xml_name")
    public String getXmlName() {
        return xmlName.get();
    }

    /**
     * Lightweight accessor method.
     * @return Track name that is used in the XML when exporting as a property.
     */
    public StringProperty xmlNameProperty() {
        return xmlName;
    }

    /**
     * Lightweight mutator method.
     * @param xmlName Track name that is used in the XML when exporting.
     */
    public void setXmlName(String xmlName) {
        this.xmlName.set(xmlName);
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if the track is an oval track or a road course.
     */
    @XmlElement(name = "is_oval")
    public boolean isOval() {
        return isOval.get();
    }

    /**
     * Lightweight accessor method.
     * @return Flag that determines if the track is an oval track or a road course as a property.
     */
    public BooleanProperty isOvalProperty() {
        return isOval;
    }

    /**
     * Lightweight mutator method.
     * @param isOval Flag that determines if the track is an oval track or a road course.
     */
    public void setOval(boolean isOval) {
        this.isOval.set(isOval);
    }
}
