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
import net.dragondelve.customdriversutil.util.*;

import java.io.File;
import java.io.IOException;

/**
 * This controller controls configuration screen that allows the user to edit the configuration file directly. When
 * ConfigurationScreen is shown to the user it creates a buffer of the current Configuration. If the user closes the stage
 * using the okButton it assigns the buffer configuration as the current configuration and saves it. If the user closes
 * the stage in any other way it disregards any changes made.
 * This controller controls the fxml/ConfigurationScreen.fxml
 */
public class ConfigurationScreenController implements StageController {
    /**
     * AnchorPane that contains the OverrideFlagsEditor that edits default overrideFlags for any new driver.
     */
    @FXML
    private AnchorPane defaultNewDriverAnchorPane;

    /**
     * TextField that allows the user to edit the location of the VehicleClassLibrary xml file.
     */
    @FXML
    private TextField vehicleClassLibraryTextField;

    /**
     * TextField that allows the user to edit the location of the TrackLibrary xml file.
     */
    @FXML
    private TextField trackLibraryTextField;

    /**
     * AnchorPane that contains the OverrideFlagsEditor that edits default overrideFlags for any new track override.
     */
    @FXML
    private AnchorPane defaultNewOverrideAnchorPane;

    /**
     * Root Pane of the main screen. Is used to display everything inside the main window.
     * The main css style is applied to the rootPane.
     */
    @FXML
    private VBox rootPane;

    /**
     * Button that displays the FileChooser that allows the user to choose the new vehicle class library xml file.
     */
    @FXML
    private Button vehicleClassFileChooserButton;

    /**
     * Button that displays the FileChooser that allows the user to choose the new driver library xml file.
     */
    @FXML
    private Button driverLibraryFileChooserButton;

    /**
     * TextField that allows the user to edit the location of the DriverLibrary xml file.
     */
    @FXML
    private TextField driverLibraryTextField;

    /**
     * CheckBox that is used to edit the chooseLivery flag of the configuration.
     */
    @FXML
    private CheckBox chooseLiveryCheckBox;

    /**
     * CheckBox that is used to edit the skipWelcomeScreen flag of the configuration.
     */
    @FXML
    private CheckBox skipWelcomeScreenCheckBox;

    /**
     * Button that displays the FileChooser that allows the user to choose the new track library xml file.
     */
    @FXML
    private Button trackLibraryFileChooserButton;

    /**
     * Performs okAction on action.
     * Button that commits changes to the current configuration,saves it and closes the stage.
     */
    @FXML
    private Button okButton;

    /**
     * Performs cancelAction on action.
     * Button that disregards all changes and closes the stage.
     */
    @FXML
    private Button cancelButton;

    /**
     * Stage on which this StageController is displayed.
     */
    private Stage stage;

    /**
     * Buffer of the current configuration.
     */
    private final Configuration buffer = new Configuration();

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        createConfigurationBuffer();

        trackLibraryTextField.setText(buffer.getTrackLibraryPathname());
        vehicleClassLibraryTextField.setText(buffer.getVehicleClassLibraryPathname());
        driverLibraryTextField.setText(buffer.getDriverLibraryPathname());

        okButton.setOnAction(e -> okAction());
        cancelButton.setOnAction(e -> cancelAction());

        loadOverrideFlagsEditor(defaultNewDriverAnchorPane, buffer.getDefaultDriverFlags());
        loadOverrideFlagsEditor(defaultNewOverrideAnchorPane, buffer.getDefaultTrackOverrideFlags());

        skipWelcomeScreenCheckBox.selectedProperty().set(buffer.isSkipWelcomeScreen());
        chooseLiveryCheckBox.selectedProperty().set(buffer.isChooseLivery());

        trackLibraryFileChooserButton.setOnAction(e -> {
            File file = LibraryManager.createLibraryFileChooser("Choose Track Library", "library/tracks").showOpenDialog(stage);
            if (file != null) {
                PathRelativisor relativisor = new PathRelativisor(file);
                trackLibraryTextField.setText(relativisor.relativize());
            }
        });

        vehicleClassFileChooserButton.setOnAction(e -> {
            File file = LibraryManager.createLibraryFileChooser("Choose Vehicle Library", "library/vehicles").showOpenDialog(stage);
            if (file != null) {
                PathRelativisor relativisor = new PathRelativisor(file);
                vehicleClassLibraryTextField.setText(relativisor.relativize());
            }
        });

        driverLibraryFileChooserButton.setOnAction(e -> {
            File file = LibraryManager.createLibraryFileChooser("Choose Driver Library", "library/drivers").showOpenDialog(stage);
            if (file != null) {
                PathRelativisor relativisor = new PathRelativisor(file);
                driverLibraryTextField.setText(relativisor.relativize());
            }
        });
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets all fields of the buffer configuration to be equal to the current configuration's fields.
     */
    private void createConfigurationBuffer() {
        buffer.setTrackLibraryPathname(Configurator.getInstance().getConfiguration().getTrackLibraryPathname());
        buffer.setVehicleClassLibraryPathname(Configurator.getInstance().getConfiguration().getVehicleClassLibraryPathname());
        buffer.setDriverLibraryPathname(Configurator.getInstance().getConfiguration().getDriverLibraryPathname());
        buffer.setDefaultDriverFlags(Configurator.getInstance().getConfiguration().getDefaultDriverFlags());
        buffer.setDefaultTrackOverrideFlags(Configurator.getInstance().getConfiguration().getDefaultTrackOverrideFlags());
        buffer.setSkipWelcomeScreen(Configurator.getInstance().getConfiguration().isSkipWelcomeScreen());
        buffer.setChooseLivery(Configurator.getInstance().getConfiguration().isChooseLivery());
        buffer.setUpdateURL(Configurator.getInstance().getConfiguration().getUpdateURL());
    }

    /**
     * Action that is performed by the okButton.
     * Commits changes to the current configuration,saves it and closes the stage.
     */
    private void okAction() {
        buffer.setTrackLibraryPathname(trackLibraryTextField.getText());
        buffer.setVehicleClassLibraryPathname(vehicleClassLibraryTextField.getText());
        buffer.setDriverLibraryPathname(driverLibraryTextField.getText());
        buffer.setSkipWelcomeScreen(skipWelcomeScreenCheckBox.isSelected());
        buffer.setChooseLivery(chooseLiveryCheckBox.isSelected());
        Configurator.getInstance().setConfiguration(buffer);
        Configurator.getInstance().saveConfiguration();
        stage.close();
    }

    /**
     * Action that is performed by the cancelButton.
     * Disregards all changes and closes the stage.
     */
    private void cancelAction() {
        stage.close();
    }

    /**
     * Loads the FXML file of the OverrideFlagsEditor, and adds its rootPane to a given AnchorPane as a child node.
     * Sets the edited item of the OverrideFlagsEditor to be equal to the given item.
     * @param targetPane Target AnchorPane that is used to display the OverrideFlagsEditor
     * @param item OverrideFlags that are edited by the new OverrideFlagsEditor.
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