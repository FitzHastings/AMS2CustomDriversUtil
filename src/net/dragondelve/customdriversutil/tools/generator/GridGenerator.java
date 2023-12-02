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

import javafx.collections.ObservableList;
import net.dragondelve.customdriversutil.CustomDriverUtilMain;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.model.TrackOverride;
import net.dragondelve.customdriversutil.model.xml.XMLGridImporter;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.util.NoSuchElementException;
import java.util.logging.Level;

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
     * not contain any values as ValueGenerator is not provided.
     *
     * @param settings Settings that dictate the desired grid that is generated when the generateGrid method is called.
     */
    public GridGenerator(GeneratorSettings settings) {
        this.settings = settings;
        this.generator = null;
    }

    /**
     * Creates a new instance of GridGenerator.
     *
     * @param settings  Settings that dictate the desired grid that is generated when the generateGrid method is called.
     * @param generator ValueGenerator used in generation of values for each driver in the grid.
     */
    public GridGenerator(GeneratorSettings settings, ValueGenerator generator) {
        this.settings = settings;
        this.generator = generator;
    }

    /**
     * Generates a new Grid for the class set in GeneratorSettings using the ValueGenerator if one is provided.
     *
     * @return Newly generated grid.
     */
    public Grid generateNewGrid() {
        Grid grid = new Grid();
        grid.setVehicleClass(settings.getVehicleClass());
        int i = 0;
        Grid namesSource = null;
        boolean noNames = false;
        if (settings.isUseNAMeS()) {
            namesSource = (new XMLGridImporter().importFromStream(CustomDriverUtilMain.class.getClassLoader().getResourceAsStream("NAMeS/" + settings.getVehicleClass().getXmlName() + ".xml")));
        }
        while (i < settings.getnDrivers()) {
            Driver driver = new Driver();
            driver.setOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultDriverFlags());

            if (settings.isFromLiveryNames()) {
                String liveryName = driver.getLiveryName();
                driver.nameProperty().set("drv" + (i + 1) + liveryName.substring(liveryName.length() - 8));
                driver.countryProperty().set("GBR");
                driver.liveryNameProperty().set(settings.getVehicleClass().getLiveryNames().get(i));
            } else if (settings.isUseNAMeS()) {
                if (namesSource != null) {
                    try {
                        Driver name = namesSource.getDrivers().filtered(d -> d.getLiveryName().equals(driver.getLiveryName())).stream().findFirst().get();
                        driver.nameProperty().set(name.getName());
                        driver.countryProperty().set(name.getCountry());
                        driver.liveryNameProperty().set(settings.getVehicleClass().getLiveryNames().get(i));
                    } catch (NoSuchElementException e) {
                        DDUtil.DEFAULT_LOGGER.log(Level.SEVERE, "No such Livery in NAMeS: " + driver.getLiveryName());
                        driver.countryProperty().set("GBR");
                    }
                } else {
                    driver.countryProperty().set("GBR");
                }

            } else {
                noNames = true;
                driver.countryProperty().set("GBR");
            }

            if (generator != null) {
                driver.qualifyingSkillProperty().set(generator.nextValue());
                if (settings.isBindQualiAndRaceSkills())
                    driver.raceSkillProperty().set(Math.max(driver.getQualifyingSkill() - settings.getBoundSkillsGap(), 0.0));
                else
                    driver.raceSkillProperty().set(generator.nextValue());

                if (settings.isLimitAggression())
                    driver.aggressionProperty().setValue(Math.min(generator.nextValue(), settings.getAggressionLimit()));
                else
                    driver.aggressionProperty().setValue(generator.nextValue());

                if (noNames)
                    driver.nameProperty().set(generator.getName());
                driver.defendingProperty().set(generator.nextValue());
                driver.staminaProperty().set(generator.nextValue());
                driver.consistencyProperty().set(generator.nextValue());
                driver.startReactionsProperty().set(generator.nextValue());
                driver.wetSkillProperty().set(generator.nextValue());
                driver.tyreManagementProperty().set(generator.nextValue());
                driver.fuelManagementProperty().set(generator.nextValue());
                driver.blueFlagConcedingProperty().set(1.0 - generator.nextValue());
                driver.weatherTyreChangeProperty().set(generator.nextValue());
                driver.avoidanceOfMistakesProperty().set(generator.nextValue());
                driver.avoidanceOfForcedMistakesProperty().set(generator.nextValue());
                driver.vehicleReliabilityProperty().set(generator.nextValue());

                generator.nexDriver();
            }

            grid.getDrivers().add(driver);
            i++;
        }

        if (settings.isReduceGapsOnOvals()) {
            double minRaceSkill = 1.0;
            double maxRaceSkill = 0.0;

            for (Driver driver : grid.getDrivers()) {
                minRaceSkill = Math.min(minRaceSkill, driver.getRaceSkill());
                maxRaceSkill = Math.max(maxRaceSkill, driver.getRaceSkill());
            }

            double delta = maxRaceSkill - minRaceSkill;

            ObservableList<Track> ovals = LibraryManager.getInstance().getTrackLibrary().getTracks().filtered(Track::isOval);

            for (Driver driver : grid.getDrivers()) {
                TrackOverride trackOverride = new TrackOverride();
                trackOverride.setOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultTrackOverrideFlags());
                double newRaceSkill = ((maxRaceSkill - driver.getRaceSkill()) / delta) * (delta * 0.5) + driver.getRaceSkill();
                trackOverride.getOverrideFlags().overrideRaceSkillProperty().set(true);
                trackOverride.getOverrideFlags().overrideQualifyingSkillProperty().set(true);
                trackOverride.raceSkillProperty().set(newRaceSkill);
                trackOverride.qualifyingSkillProperty().set(newRaceSkill);
                trackOverride.getTrack().addAll(ovals);

                driver.getTrackOverrides().add(trackOverride);
            }
        }
        return grid;
    }
}