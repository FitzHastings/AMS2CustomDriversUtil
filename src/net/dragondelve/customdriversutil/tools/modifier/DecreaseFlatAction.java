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
 * Decreases all values by a flat amount. If the resulting value is smaller than 0.0 it will use 0.0.
 */
public class DecreaseFlatAction implements ModifierAction {
    /**
     * A flat amount by which all properties of all drivers in grid should be decreased.
     */
    private final double decreaseAmount;

    /**
     * Creates a new instance of DecreaseFlatAction
     *
     * @param decreaseAmount A flat amount by which all properties of all drivers in grid should be decreased.
     */
    public DecreaseFlatAction(double decreaseAmount) {
        this.decreaseAmount = decreaseAmount;
    }

    /**
     * Decreases a given driver's property by a fixed amount.
     *
     * @param driverProperty value to be modified.
     * @return modified value. If modified value is smaller than 0.0 then it returns 0.0 instead.
     */
    @Override
    public double performAction(double driverProperty) {
        return Math.max(driverProperty - decreaseAmount, 0.0);
    }
}
