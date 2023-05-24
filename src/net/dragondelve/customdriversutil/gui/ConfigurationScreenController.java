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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.util.Configuration;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;

public class ConfigurationScreenController implements StageController {
    @FXML
    private AnchorPane defaultNewDriverAnchorPane;

    @FXML
    private TextField vehicleClassLibraryTextField;

    @FXML
    private TextField trackLibraryTextField;

    @FXML
    private AnchorPane defaultNewOverrideAnchorPane;

    @FXML
    private VBox rootPane;

    @FXML
    private Button vehicleClassFileChooserButton;

    @FXML
    private Button driverLibraryFileChooserButton;

    @FXML
    private TextField driverLibraryTextField;

    @FXML
    private CheckBox chooseLiveryCheckBox;

    @FXML
    private Button trackLibraryFileChooserButton;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    Stage stage;
    Configuration buffer = new Configuration();

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        createConfigurationBuffer();

        trackLibraryTextField.setText(buffer.getTrackLibraryPathname());
        vehicleClassLibraryTextField.setText(buffer.getVehicleClassLibraryPathname());

        okButton.setOnAction(e -> okAction());
        cancelButton.setOnAction(e -> cancelAction());
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void createConfigurationBuffer() {
        buffer.setTrackLibraryPathname(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        buffer.setVehicleClassLibraryPathname(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
    }

    private void okAction() {
        Configurator.getInstance().setConfiguration(buffer);
        Configurator.getInstance().saveConfiguration();
        stage.close();
    }

    private void cancelAction() {
        stage.close();
    }
}
