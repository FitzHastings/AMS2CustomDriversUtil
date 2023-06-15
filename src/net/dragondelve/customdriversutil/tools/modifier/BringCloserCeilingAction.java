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
 * Action that reduces the gap in properties by a percent value.
 * This action brings all values closer to the ceiling.
 */
public class BringCloserCeilingAction extends FloorCeilingAction {

    /**
     * Value from 0.0 to 1.0 that is used as a percentage value of modification.
     */
    private final double modificationValue;

    /**
     * Creates a new instance of BringCloserCeilingAction.
     * @param modificationValue Value from 0.0 to 1.0 that is used as a percentage value of modification.
     */
    public BringCloserCeilingAction(double modificationValue) {
        this.modificationValue = modificationValue;
    }

    /**
     * Brings the value closer to the ceiling by a percent modification value.
     * @param driverProperty value to be modified.
     * @return modified value.
     */
    @Override
    public double performAction(double driverProperty) {
        double delta = ceiling - floor;
        return  driverProperty + ((ceiling - driverProperty) / delta) * (delta * modificationValue * 0.5);
    }
}
