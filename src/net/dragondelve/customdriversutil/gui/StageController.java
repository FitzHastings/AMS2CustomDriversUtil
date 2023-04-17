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

package net.dragondelve.customdriversutil.gui;

import javafx.stage.Stage;

/**
 * A JavaFX entity that controls the stage on which it is displayed should implement StageController. This interface
 * is used when the given Controller must have an ability to create its own pop-up windows, alerts and so on, or
 * if this Controller needs a way to close itself.
 */
public interface StageController {
    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    void setStage(Stage stage);
}
