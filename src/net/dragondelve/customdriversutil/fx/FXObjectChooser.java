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

package net.dragondelve.customdriversutil.fx;

import javafx.collections.ObservableList;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.dragondelve.customdriversutil.util.DDUtil;

/**
 * Stage controller that displays a list of items to the user on the stage provided to it. When the user presses the
 * okButton if a selection in the Table is made then it will return the item chosen if the selection in the table is
 * not made then it will return null instead.
 * @param <T> Class of object that will choose by the user from a list of objects.
 */
public class FXObjectChooser<T> {
    /**
     * TableView that displays the items chosen by the user.
     */
    private final TableView<T> tableView = new TableView<>();

    /**
     * Stage on which FXObjectChooser is going to be displayed.
     */
    private final Stage stage = new Stage();

    /**
     * Root pane of the scene of this stage. Used to apply the main css resource to the entire scene.
     */
    private final VBox rootPane = new VBox();

    /**
     * Message that is displayed on the okButton if the selection is made in the table.
     */
    private static final String OK_MESSAGE = "Ok";

    /**
     * Message that is displayed in the table if the selection is not made in the table.
     */
    private static final String CANCEL_MESSAGE = "Cancel";

    /**
     * Default constructor
     */
    public FXObjectChooser() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        ToolBar toolBar = new ToolBar();
        Button okButton = new Button();
        toolBar.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        toolBar.getItems().add(okButton);

        okButton.setText(CANCEL_MESSAGE);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && okButton.getText().equals(CANCEL_MESSAGE)) {
                okButton.setText(OK_MESSAGE);
            } else if (newValue == null && okButton.getText().equals(OK_MESSAGE)) {
                okButton.setText(CANCEL_MESSAGE);
            }
        });

        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2)
                tableView.getSelectionModel().select(null);
        });

        rootPane.getChildren().addAll(tableView, toolBar);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        okButton.setOnAction(e -> stage.close());
    }

    /**
     * Shows the FXObjectChooser to the user and waits until the user presses the okButton.
     * @return item selected by the user if the selection is made or null if the selection is not made.
     */
    public T showChooseDialog() {
        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.showAndWait();
        if (tableView.getSelectionModel().getSelectedItem() != null)
            return tableView.getSelectionModel().getSelectedItem();
        else
            return null;
    }

    /**
     * Sets items to the TableView should be called
     * @param items items to be displayed to the user, so he can make his selection
     */
    public void setItems(ObservableList<T> items) {
        tableView.setItems(items);
    }

    /**
     * Lightweight accessor method.
     * @return tableView that displays the list of items to the user.
     */
    public TableView<T> getTableView() {
        return tableView;
    }

    /**
     * Initializes the stage on which FXObjectChooser is going to be displayed to a given Window
     * @param owner Owner of FXObjectChooser's stage.
     */
    public void initOwner(Window owner) {
        stage.initOwner(owner);
    }
}
