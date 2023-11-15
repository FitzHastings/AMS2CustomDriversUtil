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

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Hybrid Combo Box is a Combo box with a checkbox inside an HBox. if the checkbox is set then Combo Box is
 * displayed. if the checkbox is not selected then it displays a TextField instead.
 *
 * @param <T> Combo box that will be displayed if the CheckBox is selected.
 */
public class HybridComboBox<T extends ComboBox<?>> extends HBox {
    /**
     * CheckBox that controls whether the ComboBox is shown to the user or the TextField.
     */
    private final CheckBox checkBox = new CheckBox();
    /**
     * TextField that is displayed to the user if the CheckBox is NOT selected.
     */
    private final TextField textField = new TextField();
    /**
     * Combo Box that is displayed to the user if the CheckBox is selected.
     */
    private T comboBox;

    /**
     * Initializes the HybridCheckBox. You must call this method before the v is shown on a Stage.
     * Binds the comboBox and the TextField with the CheckBox and sets the checkBox's text.
     *
     * @param checkBoxText Text that is going to be displayed next to the CheckBox.
     * @param comboBox     comboBox that is going to be shown if the CheckBox is selected.
     */
    public void initialize(String checkBoxText, T comboBox) {
        this.comboBox = comboBox;
        HBox.setHgrow(comboBox, Priority.ALWAYS);
        HBox.setHgrow(textField, Priority.ALWAYS);

        this.getChildren().addAll(textField, checkBox);
        comboBox.setMaxWidth(Double.MAX_VALUE);

        checkBox.setText(checkBoxText);
        //Makes it so the label of the checkbox should be always visible if it's possible in the current layout.
        checkBox.setMinWidth(checkBox.getPrefWidth());
        checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                this.getChildren().remove(textField);
                this.getChildren().add(0, comboBox);
            } else {
                this.getChildren().remove(comboBox);
                this.getChildren().add(0, textField);
            }
        });
    }

    /**
     * Lightweight accessor method.
     *
     * @return Combo Box that is displayed to the user if the CheckBox is selected.
     */
    public T getComboBox() {
        return comboBox;
    }

    /**
     * Lightweight accessor method.
     *
     * @return CheckBox that controls whether the ComboBox is shown to the user or the TextField.
     */
    public CheckBox getCheckBox() {
        return checkBox;
    }

    /**
     * Lightweight accessor method.
     *
     * @return TextField that is displayed to the user if the CheckBox is NOT selected.
     */
    public TextField getTextField() {
        return textField;
    }
}
