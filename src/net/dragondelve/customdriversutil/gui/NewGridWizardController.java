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
    /**
     * Radio button that determines if the generated grid is going to use blank names as a source.
     */
    @FXML
    private RadioButton blankNamesRadioButton;

    /**
     * Button that erforms generateAction() on action.
     */
    @FXML
    private Button generateButton;

    /**
     * choiceBox that determines what vehicleClass is going to be used when generating a new grid.
     */
    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;

    /**
     * Radio button that determines if noNoise should be used when generating values for a grid when using rangeValueGenerator.
     */
    @FXML
    private RadioButton noNoiseRadioButton;

    /**
     * CheckBox that determines if the user wants to start with a blank grid.
     */
    @FXML
    private CheckBox emptyGridCheckBox;

    /**
     * CheckBox that determines if QualiSkill should exceed raceSkill if those values are bound.
     */
    @FXML
    private CheckBox qualiExceedsRaceSkillCheckBox;

    /**
     * CheckBox that determines if the GridGenerator will generate trackOverrides for ovals, reducing the gap in
     * race skill and quali skill in half.
     */
    @FXML
    private CheckBox reduceGapOnOvalsCheckBox;

    /**
     * CheckBox that determines if the raceskill and qualiSkill properties of every Driver in the grid should be bound
     * instead of being generated independently.
     */
    @FXML
    private CheckBox bindQualiCheckBox;

    /**
     * CheckBox that determines if aggression should be limited to a particular value set by the limitToTextField.
     */
    @FXML
    private CheckBox limitAggressionCheckBox;

    /**
     * TextField that displays and allows the user to edit the aggressionLimit property that determines tha maximum
     * value that each driver's aggression property can have.
     */
    @FXML
    private TextField limitToTextField;

    /**
     * CheckBox that determines if no values should be set to the grid when generating it.
     */
    @FXML
    private CheckBox noValuesCheckBox;

    /**
     * TextField that displays and allows the user to edit the values of maxValue doubleProperty. maxValue determines the
     * max value that can be set on any property.
     */
    @FXML
    private TextField maxValueTextField;

    /**
     * CheckBox that determines if the user wants a grid to be generated using GridGenerator.
     */
    @FXML
    private CheckBox generateGridCheckBox;

    /**
     * The root pane of this window, used to apply the CSS resource to this entire scene.
     */
    @FXML
    private VBox rootPane;

    /**
     * CheckBox that determines if the RandomValuesGenerator is going to be used to generate the values of the new
     * grid.
     */
    @FXML
    private CheckBox randomValuesCheckBox;

    /**
     * TextField that determines by how much the quali skill will exceed race skill if those values are bound.
     */
    @FXML
    private TextField qualiExceedsTextField;

    /**
     * RadioButton that determines if all properties of generated drivers are going to be provided with RandomValueGenerator.
     */
    @FXML
    private RadioButton randomAllRadioButton;

    /**
     * CheckBox that determines if the amount of drivers generated is going to be equal to how many liveries there are in
     * the class.
     */
    @FXML
    private CheckBox forEachLiveryCheckBox;

    /**
     * RadioButton that determines if drivers in the generated grid should have generated names based on their livery name.
     */
    @FXML
    private RadioButton fromLiveryNamesRadioButton;

    /**
     * CheckBox that determines if RangeValueGenerator should be used to generate values for the generated grid.
     */
    @FXML
    private CheckBox rangeOfValuesCheckBox;

    /**
     * TextField that displays and allows the user to edit the values of minValue doubleProperty. maxValue determines the
     * minimum value that can be set on any property.
     */
    @FXML
    private TextField minValueTextField;

    /**
     * Radio button that determines if high noise should be used when generating values for a grid when using rangeValueGenerator.
     */
    @FXML
    private RadioButton highNoiseRadioButton;

    /**
     * RadioButton that determines if drivers in the generated grid should have names borrowed from NAMeS resources if such
     * resources are available for this VehicleClass..
     */
    @FXML
    private RadioButton useNAMeSRadioButton;

    /**
     * RadioButton that determines if RandomValueGenerator should be only determinning the race skill of all drivers generated
     * by this item.
     */
    @FXML
    private RadioButton randomSkillRadioButton;

    /**
     * Radio button that determines if low noise should be used when generating values for a grid when using rangeValueGenerator.
     */
    @FXML
    private RadioButton lowNoiseRadioButton;

    /**
     * TextField that displays and allows user to edit the nDrivers property of GeneratorSettings that will be passed to
     * the GridGenerator. This value only matters if forEachLiveryCheckBox is not selecteed.
     */
    @FXML
    private TextField amountTextField;

    /**
     * Pane that contains the settings for grid generation. Used for disabling all generator settings if empty grid is
     * selected by the user.
     */
    @FXML
    private GridPane generateGridPane;

    /**
     * Label that displays the notice for NAMeS files if useNAMeS radioButton is selected.
     */
    @FXML
    private Label namesNoticeLabel;

    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    /**
     * Minimum value of all driver properties to be used in generating the new Grid.
     */
    private final DoubleProperty minValue = new SimpleDoubleProperty(0.0);
    /**
     * Maximum value of all driver properties to be used in generating the new Grid.
     */
    private final DoubleProperty maxValue = new SimpleDoubleProperty(1.0);

    /**
     * Generator settings that are passed to the grid generator if the user wants to generate a new grid.
     */
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
                if (vehicleClassChoiceBox.valueProperty().get() == null)
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
            if (newValue != null)
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


    /**
     * Performed by the GenerateButton.
     * Uses the currently set GeneratorSettings to generate a new grid and advance to the main window passing that grid
     * to the controller of the main window.
     */
    private void generateAction() {
        if (emptyGridCheckBox.isSelected()) {
            loadMainWindow(new Grid());
            return;
        }

        generatorSettings.setVehicleClass(vehicleClassChoiceBox.valueProperty().get());

        if (forEachLiveryCheckBox.isSelected())
            generatorSettings.nDriversProperty().set(vehicleClassChoiceBox.valueProperty().get().getLiveryNames().size());

        generatorSettings.useNAMeSProperty().set(useNAMeSRadioButton.isSelected());
        generatorSettings.fromLiveryNamesProperty().set(fromLiveryNamesRadioButton.isSelected());

        ValueGenerator generator = null;

        if (generatorSettings.getnDrivers() > generatorSettings.getVehicleClass().getLiveryNames().size())
            generatorSettings.nDriversProperty().set(generatorSettings.getVehicleClass().getLiveryNames().size());

        int nDrivers = generatorSettings.getnDrivers();
        if (rangeOfValuesCheckBox.isSelected()) {
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

    /**
     * Loads the main window and sets the provided grid as the edited grid in the main window.
     * @param generatorGrid Grid generated by the GridGenerator or a new empty grid if the user wants to start with an empty grid.
     */
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
