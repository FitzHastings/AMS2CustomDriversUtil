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

package net.dragondelve.customdriversutil.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.generator.ClassicGridGeneratorController;
import net.dragondelve.customdriversutil.gui.generator.TableGridGeneratorController;
import net.dragondelve.customdriversutil.model.Grid;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.TooltipUtil;
import net.dragondelve.mabelfx.StageController;

import java.io.IOException;

/**
 * New Grid Wizard controller controls the fxml/NewGridWizard.fxml
 * NewGridWizard allows the user to create a new Grid or to generate a new grid using the GridGenerator.
 */
public class NewGridWizardController implements StageController {
    /**
     * Scene that was displayed on the primaryStage before this scene. Used for Back button.
     */
    Scene previousScene;
    /**
     * Button that performs generateAction() on action.
     */
    @FXML
    private Button generateButton;
    /**
     * Button that shows the previous scene from which this screen was called. Performs back action on action.
     */
    @FXML
    private Button backButton;
    /**
     * CheckBox that determines if the user wants to start with a blank grid.
     */
    @FXML
    private CheckBox emptyGridCheckBox;
    /**
     * CheckBox that determines if the user wants a grid to be generated using GridGenerator.
     */
    @FXML
    private CheckBox generateGridCheckBox;
    /**
     * The root pane of this window, used to apply the CSS resource to this entire scene.
     */
    @FXML
    private VBox rootPane;
    /**
     * Label that displays the notice for NAMeS files if useNAMeS radioButton is selected.
     */
    @FXML
    private Label namesNoticeLabel;
    /**
     * Main HBox that contains the classic grid generator or the table grid generator depending on the checkbox selection.
     */
    @FXML
    private HBox mainHBox;
    /**
     * CheckBox that determines if the user wants to generate a grid using the table grid generator.
     */
    @FXML
    private CheckBox tableGridCheckBox;
    /**
     * Stage on which this StageController is going to be displayed.
     */
    private Stage stage;

    /**
     * Controller for the classic grid generator.
     */
    private ClassicGridGeneratorController classicGridGenerator;

    /**
     * Root Node of the classic grid generator.
     */
    private Node classigGridGeneratorRootNode;

    /**
     * Controller for the table grid generator.
     */
    private final TableGridGeneratorController tableGridGenerator = new TableGridGeneratorController();
    /**
     * Root Node of the table grid generator.
     */
    private Node tableGridGeneratorRootNode;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * Initialize JavaFX calls method automatically when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        classicGridGenerator = new ClassicGridGeneratorController(generateButton);
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().CLASSIC_GRID_GENERATOR_FXML_URL);
        loader.setController(classicGridGenerator);
        try {
            classigGridGeneratorRootNode = loader.load();
            mainHBox.getChildren().add(classigGridGeneratorRootNode);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to load ClassicGridGenerator.fxml");
        }

        FXMLLoader tableLoader = new FXMLLoader(DDUtil.getInstance().TABLE_GRID_GENERATOR_FXML_URL);
        tableLoader.setController(tableGridGenerator);
        try {
            tableGridGeneratorRootNode = tableLoader.load();
            HBox.setHgrow(tableGridGeneratorRootNode, Priority.ALWAYS);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Failed to load ClassicGridGenerator.fxml");
        }

        namesNoticeLabel.visibleProperty().bind(classicGridGenerator.getUseNAMeSRadioButton().selectedProperty());

        emptyGridCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                generateGridCheckBox.selectedProperty().set(false);
                tableGridCheckBox.selectedProperty().set(false);
                generateButton.setDisable(false);
                mainHBox.getChildren().remove(mainHBox.getChildren().size()-1);
                mainHBox.getChildren().add(new HBox());
            }
        });

        generateGridCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                emptyGridCheckBox.selectedProperty().set(false);
                tableGridCheckBox.selectedProperty().set(false);
                if (!classicGridGenerator.isGoodToGenerate())
                    generateButton.setDisable(true);
                mainHBox.getChildren().remove(mainHBox.getChildren().size()-1);
                mainHBox.getChildren().add(classigGridGeneratorRootNode);
            }
        });

        tableGridCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                emptyGridCheckBox.selectedProperty().set(false);
                generateGridCheckBox.selectedProperty().set(false);
                generateButton.setDisable(true);
                mainHBox.getChildren().remove(mainHBox.getChildren().size()-1);
                mainHBox.getChildren().add(tableGridGeneratorRootNode);
            }
        });

        generateButton.setOnAction(e -> generateAction());
        backButton.setOnAction(e -> backAction());

        generateGridCheckBox.selectedProperty().set(true);

        initTooltips();
    }

    /**
     * Lightweight mutator method.
     *
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Lightweight mutator method.
     *
     * @param previousScene Previous scene that was displayed in the primaryStage before this scene.
     */
    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    /**
     * Performed by the GenerateButton.
     * Uses the currently set GeneratorSettings to generate a new grid and advance to the main window passing that grid
     * to the controller of the main window.
     */
    private void generateAction() {
        if (emptyGridCheckBox.isSelected()) {
            loadMainWindow(new Grid());
            return;
        }

        loadMainWindow(classicGridGenerator.createGridGenerator().generateNewGrid());
    }

    /**
     * Performed by the BackButton.
     * Shows the previous scene in the primaryStage.
     */
    private void backAction() {
        if (previousScene != null)
            stage.setScene(previousScene);
    }

    /**
     * Loads the main window and sets the provided grid as the edited grid in the main window.
     *
     * @param generatorGrid Grid generated by the GridGenerator or a new empty grid if the user wants to start with an empty grid.
     */
    private void loadMainWindow(Grid generatorGrid) {
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MAIN_WINDOW_FXML_URL);
        CustomDriverUtilController controller = new CustomDriverUtilController();
        controller.setStage(stage);
        controller.setEditedGrid(generatorGrid);
        loader.setController(controller);
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes all tooltips for the control elements
     */
    private void initTooltips() {
        emptyGridCheckBox.setTooltip(TooltipUtil.EMPTY_GRID_TOOLTIP);
        generateGridCheckBox.setTooltip(TooltipUtil.GENERATE_GRID_TOOLTIP);

        backButton.setTooltip(TooltipUtil.BACK_TOOLTIP);
        generateButton.setTooltip(TooltipUtil.GENERATE_TOOLTIP);
    }
}
