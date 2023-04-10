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

/**
 * Represents an in game track.
 * Stores all properties to allow an automatic generation of track specific overrides.
 */
public class Track {
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
     * Lightweight accessor method.
     * @return Track name that is human-readable, it's used for display purposes.
     */
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
     * Lightweight accessor method.
     * @return Track name that is used in the XML when exporting.
     */
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
     * Lightweight accessor method.
     * @return Flag that determines if the track is an oval track or a road course.
     */
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
}
