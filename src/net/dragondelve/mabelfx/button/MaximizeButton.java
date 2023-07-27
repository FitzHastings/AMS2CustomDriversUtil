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

import net.dragondelve.mabelfx.util.MabelUtil;
import java.util.logging.Level;

/**
 * Button that maximizes the stage that is provided to it on action if it isn't maximized already.
 * If the stage provided is already maximized it unmaximizes it.
 */
public class MaximizeButton extends StageControlButton{

    /**
     * Initializes the behaviour of the button.
     * onAction if the stage provided to it is not null it will maximize that stage if it isn't maximized.
     * If the stage provided to it is already maximized it will unmaximize it.
     */
    @Override
    protected void initBehaviour() {
        this.setOnAction(e-> {
            if(stage != null)
                stage.setMaximized(!stage.isMaximized());
            else {
                MabelUtil.DEFAULT_LOGGER.log(Level.WARNING, "MaximizeButton attempted to maximize a stage that was equal to null.");
            }
        });
    }
}
