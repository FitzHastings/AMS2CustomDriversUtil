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

package net.dragondelve.mabelfx.button;

import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * A type of button that controls a JavaFX Stage state.
 * IE: closes it, minimizes it, maximizes it, etc.
 */
public class StageControlButton extends Button {
    protected Stage stage;

    /**
     * Default constructor. Stage is set to null and button Text is set to "".
     * You should set the controlled stage later if using this constructor with setStage.
     */
    public StageControlButton() {
        this("");
    }

    /**
     * Stage is set to null. You should set the controlled stage later if using this constructor with setStage.
     * @param text text that is displayed on the button.
     */
    public StageControlButton(String text) {
        this(text, null);
    }

    /**
     *
     * @param text text that is displayed on the button.
     * @param stage the JavaFX stage that will be controlled by this button.
     */
    public StageControlButton(String text, Stage stage) {
        super(text);
        this.stage = stage;
        initBehaviour();
    }

    /**
     * Semi-Lightweight Mutator Method.
     * @param stage the JavaFX stage that will be controlled by this button.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Overridden in subclasses to init the setOnAction to a particular action
     */
    protected void initBehaviour() {

    }
}
