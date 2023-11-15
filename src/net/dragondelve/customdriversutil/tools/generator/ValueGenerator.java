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

package net.dragondelve.customdriversutil.tools.generator;

/**
 * Generates values for each of the double parameters of the driver ranging from 0 to 1, or a floor and ceiling values
 * that are passed to it.
 */
public interface ValueGenerator {
    /**
     * Informs the generator that you're done generating values for the current driver. You don't need to call it on
     * your first driver.
     */
    void nexDriver();

    /**
     * Generates a new value according to the ValueGenerator's rules.
     *
     * @return new value between 0 and 1.0 or floor and ceiling values if those have been provided to the generator.
     */
    double nextValue();

    /**
     * Should be a lightweight mutator method.
     *
     * @param floor   Minimum value for any value generated by this generator cannot exceed 1.0 and can't be below 0.
     * @param ceiling Maximym value generated by this generator. Has to be positive and cannot exceed 1.0.
     */
    void setLimits(double floor, double ceiling);
}
