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

import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.util.GridExporter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Responsible for the conversion between this program's data model and  the AMS2 XML Custom Driver storage method.
 * AMS2 XML representation of the custom AI drivers can be found here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 */
public class XMLGridExporter implements GridExporter {
    /**
     * Exports the given grid to a given file using JAXB. It uses the formatting described in
     * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
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
        grid.getDrivers().forEach(e-> {
            XMLDriver driver = new XMLDriver();
            driver.setName(e.getName());
            driver.setLiveryName(e.getLiveryName());
            driver.setCountry(e.getCountry());
            driver.setRaceSkill(e.getRaceSkill());
            driver.setQualifyingSkill(e.getQualifyingSkill());
            driver.setAggression(e.getAggression());
            driver.setDefending(e.getDefending());
            driver.setStamina(e.getStamina());
            driver.setConsistency(e.getConsistency());
            driver.setStartReactions(e.getStartReactions());
            driver.setWetSkill(e.getWetSkill());
            driver.setTyreManagement(e.getTyreManagement());
            driver.setFuelManagement(e.getFuelManagement());
            driver.setBlueFlagConceding(e.getBlueFlagConceding());
            driver.setWeatherTyreChanges(e.getWeatherTyreChange());
            driver.setAvoidanceOfMistakes(e.getAvoidanceOfMistakes());
            driver.setAvoidanceOfForcedMistakes(e.getAvoidanceOfForcedMistakes());
            driver.setVehicleReliability(e.getVehicleReliability());

            xmlGrid.getXmlDrivers().add(driver);
        });

        //TODO: Parse Track Specific Overrides here.

        return xmlGrid;
    }
}
