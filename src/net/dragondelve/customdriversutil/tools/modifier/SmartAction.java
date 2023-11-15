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
// limitations under the License.F

package net.dragondelve.customdriversutil.tools.modifier;

import java.util.List;

/**
 * Performs a particular action on values that are passed to it one by one returning the new value.
 * Modifier action that requires a pass of all values that
 */
public interface SmartAction extends ModifierAction {
    /**
     * analyzes the values that are going to be modified by the SmartAction to adjust internal values.
     *
     * @param modifiedValues List of values that are going to be modified by this action.
     */
    void analyzeValues(List<Double> modifiedValues);
}
