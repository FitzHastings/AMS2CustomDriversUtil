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

package net.dragondelve.mabelfx.util;

import javafx.scene.control.TableView;

/**
 * Refreshes the table when the refresh method is called. Usually passed to a window or a controller of a small part of
 * the entire view to allow it to refresh the table when some event happens.
 *
 * @param <T> TableView or something that extends it
 */
public class FXTableRefresher<T extends TableView<?>> {
    /**
     * TableView to be refreshed when the refresh method is called
     */
    private final T tableView;

    /**
     * Creates a new instance of
     *
     * @param tableView TableView to be refreshed when the refresh method is called
     */
    public FXTableRefresher(T tableView) {
        super();
        this.tableView = tableView;
    }

    /**
     * Refreshes the TableView associated with this FXTableRefresher
     */
    public void refresh() {
        tableView.refresh();
    }
}
