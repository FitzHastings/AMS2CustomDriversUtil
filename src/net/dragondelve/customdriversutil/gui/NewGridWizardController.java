package net.dragondelve.customdriversutil.gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import net.dragondelve.customdriversutil.model.VehicleClass;
import net.dragondelve.customdriversutil.util.DDUtil;

public class NewGridWizardController {
    @FXML
    private RadioButton blankNamesRadioButton;

    @FXML
    private RadioButton useNAMeSPlusRadioButton;

    @FXML
    private Button generateButton;

    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;

    @FXML
    private RadioButton noNoizeRadioButton;

    @FXML
    private CheckBox emptyGridCheckBox;

    @FXML
    private CheckBox qualiExceedsRaceSkillCheckBox;

    @FXML
    private CheckBox reduceGapOnOvalsCheckBox;

    @FXML
    private CheckBox bindQualiCheckBox;

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
    private RadioButton highNoizeRadioButton;

    @FXML
    private RadioButton useNAMeSRadioButton;

    @FXML
    private RadioButton randomSkillRadioButton;

    @FXML
    private RadioButton lowNoizeRadioButton;

    @FXML
    private TextField amountCheckBox;

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
    }
}
