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
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.CustomDriverUtilController;
import net.dragondelve.customdriversutil.gui.CustomGridWelcomeController;
import net.dragondelve.customdriversutil.gui.StageController;
import net.dragondelve.customdriversutil.model.OverrideFlags;
import net.dragondelve.customdriversutil.util.Configuration;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

/**
 * CustomDriverUtility's main class.
 * Responsible for launching the application, making sure the Configurator loads the Configuration and the
 * LibraryManager loads the last used libraries.
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
     * Application Start Method.
     * @param primaryStage primary stage that is used to display the GUI.
     * @throws Exception any uncaught exception.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        if(!Configurator.getInstance().loadConfiguration()) {
            Configurator.getInstance().setConfiguration(generateDefaultConfiguration());
            Configurator.getInstance().saveConfiguration();
        }
        LibraryManager.getInstance().importTrackLibrary(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        LibraryManager.getInstance().importVehicleClassLibrary(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            StageController stageController = new CustomGridWelcomeController();
            stageController.setStage(primaryStage);
            fxmlLoader.setController(stageController);
            Scene scene = fxmlLoader.load(DDUtil.getInstance().CUSTOM_GRID_WELCOME_FXML_URL);
            primaryStage.setTitle("Custom Driver Utility V2.0");
            primaryStage.setScene(scene);
            primaryStage.setMinWidth(880);
            primaryStage.setMinHeight(700);
            primaryStage.setHeight(700);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a new configuration for the program with default values if conf.xml was not found.
     * @return newly generated default configuration.
     */
    private Configuration generateDefaultConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setTrackLibraryPathname(DDUtil.TRACK_LIBRARY_DEFAULT_PATHNAME);
        configuration.setVehicleClassLibraryPathname(DDUtil.VEHICLE_CLASS_LIBRARY_DEFAULT_PATHNAME);

        OverrideFlags defaultDriverOverrideFlags = new OverrideFlags();
        defaultDriverOverrideFlags.setOverrideAll(true);
        configuration.setDefaultDriverFlags(defaultDriverOverrideFlags);

        OverrideFlags defaultTrackOverrideFlags = new OverrideFlags();
        defaultTrackOverrideFlags.setOverrideRaceSkill(true);
        configuration.setDefaultTrackOverrideFlags(defaultTrackOverrideFlags);

        return configuration;
    }
}
