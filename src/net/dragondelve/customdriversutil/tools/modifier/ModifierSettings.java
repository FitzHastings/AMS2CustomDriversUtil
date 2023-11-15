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

package net.dragondelve.customdriversutil.tools.modifier;

import net.dragondelve.customdriversutil.model.DriverBase;
import net.dragondelve.customdriversutil.model.OverrideFlags;

import java.util.List;

/**
 * Encapsulates settings that can be passed to a GridModifier.
 */
public class ModifierSettings extends OverrideFlags {
    /**
     * List of drivers who need to be modified.
     */
    private final List<? extends DriverBase> grid;

    /**
     * Creates a new instance of ModifierSettings.
     *
     * @param grid List of drivers who need to be modified.
     */
    public ModifierSettings(List<? extends DriverBase> grid) {
        this.grid = grid;
    }

    /**
     * Lightweight accessor method.
     *
     * @return List of drivers who need to be modified.
     */
    public List<? extends DriverBase> getGrid() {
        return grid;
    }
}
