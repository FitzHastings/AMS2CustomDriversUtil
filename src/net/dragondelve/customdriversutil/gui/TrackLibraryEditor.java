package net.dragondelve.customdriversutil.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.gui.editor.Editor;
import net.dragondelve.customdriversutil.model.Track;

import java.util.List;

public class TrackLibraryEditor implements Editor<Track> {

    @FXML
    private Button addTrackButton;

    @FXML
    private CheckBox isOvalCheckBox;

    @FXML
    private Button removeTrackButton;

    @FXML
    private TableColumn<Track, String> trackNameColumn;

    @FXML
    private TextField trackNameTextField;

    @FXML
    private TableView<Track> trackTableView;

    @FXML
    private TextField trackXMLNameTextField;

    private final ObservableList<Track>  items = FXCollections.observableArrayList();
    private Stage stage;

    @FXML
    public void initialize() {
        trackTableView.setItems(items);
        trackNameColumn.setCellValueFactory(e-> e.getValue().nameProperty());
        trackTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null)
                unbindTrack(oldValue);
            if(newValue != null)
                bindTrack(newValue);
        });

    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public List<Track> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Track> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    private void bindTrack(Track track) {
        trackNameTextField.textProperty().bindBidirectional(track.nameProperty());
        isOvalCheckBox.selectedProperty().bindBidirectional(track.isOvalProperty());
    }

    private void unbindTrack(Track track) {
        isOvalCheckBox.selectedProperty().unbindBidirectional(track.isOvalProperty());
        trackNameTextField.textProperty().unbindBidirectional(track.isOvalProperty());

    }
}

