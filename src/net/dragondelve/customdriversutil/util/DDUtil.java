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

import java.util.logging.Logger;

/**
 * General Utility class that provides some useful static values.
 */
public abstract class DDUtil {
    public static Logger DEFAULT_LOGGER = Logger.getLogger("AMS2 Custom Drivers Util");

    public static String TRACK_EDITOR_FXML_PATHNAME = "fxml/editor/TrackEditor.fxml";
    public static String DRIVER_EDITOR_FXML_PATHNAME = "fxml/editor/DriverEditor.fxml";

    public static String MAIN_CSS_RESOURCE = "css/Eraconstas.css";
}
