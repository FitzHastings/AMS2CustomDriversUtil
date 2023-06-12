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

package net.dragondelve.customdriversutil.gui.editor;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.fx.FXObjectChooser;
import net.dragondelve.customdriversutil.fx.HybridChoiceHBox;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.DriverBase;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.Configurator;
import net.dragondelve.customdriversutil.util.LibraryManager;

/**
 * Driver Editor is designed to edit a single driver or a single track specific override if it is put into
 * trackOverrideMode. This is a controller for the fxml/editor/DriverEditor.fxml.
 */
public class DriverEditor {

    /**
     * Slider that determines the value of the Driver's Aggression. The values range from 0.0 to 1.0
     */
    @FXML
    private Slider aggressionSlider;

    /**
     * Textfield that displays the value of the Driver's Aggression.
     */
    @FXML
    private TextField aggressionTextField;

    /**
     * Slider that determines the value of the driver's blueFlagConceding. The values range from 0.0 to 1.0
     */
    @FXML
    private Slider blueFlagSlider;

    /**
     * TextField that displays the value of the Driver's blueFlagConceding.
     */
    @FXML
    private TextField blueFlagTextField;

    /**
     * Hybrid Choice Box for selecting or typing the driver's livery name.
     */
    @FXML
    private HybridChoiceHBox<ChoiceBox<String>> chooseLiveryHBox;

    /**
     * Slider that determines the driver's consistency. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider consistencySlider;

    /**
     * TextField that displays the value of the driver's consistency.
     */
    @FXML
    private TextField consistencyTextField;

    /**
     * Slider that determines the driver's defending. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider defendingSlider;

    /**
     * TextField that displays the value of the driver's defending.
     */
    @FXML
    private TextField defendingTextField;

    /**
     * TextField that displays the driver's country code.
     * This country code is used to display the flag in the game. Should use three-letter codes
     * for the desired country.
     */
    @FXML
    private TextField driverCountryTextField;

    /**
     * TextField that is bound to the driver's name property.
     */
    @FXML
    private TextField driverNameTextField;

    /**
     * TextField that displays the value of the driver's avoidanceOfMistakes.
     */
    @FXML
    private TextField mistakeAvoidanceTextField;

    /**
     * Slider that determines the driver's avoidanceOfForcedMistakes. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider forcedMistakeSlider;

    /**
     * TextField that displays the value of the driver's avoidanceOfForcedMistakes..
     */
    @FXML
    private TextField forcedMistakeTextField;

    /**
     * Slider that determines the driver's fuelManagement. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider fuelManagementSlider;

    /**
     * TextField that displays the value of the driver's fuelManagement..
     */
    @FXML
    private TextField fuelManagementTextField;

    /**
     * Slider that determines the driver's avoidanceOfMistakes. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider mistakeAvoidanceSlider;

    /**
     * CheckBox that determines whether the aggression value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideAggressionCheckBox;

    /**
     * CheckBox that determines whether the bleFlagConceding value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideBlueFlagCheckBox;

    /**
     * CheckBox that determines whether the country value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideCountryCheckBox;

    /**
     * CheckBox that determines whether the consistency value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideConsistencyCheckBox;

    /**
     * CheckBox that determines whether the defending value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideDefendingCheckBox;

    /**
     * CheckBox that determines whether the name value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideDriverNameCheckBox;

    /**
     * CheckBox that determines whether the avoidanceOfForcedMistakes value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideForcedMistakeAvoidanceCheckBox;

    /**
     * CheckBox that determines whether the fuelManagement value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideFuelManagementCheckBox;

    /**
     * CheckBox that determines whether the avoidanceOfMistakes value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideMistakeAvoidanceCheckBox;

    /**
     * CheckBox that determines whether the qualifyingSkill value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideQualiSkillCheckBox;

    /**
     * CheckBox that determines whether the raceSkill value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideRacingSkillCheckBox;

    /**
     * CheckBox that determines whether the stamina value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideStaminaCheckBox;

    /**
     * CheckBox that determines whether the startReaction value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideStartReactions;

    /**
     * CheckBox that determines whether the aggression value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideTyreManagementCheckBox;

    /**
     * CheckBox that determines whether the aggression value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideVehicleReliabilityCheckBox;
    /**
     * CheckBox that determines whether the weatherTyreChange value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideWeatherPitCheckBox;

    /**
     * CheckBox that determines whether the wetSkill value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox overrideWetSkillCheckbox;

    /**
     * Slider that determines the driver's qualifyingSkill. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider qualiSkillSlider;

    /**
     * TextField that displays the value of the driver's avoidanceOfForcedMistakes..
     */
    @FXML
    private TextField qualiSkillTextField;

