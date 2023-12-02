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

import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;

/**
 * ValueGenerator that generates values based on the table provided in the constructor.
 */
public class TableGenerator implements ValueGenerator {
    /**
     * Grid that is used to generate values.
     */
    private final Grid grid;
    /**
     * Minimum value for any parameter
     */
    private double floor = 0.0;
    /**
     * Maximum value for any parameter
     */
    private double ceiling = 1.0;
    /**
     * Determines if the next value asked is going to be Race Skill
     */
    private boolean isRaceSkill = true;
    /**
     * Minimum points for a driver in the grid
     */
    private final int minPoints;
    /**
     * Maximum points for a driver in the grid
     */
    private final int maxPoints;
    /**
     * Index of the current driver in the grid
     */
    private int currentDriver = 0;

    /**
     * Creates a new instance of TableGenerator
     *
     * @param pregeneratedGrid Grid that is used to generate values.
     */
    public TableGenerator(Grid pregeneratedGrid) {
        this.grid = pregeneratedGrid;
        grid.getDrivers().sort((o1, o2) -> o1.getPoints() > o2.getPoints() ? -1 : 1);
        this.minPoints = grid.getDrivers().get(grid.getDrivers().size() - 1).getPoints();
        this.maxPoints = grid.getDrivers().get(0).getPoints();
    }

    /**
     * Informs the generator that the next value asked is going to be for the next driver in the grid.
     */
    @Override
    public void nexDriver() {
        currentDriver++;
        isRaceSkill = true;
        if (currentDriver > grid.getDrivers().size())
            throw new IllegalStateException("No more drivers in the grid");
    }

    /**
     * Generates a new value for the current driver in the grid.
     * @return new value for the current driver in the grid.
     */
    @Override
    public double nextValue() {
        Driver current = grid.getDrivers().get(currentDriver);
        int pointDistance = maxPoints - minPoints;
        double range = ceiling - floor;
        double weight = (current.getPoints() - minPoints) / (double) pointDistance;
        if (isRaceSkill) {
            isRaceSkill = false;
            return floor + range * weight;
        } else return floor + range * weight - 0.1;
    }

    /**
     * Lightweight mutator method.
     * @param floor   Minimum value for any value generated by this generator cannot exceed 1.0 and can't be below 0.
     * @param ceiling Maximym value generated by this generator. Has to be positive and cannot exceed 1.0.
     */
    @Override
    public void setLimits(double floor, double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }

    /**
     * returns the default name of this driver if no name source was provided.
     * @return name of the current driver in the grid.
     */
    @Override
    public String getName() {
        return grid.getDrivers().get(currentDriver).getName();
    }
}