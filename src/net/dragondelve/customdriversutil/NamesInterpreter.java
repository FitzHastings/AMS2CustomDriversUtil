package net.dragondelve.customdriversutil;

import javafx.collections.transformation.FilteredList;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.OverrideFlags;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.xml.XMLGridImporter;
import net.dragondelve.customdriversutil.util.Configuration;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.io.File;
import java.util.Objects;
import java.util.logging.Level;

public class NamesInterpreter {
    private static void parseALlFilesInFolder(File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            String xml_name = file.getName().substring(0, file.getName().length() - 4);
            VehicleClass vehicleClass = fromLibrary(xml_name);
            if (vehicleClass == null) {
                DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Found Something Thad doesn't belong " + xml_name);
                LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().add(newVehicleClass(file));
            } else {
                updateVehicleClass(vehicleClass, file);
            }
        }

        LibraryManager.getInstance().exportVehicleClassLibrary("ams2_vehicles_1.5.0.2.xml");
    }

    private static void updateVehicleClass(VehicleClass vehicleClass, File file) {
        Grid grid = new XMLGridImporter().importFromFile(file);
        vehicleClass.getLiveryNames().clear();
        for (Driver driver : grid.getDrivers()) {
            vehicleClass.getLiveryNames().add(driver.getLiveryName());
        }
    }

    private static VehicleClass newVehicleClass(File file) {
        Grid grid = new XMLGridImporter().importFromFile(file);
        VehicleClass vehicleClass = new VehicleClass();
        String xmlName = file.getName().substring(0, file.getName().length() - 4);
        vehicleClass.setName(xmlName);
        vehicleClass.setXmlName(xmlName);
        for (Driver driver : grid.getDrivers()) {
            vehicleClass.getLiveryNames().add(driver.getLiveryName());
        }
        return vehicleClass;
    }

    private static VehicleClass fromLibrary(String xml_name) {
        FilteredList<VehicleClass> fromLib = LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses().filtered(vehicleClass -> vehicleClass.getXmlName().equals(xml_name));
        if (fromLib.isEmpty())
            return null;
        else
            return fromLib.get(0);
    }

    public static void main(String[] args) {
        if (!Configurator.getInstance().loadConfiguration()) {
            Configurator.getInstance().setConfiguration(generateDefaultConfiguration());
            Configurator.getInstance().saveConfiguration();
        }
        LibraryManager.getInstance().importTrackLibrary(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        LibraryManager.getInstance().importVehicleClassLibrary(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
        if (Configurator.getInstance().getConfiguration().getDriverLibraryPathname() != null && Configurator.getInstance().getConfiguration().getDriverLibraryPathname().length() > 0) {
            LibraryManager.getInstance().importDriverLibrary(Configurator.getInstance().getConfiguration().getDriverLibraryPathname());
        }

        File file = new File("names");
        parseALlFilesInFolder(file);
    }

    private static Configuration generateDefaultConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setTrackLibraryPathname(DDUtil.TRACK_LIBRARY_DEFAULT_PATHNAME);
        configuration.setVehicleClassLibraryPathname(DDUtil.VEHICLE_CLASS_LIBRARY_DEFAULT_PATHNAME);
        configuration.setUpdateURL(DDUtil.DEFAULT_UPDATE);

        OverrideFlags defaultDriverOverrideFlags = new OverrideFlags();
        defaultDriverOverrideFlags.setOverrideAll(true);
        configuration.setDefaultDriverFlags(defaultDriverOverrideFlags);

        OverrideFlags defaultTrackOverrideFlags = new OverrideFlags();
        defaultTrackOverrideFlags.setOverrideRaceSkill(true);
        configuration.setDefaultTrackOverrideFlags(defaultTrackOverrideFlags);

        return configuration;
    }
}
