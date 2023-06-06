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
import net.dragondelve.customdriversutil.gui.StageController;
import net.dragondelve.customdriversutil.util.DDUtil;

/**
 * Stage controller that displays a list of items to the user on the stage provided to it. When the user presses the
 * okButton if a selection in the Table is made then it will return the item chosen if the selection in the table is
 * not made then it will return null instead.
 * @param <T> Class of object that will chosen by the user from a list of objects.
 */
public class FXObjectChooser<T> implements StageController {
    /**
     * TableView that displays the items chosen by the user.
     */
    private final TableView<T> tableView = new TableView<>();

    /**
     * Stage on which FXObjectChooser is going to be displayed.
     */
    private Stage stage;

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
        if(tableView.getSelectionModel().getSelectedItem() != null)
            return tableView.getSelectionModel().getSelectedItem();
        else
            return null;
    }

    /**
     * Sets items to the TableView should be called
     * @param items items to be displayed to the user so he can make his selection
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
     * Lightweight mutator method.
     * @param stage Stage on which this controller is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
