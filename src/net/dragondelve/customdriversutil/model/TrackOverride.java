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

/**
 * Represents a model of a specific track override as described by AMS2 Developers here:
 * https://forum.reizastudios.com/threads/information-for-customizing-ai-drivers-in-ams2-v1-3.21758/
 * It stores a list of track names for which this track is an override.
 */
final public class TrackOverride extends DriverBase {
    /**
     * Full list of tracks that this override applies to.
     */
    private final ObservableList<Track> tracks = FXCollections.observableArrayList();

    /**
     * Lightweight Accessor Method.
     * @return Full list of tracks that this override applies to.
     */
    public ObservableList<Track> getTrack() {
        return tracks;
    }
}
