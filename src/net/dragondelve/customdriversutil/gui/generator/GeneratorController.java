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

package net.dragondelve.customdriversutil.gui.generator;

import net.dragondelve.customdriversutil.tools.generator.GridGenerator;

/**
 * Interface for controllers for fxml files that are used to generate grids.
 */
public interface GeneratorController {
    /**
     * Creates a new GridGenerator instance based on the settings provided by the user.
     * @return new GridGenerator instance.
     */
    GridGenerator createGridGenerator();

    /**
     * Checks if the settings provided by the user are good enough to generate a grid.
     * @return true if the settings are good enough to generate a grid.
     */
    boolean isGoodToGenerate();
}
