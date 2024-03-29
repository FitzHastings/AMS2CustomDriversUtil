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
     * Pathname to the last imported DriverLibrary
     */
    private String driverLibraryPathname;

    /**
     * Default override flags that are set to every new Driver.
     */
    private OverrideFlags defaultDriverFlags;

    /**
     * Default override flags that are set to every new TrackOverride.
     */
    private OverrideFlags defaultTrackOverrideFlags;

    /**
     * URL to which the user goes when pressing on the manual update button.
     */
    private String updateURL;

    /**
     * Flag that determines if the user prefers to choose the livery or to type the livery's name into the textField.
     */
    private boolean chooseLivery = false;

    /**
     * Flag that determines if the user prefers to skip the welcome screen of the Custom Driver Utility.
     */
    private boolean skipWelcomeScreen = false;

    /**
     * Flag that determines if during export phase the generated values should be rounded or not.
     */
    private boolean roundGeneratedValues = true;

    /**
     * Value that determines how many decimal places should be kept for each property during Grid exporting.
     */
    private int roundingDecimalPlaces = 2;

    /**
     * Lightweight accessor method.
     *
     * @return Pathname to the last imported TrackLibrary.
     */
    @XmlElement(name = "track_library")
    public String getTrackLibraryPathname() {
        return trackLibraryPathname;
    }

    /**
     * Lightweight mutator method.
     *
     * @param trackLibraryPathname pathname to the last imported TrackLibrary.
     */
    public void setTrackLibraryPathname(String trackLibraryPathname) {
        this.trackLibraryPathname = trackLibraryPathname;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Pathname to the last imported VehicleClassLibrary.
     */
    @XmlElement(name = "vehicle_class_library")
    public String getVehicleClassLibraryPathname() {
        return vehicleClassLibraryPathname;
    }

    /**
     * Lightweight mutator method.
     *
     * @param vehicleClassLibraryPathname Pathname to the last imported VehicleClassLibrary.
     */
    public void setVehicleClassLibraryPathname(String vehicleClassLibraryPathname) {
        this.vehicleClassLibraryPathname = vehicleClassLibraryPathname;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Pathname to the last imported DriverLibrary
     */
    @XmlElement(name = "driver_library")
    public String getDriverLibraryPathname() {
        return driverLibraryPathname;
    }

    /**
     * Lightweight mutator method.
     *
     * @param driverLibraryPathname Pathname to the last imported DriverLibrary
     */
    public void setDriverLibraryPathname(String driverLibraryPathname) {
        this.driverLibraryPathname = driverLibraryPathname;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Default override flags that are set to every new Driver.
     */
    @XmlElement(name = "default_driver_flags")
    public OverrideFlags getDefaultDriverFlags() {
        return defaultDriverFlags;
    }

    /**
     * Lightweight mutator method.
     *
     * @param defaultDriverFlags Default override flags that are set to every new Driver.
     */
    public void setDefaultDriverFlags(OverrideFlags defaultDriverFlags) {
        this.defaultDriverFlags = defaultDriverFlags;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Default override flags that are set to every new TrackOverride.
     */
    @XmlElement(name = "default_track_override_flags")
    public OverrideFlags getDefaultTrackOverrideFlags() {
        return defaultTrackOverrideFlags;
    }

    /**
     * Lightweight mutator method.
     *
     * @param defaultTrackOverrideFlags Default override flags that are set to every new TrackOverride.
     */
    public void setDefaultTrackOverrideFlags(OverrideFlags defaultTrackOverrideFlags) {
        this.defaultTrackOverrideFlags = defaultTrackOverrideFlags;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Flag that determines if the user prefers to choose the livery or to type the livery's name into the textField.
     */
    @XmlElement(name = "choose_livery")
    public boolean isChooseLivery() {
        return chooseLivery;
    }

    /**
     * Lightweight mutator method.
     *
     * @param chooseLivery Flag that determines if the user prefers to choose the livery or to type the livery's name into the textField.
     */
    public void setChooseLivery(boolean chooseLivery) {
        this.chooseLivery = chooseLivery;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Flag that determines if the user prefers to skip the welcome screen of the Custom Driver Utility.
     */
    @XmlElement(name = "skip_welcome_screen")
    public boolean isSkipWelcomeScreen() {
        return skipWelcomeScreen;
    }

    /**
     * Lightweight mutator method.
     *
     * @param skipWelcomeScreen Flag that determines if the user prefers to skip the welcome screen of the Custom Driver Utility.
     */
    public void setSkipWelcomeScreen(boolean skipWelcomeScreen) {
        this.skipWelcomeScreen = skipWelcomeScreen;
    }

    /**
     * Lightweight accessor method.
     *
     * @return URL to which the user goes when pressing on the manual update button.
     */
    @XmlElement(name = "update_url")
    public String getUpdateURL() {
        return updateURL;
    }

    /**
     * Lightweight mutator method.
     *
     * @param updateURL URL to which the user goes when pressing on the manual update button.
     */
    public void setUpdateURL(String updateURL) {
        this.updateURL = updateURL;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Flag that determines if during export phase the generated values should be rounded or not.
     */
    @XmlElement(name = "override_generated_values")
    public boolean isRoundGeneratedValues() {
        return roundGeneratedValues;
    }

    /**
     * Lightweight mutator method.
     *
     * @param roundGeneratedValues Flag that determines if during export phase the generated values should be rounded or not.
     */
    public void setRoundGeneratedValues(boolean roundGeneratedValues) {
        this.roundGeneratedValues = roundGeneratedValues;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Value that determines how many decimal places should be kept for each property during Grid exporting.
     */
    public int getRoundingDecimalPlaces() {
        return roundingDecimalPlaces;
    }

    /**
     * Lightweight mutator method.
     *
     * @param roundingDecimalPlaces Value that determines how many decimal places should be kept for each property during Grid exporting.
     */
    public void setRoundingDecimalPlaces(int roundingDecimalPlaces) {
        this.roundingDecimalPlaces = roundingDecimalPlaces;
    }
}
