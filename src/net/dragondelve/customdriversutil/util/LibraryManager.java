package net.dragondelve.customdriversutil.util;

import net.dragondelve.customdriversutil.model.TrackLibrary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;

public class LibraryManager {
    private TrackLibrary trackLibrary = new TrackLibrary();
    public static LibraryManager instance = new LibraryManager();

    private LibraryManager() {
        super();
    }

    public static LibraryManager getInstance() {
        return instance;
    }

    public TrackLibrary getTrackLibrary() {
        return trackLibrary;
    }

    public boolean importTrackLibrary(String pathname) {
        File library = new File(pathname);
        DDUtil.DEFAULT_LOGGER.log(Level.FINE, "Track library loading initiated with path: " + pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(TrackLibrary.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            trackLibrary = (TrackLibrary) unmarshaller.unmarshal(library);
            return true;
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING,"Track library loading failed");
            return false;
        }
    }

    public boolean exportTrackLibrary(String pathname) {
        File library = new File(pathname);
        DDUtil.DEFAULT_LOGGER.log(Level.FINE, "Track Library saving initiated with path: " + pathname);
        try {
            JAXBContext context = JAXBContext.newInstance(TrackLibrary.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(trackLibrary, library);
            return true;
        } catch (JAXBException | IllegalArgumentException e) {
            e.printStackTrace();
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Track Library loading failed");
            return false;
        }
    }
}
