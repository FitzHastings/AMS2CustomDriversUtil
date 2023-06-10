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

import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.util.Configurator;

/**
 * Grid Generator. Generates a new grid when the generateNewGrid() method is called. The grid is generated based on
 * GeneratorSettings and the ValueGenerator provided. If no value generator is provided in the constructor it will
 * generate a grid with empty values.
 */
public class GridGenerator {

    /**
     * GeneratorSettings that dictate the desired grid that is generated when the generateGrid method is called.
     */
    private final GeneratorSettings settings;

    /**
     * ValueGenerator used in generation of values for each driver in the grid.
     */
    private final ValueGenerator generator;

    /**
     * Creates a new instance of GridGenerator. If this constructor is used grids created with generateGrid() method will
     * not contain any values set as ValueGenerator is not provided.
     * @param settings Settings that dictate the desired grid that is generated when the generateGrid method is called.
     */
    public GridGenerator(GeneratorSettings settings) {
        this.settings = settings;
        this.generator = null;
    }

    /**
     * Creates a new instance of GridGenerator.
     * @param settings Settings that dictate the desired grid that is generated when the generateGrid method is called.
     * @param generator ValueGenerator used in generation of values for each driver in the grid.
     */
    public GridGenerator(GeneratorSettings settings, ValueGenerator generator) {
        this.settings = settings;
        this.generator = generator;
    }


    /**
     * Generates a new Grid for the class set in GeneratorSettings using the ValueGenerator if one is provided.
     * @return Newly generated grid.
     */
    public Grid generateNewGrid() {
        Grid grid = new Grid();
        int i = 0;
        while (i < settings.getnDrivers()) {
            Driver driver = new Driver();
            driver.setOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultDriverFlags());
            driver.liveryNameProperty().set(settings.getVehicleClass().getLiveryNames().get(i));

            if (settings.isFromLiveryNames()) {
                String liveryName = driver.getLiveryName();
                driver.nameProperty().set("drv"+ (i+1) + liveryName.substring(liveryName.length()-8));
            } else if (settings.isUseNAMeS()) {
                driver.nameProperty().set("Names!");
            }

            if(generator != null) {
                driver.raceSkillProperty().set(generator.nextValue());
                driver.qualifyingSkillProperty().set(generator.nextValue());
            }

            i++;
        }

//        if (settings.getVehicleClass() == null)
//            return null;
//        Grid grid = new Grid();
//        settings.getVehicleClass().getLiveryNames().forEach(liveryName->{
//                Driver driver = new Driver();
//                driver.nameProperty().set("drv2 "+liveryName.substring(liveryName.length()-8));
//                driver.countryProperty().set("USA");
//                driver.liveryNameProperty().set(liveryName);
//                driver.randomize();
//                driver.getOverrideFlags().setOverrideAll(true);
//                grid.getDrivers().add(driver);
//        });
//
//        return grid;
        return null;
    }
}