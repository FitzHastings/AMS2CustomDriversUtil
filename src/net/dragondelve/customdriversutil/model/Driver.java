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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a model of a driver with their track overrides included as described by AMS2 Developers here:
 * <a href="https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/">Reiza AMS2Forums</a>
 * Stores Livery Name that is shared with its track specific overrides.
 */
public final class Driver extends DriverBase {
    /**
     * In game name of the livery. Ths value determines to which car the custom AI values are going to be applied.
     */
    private final StringProperty liveryName = new SimpleStringProperty();

    /**
     * Points that the driver has This value is used to determine the driver's skill level when generating a grid with
     * a table generator.
     */
    private final IntegerProperty points = new SimpleIntegerProperty();

    /**
     * List of all track specific overrides that this driver has.
     * Track specific overrides are applied on specific tracks and can override a variety of different values
     * including a driver's name.
     */
    private final ObservableList<TrackOverride> trackOverrides = FXCollections.observableArrayList();

    /**
     * Lightweight accessor method.
     *
     * @return In game name of the livery.
     */
    public String getLiveryName() {
        return liveryName.get();
    }

    /**
     * Lightweight accessor method.
     *
     * @return In game name of the livery as a property.
     */
    public StringProperty liveryNameProperty() {
        return liveryName;
    }

    /**
     * Lightweight accessor method.
     *
     * @return List of all track specific overrides.
     */
    public ObservableList<TrackOverride> getTrackOverrides() {
        return trackOverrides;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Points that the driver has.
     */
    public int getPoints() {
        return points.get();
    }

    /**
     * Lightweight accessor method.
     *
     * @return Points that the driver has as a property.
     */
    public IntegerProperty pointsProperty() {
        return points;
    }
}
