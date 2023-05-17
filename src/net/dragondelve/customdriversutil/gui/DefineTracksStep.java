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

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

/**
 * DefineTracksStep is an intermediate step between pressing the addTrackOverrideButton and the Driver Editor opening
 * in the Override mode to allow the user to set all the tracks on which the newly created override will be overriding
 * the base driver properties. This is a controller class for  fxml/DefineTracksStep.fxml.
 */
public class DefineTracksStep {
    
    @FXML
    private Button nextButton;

    @FXML
    private Button cancelButton;

    @FXML
    private VBox rootPane;

    @FXML
    private ListView<Track> selectedListView;

    @FXML
    private ListView<Track> trackLibraryListView;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        trackLibraryListView.setItems(LibraryManager.getInstance().getTrackLibrary().getTracks());
    }
}
