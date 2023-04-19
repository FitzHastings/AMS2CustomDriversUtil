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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * General Utility class that provides some useful static values for default file locations.
 * Non instatiable. in order to retrieve its only instance use getInstance.
 */
public class DDUtil {
    public static Logger DEFAULT_LOGGER = Logger.getLogger("AMS2 Custom Drivers Util");

    public static final String TRACK_EDITOR_FXML_PATHNAME = "fxml/editor/TrackEditor.fxml";
    public static final String VEHICLE_CLASS_EDITOR_FXML_PATHNAME = "fxml/editor/VehicleClassEditor.fxml";
    public static final String DRIVER_EDITOR_FXML_PATHNAME = "fxml/editor/DriverEditor.fxml";
    public static final String MAIN_WINDOW_FXML_PATHNAME = "fxml/CustomDriverUtilMain.fxml";

    public static final String TRACK_LIBRARY_DEFAULT_PATHNAME = "library/tracks/ams2_tracks_1.4.6.4.xml";
    public static final String VEHICLE_CLASS_LIBRARY_DEFAULT_PATHNAME = "library/vehicles/ams2_vehicles_1.4.6.4.xml";

    public static final String MAIN_CSS_RESOURCE = "css/Eraconstas.css";

    public URL TRACK_EDITOR_FXML_URL;
    public URL VEHICLE_CLASS_EDITOR_FXML_URL;
    public URL DRIVER_EDITOR_FXML_URL;
    public URL MAIN_WINDOW_FXML_URL;

    private final static DDUtil instance = new DDUtil();

    public static DDUtil getInstance() {
        return instance;
    }

    /**
     * Private Constructor. It is used to make this class non-instatiable. Attempts to form URLs to all FXML files.
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
            MAIN_WINDOW_FXML_URL = new URL("file:" + MAIN_WINDOW_FXML_PATHNAME);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
