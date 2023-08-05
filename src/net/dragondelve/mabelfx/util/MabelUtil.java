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

package net.dragondelve.mabelfx.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * MabelFX's Utility class. Contains some useful stuff
 */
public final class MabelUtil {
    public static final Logger DEFAULT_LOGGER = Logger.getLogger("MabelFX");

    public static final String LIST_TO_LIST_FXML_PATHNAME = "fxml/mabelfx/ListToList.fxml";

    public static final URL LIST_TO_LIST_FXML_URL = toURL(LIST_TO_LIST_FXML_PATHNAME);

    public static final String DEFAULT_STYLESHEET = "css/Eraconstas.css";

    public static URL toURL(String url) {
        try {
            return new URL("file:"+url);
        } catch (MalformedURLException e) {
            DEFAULT_LOGGER.log(Level.SEVERE, "URL: '" + url + "could not be parsed as a URL");
            return null;
        }
    }

    private MabelUtil() {
        super();
    }
}
