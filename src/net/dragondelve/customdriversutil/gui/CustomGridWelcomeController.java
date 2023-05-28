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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.util.DDUtil;

import java.io.IOException;


public class CustomGridWelcomeController implements StageController {
    @FXML
    private Button loadExistingVanillaGrid;

    @FXML
    private Button newGeneratedGridButton;

    @FXML
    private Button newEmptyGridButton;

    @FXML
    private Button manualUpdateButton;

    @FXML
    private Button loadModdedGridButton;

    @FXML
    private VBox rootPane;

    Stage stage;

    @FXML
    public void initialize() {
        newEmptyGridButton.setOnAction(e -> newEmptyGridAction());
    }

    private void newEmptyGridAction() {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
        StageController controller = new CustomDriverUtilController();
        controller.setStage(stage);
        loader.setController(controller);

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
