package net.dragondelve.customdriversutil.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A non instatiable class with a single instance. It loads the current configuration of the program and allows parts of the program to access
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
    private Configuration configuration;

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
     * Private constructor. it is private to ensure that
     * only one instance of configurator exists per program.
     */
    private Configurator() {}
}