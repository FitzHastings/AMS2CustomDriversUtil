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

import javafx.scene.image.Image;
import net.dragondelve.customdriversutil.CustomDriverUtilMain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * General Utility class that provides some useful static values for default file locations.
 * Non instantiable. in order to retrieve its only instance use getInstance.
 */
public class DDUtil {
    public static final Logger DEFAULT_LOGGER = Logger.getLogger("AMS2 Custom Drivers Util");

    public static final String DEFAULT_UPDATE = "https://www.racedepartment.com/downloads/ams2-custom-drivers-utility.61336/";

    public static final String TRACK_EDITOR_FXML_PATHNAME = "fxml/editor/TrackEditor.fxml";
    public static final String VEHICLE_CLASS_EDITOR_FXML_PATHNAME = "fxml/editor/VehicleClassEditor.fxml";
    public static final String DRIVER_EDITOR_FXML_PATHNAME = "fxml/editor/DriverEditor.fxml";
    public static final String OVERRIDE_FLAGS_EDITOR_FXML_PATHNAME = "fxml/editor/OverrideFlagsEditor.fxml";
    public static final String MAIN_WINDOW_FXML_PATHNAME = "fxml/CustomDriverUtilMain.fxml";
    public static final String DEFINE_TRACKS_STEP_FXML_PATHNAME = "fxml/DefineTracksStep.fxml";
    public static final String CONFIGURATION_SCREEN_FXML_PATHNAME = "fxml/ConfigurationScreen.fxml";
    public static final String NEW_GRID_WIZARD_FXML_PATHNAME = "fxml/NewGridWizard.fxml";
    public static final String CUSTOM_GRID_WELCOME_FXML_PATHNAME = "fxml/CustomGridWelcome.fxml";
    public static final String MASS_MODIFY_TOOL_FXML_PATHNAME = "fxml/MassModifyTool.fxml";

    public static final String TRACK_LIBRARY_DEFAULT_PATHNAME = "library/tracks/ams2_tracks_1.4.8.1.xml";
    public static final String VEHICLE_CLASS_LIBRARY_DEFAULT_PATHNAME = "library/vehicles/ams2_vehicles_1.4.8.1.xml";

    public static final String MAIN_CSS_RESOURCE = "css/Eraconstas.css";


    public static final Image MAIN_ICON_IMAGE = new Image(Objects.requireNonNull(CustomDriverUtilMain.class.getClassLoader().getResourceAsStream("ams2CDU.png")));

    public URL TRACK_EDITOR_FXML_URL;
    public URL VEHICLE_CLASS_EDITOR_FXML_URL;
    public URL DRIVER_EDITOR_FXML_URL;
    public URL OVERRIDE_FLAGS_EDITOR_FXML_URL;
    public URL MAIN_WINDOW_FXML_URL;
    public URL DEFINE_TRACKS_STEP_FXML_URL;
    public URL CONFIGURATION_SCREEN_FXML_URL;
    public URL NEW_GRID_WIZARD_FXML_URL;
    public URL CUSTOM_GRID_WELCOME_FXML_URL;
    public URL MASS_MODIFY_TOOL_FXML_URL;

    private final static DDUtil instance = new DDUtil();

    public static DDUtil getInstance() {
        return instance;
    }

    /**
     * Private Constructor. It is used to make this class non-instantiable. Attempts to form URLs to all FXML files.
     */
    private DDUtil() {
        super();

        try {
            TRACK_EDITOR_FXML_URL = new URL("file:" + TRACK_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            VEHICLE_CLASS_EDITOR_FXML_URL = new URL("file:" + VEHICLE_CLASS_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DRIVER_EDITOR_FXML_URL = new URL("file:" + DRIVER_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            OVERRIDE_FLAGS_EDITOR_FXML_URL = new URL("file:" + OVERRIDE_FLAGS_EDITOR_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            MAIN_WINDOW_FXML_URL = new URL("file:" + MAIN_WINDOW_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DEFINE_TRACKS_STEP_FXML_URL = new URL("file:" + DEFINE_TRACKS_STEP_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            CONFIGURATION_SCREEN_FXML_URL = new URL("file:" + CONFIGURATION_SCREEN_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            NEW_GRID_WIZARD_FXML_URL = new URL("file:" + NEW_GRID_WIZARD_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            CUSTOM_GRID_WELCOME_FXML_URL = new URL("file:" + CUSTOM_GRID_WELCOME_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            MASS_MODIFY_TOOL_FXML_URL = new URL("file:" + MASS_MODIFY_TOOL_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
