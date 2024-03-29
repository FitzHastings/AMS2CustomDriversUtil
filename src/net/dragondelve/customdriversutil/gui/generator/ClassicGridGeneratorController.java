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

package net.dragondelve.customdriversutil.gui.generator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.NumberStringConverter;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.tools.generator.*;
import net.dragondelve.customdriversutil.util.LibraryManager;
import net.dragondelve.customdriversutil.util.TooltipUtil;

/**
 * Controller for fxml/ClassicGridGenerator.fxml
 */
public class ClassicGridGeneratorController implements GeneratorController {
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
     * Button that performs generateAction() on action.
     * Passed from the parent controller.
     */
    private final Button generateButton;
    /**
     * Radio button that determines if the generated grid is going to use blank names as a source.
     */
    @FXML
    private RadioButton blankNamesRadioButton;
    /**
     * choiceBox that determines what vehicleClass is going to be used when generating a new grid.
     */
    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;
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
     * RadioButton that determines if no values should be set to the grid when generating it.
     */
    @FXML
    private RadioButton noValuesRadioButton;
    /**
     * TextField that displays and allows the user to edit the values of maxValue doubleProperty. maxValue determines the
     * max value that can be set on any property.
     */
    @FXML
    private TextField maxValueTextField;
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
     * RadioButton that determines if drivers in the generated grid should have names borrowed from NAMeS resources if such
     * resources are available for this VehicleClass.
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
     * Slider that determines the amount of noise used to generate these items.
     */
    @FXML
    private Slider noiseSlider;
    /**
     * Text field that displays the noise value set in the noiseValueSlider
     */
    @FXML
    private TextField noiseTextField;
    /**
     * TextField that displays and allows user to edit the nDrivers property of GeneratorSettings that will be passed to
     * the GridGenerator. This value only matters if forEachLiveryCheckBox is not selected.
     */
    @FXML
    private TextField amountTextField;

    /**
     * Constructor that takes the generateButton from the parent controller.
     *
     * @param generateButton Button that performs generateAction() on action.
     */
    public ClassicGridGeneratorController(Button generateButton) {
        this.generateButton = generateButton;
    }

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * Initialize JavaFX calls method automatically when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        amountTextField.disableProperty().bind(forEachLiveryCheckBox.selectedProperty());

        randomSkillRadioButton.disableProperty().bind(randomValuesCheckBox.selectedProperty().not());
        randomAllRadioButton.disableProperty().bind(randomValuesCheckBox.selectedProperty().not());

        noiseSlider.disableProperty().bind(rangeOfValuesCheckBox.selectedProperty().not());
        noiseTextField.disableProperty().bind(rangeOfValuesCheckBox.selectedProperty().not());

        minValueTextField.disableProperty().bind(noValuesRadioButton.selectedProperty());
        maxValueTextField.disableProperty().bind(noValuesRadioButton.selectedProperty());
        limitAggressionCheckBox.disableProperty().bind(noValuesRadioButton.selectedProperty());
        reduceGapOnOvalsCheckBox.disableProperty().bind(noValuesRadioButton.selectedProperty());
        bindQualiCheckBox.disableProperty().bind(noValuesRadioButton.selectedProperty());
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

        randomValuesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue) && rangeOfValuesCheckBox.isSelected())
                rangeOfValuesCheckBox.selectedProperty().set(false);
            else noValuesRadioButton.selectedProperty().set(!newValue && !rangeOfValuesCheckBox.isSelected());
        });

        rangeOfValuesCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue) && randomValuesCheckBox.isSelected())
                randomValuesCheckBox.selectedProperty().set(false);
            else noValuesRadioButton.selectedProperty().set(!newValue && !randomValuesCheckBox.isSelected());
        });

        noValuesRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            limitAggressionCheckBox.selectedProperty().set(false);
            bindQualiCheckBox.selectedProperty().set(false);
            qualiExceedsRaceSkillCheckBox.selectedProperty().set(false);
        });

        noValuesRadioButton.disableProperty().set(true);
        noValuesRadioButton.setStyle("-fx-opacity: 1");

        noiseTextField.textProperty().bind(noiseSlider.valueProperty().asString("%.2f"));
        noiseTextField.setEditable(false);

        vehicleClassChoiceBox.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());

        amountTextField.textProperty().bindBidirectional(generatorSettings.nDriversProperty(), new NumberStringConverter());
        limitToTextField.textProperty().bindBidirectional(generatorSettings.aggressionLimitProperty(), new NumberStringConverter());
        qualiExceedsTextField.textProperty().bindBidirectional(generatorSettings.boundSkillsGapProperty(), new NumberStringConverter());
        minValueTextField.textProperty().bindBidirectional(minValue, new NumberStringConverter());
        maxValueTextField.textProperty().bindBidirectional(maxValue, new NumberStringConverter());
        reduceGapOnOvalsCheckBox.selectedProperty().bindBidirectional(generatorSettings.reduceGapsOnOvalsProperty());
        bindQualiCheckBox.selectedProperty().bindBidirectional(generatorSettings.bindQualiAndRaceSkillsProperty());

        forEachLiveryCheckBox.selectedProperty().set(true);
        rangeOfValuesCheckBox.selectedProperty().set(true);
        useNAMeSRadioButton.selectedProperty().set(true);
        noiseSlider.setValue(0.2);
        generatorSettings.aggressionLimitProperty().set(0.8);
        generatorSettings.boundSkillsGapProperty().set(0.1);

        vehicleClassChoiceBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                generateButton.setDisable(false);
                if (newValue.isModded()) {
                    if (useNAMeSRadioButton.isSelected())
                        fromLiveryNamesRadioButton.selectedProperty().set(true);
                } else
                    useNAMeSRadioButton.visibleProperty().set(true);
                useNAMeSRadioButton.visibleProperty().set(!newValue.isModded());
                generatorSettings.nDriversProperty().set(newValue.getLiveryNames().size());
            }
        });
        initTooltips();
    }

    /**
     * Method that determines if the classic grid generator will generate a valid grid with the current settings
     *
     * @return true if the grid can be generated, false otherwise.
     */
    public boolean isGoodToGenerate() {
        return vehicleClassChoiceBox.valueProperty().get() != null;
    }

    /**
     * Lightweight accessor method.
     *
     * @return RadioButton that determines if drivers in the generated grid should have names borrowed from NAMeS resources if such
     * resources are available for this VehicleClass.
     */
    public RadioButton getUseNAMeSRadioButton() {
        return useNAMeSRadioButton;
    }


    /**
     * Method that is called when the user clicks the generateButton.
     *
     * @return GridGenerator that is created using the settings provided by the user.
     */
    public GridGenerator createGridGenerator() {
        generatorSettings.setVehicleClass(vehicleClassChoiceBox.valueProperty().get());

        if (forEachLiveryCheckBox.isSelected())
            generatorSettings.nDriversProperty().set(vehicleClassChoiceBox.valueProperty().get().getLiveryNames().size());

        generatorSettings.useNAMeSProperty().set(useNAMeSRadioButton.isSelected());
        generatorSettings.fromLiveryNamesProperty().set(fromLiveryNamesRadioButton.isSelected());

        ValueGenerator generator = null;

        if (generatorSettings.getnDrivers() > generatorSettings.getVehicleClass().getLiveryNames().size())
            generatorSettings.nDriversProperty().set(generatorSettings.getVehicleClass().getLiveryNames().size());

        if (rangeOfValuesCheckBox.isSelected())
            generator = new RangeValueGenerator(generatorSettings.getnDrivers(), noiseSlider.getValue());
        else if (randomValuesCheckBox.isSelected())
            generator = new RandomValueGenerator();

        if (generator != null)
            generator.setLimits(minValue.get(), maxValue.get());

        return new GridGenerator(generatorSettings, generator);
    }

    /**
     * Method that initializes all the tooltips for the visual elements.
     */
    private void initTooltips() {
        reduceGapOnOvalsCheckBox.setTooltip(TooltipUtil.REDUCE_GAP_ON_OVALS_TOOLTIP);

        vehicleClassChoiceBox.setTooltip(TooltipUtil.CHOOSE_VEHICLE_CLASS_TOOLTIP);
        forEachLiveryCheckBox.setTooltip(TooltipUtil.FOR_EACH_LIVERY_TOOLTIP);
        amountTextField.setTooltip(TooltipUtil.N_DRIVERS_TOOLTIP);

        randomValuesCheckBox.setTooltip(TooltipUtil.RANDOM_VALUES_TOOLTIP);
        randomSkillRadioButton.setTooltip(TooltipUtil.RANDOM_SKILL_TOOLTIP);
        randomAllRadioButton.setTooltip(TooltipUtil.RANDOM_ALL_TOOLTIP);

        rangeOfValuesCheckBox.setTooltip(TooltipUtil.RANGE_OF_VALUES_TOOLTIP);
        noiseSlider.setTooltip(TooltipUtil.NOISE_TOOLTIP);
        noiseTextField.setTooltip(TooltipUtil.NOISE_TOOLTIP);

        noValuesRadioButton.setTooltip(TooltipUtil.NO_VALUES_TOOLTIP);

        minValueTextField.setTooltip(TooltipUtil.MIN_GENERATED_VALUE_TOOLTIP);
        maxValueTextField.setTooltip(TooltipUtil.MAX_GENERATED_VALUE_TOOLTIP);
        limitAggressionCheckBox.setTooltip(TooltipUtil.LIMIT_AGGRESSION_TOOLTIP);
        limitToTextField.setTooltip(TooltipUtil.LIMIT_AGGRESSION_TO_TOOLTIP);

        bindQualiCheckBox.setTooltip(TooltipUtil.BIND_QUALI_SKILL_TOOLTIP);
        qualiExceedsRaceSkillCheckBox.setTooltip(TooltipUtil.QUALI_SKILL_EXCEEDS_TOOLTIP);
        qualiExceedsTextField.setTooltip(TooltipUtil.EXCEEDS_BY_AMOUNT_TOOLTIP);

        blankNamesRadioButton.setTooltip(TooltipUtil.BLANK_NAMES_TOOLTIP);
        useNAMeSRadioButton.setTooltip(TooltipUtil.USE_NAMES_TOOLTIP);
        fromLiveryNamesRadioButton.setTooltip(TooltipUtil.FROM_LIVERY_NAME_TOOLTIP);
    }
}
