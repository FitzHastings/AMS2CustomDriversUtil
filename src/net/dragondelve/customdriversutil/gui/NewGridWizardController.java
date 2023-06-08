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
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

/**
 * New Grid Wizard controller controls the fxml/NewGridWizard.fxml
 * NewGridWizard allows the user to create a new Grid or to generate a new grid using the GridGenerator.
 */
public class NewGridWizardController implements StageController {
    @FXML
    private RadioButton blankNamesRadioButton;

    @FXML
    private Button generateButton;

    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;

    @FXML
    private RadioButton noNoiseRadioButton;

    @FXML
    private CheckBox emptyGridCheckBox;

    @FXML
    private CheckBox qualiExceedsRaceSkillCheckBox;

    @FXML
    private CheckBox reduceGapOnOvalsCheckBox;

    @FXML
    private CheckBox bindQualiCheckBox;

    @FXML
    private CheckBox limitAggressionCheckBox;

    @FXML
    private TextField limitToTextField;

    @FXML
    private CheckBox noValuesCheckBox;

    @FXML
    private TextField maxValueTextField;

    @FXML
    private CheckBox generateGridCheckBox;

    @FXML
    private VBox rootPane;

    @FXML
    private CheckBox randomValuesCheckBox;

    @FXML
    private TextField qualiExceedsTextField;

    @FXML
    private RadioButton randomAllRadioButton;

    @FXML
    private CheckBox forEachLiveryCheckBox;

    @FXML
    private RadioButton fromLiveryNamesRadioButton;

    @FXML
    private CheckBox rangeOfValuesCheckBox;

    @FXML
    private TextField minValueTextField;

    @FXML
    private RadioButton highNoiseRadioButton;

    @FXML
    private RadioButton useNAMeSRadioButton;

    @FXML
    private RadioButton randomSkillRadioButton;

    @FXML
    private RadioButton lowNoiseRadioButton;

    @FXML
    private TextField amountCheckBox;

    @FXML
    private GridPane generateGridPane;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    Stage stage;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        generateGridPane.disableProperty().bind(generateGridCheckBox.selectedProperty().not());
        amountCheckBox.disableProperty().bind(forEachLiveryCheckBox.selectedProperty());

        randomSkillRadioButton.disableProperty().bind(randomValuesCheckBox.selectedProperty().not());
        randomAllRadioButton.disableProperty().bind(randomValuesCheckBox.selectedProperty().not());

        noNoiseRadioButton.disableProperty().bind(rangeOfValuesCheckBox.selectedProperty().not());
        lowNoiseRadioButton.disableProperty().bind(rangeOfValuesCheckBox.selectedProperty().not());
        highNoiseRadioButton.disableProperty().bind(rangeOfValuesCheckBox.selectedProperty().not());

        minValueTextField.disableProperty().bind(noValuesCheckBox.selectedProperty());
        maxValueTextField.disableProperty().bind(noValuesCheckBox.selectedProperty());
        limitAggressionCheckBox.disableProperty().bind(noValuesCheckBox.selectedProperty());
        reduceGapOnOvalsCheckBox.disableProperty().bind(noValuesCheckBox.selectedProperty());
        bindQualiCheckBox.disableProperty().bind(noValuesCheckBox.selectedProperty());
        limitToTextField.disableProperty().bind(limitAggressionCheckBox.selectedProperty().not());

        qualiExceedsTextField.disableProperty().bind(qualiExceedsRaceSkillCheckBox.selectedProperty().not());
        qualiExceedsRaceSkillCheckBox.disableProperty().bind(bindQualiCheckBox.selectedProperty().not());

        ToggleGroup nameGroup = new ToggleGroup();
        blankNamesRadioButton.setToggleGroup(nameGroup);
        useNAMeSRadioButton.setToggleGroup(nameGroup);
        fromLiveryNamesRadioButton.setToggleGroup(nameGroup);

        ToggleGroup randomGroup = new ToggleGroup();
        randomAllRadioButton.setToggleGroup(randomGroup);
        randomSkillRadioButton.setToggleGroup(randomGroup);

        ToggleGroup noiseGroup = new ToggleGroup();
        noNoiseRadioButton.setToggleGroup(noiseGroup);
        lowNoiseRadioButton.setToggleGroup(noiseGroup);
        highNoiseRadioButton.setToggleGroup(noiseGroup);

        emptyGridCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                generateGridCheckBox.selectedProperty().set(false);
                generateButton.setDisable(false);
            }
        });

        generateGridCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                emptyGridCheckBox.selectedProperty().set(false);
                if(vehicleClassChoiceBox.valueProperty().get() == null)
                    generateButton.setDisable(true);
            }
        });

        randomValuesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && rangeOfValuesCheckBox.isSelected())
                rangeOfValuesCheckBox.selectedProperty().set(false);
            else noValuesCheckBox.selectedProperty().set(!newValue && !rangeOfValuesCheckBox.isSelected());
        });

        rangeOfValuesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && randomValuesCheckBox.isSelected())
                randomValuesCheckBox.selectedProperty().set(false);
            else noValuesCheckBox.selectedProperty().set(!newValue && !randomValuesCheckBox.isSelected());
        });

        noValuesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            limitAggressionCheckBox.selectedProperty().set(false);
            bindQualiCheckBox.selectedProperty().set(false);
            qualiExceedsRaceSkillCheckBox.selectedProperty().set(false);
        });

        vehicleClassChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null)
                generateButton.setDisable(false);
        });

        noValuesCheckBox.disableProperty().set(true);
        noValuesCheckBox.setStyle("-fx-opacity: 1");

        generateGridCheckBox.selectedProperty().set(true);
        forEachLiveryCheckBox.selectedProperty().set(true);
        rangeOfValuesCheckBox.selectedProperty().set(true);
        useNAMeSRadioButton.selectedProperty().set(true);
        lowNoiseRadioButton.selectedProperty().set(true);

        vehicleClassChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
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
