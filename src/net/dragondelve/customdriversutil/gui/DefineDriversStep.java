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

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.Driver;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.mabelfx.ListToListChooser;
import net.dragondelve.mabelfx.StageController;

import java.io.IOException;

public class DefineDriversStep implements StageController {
    /**
     * Button that performs nextAction on action.
     */
    @FXML
    private Button nextButton;

    /**
     * Button that performs the cancelAction on action.
     */
    @FXML
    private Button cancelButton;

    /**
     * List to List chooser for user choice
     */
    @FXML
    private ListToListChooser<Driver> listToListChooser;

    /**
     * The root pane of the entire scene. Used to assign the css style to the controls and containers.
     */
    @FXML
    private VBox rootPane;

    /**
     * Stage on which this editor is displayed.
     */
    private Stage stage;

    /**
     * Drivers from the currently edited grid
     */
    private final ObservableList<Driver> drivers = FXCollections.observableArrayList();

    /**
     * creates a new instance of DefineDriversStep.
     * @param drivers All drivers from the currently edited grid
     */
    public DefineDriversStep(ObservableList<Driver> drivers) {
        this.drivers.addAll(drivers);
    }

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        stage.setTitle("Choose Tracks");

        listToListChooser.getLibraryListView().setItems(drivers);

        listToListChooser.getSelectedListView().getItems().addListener((ListChangeListener<? super Driver>) c -> nextButton.setDisable(c.getList().isEmpty()));

        //setting up the buttons
        nextButton.setOnAction(e -> nextAction());
        cancelButton.setOnAction(e -> cancelAction());
    }

    /**
     * Lightweight mutator method.
     * Should be called before the editor is initialized by JavaFX.
     * @param stage stage on which this editor is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }


    /**
     * Action that is performed by the cancelButton. Closes this editor's Stage.
     */
    private void cancelAction() {
        stage.close();
    }

    /**
     * Action that is performed by the nextButton. Opens the mass modify tool in a new window
     */
    private void nextAction() {
        if (listToListChooser.getSelectedListView().getItems().isEmpty())
            return;
        FXMLLoader loader = new FXMLLoader(DDUtil.getInstance().MASS_MODIFY_TOOL_FXML_URL);
        MassModifyToolController controller = new MassModifyToolController(listToListChooser.getSelectedListView().getItems());
        loader.setController(controller);
        try {
            Stage editorStage = new Stage();
            editorStage.getIcons().add(DDUtil.MAIN_ICON_IMAGE);
            controller.setStage(editorStage);
            BorderPane borderPane = new BorderPane();
            borderPane.setCenter(loader.load());
            ListView<Driver> listView = new ListView<>();
            listView.setItems(listToListChooser.getSelectedListView().getItems());
            listView.getStyleClass().add("inactive-list-view");
            borderPane.setLeft(listView);
            borderPane.getStylesheets().clear();
            borderPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
            Scene scene = new Scene(borderPane);
            editorStage.setScene(scene);
            editorStage.setTitle("Custom Mass Modify Tool");
            stage.close();
            editorStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
