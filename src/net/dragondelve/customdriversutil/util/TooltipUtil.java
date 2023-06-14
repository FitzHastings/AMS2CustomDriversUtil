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

package net.dragondelve.customdriversutil.util;

import javafx.scene.control.Tooltip;

/**
 * Utility class that provides static tooltips for informing the user. Non instantiable.
 */
public class TooltipUtil {

    public static final Tooltip driverLiveryTooltip = new Tooltip("Livery determines which base driver will be overridden by this driver");

    public static final Tooltip chooseLiveryTooltip = new Tooltip("Changes modes between choice mode and manual entry mode");

    public static final Tooltip driverNameTooltip = new Tooltip("Driver name");

    public static final Tooltip driverCountryTooltip = new Tooltip("3-letter country code. This is used for displaying the country flag.");

    public static final Tooltip raceSkillTooltip = new Tooltip("Race session driver skill. It is mapped into a smaller range based on the \"Opponent Skill Level\" slider setting.");

    public static final Tooltip qualiSkillTooltip = new Tooltip("Qualification Session driver skill. Its completely independent of the race_skill");

    public static final Tooltip aggressionTooltip = new Tooltip("Driver aggression. It is scaled by the \"Opponent Aggression\" setting");

    public static final Tooltip defendingTooltip = new Tooltip("How much the driver will try to defend his position. Is also scaled by the \"Opponent Aggression\" slider setting");

    public static final Tooltip staminaTooltip = new Tooltip("Lower stamina value means the driver loses more of his skill during the session");

    public static final Tooltip consistencyTooltip = new Tooltip("Lower consistency value means the driver skill can be randomly reduced more");

    public static final Tooltip startReactionsTooltip = new Tooltip("Lower start_reactions value means the driver will take more time to react to the race green flag and is more likely to make race start programmed mistakes");

    public static final Tooltip wetSkillTooltip = new Tooltip("How good he is on a wet track. Controls how much he will slow down in curves as the track gets wet");

    public static final Tooltip tyreManagementTooltip = new Tooltip("How good he is in preventing tyre wear");

    public static final Tooltip fuelManagementTooltip = new Tooltip("For now this parameter works in oval tracks only. The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing");

    public static final Tooltip blueFlagConcedingTooltip = new Tooltip("Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag");

    public static final Tooltip weatherPitTooltip = new Tooltip("Drivers with high weather_tyre_changes are more likely to make pit stops for changing tyres when the track wetness state changes");

    public static final Tooltip mistakeAvoidanceTooltip = new Tooltip("Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes during the session in general");

    public static final Tooltip forcedMistakeAvoidanceTooltip = new Tooltip("Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure");

    public static final Tooltip vehicleReliabilityTooltip = new Tooltip("Ratio between the lowest and highest possible reliability for the car/class in question");

    public static final Tooltip overrideTooltip = new Tooltip("If checked this value will be override. If unchecked then the default value will be kept instead.");

    public static final Tooltip randomizDriverTooltip = new Tooltip("Randomizes all properties of the driver");

    public static final Tooltip addDriverTooltip = new Tooltip("Adds a new driver to the grid");

    public static final Tooltip removeDriverTooltip = new Tooltip("Removes the currently selected driver from the grid");

    public static final Tooltip addTrackOverrideTooltip = new Tooltip("Adds a new track specific override to the currently selected driver");

    public static final Tooltip removeTrackOverrideTooltip = new Tooltip("Removes the currently selected track specific override from the driver");

    public static final Tooltip addLibraryDriverTooltip = new Tooltip("Adds the currently selected driver to the driver library");

    public static final Tooltip removeLibraryDriverTooltip = new Tooltip("Removes the currently selected library driver from the library");

    public static final Tooltip saveGridTooltip = new Tooltip("Save Grid to an xml file");

    public static final Tooltip saveDriverLibraryTooltip = new Tooltip("Save Driver Library to an xml file");

    public static final Tooltip chooseVehicleClassTooltip = new Tooltip("Choose Vehicle class for which to generate the new grid");

    public static final Tooltip forEachLiveryTooltip = new Tooltip("Generate a new driver for each livery if checked or a specific amount if not checked");

    public static final Tooltip nDriversTooltip = new Tooltip("How many drivers you want to generate. If there are fewer liveries in a class it will instead generate as many as there are liveries in a class");

    public static final Tooltip randomValuesTooltip = new Tooltip("Source of Values: Will generate fully random values");

    public static final Tooltip randomAllTooltip = new Tooltip("Randomizes ALL properties of each drivers in a grid");

    public static final Tooltip randomSkillTooltip = new Tooltip("Randomize only the RaceSkill, leaving other properties at 0 for manual entry");

    public static final Tooltip rangeOfValuesTooltip = new Tooltip("Source of Values: Generates a grid with values ranging from min to max value while applying random noise");

    public static final Tooltip noiseTooltip = new Tooltip("Noise %, more noise means more randomness in generation at 1 it is 100% random at 0 it is 0$ random");

    public static final Tooltip noValuesTooltip = new Tooltip("Uncheck all Sources of Values to have a grid with no values set");

    public static final Tooltip reduceGapOnOvalsTooltip = new Tooltip("Automatically generates a track specific override on ovals that reduces the gap by 50%");

    public static final Tooltip minGeneratedValueTooltip = new Tooltip("Minimum value from 0.0 to 1.0 that each driver property can have");

    public static final Tooltip maxGeneratedValueTooltip = new Tooltip("Maximum value from 0.0 to 1.0 that each driver property can have");

    public static final Tooltip limitAggressionTooltip = new Tooltip("Check if you want to cap aggression to a particular value different from Max Value");

    public static final Tooltip limitAggressionToTooltip = new Tooltip("Aggression Limit");

    public static final Tooltip bindQualiSkillTooltip = new Tooltip("Check if you want Quali Skill and Race Skill to be generated together rather then be generated independently");

    public static final Tooltip qualiSkillExceedsTooltip = new Tooltip("Check if you want Quali Skill to exceed race skill by a certain amount rather than just being equal to it");

    public static final Tooltip exceedsByAmountTooltip = new Tooltip("Value from 0.0 to 1.0 that determines by how much you want Quali Skill to exceed Race Skill");

    public static final Tooltip blankNamesTooltip = new Tooltip("Source of Names: Grid will be generated with blank names allowing users to easily copy paste them from external sources");

    public static final Tooltip useNAMeSTooltip = new Tooltip("Use real driver names from John B. Ellis NAMeS files. Only available for vanilla grids.");

    public static final Tooltip fromLiveryNameTooltip = new Tooltip("Source of Names: Grid will be generated with names generated from driver's livery names (useful for testing)");

    public static final Tooltip backTooltip = new Tooltip("Return to the previous screen");

    public static final Tooltip generateTooltip = new Tooltip("Generate a new Grid with values provided");

    public static final Tooltip emptyGridTooltip = new Tooltip("Check if you want to start with a new empty grid");

    public static final Tooltip generateGridTooltip = new Tooltip("Check if you want to start with a pre-generated grid");

    private TooltipUtil() {
        super();
    }
}
