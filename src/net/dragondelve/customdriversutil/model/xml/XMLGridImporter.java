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

import net.dragondelve.customdriversutil.model.*;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.GridImporter;
import net.dragondelve.customdriversutil.util.LibraryManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * Responsible for the conversion between AMS2 XML Custom Driver storage method and this program's data model.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * <a href="https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/">Reiza Studios Forum</a>
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
        xmlGrid.getXmlDrivers().forEach(xmlDriver-> {
            if (xmlDriver.getTracks() != null)
                return;
            
            Driver driver = new Driver();
            
            if (xmlDriver.getLiveryName() != null)
                driver.liveryNameProperty().set(xmlDriver.getLiveryName());
            
            importBaseProperties(xmlDriver, driver);
            grid.getDrivers().add(driver);
        });

        xmlGrid.getXmlDrivers().forEach(xmlDriver -> {
            if(xmlDriver.getTracks() == null)
                return;

            TrackOverride override = new TrackOverride();
            List<String> stringTracks = Arrays.asList(xmlDriver.getTracks().split(","));
            List<Track> tracks = new ArrayList<>();
            stringTracks.forEach(stringTrack-> {
                Track track = LibraryManager.getInstance().getTrackLibrary().findTrackWithXmlName(stringTrack);
                if(track != null)
                    tracks.add(track);
                else
                    tracks.add(new Track(stringTrack, stringTrack));
            });

            override.getTrack().addAll(tracks);

            List<Driver> collect = grid.getDrivers().stream().filter(driver -> driver.liveryNameProperty().get().equals(xmlDriver.getLiveryName())).collect(Collectors.toList());

            importBaseProperties(xmlDriver, override);

            if(collect.size() == 1) {
                collect.get(0).getTrackOverrides().add(override);
            }
        });

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

    /**
     * Imports the base properties shared between the track specific overrides and driver overrides.
     * @param source Source XML driver, whose fields will be used to set the base properties.
     * @param target Target whose properties are going to be set. Should be either a Driver or a TrackOverride.
     */
    private void importBaseProperties(XMLDriver source, DriverBase target) {
        if (source.getName() != null) {
            target.nameProperty().set(source.getName());
            target.getOverrideFlags().overrideNameProperty().set(true);
        } else
            target.getOverrideFlags().overrideNameProperty().set(false);

        if (source.getCountry() != null) {
            target.countryProperty().set(source.getCountry());
            target.getOverrideFlags().overrideCountryProperty().set(true);
        } else
            target.getOverrideFlags().overrideCountryProperty().set(false);

        if (source.getRaceSkill() != null) {
            target.raceSkillProperty().set(source.getRaceSkill());
            target.getOverrideFlags().overrideRaceSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideRaceSkillProperty().set(false);

        if (source.getQualifyingSkill() != null) {
            target.qualifyingSkillProperty().set(source.getQualifyingSkill());
            target.getOverrideFlags().overrideQualifyingSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideQualifyingSkillProperty().set(false);

        if (source.getAggression() != null) {
            target.aggressionProperty().set(source.getAggression());
            target.getOverrideFlags().overrideAggressionProperty().set(true);
        } else
            target.getOverrideFlags().overrideAggressionProperty().set(false);

        if (source.getDefending() != null) {
            target.defendingProperty().set(source.getDefending());
            target.getOverrideFlags().overrideDefendingProperty().set(true);
        } else
            target.getOverrideFlags().overrideDefendingProperty().set(false);

        if (source.getStamina() != null) {
            target.staminaProperty().set(source.getStamina());
            target.getOverrideFlags().overrideStaminaProperty().set(true);
        } else
            target.getOverrideFlags().overrideStaminaProperty().set(false);

        if (source.getConsistency() != null) {
            target.consistencyProperty().set(source.getConsistency());
            target.getOverrideFlags().overrideConsistencyProperty().set(true);
        } else
            target.getOverrideFlags().overrideConsistencyProperty().set(false);

        if (source.getStartReactions() != null) {
            target.startReactionsProperty().set(source.getStartReactions());
            target.getOverrideFlags().overrideStartReactionsProperty().set(true);
        } else
            target.getOverrideFlags().overrideStartReactionsProperty().set(false);

        if (source.getWetSkill() != null) {
            target.wetSkillProperty().set(source.getWetSkill());
            target.getOverrideFlags().overrideWetSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideWetSkillProperty().set(false);

        if (source.getTyreManagement() != null) {
            target.tyreManagementProperty().set(source.getTyreManagement());
            target.getOverrideFlags().overrideTyreManagementProperty().set(true);
        } else
            target.getOverrideFlags().overrideTyreManagementProperty().set(false);

        if (source.getFuelManagement() != null) {
            target.fuelManagementProperty().set(source.getFuelManagement());
            target.getOverrideFlags().overrideFuelManagementProperty().set(true);
        } else
            target.getOverrideFlags().overrideFuelManagementProperty().set(false);

        if (source.getBlueFlagConceding() != null) {
            target.blueFlagConcedingProperty().set(source.getBlueFlagConceding());
            target.getOverrideFlags().overrideBlueFlagConcedingProperty().set(true);
        } else
            target.getOverrideFlags().overrideBlueFlagConcedingProperty().set(false);

        if (source.getWeatherTyreChanges() != null) {
            target.weatherTyreChangeProperty().set(source.getWeatherTyreChanges());
            target.getOverrideFlags().overrideWeatherTyreChangeProperty().set(true);
        } else
            target.getOverrideFlags().overrideWeatherTyreChangeProperty().set(false);

        if (source.getAvoidanceOfMistakes() != null) {
            target.avoidanceOfMistakesProperty().set(source.getAvoidanceOfForcedMistakes());
            target.getOverrideFlags().overrideAvoidanceOfMistakesProperty().set(true);
        } else
            target.getOverrideFlags().overrideAvoidanceOfMistakesProperty().set(false);

        if (source.getAvoidanceOfForcedMistakes() != null) {
            target.avoidanceOfForcedMistakesProperty().set(source.getAvoidanceOfForcedMistakes());
            target.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty().set(true);
        } else
            target.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty().set(false);

        if (source.getVehicleReliability() != null) {
            target.vehicleReliabilityProperty().set(source.getVehicleReliability());
            target.getOverrideFlags().overrideVehicleReliabilityProperty().set(true);
        } else
            target.getOverrideFlags().overrideVehicleReliabilityProperty().set(false);
    }
}
