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

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.Editor;
import net.dragondelve.customdriversutil.gui.editor.OverrideFlagsEditor;
import net.dragondelve.customdriversutil.model.OverrideFlags;
import net.dragondelve.customdriversutil.util.Configuration;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.DDUtil;

import java.io.IOException;

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

        loadOverrideFlagsEditor(defaultNewDriverAnchorPane, buffer.getDefaultDriverFlags());
        loadOverrideFlagsEditor(defaultNewOverrideAnchorPane, buffer.getDefaultTrackOverrideFlags());
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void createConfigurationBuffer() {
        buffer.setTrackLibraryPathname(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        buffer.setVehicleClassLibraryPathname(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
        buffer.setDefaultDriverFlags(Configurator.getInstance().getConfiguration().getDefaultDriverFlags());
        buffer.setDefaultTrackOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultTrackOverrideFlags());
    }

    private void okAction() {
        buffer.setTrackLibraryPathname(trackLibraryTextField.getText());
        buffer.setVehicleClassLibraryPathname(vehicleClassLibraryTextField.getText());
        Configurator.getInstance().setConfiguration(buffer);
        Configurator.getInstance().saveConfiguration();
        stage.close();
    }

    private void cancelAction() {
        stage.close();
    }

    /**
     *
     * @param targetPane
     * @param item
     */
    private void loadOverrideFlagsEditor(AnchorPane targetPane, OverrideFlags item) {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().OVERRIDE_FLAGS_EDITOR_FXML_URL);
        Editor<OverrideFlags> controller = new OverrideFlagsEditor();
        controller.setStage(stage);
        controller.setItems(new ImmutableObservableList<>(item));
        loader.setController(controller);
        try {
            Node node = loader.load();
            targetPane.getChildren().add(node);
            AnchorPane.setBottomAnchor(node, 0.0);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
