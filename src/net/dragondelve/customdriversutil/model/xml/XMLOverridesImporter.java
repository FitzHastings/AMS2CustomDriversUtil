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

import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.DDUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

/**
 *  Responsible for the conversion between AMS2 XML Overrides storage method and this program's data model.
 */
public class XMLOverridesImporter {
    /**
     * Imports a overrides from a given File. If the File contains a valid XML formatted with AMS2 XML representation of Custom AI it will return a overrides.
     * This will not set the correct Class for the overrides. This should be handled elsewhere.
     *
     * @param file An XML file formatted with AMS2 XML representation of Custom AI.
     * @return New instance of a overrides from the source, or null if the import has failed.
     */
    public VehicleClass importFromFile(File file) {
        XMLOverrides xmlOverrides = loadXMLOverrides(file);
        if (xmlOverrides != null)
            return fromXmlOverrides(xmlOverrides);
        else
            return null;
    }

    /**
     * Converts XMLOverrides to a VehicleClass.
     * @param overrides XMLOverrides to convert.
     * @return New instance of VehicleClass.
     */
    private VehicleClass fromXmlOverrides(XMLOverrides overrides) {
        VehicleClass newClass = new VehicleClass();
        newClass.setXmlName("New Class");
        for (XMLLiveryOverride liveryOverride : overrides.getLiveryOverrides()) {
            newClass.getLiveryNames().add(liveryOverride.getName());
        }
        return new VehicleClass();
    }

    /**
     * Unmarshals xml overrides from a given file.
     *
     * @param file File that contains XMLOverrides formatted with XML.
     * @return New instance of XMLOverrides.
     */
    private static XMLOverrides loadXMLOverrides(File file) {
        return parseXMLOverrides(preParseFile(file));
    }

    /**
     * Parses a String containing XMLOverrides formatted to an xml
     * @param xml String containing XMLOverrides formatted to an xml
     * @return New instance of XMLOverrides.
     */
    private static XMLOverrides parseXMLOverrides(String xml) {
        try {
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML overrides loading initiated from String");
            JAXBContext context = JAXBContext.newInstance(XMLOverrides.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLOverrides xmlOverrides = (XMLOverrides) unmarshaller.unmarshal(new StringReader(xml));
            DDUtil.DEFAULT_LOGGER.log(Level.FINE, "XML overrides loading successful from String");
            return xmlOverrides;
        } catch (JAXBException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "XML overrides loading failed from from String");
            return null;
        }
    }
    
    /**
     * Removes things from the XML file that are technically not allowed but AMS2 will ignore and still load the file
     * For example comments containing multiple --- and multiple xml header declarations.
     *
     * @param inputStream InputStream that contains the xml overrides.
     * @return String containing the XMLOverrides
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
            String xml = stringBuilder.toString().trim().replaceFirst("^([\\W]+)<", "<");
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
     *
     * @param file File that contains the xml overrides.
     * @return String containing the XMLOverrides
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
