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

package net.dragondelve.customdriversutil.util;

import net.dragondelve.customdriversutil.model.TrackLibrary;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;

/**
 * Manages all the libraries that are used in this program as a reference.
 * Manages the active instances of TrackLibrary VehicleClassLibrary, and DriverLibrary
 * THis class is non-instatiable, in order to get its only instance you should use the getInstance() method.
 * Library manager is also responsible for importing and exporting of libraries.
 */
public class LibraryManager {
    /**
     * Currently used Track Library. Track Library is used for generating new Track Specific Overrides.
     */
    private TrackLibrary trackLibrary = new TrackLibrary();

    /**
     * The only instance of Library manager that exists, You should use getInstance() in order to get it.
     */
    private static final LibraryManager instance = new LibraryManager();

    /**
     * Private constructor. Used to make this class non instatiable.
     */
    private LibraryManager() {
        super();
    }

    /**
     * Lightweight accessor method.
     * @return The only instance of this class.
     */
    public static LibraryManager getInstance() {
        return instance;
    }

    /**
     * Lightweight accessor method.
     * @return The currently loaded TrackLibrary.
     */
    public TrackLibrary getTrackLibrary() {
        return trackLibrary;
    }

    /**
     * Imports the Track Library from a File located at a given pathname.
     * @param pathname Pathname to an XML file that contains a Track Library.
     * @return true if importing has succeeded, false if it has failed.
     */
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

    /**
     * Exports the currently loaded TrackLibrary to an XML file located at a given pathname.
     * @param pathname pathname to a File to which the TrackLibrary should be exported.
     * @return true if exporting has succeeded, false if it has failed.
     */
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
