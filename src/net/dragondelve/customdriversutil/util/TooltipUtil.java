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

    public static final Tooltip DRIVER_LIVERY_TOOLTIP = new Tooltip("Livery determines which base driver will be overridden by this driver");

    public static final Tooltip CHOOSE_LIVERY_TOOLTIP = new Tooltip("Changes modes between choice mode and manual entry mode");

    public static final Tooltip DRIVER_NAME_TOOLTIP = new Tooltip("Driver name");

    public static final Tooltip DRIVER_COUNTRY_TOOLTIP = new Tooltip("3-letter country code. This is used for displaying the country flag.");

    public static final Tooltip RACE_SKILL_TOOLTIP = new Tooltip("Race session driver skill. It is mapped into a smaller range based on the \"Opponent Skill Level\" slider setting.");

    public static final Tooltip QUALI_SKILL_TOOLTIP = new Tooltip("Qualification Session driver skill. Its completely independent of the race_skill");

    public static final Tooltip AGGRESSION_TOOLTIP = new Tooltip("Driver aggression. It is scaled by the \"Opponent Aggression\" setting");

    public static final Tooltip DEFENDING_TOOLTIP = new Tooltip("How much the driver will try to defend his position. Is also scaled by the \"Opponent Aggression\" slider setting");

    public static final Tooltip STAMINA_TOOLTIP = new Tooltip("Lower stamina value means the driver loses more of his skill during the session");

    public static final Tooltip CONSISTENCY_TOOLTIP = new Tooltip("Lower consistency value means the driver skill can be randomly reduced more");

    public static final Tooltip START_REACTIONS_TOOLTIP = new Tooltip("Lower start_reactions value means the driver will take more time to react to the race green flag and is more likely to make race start programmed mistakes");

    public static final Tooltip WET_SKILL_TOOLTIP = new Tooltip("How good he is on a wet track. Controls how much he will slow down in curves as the track gets wet");

    public static final Tooltip TYRE_MANAGEMENT_TOOLTIP = new Tooltip("How good he is in preventing tyre wear");

    public static final Tooltip FUEL_MANAGEMENT_TOOLTIP = new Tooltip("For now this parameter works in oval tracks only. The higher the value, the more the AI will try to save fuel in some strategic situations instead of pushing");

    public static final Tooltip BLUE_FLAG_CONCEDING_TOOLTIP = new Tooltip("Drivers with high blue_flag_conceding will work harder to concede the position when under blue flag");

    public static final Tooltip WEATHER_PIT_TOOLTIP = new Tooltip("Drivers with high weather_tyre_changes are more likely to make pit stops for changing tyres when the track wetness state changes");

    public static final Tooltip MISTAKE_AVOIDANCE_TOOLTIP = new Tooltip("Drivers with lower avoidance_of_mistakes value are more likely to make AI programmed mistakes during the session in general");

    public static final Tooltip FORCED_MISTAKE_AVOIDANCE_TOOLTIP = new Tooltip("Drivers with lower value for avoidance_of_forced_mistakes will have their chances of mistakes increased when under pressure");

    public static final Tooltip VEHICLE_RELIABILITY_TOOLTIP = new Tooltip("Ratio between the lowest and highest possible reliability for the car/class in question");

    public static final Tooltip OVERRIDE_TOOLTIP = new Tooltip("If checked this value will be override. If unchecked then the default value will be kept instead.");

    public static final Tooltip RANDOMIZ_DRIVER_TOOLTIP = new Tooltip("Randomizes all properties of the driver");

    public static final Tooltip ADD_DRIVER_TOOLTIP = new Tooltip("Adds a new driver to the grid");

    public static final Tooltip REMOVE_DRIVER_TOOLTIP = new Tooltip("Removes the currently selected driver from the grid");

    public static final Tooltip ADD_TRACK_OVERRIDE_TOOLTIP = new Tooltip("Adds a new track specific override to the currently selected driver");

    public static final Tooltip REMOVE_TRACK_OVERRIDE_TOOLTIP = new Tooltip("Removes the currently selected track specific override from the driver");

    public static final Tooltip EDIT_TRACK_OVERRIDE_TOOLTIP = new Tooltip("Edit the currently selected track override");

    public static final Tooltip ADD_LIBRARY_DRIVER_TOOLTIP = new Tooltip("Adds the currently selected driver to the driver library");

    public static final Tooltip REMOVE_LIBRARY_DRIVER_TOOLTIP = new Tooltip("Removes the currently selected library driver from the library");

    public static final Tooltip SAVE_GRID_TOOLTIP = new Tooltip("Save Grid to an xml file");

    public static final Tooltip SAVE_DRIVER_LIBRARY_TOOLTIP = new Tooltip("Save Driver Library to an xml file");

    public static final Tooltip CHOOSE_VEHICLE_CLASS_TOOLTIP = new Tooltip("Choose Vehicle class for which to generate the new grid");

    public static final Tooltip FOR_EACH_LIVERY_TOOLTIP = new Tooltip("Generate a new driver for each livery if checked or a specific amount if not checked");

    public static final Tooltip N_DRIVERS_TOOLTIP = new Tooltip("How many drivers you want to generate. If there are fewer liveries in a class it will instead generate as many as there are liveries in a class");

    public static final Tooltip RANDOM_VALUES_TOOLTIP = new Tooltip("Source of Values: Will generate fully random values");

    public static final Tooltip RANDOM_ALL_TOOLTIP = new Tooltip("Randomizes ALL properties of each drivers in a grid");

    public static final Tooltip RANDOM_SKILL_TOOLTIP = new Tooltip("Randomize only the RaceSkill, leaving other properties at 0 for manual entry");

    public static final Tooltip RANGE_OF_VALUES_TOOLTIP = new Tooltip("Source of Values: Generates a grid with values ranging from min to max value while applying random noise");

    public static final Tooltip NOISE_TOOLTIP = new Tooltip("Noise %, more noise means more randomness in generation at 1 it is 100% random at 0 it is 0$ random");

    public static final Tooltip NO_VALUES_TOOLTIP = new Tooltip("Uncheck all Sources of Values to have a grid with no values set");

    public static final Tooltip REDUCE_GAP_ON_OVALS_TOOLTIP = new Tooltip("Automatically generates a track specific override on ovals that reduces the gap by 50%");

    public static final Tooltip MIN_GENERATED_VALUE_TOOLTIP = new Tooltip("Minimum value from 0.0 to 1.0 that each driver property can have");

    public static final Tooltip MAX_GENERATED_VALUE_TOOLTIP = new Tooltip("Maximum value from 0.0 to 1.0 that each driver property can have");

    public static final Tooltip LIMIT_AGGRESSION_TOOLTIP = new Tooltip("Check if you want to cap aggression to a particular value different from Max Value");

    public static final Tooltip LIMIT_AGGRESSION_TO_TOOLTIP = new Tooltip("Aggression Limit");

    public static final Tooltip BIND_QUALI_SKILL_TOOLTIP = new Tooltip("Check if you want Quali Skill and Race Skill to be generated together rather then be generated independently");

    public static final Tooltip QUALI_SKILL_EXCEEDS_TOOLTIP = new Tooltip("Check if you want Quali Skill to exceed race skill by a certain amount rather than just being equal to it");

    public static final Tooltip EXCEEDS_BY_AMOUNT_TOOLTIP = new Tooltip("Value from 0.0 to 1.0 that determines by how much you want Quali Skill to exceed Race Skill");

    public static final Tooltip BLANK_NAMES_TOOLTIP = new Tooltip("Source of Names: Grid will be generated with blank names allowing users to easily copy paste them from external sources");

    public static final Tooltip USE_NAMES_TOOLTIP = new Tooltip("Use real driver names from John B. Ellis NAMeS files. Only available for vanilla grids.");

    public static final Tooltip FROM_LIVERY_NAME_TOOLTIP = new Tooltip("Source of Names: Grid will be generated with names generated from driver's livery names (useful for testing)");

    public static final Tooltip BACK_TOOLTIP = new Tooltip("Return to the previous screen");

    public static final Tooltip GENERATE_TOOLTIP = new Tooltip("Generate a new Grid with values provided");

    public static final Tooltip EMPTY_GRID_TOOLTIP = new Tooltip("Check if you want to start with a new empty grid");

    public static final Tooltip GENERATE_GRID_TOOLTIP = new Tooltip("Check if you want to start with a pre-generated grid");

    public static final Tooltip INCREASE_FLAT_TOOLTIP = new Tooltip("Increases all selected properties by a given flat value");

    public static final Tooltip DECREASE_FLAT_TOOLTIP = new Tooltip("Decreases all selected properties by a given flat value");

    public static final Tooltip INCREASE_PERCENT_TOOLTIP = new Tooltip("Increases all selected properties by a given % amount");

    public static final Tooltip DECREASE_PERCENT_TOOLTIP = new Tooltip("Decreases all selected properties by a given % amount");

    public static final Tooltip BRING_CLOSER_FLOOR_TOOLTIP = new Tooltip("Brings all values closer to the smallest value reducing the gap by a % value");

    public static final Tooltip BRING_CLOSER_CEILING_TOOLTIP = new Tooltip("Brings all values closer to the highest value reducing the gap by a % value");

    public static final Tooltip INCREASE_DISTANCE_FLOOR_TOOLTIP = new Tooltip("Increases distance between the highest and lowest value by a % value by making smallest value smaller");

    public static final Tooltip INCREASE_DISTANCE_CEILING_TOOLTIP = new Tooltip("Increases distance between the highest and lowest value by a % value by making largest value larger");

    public static final Tooltip MODIFICATION_VALUE_TOOLTIP = new Tooltip("Modification value by which the original values are going to be modified. Can represent flat or % value depending on action chosen");

    public static final Tooltip MODIFY_TOOLTIP = new Tooltip("Modify selected driver properties with the action selected by the value selected");

    public static final Tooltip CANCEL_TOOLTIP = new Tooltip("Closes this window without modifying the grid");

    public static final Tooltip ADD_TRACK_TOOLTIP = new Tooltip("Add a new track to the library");

    public static final Tooltip REMOVE_TRACK_TOOLTIP = new Tooltip("Remove the currently selected track from the library");

    public static final Tooltip TRACK_NAME_TOOLTIP = new Tooltip("Human Readable name for the track");

    public static final Tooltip TRACK_XMLNAME_TOOLTIP = new Tooltip("XML name used for track overrides");

    public static final Tooltip IS_OVAL_TOOLTIP = new Tooltip("Flag used in grid generation. Tick if the track is an oval track");

    public static final Tooltip ADD_VEHICLE_CLASS_TOOLTIP = new Tooltip("Add a new vehicle class to a library");

    public static final Tooltip REMOVE_VEHICLE_CLASS_TOOLTIP = new Tooltip("Remove the currently selected vehicle class from a library");

    public static final Tooltip ADD_LIVERY_TOOLTIP = new Tooltip("Add a new Livery to this class");

    public static final Tooltip REMOVE_LIVERY_TOOLTIP = new Tooltip("Remove the currently selected livery from this vehicle class");

    public static final Tooltip VEHICLE_CLASS_NAME_TOOLTIP = new Tooltip("Human Readable name of the vehicle class");

    public static final Tooltip VEHICLE_CLASS_XMLNAME_TOOLTIP = new Tooltip("Name of the xml file that AMS2 uses to look for this class's AI override file");

    public static final Tooltip IS_MODDED_TOOLTIP = new Tooltip("Determines if the vehicle class a modded or a modded file. Used in grid generation");

    public static final Tooltip FILE_CHOOSER_TOOLTIP = new Tooltip("Choose a file");

    public static final Tooltip PATH_TO_TRACK_LIBRARY_TOOLTIP = new Tooltip("Path to the default track library");

    public static final Tooltip PATH_TO_VEHICLE_CLASS_LIBRARY_TOOLTIP = new Tooltip("Path to the default vehicle class library");

    public static final Tooltip PATH_TO_DRIVER_LIBRARY_TOOLTIP = new Tooltip("Path to the last loaded driver library");

    public static final Tooltip ROUND_GENERATED_VALUE_TOOLTIP = new Tooltip("Determines if generated values should be rounded (floored) before exporting");

    public static final Tooltip ROUND_DECIMAL_POINTS_TOOLTIP = new Tooltip("Determines the number of decimal places after the . that will be left after rounding");

    public static final Tooltip CONFIG_CHOOSE_LIVERY_TOOLTIP = new Tooltip("Tick if you want to have the 'Choose Livery' checkbox ticked by default on the driver editor");

    public static final Tooltip SKIP_WELCOME_SCREEN_TOOLTIP = new Tooltip("Tick if you want to skip the welcome screen shown on startup");

    public static final Tooltip CONFIG_OK_BUTTON = new Tooltip("Confirm and save the configuration");

    public static final Tooltip CONFIG_CANCEL_BUTTON = new Tooltip("Discard changes and exit");

    private TooltipUtil() {
        super();
    }
}
