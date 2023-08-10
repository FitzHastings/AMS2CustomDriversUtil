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

import net.dragondelve.customdriversutil.model.DriverBase;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.GridExporter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Responsible for the conversion between this program's data model and  the AMS2 XML Custom Driver storage method.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * <a href="https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/">AMS2 Reiza Forums</a>
 */
public class XMLGridExporter implements GridExporter {
    /**
     * Exports the given grid to a given file using JAXB. It uses the formatting described in
     * <a href="https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/">AMS2 Reiza Forums</a>
     * @param grid Grid to be exported in an AMS2 XML format.
     * @param file File to which the grid is to be exported. Should preferably end with .xml.
     */
    @Override
    public void exportToFile(Grid grid, File file) {
        exportXMLGrid(toXMLGrid(grid), file);
    }

    /**
     * Exports a given XMLGrid to a given file.
     * @param xmlGrid XMLGrid to be exported to a file.
     * @param file File to which the XMLGrid is to be exported.
     */
    private void exportXMLGrid(XMLGrid xmlGrid, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLGrid.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xmlGrid, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given Grid to an XMLGrid
     * @param grid Grid that is going to be converted to an XMLGrid.
     * @return New instance of XMLGrid.
     */
    private XMLGrid toXMLGrid(Grid grid) {
        XMLGrid xmlGrid = new XMLGrid();
        grid.getDrivers().forEach(driver-> {
            XMLDriver xmlDriver = new XMLDriver();
            xmlDriver.setLiveryName(driver.getLiveryName());
            exportBaseProperties(driver, xmlDriver);
            xmlGrid.getXmlDrivers().add(xmlDriver);

            driver.getTrackOverrides().forEach(trackOverride -> {
                XMLDriver xmlOverride = new XMLDriver();
                StringBuilder builder = new StringBuilder();
                trackOverride.getTrack().forEach(track -> {
                    if (builder.length() > 0)
                        builder.append(',');
                    builder.append(track.getXmlName());

                });
                xmlOverride.setTracks(builder.toString());
                xmlOverride.setLiveryName(driver.getLiveryName());
                exportBaseProperties(trackOverride, xmlOverride);
                xmlGrid.getXmlDrivers().add(xmlOverride);
            });
        });

        return xmlGrid;
    }

    /**
     * Exports the base properties shared between the track specific overrides and driver overrides.
     * @param source Source DriverBase, whose fields will be used to set the base properties of the xmlDriver.
     * @param target Target whose properties are going to be set. Should be an xmlDriver whose other fields are set appropriately.
     */
    private void exportBaseProperties(DriverBase source, XMLDriver target) {
        if (source.getOverrideFlags().isOverrideName())
            target.setName(source.getName());
        if (source.getOverrideFlags().isOverrideCountry())
            target.setCountry(source.getCountry());
        if (source.getOverrideFlags().isOverrideRaceSkill())
            target.setRaceSkill(convertProperty(source.getRaceSkill()));
        if (source.getOverrideFlags().isOverrideQualifyingSkill())
            target.setQualifyingSkill(convertProperty(source.getQualifyingSkill()));
        if (source.getOverrideFlags().isOverrideAggression())
            target.setAggression(convertProperty(source.getAggression()));
        if (source.getOverrideFlags().isOverrideDefending())
            target.setDefending(convertProperty(source.getDefending()));
        if (source.getOverrideFlags().isOverrideStamina())
            target.setStamina(convertProperty(source.getStamina()));
        if (source.getOverrideFlags().isOverrideConsistency())
            target.setConsistency(convertProperty(source.getConsistency()));
        if (source.getOverrideFlags().isOverrideStartReactions())
            target.setStartReactions(convertProperty(source.getStartReactions()));
        if (source.getOverrideFlags().isOverrideWetSkill())
            target.setWetSkill(convertProperty(source.getWetSkill()));
        if (source.getOverrideFlags().isOverrideTyreManagement())
            target.setTyreManagement(convertProperty(source.getTyreManagement()));
        if (source.getOverrideFlags().isOverrideFuelManagement())
            target.setFuelManagement(convertProperty(source.getFuelManagement()));
        if (source.getOverrideFlags().isOverrideBlueFlagConceding())
            target.setBlueFlagConceding(convertProperty(source.getBlueFlagConceding()));
        if (source.getOverrideFlags().isOverrideWeatherTyreChange())
            target.setWeatherTyreChanges(convertProperty(source.getWeatherTyreChange()));
        if (source.getOverrideFlags().isOverrideAvoidanceOfMistakes())
            target.setAvoidanceOfMistakes(convertProperty(source.getAvoidanceOfMistakes()));
        if (source.getOverrideFlags().isOverrideAvoidanceOfForcedMistakes())
            target.setAvoidanceOfForcedMistakes(convertProperty(source.getAvoidanceOfForcedMistakes()));
        if (source.getOverrideFlags().isOverrideVehicleReliability())
            target.setVehicleReliability(convertProperty(source.getVehicleReliability()));
    }

    /**
     * Converts the given driver's property to a String according to the current Export Rules
     * @param property property to be converted
     * @return same property converted to a String
     */
    private String convertProperty(double property) {
        String stringProperty = Double.toString(property);
        int nDigits = DDUtil.DECIMAL_POINT_CONSTANT + Configurator.getInstance().getConfiguration().getRoundingDecimalPlaces();
        if (Configurator.getInstance().getConfiguration().isRoundGeneratedValues() && stringProperty.length() >= nDigits)
            return stringProperty.substring(0, nDigits);
        else
            return stringProperty;
    }

}