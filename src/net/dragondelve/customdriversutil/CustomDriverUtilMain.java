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
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.CustomDriverUtilController;
import net.dragondelve.customdriversutil.gui.StageController;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.net.URL;

/**
 *
 */
public class CustomDriverUtilMain extends Application {
    /**
     * Application's main method.
     * @param args Program arguments (ignored).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     *
     * @param primaryStage primary stage that is used to display the GUI.
     * @throws Exception any uncaught exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if(!Configurator.getInstance().loadConfiguration()) {
            Configurator.getInstance().getConfiguration().setTrackLibraryPathname("library/tracks/ams2_1.4.6.4.xml");
            Configurator.getInstance().saveConfiguration();
        }

        if(!LibraryManager.getInstance().importTrackLibrary(Configurator.getInstance().getConfiguration().getTrackLibraryPathname())) {
            Track testTrack = new Track();
            testTrack.isOvalProperty().set(false);
            testTrack.xmlNameProperty().set("test_track");
            testTrack.nameProperty().set("test track");
            LibraryManager.getInstance().getTrackLibrary().setTracks(FXCollections.observableArrayList(testTrack));
            LibraryManager.getInstance().exportTrackLibrary(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        }

        try {
            FXMLLoader loader = new FXMLLoader(new URL("file:fxml/CustomDriverUtilMain.fxml"));
            StageController controller = new CustomDriverUtilController();
            controller.setStage(primaryStage);

            loader.setController(controller);
            primaryStage.setTitle("Custom Driver Utility V2");
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(880);
            primaryStage.setMinHeight(700);
            primaryStage.setHeight(700);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
