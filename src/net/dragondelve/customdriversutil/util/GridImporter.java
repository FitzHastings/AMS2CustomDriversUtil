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

package net.dragondelve.customdriversutil.util;

import net.dragondelve.customdriversutil.model.Grid;

import java.io.File;

/**
 * Imports a Grid from a File or a String.
 */
public interface GridImporter {
    /**
     * Imports a given string as a grid
     * This will not set the correct Class for the grid. This should be handled elsewhere.
     * @param source Source String.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    Grid importThis(String source);

    /**
     * Imports a grid from a given File.
     * This will not set the correct Class for the grid. This should be handled elsewhere.
     * @param file a file that contains the Grid.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    Grid importFromFile(File file);
}
