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

package net.dragondelve.customdriversutil.util;

import net.dragondelve.customdriversutil.model.OverrideFlags;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Configuration file holds all possible configuration states.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "config")
public class Configuration {
    /**
     * Pathname to the last imported TrackLibrary.
     */
    private String trackLibraryPathname;

    /**
     * Pathname to the last imported VehicleClassLibrary
     */
    private String vehicleClassLibraryPathname;

    /**
     * Default override flags that are set to every new Driver.
     */
    private OverrideFlags defaultDriverFlags;

    /**
     * Default override flags that are set to every new TrackOverride.
     */
    private OverrideFlags defaultTrackOverrideFlags;

    /**
     * Lightweight accessor method.
     * @return Pathname to the last imported TrackLibrary.
     */
    @XmlElement (name = "track_library")
    public String getTrackLibraryPathname() {
        return trackLibraryPathname;
    }

    /**
     * Lightweight mutator method.
     * @param trackLibraryPathname pathname to the last imported TrackLibrary.
     */
    public void setTrackLibraryPathname(String trackLibraryPathname) {
        this.trackLibraryPathname = trackLibraryPathname;
    }

    /**
     * Lightweight accessor method.
     * @return Pathname to the last imported VehicleClassLibrary.
     */
    @XmlElement (name = "vehicle_class_library")
    public String getVehicleClassLibraryPathname() {
        return vehicleClassLibraryPathname;
    }

    /**
     * Lightweight mutator method.
     * @param vehicleClassLibraryPathname Pathname to the last imported VehicleClassLibrary.
     */
    public void setVehicleClassLibraryPathname(String vehicleClassLibraryPathname) {
        this.vehicleClassLibraryPathname = vehicleClassLibraryPathname;
    }

    /**
     * Lightweight accessor method.
     * @return Default override flags that are set to every new Driver.
     */
    @XmlElement(name = "default_driver_flags")
    public OverrideFlags getDefaultDriverFlags() {
        return defaultDriverFlags;
    }

    /**
     * Lightweight mutator method.
     * @param defaultDriverFlags Default override flags that are set to every new Driver.
     */
    public void setDefaultDriverFlags(OverrideFlags defaultDriverFlags) {
        this.defaultDriverFlags = defaultDriverFlags;
    }

    /**
     * Lightweight accessor method.
     * @return Default override flags that are set to every new TrackOverride.
     */
    @XmlElement (name = "default_track_override_flags")
    public OverrideFlags getDefaultTrackOverrideFlags() {
        return defaultTrackOverrideFlags;
    }

    /**
     * Lightweight mutator method.
     * @param defaultTrackOverrideFlags Default override flags that are set to every new TrackOverride.
     */
    public void setDefaultTrackOverrideFlags(OverrideFlags defaultTrackOverrideFlags) {
        this.defaultTrackOverrideFlags = defaultTrackOverrideFlags;
    }
}
