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

package net.dragondelve.customdriversutil.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.dragondelve.customdriversutil.util.DDUtil;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.NoSuchElementException;
import java.util.logging.Level;

/**
 * Library of tracks that is used when generating a custom AI grid.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "track_library")
public class TrackLibrary {
    /**
     * List of all tracks in the Track Library.
     */
    private ObservableList<Track> tracks = FXCollections.observableArrayList();

    /**
     * Lightweight accessor method.
     * @return List of all tracks in the Track Library.
     */
    @XmlElementWrapper(name = "tracks")
    public ObservableList<Track> getTracks() {
        return tracks;
    }

    /**
     * Finds a track in the library with a given name.
     * @param xmlName Track name that is used in the XML when exporting.
     * @return track from the library with that name or null if none found.
     */
    public Track findTrackWithXmlName(String xmlName) {
        try {
            return tracks.stream().filter(track -> track.getXmlName().equals(xmlName)).findFirst().get();
        } catch (NoSuchElementException e) {
            DDUtil.DEFAULT_LOGGER.log(Level.WARNING, "Trying to find track with name '"+xmlName+"' in the library but no such track found");
            return null;
        }
    }

    /**
     * Lightweight mutator method.
     * @param tracks List of all tracks in the Track Library.
     */
    public void setTracks(ObservableList<Track> tracks) {
        this.tracks = tracks;
    }
}
