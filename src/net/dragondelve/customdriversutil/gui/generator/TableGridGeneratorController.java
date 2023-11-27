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

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.model.VehicleClass;

public class TableGridGeneratorController {
    @FXML
    private Slider maxRaceSkillSlider;

    @FXML
    private TextField maxRaceSkillTextField;

    @FXML
    private CheckBox limitAgressionCheckBox;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<VehicleClass> vehicleClassChoiceBox;

    @FXML
    private TextField limitToTextFIeld;

    @FXML
    private Slider noizeSlider;

    @FXML
    private CheckBox qualiExceedsRaceCheckbox;

    @FXML
    private TableView<Driver> driversTable;

    @FXML
    private TextField exceedsByTextField;

    @FXML
    private TableColumn<Driver, String> nameColumn;

    @FXML
    private HBox bindQualiCheckBox;

    @FXML
    private TableColumn<Driver, Integer> pointsColumn;

    @FXML
    private Button removeButton;

    @FXML
    private TableColumn<Driver, String> liveryColumn;

    @FXML
    private TextField noizeTextField;

    @FXML
    public void initialize() {

    }
}