    /**
     * Slider that determines the driver's racingSkill. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider racingSkillSlider;

    /**
     * TextField that displays the value of the driver's racingSkill..
     */
    @FXML
    private TextField racingSkillTextField;

    /**
     * Button that randomizes the values of the sliders. Performs randomizeDriverAction.
     */
    @FXML
    private Button randomizeButton;

    /**
     * rootPane of the editor that is used to apply the stylesheet to the entire editor.
     */
    @FXML
    private VBox rootPane;

    /**
     * Slider that determines the driver's stamina. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider staminaSlider;

    /**
     * TextField that displays the value of the driver's stamina.
     */
    @FXML
    private TextField staminaTextField;

    /**
     * Slider that determines the driver's startReactions. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider startReactionsSlider;

    /**
     * TextField that displays the value of the driver's startReactions.
     */
    @FXML
    private TextField startReactionsTextField;

    /**
     * Slider that determines the driver's tyreManagement. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider tyreManagementSlider;

    /**
     * TextField that displays the value of the driver's tyreManagement.
     */
    @FXML
    private TextField tyreManagementTextField;

    /**
     * CheckBox that determines whether the vehicleReliability value of the default driver should be
     * overridden or not.
     */
    @FXML
    private CheckBox vehicleReliabilityCheckBox;

    /**
     * TextField that displays the value of the driver's vehicleReliability.
     */
    @FXML
    private TextField vehicleReliabilityTextField;

    /**
     * Slider that determines the driver's vehicleReliability. Values range from 0.0 to 1.0. Actual accepted values have
     * a much broader range but this slider has the same default behaviour as the rest of the Sliders.
     */
    @FXML
    private Slider vehicleReliabilitySlider;

    /**
     * Slider that determines the driver's weatherTyreChanges. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider weatherPitSlider;

    /**
     * TextField that displays the value of the driver's vehicleReliability.
     */
    @FXML
    private TextField weatherPitTextField;

    /**
     * Slider that determines the driver's wetSkill. Values range from 0.0 to 1.0.
     */
    @FXML
    private Slider wetSkillSlider;

    /**
     * TextField that displays the value of the driver's wetSkill.
     */
    @FXML
    private TextField wetSkillTextField;

    /**
     * Driver that is currently being edited by this editor. His properties are bound bidirectionally to the controls,
     * and they have to be unbound with unbindDriver(Driver driver) if the value changes.
     */
    private DriverBase editedDriver;

    /**
     * A flag that determines whether this editor is run in the trackOverride mode or not. This flag should be set to
     * true if you want to edit a trackOverride. When setting this flag you have to already have set a driver, and you
     * need to provide a track or a String value of an xmlName of the track that is going to be actually edited.
     */
    private final BooleanProperty overrideMode = new SimpleBooleanProperty();

    /**
     * Choice box that allows the user to choose the driver's livery from the list instead of typing it.
     * Is a part of HybridChoiceHBox. it is only displayed if the CheckBox inside HybridChoiceHBox is selected.
     */
    private final ChoiceBox<String> liveryNameChoiceBox = new ChoiceBox<>();

