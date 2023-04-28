// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License";
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

package net.dragondelve.customdriversutil.model.xml;

import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.VehicleClassLibrary;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.GridImporter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;

/**
 * Responsible for the conversion between AMS2 XML Custom Driver storage method and this program's data model.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
public class XMLGridImporter implements GridImporter {

    /**
     * Imports a grid from a given File. If the File contains a valid XML formatted with AMS2 XML representation of Custom AI it will return a Grid.
     * This will not set the correct Class for the grid. This should be handled elsewhere.
     * @param file An XML file formatted with AMS2 XML representation of Custom AI.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    @Override
    public Grid importFromFile(File file) {
        XMLGrid xmlGrid = loadXMLGrid(file);
        if(xmlGrid != null)
            return fromXMLGrid(xmlGrid);
        else
            return null;
    }


    /**
     * Imports a grid from a given File and generates a new VehicleClass if a vehicle class is not present in the given
     * VehicleClassLibrary. This will set the VehicleClass of Grid.
     * @param file a file that contains the Grid formatted to XML.
     * @param library Vehicle Class Library that is meant to contain the newly loaded VehicleClass.
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    @Override
    public Grid importWithVehicleClass(File file, VehicleClassLibrary library) {
        XMLGrid xmlGrid = loadXMLGrid(file);
        if (xmlGrid != null) {
            VehicleClass vehicleClass = xmlGrid.generateVehicleClass(file.getName(), file.getName());
            //TODO: Check if VehicleClassLibrary Already contains a Vehicle Class with the same livery names.
            library.getVehicleClasses().add(vehicleClass);
            return fromXMLGrid(xmlGrid);
        }
        return null;
    }

    /**
     * Loads a Vehicle Class only from an XML Grid assigning all livery names to the grid setting its name and xmlname to
     * the name of the file.
     * @param file File that contains the xmlGrid.
     * @return new Vehicle Class that was used to create this XMLGrid.
     */
    public static VehicleClass importVehicleClassFromXMLGrid(File file) {
        XMLGrid xmlGrid = loadXMLGrid(file);
        if(xmlGrid != null)
            return xmlGrid.generateVehicleClass(file.getName(), file.getName());
        else
            return null;
    }

    /**
     * Coverts a given XMLGrid to a Grid.
     * @param xmlGrid given XML Grid.
     * @return new instance of Grid from XMLGrid.
     */
    private Grid fromXMLGrid(XMLGrid xmlGrid) {
        if(xmlGrid == null)
            return null;

        Grid grid = new Grid();
        xmlGrid.getXmlDrivers().forEach(e-> {
            if (e.getTracks() != null)
                return;

            Driver driver = new Driver();
            if (e.getLiveryName() != null) {
                driver.liveryNameProperty().set(e.getLiveryName());
                driver.overrideNameProperty().set(true);
            } else
                driver.overrideNameProperty().set(false);

            if (e.getName() != null) {
                driver.nameProperty().set(e.getName());
                driver.overrideNameProperty().set(true);
            } else
                driver.overrideNameProperty().set(false);

            if (e.getCountry() != null) {
                driver.countryProperty().set(e.getCountry());
                driver.overrideCountryProperty().set(true);
            } else
                driver.overrideCountryProperty().set(false);

            if (e.getRaceSkill() != null) {
                driver.raceSkillProperty().set(e.getRaceSkill());
                driver.overrideRaceSkillProperty().set(true);
            } else
                driver.overrideRaceSkillProperty().set(false);

            if (e.getQualifyingSkill() != null) {
                driver.qualifyingSkillProperty().set(e.getQualifyingSkill());
                driver.overrideQualifyingSkillProperty().set(true);
            } else
                driver.overrideQualifyingSkillProperty().set(false);

            if (e.getAggression() != null) {
                driver.aggressionProperty().set(e.getAggression());
                driver.overrideAggressionProperty().set(true);
            } else
                driver.overrideAggressionProperty().set(false);

            if (e.getDefending() != null) {
                driver.defendingProperty().set(e.getDefending());
                driver.overrideDefendingProperty().set(true);
            } else
                driver.overrideDefendingProperty().set(false);

            if (e.getStamina() != null) {
                driver.staminaProperty().set(e.getStamina());
                driver.overrideStaminaProperty().set(true);
            } else
                driver.overrideStaminaProperty().set(false);

            if (e.getConsistency() != null) {
                driver.consistencyProperty().set(e.getConsistency());
                driver.overrideConsistencyProperty().set(true);
            } else
                driver.overrideConsistencyProperty().set(false);

            if (e.getStartReactions() != null) {
                driver.startReactionsProperty().set(e.getStartReactions());
                driver.overrideStartReactionsProperty().set(true);
            } else
                driver.overrideStartReactionsProperty().set(false);

            if (e.getWetSkill() != null) {
                driver.wetSkillProperty().set(e.getWetSkill());
                driver.overrideWetSkillProperty().set(true);
            } else
                driver.overrideWetSkillProperty().set(false);

            if (e.getTyreManagement() != null) {
                driver.tyreManagementProperty().set(e.getTyreManagement());
                driver.overrideTyreManagementProperty().set(true);
            } else
                driver.overrideTyreManagementProperty().set(false);

            if (e.getFuelManagement() != null) {
                driver.fuelManagementProperty().set(e.getFuelManagement());
                driver.overrideFuelManagementProperty().set(true);
            } else
                driver.overrideFuelManagementProperty().set(false);

            if (e.getBlueFlagConceding() != null) {
                driver.blueFlagConcedingProperty().set(e.getBlueFlagConceding());
                driver.overrideBlueFlagConcedingProperty().set(true);
            } else
                driver.overrideBlueFlagConcedingProperty().set(false);

            if (e.getWeatherTyreChanges() != null) {
                driver.weatherTyreChangeProperty().set(e.getWeatherTyreChanges());
                driver.overrideWeatherTyreChangeProperty().set(true);
            } else
                driver.overrideWeatherTyreChangeProperty().set(false);

            if (e.getAvoidanceOfMistakes() != null) {
                driver.avoidanceOfMistakesProperty().set(e.getAvoidanceOfForcedMistakes());
                driver.overrideAvoidanceOfMistakesProperty().set(true);
            } else
                driver.overrideAvoidanceOfMistakesProperty().set(false);

            if (e.getAvoidanceOfForcedMistakes() != null) {
                driver.avoidanceOfForcedMistakesProperty().set(e.getAvoidanceOfForcedMistakes());
                driver.overrideAvoidanceOfForcedMistakesProperty().set(true);
            } else
                driver.overrideAvoidanceOfForcedMistakesProperty().set(false);

            if (e.getVehicleReliability() != null) {
                driver.vehicleReliabilityProperty().set(e.getVehicleReliability());
                driver.overrideVehicleReliabilityProperty().set(true);
            } else
                driver.overrideVehicleReliabilityProperty().set(false);

            grid.getDrivers().add(driver);
        });

        //TODO:Parse for Track Specific Overrides Here.

        return grid;
    }

    /**
     * Unmarshals xml grid from a given file.
     * @param file File that contains XMLGrid formatted with XML.
     * @return New instance of XMLGrid.
     */
    private static XMLGrid loadXMLGrid(File file) {
        try {
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML Grid loading initiated from path: " + file.getPath());
            JAXBContext context = JAXBContext.newInstance(XMLGrid.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLGrid xmlGrid = (XMLGrid) unmarshaller.unmarshal(file);
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML Grid loading successful from path: " + file.getPath());
            return xmlGrid;
        } catch (JAXBException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "XML Grid loading failed from path: " + file.getPath());
            return null;
        }
    }
}
