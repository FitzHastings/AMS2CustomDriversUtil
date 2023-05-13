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
        if (source.isOverrideName())
            target.setName(source.getName());
        if (source.isOverrideCountry())
            target.setCountry(source.getCountry());
        if (source.isOverrideRaceSkill())
            target.setRaceSkill(source.getRaceSkill());
        if (source.isOverrideQualifyingSkill())
            target.setQualifyingSkill(source.getQualifyingSkill());
        if (source.isOverrideAggression())
            target.setAggression(source.getAggression());
        if (source.isOverrideDefending())
            target.setDefending(source.getDefending());
        if (source.isOverrideStamina())
            target.setStamina(source.getStamina());
        if (source.isOverrideConsistency())
            target.setConsistency(source.getConsistency());
        if (source.isOverrideStartReactions())
            target.setStartReactions(source.getStartReactions());
        if (source.isOverrideWetSkill())
            target.setWetSkill(source.getWetSkill());
        if (source.isOverrideTyreManagement())
            target.setTyreManagement(source.getTyreManagement());
        if (source.isOverrideFuelManagement())
            target.setFuelManagement(source.getFuelManagement());
        if (source.isOverrideBlueFlagConceding())
            target.setBlueFlagConceding(source.getBlueFlagConceding());
        if (source.isOverrideWeatherTyreChange())
            target.setWeatherTyreChanges(source.getWeatherTyreChange());
        if (source.isOverrideAvoidanceOfMistakes())
            target.setAvoidanceOfMistakes(source.getAvoidanceOfMistakes());
        if (source.isOverrideAvoidanceOfForcedMistakes())
            target.setAvoidanceOfForcedMistakes(source.getAvoidanceOfForcedMistakes());
        if (source.isOverrideVehicleReliability())
            target.setVehicleReliability(source.getVehicleReliability());
    }
}