    /**
     * Vehicle Class of the edited driver.
     */
    private final VehicleClass vehicleClass = new VehicleClass();
    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        driverNameTextField.disableProperty().bind(overrideDriverNameCheckBox.selectedProperty().not());
        driverCountryTextField.disableProperty().bind(overrideCountryCheckBox.selectedProperty().not());
        racingSkillSlider.disableProperty().bind(overrideRacingSkillCheckBox.selectedProperty().not());
        qualiSkillSlider.disableProperty().bind(overrideQualiSkillCheckBox.selectedProperty().not());
        aggressionSlider.disableProperty().bind(overrideAggressionCheckBox.selectedProperty().not());
        defendingSlider.disableProperty().bind(overrideDefendingCheckBox.selectedProperty().not());
        staminaSlider.disableProperty().bind(overrideStaminaCheckBox.selectedProperty().not());
        consistencySlider.disableProperty().bind(overrideConsistencyCheckBox.selectedProperty().not());
        startReactionsSlider.disableProperty().bind(overrideStartReactions.selectedProperty().not());
        wetSkillSlider.disableProperty().bind(overrideWetSkillCheckbox.selectedProperty().not());
        tyreManagementSlider.disableProperty().bind(overrideTyreManagementCheckBox.selectedProperty().not());
        fuelManagementSlider.disableProperty().bind(overrideFuelManagementCheckBox.selectedProperty().not());
        blueFlagSlider.disableProperty().bind(overrideBlueFlagCheckBox.selectedProperty().not());
        weatherPitSlider.disableProperty().bind(overrideWeatherPitCheckBox.selectedProperty().not());
        mistakeAvoidanceSlider.disableProperty().bind(overrideMistakeAvoidanceCheckBox.selectedProperty().not());
        forcedMistakeSlider.disableProperty().bind(overrideForcedMistakeAvoidanceCheckBox.selectedProperty().not());
        vehicleReliabilitySlider.disableProperty().bind(overrideVehicleReliabilityCheckBox.selectedProperty().not());

        racingSkillTextField.textProperty().bind(racingSkillSlider.valueProperty().asString("%.2f"));
        qualiSkillTextField.textProperty().bind(qualiSkillSlider.valueProperty().asString("%.2f"));
        aggressionTextField.textProperty().bind(aggressionSlider.valueProperty().asString("%.2f"));
        defendingTextField.textProperty().bind(defendingSlider.valueProperty().asString("%.2f"));
        staminaTextField.textProperty().bind(staminaSlider.valueProperty().asString("%.2f"));
        consistencyTextField.textProperty().bind(consistencySlider.valueProperty().asString("%.2f"));
        startReactionsTextField.textProperty().bind(startReactionsSlider.valueProperty().asString("%.2f"));
        wetSkillTextField.textProperty().bind(wetSkillSlider.valueProperty().asString("%.2f"));
        tyreManagementTextField.textProperty().bind(tyreManagementSlider.valueProperty().asString("%.2f"));
        fuelManagementTextField.textProperty().bind(fuelManagementSlider.valueProperty().asString("%.2f"));
        blueFlagTextField.textProperty().bind(blueFlagSlider.valueProperty().asString("%.2f"));
        weatherPitTextField.textProperty().bind(weatherPitSlider.valueProperty().asString("%.2f"));
        mistakeAvoidanceTextField.textProperty().bind(mistakeAvoidanceSlider.valueProperty().asString("%.2f"));
        forcedMistakeTextField.textProperty().bind(forcedMistakeSlider.valueProperty().asString("%.2f"));
        vehicleReliabilityTextField.textProperty().bind(vehicleReliabilitySlider.valueProperty().asString("%.2f"));

        racingSkillTextField.setEditable(false);
        qualiSkillTextField.setEditable(false);
        aggressionTextField.setEditable(false);
        defendingTextField.setEditable(false);
        staminaTextField.setEditable(false);
        consistencyTextField.setEditable(false);
        startReactionsTextField.setEditable(false);
        wetSkillTextField.setEditable(false);
        tyreManagementTextField.setEditable(false);
        fuelManagementTextField.setEditable(false);
        blueFlagTextField.setEditable(false);
        weatherPitTextField.setEditable(false);
        mistakeAvoidanceTextField.setEditable(false);
        forcedMistakeTextField.setEditable(false);
        vehicleReliabilityTextField.setEditable(false);

        randomizeButton.setOnAction(e->randomizeDriverAction());

