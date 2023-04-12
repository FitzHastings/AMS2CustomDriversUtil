package net.dragondelve.customdriversutil.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.dragondelve.customdriversutil.model.Track;

public class TrackLibraryEditor implements Editor {

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
    public void initialize() {

    }
}

