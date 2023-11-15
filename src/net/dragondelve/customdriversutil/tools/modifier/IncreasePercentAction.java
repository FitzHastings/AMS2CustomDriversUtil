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

/**
 * Increases all values by a certain percentage of the original amount. If the resulting value exceeds 1.0 it will use 1.0.
 */
public class IncreasePercentAction implements ModifierAction {

    /**
     * Value from 0.0 to 1.0 that is used as a percentage value of modification.
     */
    private final double percentValue;

    /**
     * Creates new instance of IncreasePercentAction
     *
     * @param percentValue Value from 0.0 to 1.0 that is used as a percentage value of modification.
     */
    public IncreasePercentAction(double percentValue) {
        this.percentValue = percentValue;
    }

    /**
     * Increases a given driver's property by a percent value.
     *
     * @param driverProperty value to be modified.
     * @return Modified value. If modified value exceeds 1.0 then it returns 1.0 instead.
     */
    @Override
    public double performAction(double driverProperty) {
        return Math.min(driverProperty + driverProperty * percentValue, 1.0);
    }
}