        chooseLiveryHBox.initialize("Choose Livery", liveryNameChoiceBox);

        chooseLiveryHBox.getCheckBox().selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && vehicleClass.getLiveryNames().size() == 0) {
                FXObjectChooser<VehicleClass> vehicleClassObjectChooser = new FXObjectChooser<>();
                Stage chooserStage = new Stage();
                chooserStage.setTitle("Choose Vehicle Class");
                vehicleClassObjectChooser.setItems(LibraryManager.getInstance().getVehicleClassLibrary().getVehicleClasses());
                TableColumn<VehicleClass, String> vehicleClassTableColumn = new TableColumn<>();
                vehicleClassTableColumn.setText("Vehicle Class");
                vehicleClassTableColumn.setCellValueFactory(param -> param.getValue().nameProperty());
                vehicleClassObjectChooser.getTableView().getColumns().add(vehicleClassTableColumn);
                VehicleClass chosenVehicleClass =  vehicleClassObjectChooser.showChooseDialog();

                if (chosenVehicleClass != null)
                    setVehicleClass(chosenVehicleClass);
                else
                    chooseLiveryHBox.getCheckBox().selectedProperty().set(false);
            }
        });

        chooseLiveryHBox.getCheckBox().selectedProperty().set(Configurator.getInstance().getConfiguration().isChooseLivery());

        if (editedDriver == null)
            rootPane.setDisable(true);
    }

    /**
     * NOT a lightweight mutator method, unbinds the previous driver's properties and binds the new driver to the control
     * elements of this editor.
     * @param driver Driver to be edited.
     */
    public void setEditedDriver(DriverBase driver) {
        if (this.editedDriver != null)
            unbindDriver(this.editedDriver);
        if(driver != null) {
            overrideMode.set(!driver.getClass().equals(Driver.class));
            this.editedDriver = driver;
            bindDriver(editedDriver);
            rootPane.setDisable(false);
        } else
            rootPane.setDisable(true);
    }

    /**
     * Semi-lightweight mutator method.
     * @param vehicleClass Vehicle class of the edited driver/
     */
    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass.setLiveryNames(vehicleClass.getLiveryNames());
        this.vehicleClass.setName(vehicleClass.getName());
        this.vehicleClass.setXmlName(vehicleClass.getXmlName());
        chooseLiveryHBox.getChoiceBox().setItems(vehicleClass.getLiveryNames());
    }

    /**
     * Binds the given driver's properties to the control elements that are supposed to edit them. The bind will be done
     * bidirectionally, and you should call unbindDriver on the same driver after it is no longer being edited.
     * @param driver a driver whose properties are to be bind to the control elements of this editor.
     */
    private void bindDriver(DriverBase driver) {
        if (!overrideMode.get()) {
            chooseLiveryHBox.getTextField().textProperty().bindBidirectional(((Driver) driver).liveryNameProperty());
            chooseLiveryHBox.getChoiceBox().valueProperty().bindBidirectional(((Driver) driver).liveryNameProperty());
            if (((Driver) driver).liveryNameProperty().get() == null) {
                chooseLiveryHBox.getChoiceBox().valueProperty().set("");
            }
        }
        bindBaseProperties(driver);
    }

    /**
     * Unbinds the given driver's properties from the control elements of the editor. The bind will be unbound bidirectionally.
     * If a driver was bound with bindDriver then you should unbind it with this method.
     * @param driver a driver whose properties are to be unbound from the control elements of this editor.
     */
    private void unbindDriver(DriverBase driver) {
        if (!overrideMode.get()) {
            chooseLiveryHBox.getTextField().textProperty().unbindBidirectional(((Driver) driver).liveryNameProperty());
            chooseLiveryHBox.getChoiceBox().valueProperty().unbindBidirectional(((Driver) driver).liveryNameProperty());
        }
        unbindBaseProperties(driver);
    }

    /**
     * Binds all base properties that are shared between the Driver and TrackOverride. called from within bindDriver
     * @param driver a driverbase whose properties re to be bound to the control elements of theis editor.
     */
    private void bindBaseProperties(DriverBase driver) {
        driverNameTextField.textProperty()          .bindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()       .bindBidirectional(driver.countryProperty());
        racingSkillSlider.valueProperty()           .bindBidirectional(driver.raceSkillProperty());
        qualiSkillSlider.valueProperty()            .bindBidirectional(driver.qualifyingSkillProperty());
        aggressionSlider.valueProperty()            .bindBidirectional(driver.aggressionProperty());
        defendingSlider.valueProperty()             .bindBidirectional(driver.defendingProperty());
        staminaSlider.valueProperty()               .bindBidirectional(driver.staminaProperty());
        consistencySlider.valueProperty()           .bindBidirectional(driver.consistencyProperty());
        startReactionsSlider.valueProperty()        .bindBidirectional(driver.startReactionsProperty());
        wetSkillSlider.valueProperty()              .bindBidirectional(driver.wetSkillProperty());
        tyreManagementSlider.valueProperty()        .bindBidirectional(driver.tyreManagementProperty());
        fuelManagementSlider.valueProperty()        .bindBidirectional(driver.fuelManagementProperty());
        blueFlagSlider.valueProperty()              .bindBidirectional(driver.blueFlagConcedingProperty());
        weatherPitSlider.valueProperty()            .bindBidirectional(driver.weatherTyreChangeProperty());
        mistakeAvoidanceSlider.valueProperty()      .bindBidirectional(driver.avoidanceOfMistakesProperty());
        forcedMistakeSlider.valueProperty()         .bindBidirectional(driver.avoidanceOfForcedMistakesProperty());
        vehicleReliabilitySlider.valueProperty()    .bindBidirectional(driver.vehicleReliabilityProperty());


        overrideDriverNameCheckBox.selectedProperty()               .bindBidirectional(driver.getOverrideFlags().overrideNameProperty());
        overrideCountryCheckBox.selectedProperty()                  .bindBidirectional(driver.getOverrideFlags().overrideCountryProperty());
        overrideRacingSkillCheckBox.selectedProperty()              .bindBidirectional(driver.getOverrideFlags().overrideRaceSkillProperty());
        overrideQualiSkillCheckBox.selectedProperty()               .bindBidirectional(driver.getOverrideFlags().overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty()               .bindBidirectional(driver.getOverrideFlags().overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty()                .bindBidirectional(driver.getOverrideFlags().overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty()                  .bindBidirectional(driver.getOverrideFlags().overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty()              .bindBidirectional(driver.getOverrideFlags().overrideConsistencyProperty());
        overrideStartReactions.selectedProperty()                   .bindBidirectional(driver.getOverrideFlags().overrideStartReactionsProperty());
        overrideWetSkillCheckbox.selectedProperty()                 .bindBidirectional(driver.getOverrideFlags().overrideWetSkillProperty());
        overrideTyreManagementCheckBox.selectedProperty()           .bindBidirectional(driver.getOverrideFlags().overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty()           .bindBidirectional(driver.getOverrideFlags().overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty()                 .bindBidirectional(driver.getOverrideFlags().overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty()               .bindBidirectional(driver.getOverrideFlags().overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty()         .bindBidirectional(driver.getOverrideFlags().overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty()   .bindBidirectional(driver.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty()       .bindBidirectional(driver.getOverrideFlags().overrideVehicleReliabilityProperty());

        chooseLiveryHBox.disableProperty().bind(overrideMode);
    }

    /**
     * Unbinds all base properties that are shared between the Driver and TrackOverride. called from within umbindDriver
     * @param driver a driverbase whose properties re to be unbound to the control elements of theis editor.
     */
    private void unbindBaseProperties(DriverBase driver) {
        driverNameTextField.textProperty()          .unbindBidirectional(driver.nameProperty());
        driverCountryTextField.textProperty()       .unbindBidirectional(driver.countryProperty());
        racingSkillSlider.valueProperty()           .unbindBidirectional(driver.raceSkillProperty());
        qualiSkillSlider.valueProperty()            .unbindBidirectional(driver.qualifyingSkillProperty());
        aggressionSlider.valueProperty()            .unbindBidirectional(driver.aggressionProperty());
        defendingSlider.valueProperty()             .unbindBidirectional(driver.defendingProperty());
        staminaSlider.valueProperty()               .unbindBidirectional(driver.staminaProperty());
        consistencySlider.valueProperty()           .unbindBidirectional(driver.consistencyProperty());
        startReactionsSlider.valueProperty()        .unbindBidirectional(driver.startReactionsProperty());
        wetSkillSlider.valueProperty()              .unbindBidirectional(driver.wetSkillProperty());
        tyreManagementSlider.valueProperty()        .unbindBidirectional(driver.tyreManagementProperty());
        fuelManagementSlider.valueProperty()        .unbindBidirectional(driver.fuelManagementProperty());
        blueFlagSlider.valueProperty()              .unbindBidirectional(driver.blueFlagConcedingProperty());
        weatherPitSlider.valueProperty()            .unbindBidirectional(driver.weatherTyreChangeProperty());
        mistakeAvoidanceSlider.valueProperty()      .unbindBidirectional(driver.avoidanceOfMistakesProperty());
        forcedMistakeSlider.valueProperty()         .unbindBidirectional(driver.avoidanceOfForcedMistakesProperty());
        vehicleReliabilitySlider.valueProperty()    .unbindBidirectional(driver.vehicleReliabilityProperty());

        overrideDriverNameCheckBox.selectedProperty()               .unbindBidirectional(driver.getOverrideFlags().overrideNameProperty());
        overrideCountryCheckBox.selectedProperty()                  .unbindBidirectional(driver.getOverrideFlags().overrideCountryProperty());
        overrideRacingSkillCheckBox.selectedProperty()              .unbindBidirectional(driver.getOverrideFlags().overrideRaceSkillProperty());
        overrideQualiSkillCheckBox.selectedProperty()               .unbindBidirectional(driver.getOverrideFlags().overrideQualifyingSkillProperty());
        overrideAggressionCheckBox.selectedProperty()               .unbindBidirectional(driver.getOverrideFlags().overrideAggressionProperty());
        overrideDefendingCheckBox.selectedProperty()                .unbindBidirectional(driver.getOverrideFlags().overrideDefendingProperty());
        overrideStaminaCheckBox.selectedProperty()                  .unbindBidirectional(driver.getOverrideFlags().overrideStaminaProperty());
        overrideConsistencyCheckBox.selectedProperty()              .unbindBidirectional(driver.getOverrideFlags().overrideConsistencyProperty());
        overrideStartReactions.selectedProperty()                   .unbindBidirectional(driver.getOverrideFlags().overrideStartReactionsProperty());
        overrideWetSkillCheckbox.selectedProperty()                 .unbindBidirectional(driver.getOverrideFlags().overrideWetSkillProperty());
        overrideTyreManagementCheckBox.selectedProperty()           .unbindBidirectional(driver.getOverrideFlags().overrideTyreManagementProperty());
        overrideFuelManagementCheckBox.selectedProperty()           .unbindBidirectional(driver.getOverrideFlags().overrideFuelManagementProperty());
        overrideBlueFlagCheckBox.selectedProperty()                 .unbindBidirectional(driver.getOverrideFlags().overrideBlueFlagConcedingProperty());
        overrideWeatherPitCheckBox.selectedProperty()               .unbindBidirectional(driver.getOverrideFlags().overrideWeatherTyreChangeProperty());
        overrideMistakeAvoidanceCheckBox.selectedProperty()         .unbindBidirectional(driver.getOverrideFlags().overrideAvoidanceOfMistakesProperty());
        overrideForcedMistakeAvoidanceCheckBox.selectedProperty()   .unbindBidirectional(driver.getOverrideFlags().overrideAvoidanceOfForcedMistakesProperty());
        overrideVehicleReliabilityCheckBox.selectedProperty()       .unbindBidirectional(driver.getOverrideFlags().overrideVehicleReliabilityProperty());
    }

    /**
     * Randomizes the traits of the driver selected.
     */
    private void randomizeDriverAction() {
        if (editedDriver == null)
            return;

        editedDriver.randomize();
    }
}
