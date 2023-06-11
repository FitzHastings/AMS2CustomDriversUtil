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

package net.dragondelve.customdriversutil.model.generator;

import net.dragondelve.customdriversutil.util.DDUtil;

import java.util.Random;
import java.util.logging.Level;

/**
 * Generates values for each of the double parameters of the driver ranging from 0 to 1, or a floor and ceiling values
 * that are passed to it. This generator uses Random to generate all values. You don't have to call nextDriver() as it
 * does not change the nature of values provided with each driver. Instead, it just generates values based on pseudo
 * random numbers.
 */
public class RandomValueGenerator implements ValueGenerator {

    /**
     * Pseudo random generator used to generate all values.
     */
    private final Random random = new Random();

    /**
     * Minimum value that will be generated by the generator.
     */
    private double floor = 0.0;

    /**
     * Maximum value that will be generated by the generator.
     */
    private double ceiling = 1.0;

    /**
     * Default constructor. Does not set the seed to Random.
     */
    public RandomValueGenerator() {
        super();
    }

    /**
     * setsSeed of the pseudo random generator used to generate values
     * @param seed Initial seed passed to Random.
     */
    public RandomValueGenerator(long seed) {
        random.setSeed(seed);
    }

    /**
     * Does nothing, as it's not necessary to inform this generator that the values are now being generated for another
     * driver.
     */
    @Override
    public void nexDriver() {}

    /**
     * Generates a random double between floor and ceiling (0.0 and 1.0 by default)
     * @return New random value.
     */
    @Override
    public double nextValue() {
        return (ceiling-floor)*random.nextDouble()+floor;
    }

    /**
     * Semi Lightweight mutator method.
     * @param floor Minimum value for any value generated by this generator cannot exceed 1.0 and can't be below 0.
     * @param ceiling Maximum value generated by this generator. Has to be positive and cannot exceed 1.0.
     */
    @Override
    public void setLimits(double floor, double ceiling) {
        if (floor > ceiling)
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Random Value generator received floor that is larger than the ceiling, Limits will not be set");
        else if (floor < 0.0 || floor > 1.0)
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Random Value generator received floor '" + floor + "' that is an incorrect value");
        else if (ceiling < 0.0 || ceiling > 1.0)
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Random Value generator received ceiling '" + floor + "' that is an incorrect value");
        else {
            this.floor = floor;
            this.ceiling = ceiling;
        }
    }
}
