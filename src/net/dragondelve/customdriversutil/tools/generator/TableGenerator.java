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

import net.dragondelve.customdriversutil.model.Grid;

public class TableGenerator implements ValueGenerator {

    private Grid grid;
    private double floor = 0.0;
    private double ceiling = 1.0;

    private int currentDriver = 0;

    public TableGenerator(Grid pregeneratedGrid) {
        this.grid = pregeneratedGrid;
        grid.getDrivers().sort((o1, o2) -> o1.getPoints() > o2.getPoints() ? -1 : 1);
    }
    @Override
    public void nexDriver() {
        currentDriver++;
        if (currentDriver >= grid.getDrivers().size())
            throw new IllegalStateException("No more drivers in the grid");
    }

    @Override
    public double nextValue() {
        return 0;
    }

    @Override
    public void setLimits(double floor, double ceiling) {
        this.floor = floor;
        this.ceiling = ceiling;
    }
}
