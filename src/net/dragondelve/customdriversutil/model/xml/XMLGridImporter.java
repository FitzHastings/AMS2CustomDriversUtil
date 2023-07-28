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
import java.io.*;
import java.nio.charset.StandardCharsets;
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
        if (xmlGrid != null)
            return fromXMLGrid(xmlGrid);
        else
            return null;
    }

    /**
     * Imports a grid from a given input stream.
     * @param inputStream an input stream that contains the Grid in an xml format;
     * @return New instance of a Grid from the source, or null if the import has failed.
     */
    @Override
    public Grid importFromStream(InputStream inputStream) {
        XMLGrid xmlGrid = loadXMLGrid(inputStream);
        if (xmlGrid != null)
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
            VehicleClass vehicleClass = xmlGrid.generateVehicleClass(file.getName().substring(0, file.getName().length() - 4), file.getName().substring(0, file.getName().length() - 4));
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
        if (xmlGrid != null)
            return xmlGrid.generateVehicleClass(file.getName().substring(0, file.getName().length() - 4), file.getName().substring(0, file.getName().length() - 4));
        else
            return null;
    }

    /**
     * Coverts a given XMLGrid to a Grid.
     * @param xmlGrid given XML Grid.
     * @return new instance of Grid from XMLGrid.
     */
    private Grid fromXMLGrid(XMLGrid xmlGrid) {
        if (xmlGrid == null)
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
            if (xmlDriver.getTracks() == null)
                return;

            TrackOverride override = new TrackOverride();
            List<String> stringTracks = Arrays.asList(xmlDriver.getTracks().split(","));
            List<Track> tracks = new ArrayList<>();
            stringTracks.forEach(stringTrack-> {
                Track track = LibraryManager.getInstance().getTrackLibrary().findTrackWithXmlName(stringTrack);
                if (track != null)
                    tracks.add(track);
                else
                    tracks.add(new Track(stringTrack, stringTrack));
            });

            override.getTrack().addAll(tracks);

            List<Driver> collect = grid.getDrivers().stream().filter(driver -> driver.liveryNameProperty().get().equals(xmlDriver.getLiveryName())).collect(Collectors.toList());

            importBaseProperties(xmlDriver, override);

            if (collect.size() == 1) {
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
        return parseXMLGrid(preParseFile(file));
    }

    /**
     * Unmarshals xml grid from a given input stream.
     * @param stream inputStream that contains XMLGrid formatted with XML.
     * @return New instance of XMLGrid.
     */
    private static XMLGrid loadXMLGrid(InputStream stream) {
        return parseXMLGrid(preParseInputStream(stream));
    }

    /**
     * Parses the xml grid from a given pre-Parsed String containing the XML grid.
     * @param xml xml grid pre-parsed to remove weird discrepancies between AMS2's loader and JAXB unmarshaller
     * @return new Instance of XMLGrid loaded from the String.
     */
    private static XMLGrid parseXMLGrid(String xml) {
        try {
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML Grid loading initiated from String");
            JAXBContext context = JAXBContext.newInstance(XMLGrid.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLGrid xmlGrid = (XMLGrid) unmarshaller.unmarshal(new StringReader(xml));
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML Grid loading successful from String");
            return xmlGrid;
        } catch (JAXBException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "XML Grid loading failed from from String");
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
            target.raceSkillProperty().set(Double.parseDouble(source.getRaceSkill()));
            target.getOverrideFlags().overrideRaceSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideRaceSkillProperty().set(false);

        if (source.getQualifyingSkill() != null) {
            target.qualifyingSkillProperty().set(Double.parseDouble(source.getQualifyingSkill()));
            target.getOverrideFlags().overrideQualifyingSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideQualifyingSkillProperty().set(false);

        if (source.getAggression() != null) {
            target.aggressionProperty().set(Double.parseDouble(source.getAggression()));
            target.getOverrideFlags().overrideAggressionProperty().set(true);
        } else
            target.getOverrideFlags().overrideAggressionProperty().set(false);

        if (source.getDefending() != null) {
            target.defendingProperty().set(Double.parseDouble(source.getDefending()));
            target.getOverrideFlags().overrideDefendingProperty().set(true);
        } else
            target.getOverrideFlags().overrideDefendingProperty().set(false);

        if (source.getStamina() != null) {
            target.staminaProperty().set(Double.parseDouble(source.getStamina()));
            target.getOverrideFlags().overrideStaminaProperty().set(true);
        } else
            target.getOverrideFlags().overrideStaminaProperty().set(false);

        if (source.getConsistency() != null) {
            target.consistencyProperty().set(Double.parseDouble(source.getConsistency()));
            target.getOverrideFlags().overrideConsistencyProperty().set(true);
        } else
            target.getOverrideFlags().overrideConsistencyProperty().set(false);

        if (source.getStartReactions() != null) {
            target.startReactionsProperty().set(Double.parseDouble(source.getStartReactions()));
            target.getOverrideFlags().overrideStartReactionsProperty().set(true);
        } else
            target.getOverrideFlags().overrideStartReactionsProperty().set(false);

        if (source.getWetSkill() != null) {
            target.wetSkillProperty().set(Double.parseDouble(source.getWetSkill()));
            target.getOverrideFlags().overrideWetSkillProperty().set(true);
        } else
            target.getOverrideFlags().overrideWetSkillProperty().set(false);

        if (source.getTyreManagement() != null) {
            target.tyreManagementProperty().set(Double.parseDouble(source.getTyreManagement()));
            target.getOverrideFlags().overrideTyreManagementProperty().set(true);
        } else
            target.getOverrideFlags().overrideTyreManagementProperty().set(false);

        if (source.getFuelManagement() != null) {
            target.fuelManagementProperty().set(Double.parseDouble(source.getFuelManagement()));
            target.getOverrideFlags().overrideFuelManagementProperty().set(true);
        } else
            target.getOverrideFlags().overrideFuelManagementProperty().set(false);

        if (source.getBlueFlagConceding() != null) {
            target.blueFlagConcedingProperty().set(Double.parseDouble(source.getBlueFlagConceding()));
            target.getOverrideFlags().overrideBlueFlagConcedingProperty().set(true);
        } else
            target.getOverrideFlags().overrideBlueFlagConcedingProperty().set(false);

        if (source.getWeatherTyreChanges() != null) {
            target.weatherTyreChangeProperty().set(Double.parseDouble(source.getWeatherTyreChanges()));
            target.getOverrideFlags().overrideWeatherTyreChangeProperty().set(true);
        } else
            target.getOverrideFlags().overrideWeatherTyreChangeProperty().set(false);

        if (source.getAvoidanceOfMistakes() != null) {
            target.avoidanceOfMistakesProperty().set(Double.parseDouble(source.getAvoidanceOfForcedMistakes()));
            target.getOverrideFlags().overrideAvoidanceOfMistakesProperty().set(true);
        } else
            target.getOverrideFlags().overrideAvoidanceOfMistakesProperty().set(false);

        if (source.getAvoidanceOfForcedMistakes() != null) {
            target.avoidanceOfForcedMistakesProperty().set(Double.parseDouble(source.getAvoidanceOfForcedMistakes()));
            target.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty().set(true);
        } else
            target.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty().set(false);

        if (source.getVehicleReliability() != null) {
            target.vehicleReliabilityProperty().set(Double.parseDouble(source.getVehicleReliability()));
            target.getOverrideFlags().overrideVehicleReliabilityProperty().set(true);
        } else
            target.getOverrideFlags().overrideVehicleReliabilityProperty().set(false);
    }

    /**
     * Removes things from the XML file that are technically not allowed but AMS2 will ignore and still load the file
     * For example comments containing multiple --- and multiple xml header declarations.
     * @param inputStream InputStream that contains the xml grid.
     * @return String containing the XMLGrid
     */
    private static String preParseInputStream(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            char[] buffer = new char[10];
            while (bufferedReader.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
                buffer = new char[10];
            }
            bufferedReader.close();

            //Fixes dropped characters at the beginning of the stream
            String xml = stringBuilder.toString().trim().replaceFirst("^([\\W]+)<","<");
            //Removes any comments left in the xml
            xml = xml.replaceAll("<!--[\\s\\S]*?-->", "");
            //Removes redundant xml header declarations (don't ask, there are sometimes 2 headers in the same file
            xml = xml.replaceAll("<\\?xml.*\\?>", "");
            //Adding a fixed header after all other headers have been removed.
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " + xml;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Removes things from the XML file that are technically not allowed but AMS2 will ignore and still load the file
     * For example comments containing multiple --- and multiple xml header declarations.
     * @param file File that contains the xml grid.
     * @return String containing the XMLGrid
     */
    private static String preParseFile(File file) {
        try {
            return preParseInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
