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

package net.dragondelve.mabelfx;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import net.dragondelve.mabelfx.util.MabelUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * HBox that contains a list of checkboxes split into groups separated by spaces between each group.
 */
public class CheckBoxHBox extends HBox {

    /**
     * CheckBoxes that are displayed inside CheckBoxHBox
     */
    private List<CheckBox> checkBoxes;

    /**
     * Creates a new instance of CheckBoxHBox. does not provide default values. if you use this constructor you will
     * still need to call both initCheckBoxes and initProperties for CheckBoxHBox to work properly.
     */
    public CheckBoxHBox() {
        this(null, null);
    }

    /**
     * Creates a new instance of CheckBoxHBox.
     * @param checkBoxes CheckBoxes tho be displayed inside CheckBoxHBox
     * @param booleanProperties Boolean properties to be bound to the checkBoxes selected property.
     */
    public CheckBoxHBox(List<CheckBox> checkBoxes, List<BooleanProperty> booleanProperties) {
        this(checkBoxes, booleanProperties, 0);
    }

    /**
     * Creates a new instance of CheckBoxHBox.
     * @param checkBoxes CheckBoxes tho be displayed inside CheckBoxHBox
     * @param booleanProperties Boolean properties to be bound to the checkBoxes selected property.
     * @param split Defines how many check boxes will be grouped together before a split. if split < 1 it will not create any breaks
     */
    public CheckBoxHBox(List<CheckBox> checkBoxes, List<BooleanProperty> booleanProperties, int split) {
        initCheckBoxes(checkBoxes, split);
        initProperties(booleanProperties);
    }

    /**
     * Initializes CheckBoxHBox with a given list of checkBoxes.
     * @param checkBoxes CheckBoxes tho be displayed inside CheckBoxHBox
     */
    public void initCheckBoxes(List<CheckBox> checkBoxes) {
        initCheckBoxes(checkBoxes, 0);
    }

    /**
     * Initializes CheckBoxHBox with a given list of checkBoxes. Will insert a break every split of checkBoxes for visual
     * clarity.
     * @param checkBoxes CheckBoxes tho be displayed inside CheckBoxHBox
     * @param split Defines how many check boxes will be grouped together before a split. if split < 1 it will not create any breaks
     */
    public void initCheckBoxes(List<CheckBox> checkBoxes, int split) {
        if (checkBoxes == null)
            return;
        this.checkBoxes = checkBoxes;
        this.getChildren().clear();

        if (split > 0) {
            this.setSpacing(20);
            int nChunks = checkBoxes.size() / split;
            for (int i = 0; i < nChunks; i++) {
                HBox hBox = new HBox();
                hBox.setSpacing(10);
                this.getChildren().add(hBox);
                for (int k = 0; k < split; k++) {
                    int index = i * split + k;
                    if (index >= checkBoxes.size())
                        return;
                    hBox.getChildren().add(checkBoxes.get(index));
                }
            }
        } else {
            this.setSpacing(10);
            this.getChildren().addAll(checkBoxes);
        }
    }

    /**
     * Binds the selectedProperty of checkBoxes contained in CheckBoxHBox to the given list of booleanProperties.
     * @param booleanProperties Boolean properties to be bound to the checkBoxes selected property.
     */
    public void initProperties(List<BooleanProperty> booleanProperties) {
        if (booleanProperties == null)
            return;
        if (booleanProperties.size() != checkBoxes.size()) {
            MabelUtil.DEFAULT_LOGGER.log(Level.WARNING, "Cannot set properties, number of check boxes and number of properties does not match");
            return;
        }

        for (int i = 0; i < checkBoxes.size(); i++) {
            checkBoxes.get(i).selectedProperty().bindBidirectional(booleanProperties.get(i));
        }
    }

    /**
     * Lightweight accessor method.
     * @return CheckBoxes that are displayed inside CheckBoxHBox
     */
    public List<CheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    /**
     * Creates an arbitrary amount of checkboxes and puts them a list. Checkboxes are given "list-check-box" styel class
     * so that they can be more appropriately styled for the use in a list with css.
     * @param amount amount of checkBoxes to be generated.
     * @return List that contains an amount of checkboxes equal to amount passed.
     */
    public static List<CheckBox> generateCheckBoxes(int amount) {
        List<CheckBox> checkBoxes = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            CheckBox checkBox = new CheckBox();
            checkBox.getStyleClass().add("list-check-box");
            checkBoxes.add(checkBox);
        }
        return checkBoxes;
    }
}
