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
import net.dragondelve.customdriversutil.util.GridExporter;

import java.io.File;

/**
 * Responsible for the conversion between this program's data model and  the AMS2 XML Custom Driver storage method.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
public class XMLGridExporter implements GridExporter {
    /**
     * Exports the given grid to a given file using JAXB. It uses the formatting described in
     * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
     * @param grid Grid to be exported in an AMS2 XML format.
     * @param file File to which the grid is to be exported. Should preferably end with .xml.
     */
    @Override
    public void exportToFile(Grid grid, File file) {

    }

    /**
     * Marshals a given Grid to a string using JAXB. It uses the formatting described in
     * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
     * @param grid Grid to be serialized.
     * @return Serialized Grid in an AMS2 Format.
     */
    @Override
    public String exportToString(Grid grid) {
        return null;
    }
}
