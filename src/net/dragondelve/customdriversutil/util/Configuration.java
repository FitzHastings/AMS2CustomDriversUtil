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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Configuration file holds all possible configuration states.
 * This class is fully annotated with JAXB for easy XML conversion.
 */
@XmlRootElement(name = "config")
public class Configuration {
    private String trackLibraryPathname;

    @XmlElement (name = "track_library")
    public String getTrackLibraryPathname() {
        return trackLibraryPathname;
    }

    public void setTrackLibraryPathname(String trackLibraryPathname) {
        this.trackLibraryPathname = trackLibraryPathname;
    }
}
