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

package net.dragondelve.customdriversutil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.CustomDriverUtilController;
import net.dragondelve.customdriversutil.gui.StageController;

import javax.xml.bind.annotation.XmlRootElement;
import java.net.URL;

public class CustomDriverUtilMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:fxml/CustomDriverUtilMain.fxml"));
            StageController controller = new CustomDriverUtilController();
            controller.setStage(primaryStage);

            loader.setController(controller);
            primaryStage.setTitle("Custom Driver Utility V2");
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(880);
            primaryStage.setMinHeight(800);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
