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

package net.dragondelve.customdriversutil.model.xml;

import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.util.GridImporter;

import java.io.File;

/**
 * Responsible for the conversion between AMS2 XML Custom Driver storage method and this program's data model.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
public class XMLGridImporter implements GridImporter {

    /**
     * Imports a given string as a grid. If the String is a valid XML formatted with AMS2 XML representation of Custom AI it will return a Grid.
     * This will not set the correct Class for the grid. This should be handled elsewhere.
     * @param source Source String. Should be an XML formatted with AMS2 XML representation of Custom AI.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    @Override
    public Grid importThis(String source) {
        return null;
    }

    /**
     * Imports a grid from a given File. If the File contains a valid XML formatted with AMS2 XML representation of Custom AI it will return a Grid.
     * This will not set the correct Class for the grid. This should be handled elsewhere.
     * @param file An XML file formatted with AMS2 XML representation of Custom AI.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    @Override
    public Grid importFromFile(File file) {
        return null;
    }
}
