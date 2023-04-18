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

import net.dragondelve.customdriversutil.model.Grid;

import java.io.File;

/**
 * Exports a given Grid to a File or to a String.
 */
public interface GridExporter {
    /**
     * Exports the given grid to a given file.
     * @param grid Grid to be exported.
     * @param file File to which the grid is to be exported.
     */
    void exportToFile(Grid grid, File file);

    /**
     * Marshals a given Grid to a String.
     * @param grid Grid to be serialized.
     * @return Serialized Grid in an AMS2 Format.
     */
    String exportToString(Grid grid);
}
