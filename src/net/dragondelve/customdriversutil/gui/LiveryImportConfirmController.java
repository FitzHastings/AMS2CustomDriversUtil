// Copyright 2023 Prokhor Kalinin
//
// Licensed under the Apache License, Version 2.0 (the "License";
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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.mabelfx.StageController;


/**
 * Controller for the LiveryImportConfirm.fxml file.
 * This controller is responsible for displaying the list of liveries that are going to be imported and for allowing the user to confirm the import.
 */
public class LiveryImportConfirmController implements StageController {
    @FXML
    private ListView<String> liveryNameListView;

    @FXML
    private Button cancelButton;

    @FXML
    private VBox rootPane;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button importButton;

    private Stage stage;

    private VehicleClass vehicleClass;

    public LiveryImportConfirmController(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * Initializes the controller.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        cancelButton.setOnAction(event -> stage.close());
    }

    /**
     * Sets the stage on which this controller is going to be displayed.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
