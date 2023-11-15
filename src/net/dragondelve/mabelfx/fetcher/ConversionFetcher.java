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

package net.dragondelve.mabelfx.fetcher;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * An interface for a visual fetcher that uses JavaFX to display a selection of instances of C before returning an instance of T on request.
 *
 * @param <T> an instance of this class will be returned by the retrieve method.
 * @param <C> a list of this class will be displayed to the user
 */
public interface ConversionFetcher<T, C> extends Fetcher<T> {
    /**
     * A method that initializes the visual elements of the VisualFetcher. It should always be run before its retrieve method
     *
     * @param parent a parent stage that will be set as an owner of the stage displayed by the VisualFetcher
     * @param items  a list of items to be displayed by the VisualFetcher
     */
    void initialize(Stage parent, ObservableList<C> items);
}
