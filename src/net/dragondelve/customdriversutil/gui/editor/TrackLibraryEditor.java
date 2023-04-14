package net.dragondelve.customdriversutil.gui.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.util.DDUtil;

import java.sql.DatabaseMetaData;
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

    @FXML
    private SplitPane rootPane;

    private final ObservableList<Track>  items = FXCollections.observableArrayList();
    private Stage stage;

    @FXML
    public void initialize() {
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);
        trackTableView.setItems(items);
        trackNameColumn.setCellValueFactory(e-> e.getValue().nameProperty());
        trackTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue != null)
                unbindTrack(oldValue);
            if(newValue != null)
                bindTrack(newValue);
        });

        addTrackButton.setOnAction(e->addTrackAction());
        removeTrackButton.setOnAction(e->removeTrackAction());
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

    private void addTrackAction() {
        Track track = new Track();
        track.nameProperty().set("New Name");
        track.xmlNameProperty().set("track_name");
        track.isOvalProperty().set(true);
        items.add(track);
    }

    private void removeTrackAction() {
        Track selectedTrack = trackTableView.getSelectionModel().getSelectedItem();
        if(selectedTrack != null) {
            if(!trackTableView.getSelectionModel().isSelected(trackTableView.getSelectionModel().getSelectedIndex()))
                trackTableView.getSelectionModel().selectNext();
            else
                trackTableView.getSelectionModel().selectPrevious();
            items.remove(selectedTrack);
        }
    }

    private void bindTrack(Track track) {
        trackNameTextField.textProperty().bindBidirectional(track.nameProperty());
        trackXMLNameTextField.textProperty().bindBidirectional(track.xmlNameProperty());
        isOvalCheckBox.selectedProperty().bindBidirectional(track.isOvalProperty());
    }

    private void unbindTrack(Track track) {
        trackNameTextField.textProperty().unbindBidirectional(track.nameProperty());
        trackXMLNameTextField.textProperty().unbindBidirectional(track.xmlNameProperty());
        isOvalCheckBox.selectedProperty().unbindBidirectional(track.isOvalProperty());
    }
}