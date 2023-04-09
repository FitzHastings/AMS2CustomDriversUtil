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
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

/**
 *
 */
public class CustomDriverUtilController implements StageController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private AnchorPane centralAnchorPane;

    Stage stage = new Stage();

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add("css/Eraconstas.css");
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:fxml/DriverEditor.fxml"));
            Node center = loader.load();
            centralAnchorPane.getChildren().add(center);
            AnchorPane.setTopAnchor(center, 0.0);
            AnchorPane.setBottomAnchor(center, 0.0);
            AnchorPane.setLeftAnchor(center, 0.0);
            AnchorPane.setRightAnchor(center, 0.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
