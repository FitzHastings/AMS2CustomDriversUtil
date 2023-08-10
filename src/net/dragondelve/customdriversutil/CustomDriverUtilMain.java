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
import net.dragondelve.customdriversutil.model.OverrideFlags;
import net.dragondelve.customdriversutil.util.Configuration;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.mabelfx.StageController;

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
     */
    @Override
    public void start(Stage primaryStage) {
        if (!Configurator.getInstance().loadConfiguration()) {
            Configurator.getInstance().setConfiguration(generateDefaultConfiguration());
            Configurator.getInstance().saveConfiguration();
        }
        LibraryManager.getInstance().importTrackLibrary(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        LibraryManager.getInstance().importVehicleClassLibrary(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
        if (Configurator.getInstance().getConfiguration().getDriverLibraryPathname() != null && Configurator.getInstance().getConfiguration().getDriverLibraryPathname().length() > 0) {
           LibraryManager.getInstance().importDriverLibrary(Configurator.getInstance().getConfiguration().getDriverLibraryPathname()); 
        }

        primaryStage.setTitle("Custom Driver Utility V2.2.1");
        primaryStage.setMinWidth(880);
        primaryStage.setMinHeight(700);
        primaryStage.setHeight(700);
        primaryStage.setWidth(1200);

        primaryStage.getIcons().add(DDUtil.LARGE_ICON_IMAGE);
        primaryStage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
        if (Configurator.getInstance().getConfiguration().isSkipWelcomeScreen()) {
            try {
                FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
                StageController stageController = new CustomDriverUtilController();
                stageController.setStage(primaryStage);
                loader.setController(stageController);
                Scene scene = new Scene(loader.load());
                //scene.setFill(Color.TRANSPARENT);
                //primaryStage.initStyle(StageStyle.TRANSPARENT);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().CUSTOM_GRID_WELCOME_FXML_URL);
                StageController stageController = new CustomGridWelcomeController();
                stageController.setStage(primaryStage);
                loader.setController(stageController);
                Scene scene = new Scene(loader.load());
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        configuration.setUpdateURL(DDUtil.DEFAULT_UPDATE);

        OverrideFlags defaultDriverOverrideFlags = new OverrideFlags();
        defaultDriverOverrideFlags.setOverrideAll(true);
        configuration.setDefaultDriverFlags(defaultDriverOverrideFlags);

        OverrideFlags defaultTrackOverrideFlags = new OverrideFlags();
        defaultTrackOverrideFlags.setOverrideRaceSkill(true);
        configuration.setDefaultTrackOverrideFlags(defaultTrackOverrideFlags);

        return configuration;
    }
}