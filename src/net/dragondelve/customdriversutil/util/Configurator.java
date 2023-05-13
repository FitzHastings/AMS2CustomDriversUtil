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

package net.dragondelve.customdriversutil.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;

/**
 * A non installable class with a single instance. It loads the current configuration of the program and allows parts of the program to access
 * the data in the configuration. In order to get access to it's only instance you should use the static method getInstance().
 */
public class Configurator {
    /**
     * A default pathname where the configuration is stored.
     */
    private final static String CONFIGURATION_DEFAULT_PATHNAME = "conf.xml";
    /**
     * The only instance of Configurator.
     */
    private final static Configurator instance = new Configurator();
    /**
     * The configuration of the program.
     */
    private Configuration configuration = new Configuration();

    /**
     * Lightweight accessor method.
     * @return the only instance of Configurator.
     */
    public static Configurator getInstance() {
       return instance;
    }

    /**
     * Lightweight accessor method.
     * @return program's current configuration.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Lightweight mutator method.
     * @param configuration program's new configuration, Should only be used if loadConfiguration has failed.
     */
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Attempts to load the configuration from the default pathname.
     * If it fails to load the configuration it will return false, if it succeeds in loading the configuration it will return true, allowing
     * the GUI to alert the user of configuration file becoming corrupted or missing.
     */
    public boolean loadConfiguration() {
        return loadConfiguration(CONFIGURATION_DEFAULT_PATHNAME);
    }

    /**
     * Attempts to load the configuration from a file at a provided pathname.
     * If it fails to load the configuration it will return false, if it succeeds in loading the configuration it will return true, allowing
     * the GUI to alert the user of configuration file becoming corrupted or missing.
     * @param pathname pathnamne to an XML file that contains a configuration.
     */
    public boolean loadConfiguration(String pathname) {
        File config = new File(pathname);
        DDUtil.DEFAULT_LOGGER.log(Level.FINE, "Configuration loading initiated with path: " + pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            configuration = (Configuration) unmarshaller.unmarshal(config);
            return true;
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING,"Configuration loading failed");
            return false;
        }
    }

    /**
     * Saves the current configuration to the defeault pathname, that is defined in CONFIGURATION_DEFAULT_PATHNAME
     * @return True if the save succeeded, false if the configuration save failed.
     */
    public boolean saveConfiguration() {
        return saveConfiguration(CONFIGURATION_DEFAULT_PATHNAME);
    }

    /**
     * Saves the current configuration to the given pathname.
     * @param pathname pathname to which the configuration file is going to be saved.
     * @return True if the save succeeded, false if the configuration save failed.
     */
    public boolean saveConfiguration(String pathname) {
        File config = new File(pathname);
        DDUtil.DEFAULT_LOGGER.log(Level.FINE, "Configuration saving initiated with path: " + pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(configuration, config);
            return true;
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Configuration loading failed");
            return false;
        }
    }

    /**
     * Private constructor. it is private to ensure that
     * only one instance of configurator exists per program.
     */
    private Configurator() {

    }
}
