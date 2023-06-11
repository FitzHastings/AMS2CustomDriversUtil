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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.model.generator.*;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.LibraryManager;

import java.io.IOException;

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
    private TextField amountTextField;

    @FXML
    private GridPane generateGridPane;

    @FXML
    private Label namesNoticeLabel;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    DoubleProperty minValue = new SimpleDoubleProperty(0.0);
    DoubleProperty maxValue = new SimpleDoubleProperty(1.0);

    private final GeneratorSettings generatorSettings = new GeneratorSettings();

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        namesNoticeLabel.visibleProperty().bind(useNAMeSRadioButton.selectedProperty());

        generateGridPane.disableProperty().bind(generateGridCheckBox.selectedProperty().not());
        amountTextField.disableProperty().bind(forEachLiveryCheckBox.selectedProperty());

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

        vehicleClassChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());

        generateButton.setOnAction(e -> generateAction());

        amountTextField.textProperty().bindBidirectional(generatorSettings.nDriversProperty(), new NumberStringConverter());
        limitToTextField.textProperty().bindBidirectional(generatorSettings.aggressionLimitProperty(), new NumberStringConverter());
        qualiExceedsTextField.textProperty().bindBidirectional(generatorSettings.boundSkillsGapProperty(), new NumberStringConverter());
        minValueTextField.textProperty().bindBidirectional(minValue, new NumberStringConverter());
        maxValueTextField.textProperty().bindBidirectional(maxValue, new NumberStringConverter());
        reduceGapOnOvalsCheckBox.selectedProperty().bindBidirectional(generatorSettings.reduceGapsOnOvalsProperty());
        bindQualiCheckBox.selectedProperty().bindBidirectional(generatorSettings.bindQualiAndRaceSkillsProperty());

        generateGridCheckBox.selectedProperty().set(true);
        forEachLiveryCheckBox.selectedProperty().set(true);
        rangeOfValuesCheckBox.selectedProperty().set(true);
        useNAMeSRadioButton.selectedProperty().set(true);
        lowNoiseRadioButton.selectedProperty().set(true);
    }

    /**
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    private void generateAction() {
        if(emptyGridCheckBox.isSelected()) {
            loadMainWindow(new Grid());
            return;
        }

        generatorSettings.setVehicleClass(vehicleClassChoiceBox.valueProperty().get());

        if (forEachLiveryCheckBox.isSelected())
            generatorSettings.nDriversProperty().set(vehicleClassChoiceBox.valueProperty().get().getLiveryNames().size());

        generatorSettings.useNAMeSProperty().set(useNAMeSRadioButton.isSelected());
        generatorSettings.fromLiveryNamesProperty().set(fromLiveryNamesRadioButton.isSelected());

        ValueGenerator generator = null;

        if (rangeOfValuesCheckBox.isSelected()) {
            int nDrivers = generatorSettings.getnDrivers();
            if (nDrivers > generatorSettings.getVehicleClass().getLiveryNames().size())
                nDrivers = generatorSettings.getVehicleClass().getLiveryNames().size();

            if (noNoiseRadioButton.isSelected())
                generator = new RangeValueGenerator(nDrivers, 0);
            else if (lowNoiseRadioButton.isSelected())
                generator = new RangeValueGenerator(nDrivers, 0.2);
            else
                generator = new RangeValueGenerator(nDrivers, 0.4);
        } else if (randomValuesCheckBox.isSelected())
            generator = new RandomValueGenerator();

        if (generator != null)
            generator.setLimits(minValue.get(), maxValue.get());

        GridGenerator gridGenerator = new GridGenerator(generatorSettings, generator);

        loadMainWindow(gridGenerator.generateNewGrid());
    }

    private void loadMainWindow(Grid generatorGrid) {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
        CustomDriverUtilController controller = new CustomDriverUtilController();
        controller.setStage(stage);
        controller.setEditedGrid(generatorGrid);
        loader.setController(controller);
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
