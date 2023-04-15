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

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

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
     * Lightweight mutator method.
     * @param tracks List of all tracks in the Track Library.
     */
    public void setTracks(ObservableList<Track> tracks) {
        this.tracks = tracks;
    }
}
