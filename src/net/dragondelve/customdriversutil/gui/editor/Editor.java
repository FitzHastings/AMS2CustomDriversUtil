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

package net.dragondelve.customdriversutil.gui.editor;

import javafx.collections.ObservableList;
import net.dragondelve.customdriversutil.gui.StageController;

/**
 * JavaFX Editor that can edit an ObservableList of items of T.
 */
public interface Editor<T> extends StageController {
    /**
     * Lightweight accessor method.
     * @return an observableArrayList of items of T.
     */
    ObservableList<T> getItems();

    /**
     * Lightweight mutator method.
     * Should be called before the editor is displayed to the user.
     * @param items an observableList of items of T.
     */
    void setItems(ObservableList<T> items);
}
