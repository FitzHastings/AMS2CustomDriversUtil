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
 * Represents a library of drivers.
 */
public class DriverLibrary {
    /**
     * List of Drivers and their overrides in the library.
     */
    private final ObservableList<Driver> drivers = FXCollections.observableArrayList();

    /**
     * Lightweight accessor method.
     *
     * @return List of Drivers and their overrides in the library.
     */
    public ObservableList<Driver> getDrivers() {
        return drivers;
    }
}
