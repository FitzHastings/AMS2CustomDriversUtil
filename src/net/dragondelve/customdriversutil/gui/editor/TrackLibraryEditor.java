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

package net.dragondelve.customdriversutil.gui.editor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.dragondelve.customdriversutil.model.Track;
import net.dragondelve.customdriversutil.util.DDUtil;
import net.dragondelve.customdriversutil.util.TooltipUtil;

/**
 * Editor that can edit a TrackLibrary's List of Track.
 * Allows the user to create new instances of track, remove tracks from the list and to edit
 * each item on the list. The Editor should be displayed on a seperate decorated stage as there is no
 * way to close the stage from inside the editor.
 */
public class TrackLibraryEditor implements Editor<Track> {

    /**
     * Button  that is responsible for adding new tracks to the list.
     */
    @FXML
    private Button addTrackButton;

    /**
     * CheckBox that determines whether the track is oval or a road course.
     */
    @FXML
    private CheckBox isOvalCheckBox;

    /**
     * Button that is responsible for removing a selected track from the list
     */
    @FXML
    private Button removeTrackButton;

    /**
     * TableColumn that displays of the track.
     */
    @FXML
    private TableColumn<Track, String> trackNameColumn;

    /**
     * TextField that is allows user to change the currently selected track's name.
     */
    @FXML
    private TextField trackNameTextField;

    /**
     * TableView that contains the list of tracks to be edited.
     */
    @FXML
    private TableView<Track> trackTableView;

    /**
     * TextField that contains the Track name that is used when exporting a grid to XML.
     */
    @FXML
    private TextField trackXMLNameTextField;

    /**
     * Root pane of the editor, used to set style.
     */
    @FXML
    private SplitPane rootPane;

    /**
     * An observable list of items that are being edited by this editor.
     */
    private ObservableList<Track> items = FXCollections.observableArrayList();

    /**
     * Stage on which this editor is displayed.
     */
    private Stage stage;

    /**
     * Flag that determines if the controls are currently disabled or not.
     */
    private boolean controlsAreDisabled = false;

    /**
     * Initialize method initializes all the visual elements before they are displayed by the user.
     * initialize method is called automatically by JavaFX when this editor is being loaded from XML.
     */
    @FXML
    public void initialize() {
        //init the main css on rootPane
        rootPane.getStylesheets().clear();
        rootPane.getStylesheets().add(DDUtil.MAIN_CSS_RESOURCE);

        //track Table View initialization
        trackTableView.setItems(items);
        trackNameColumn.setCellValueFactory(e -> e.getValue().nameProperty());
        trackTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null)
                unbindTrack(oldValue);

            if (newValue != null) {
                if (controlsAreDisabled)
                    setDisableControls(false);
                bindTrack(newValue);
            } else if (!controlsAreDisabled) {
                setDisableControls(true);
            }
        });

        //button initialization
        addTrackButton.setOnAction(e -> addTrackAction());
        removeTrackButton.setOnAction(e -> removeTrackAction());

        setDisableControls(true);

        initTooltips();
    }

    /**
     * Lightweight mutator method.
     * Should be called before the editor is initialized by JavaFX.
     *
     * @param stage stage on which this editor is going to be displayed.
     */
    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Lightweight accessor method.
     *
     * @return Observable list of Tracks that are edited by this editor.
     */
    @Override
    public ObservableList<Track> getItems() {
        return items;
    }

    /**
     * Lightweight mutator method.
     * Should be called before initialize method is called by the JavaFX.
     *
     * @param items Observable list of Tracks that are edited by this editor.
     */
    @Override
    public void setItems(ObservableList<Track> items) {
        this.items = items;
    }

    /**
     * Action that is performed by the addTrackButton.
     * Creates a new instance of Track with an empty name and xmlName. isOval is set to false. The new track is added to
     * the list of items.
     */
    private void addTrackAction() {
        Track track = new Track();
        track.nameProperty().set("");
        track.xmlNameProperty().set("");
        track.isOvalProperty().set(false);
        items.add(track);
    }

    /**
     * Action that is performed by the removeTrackButton.
     * If a track is selected by the user in the table it removes this track from the items list, if not then nothing
     * happens. If a track is removed from the list, and it is not the last track in the list the next track in the
     * TableView is selected. If it is the last track in the last then the previous track is selected.
     */
    private void removeTrackAction() {
        Track selectedTrack = trackTableView.getSelectionModel().getSelectedItem();
        if (selectedTrack != null) {
            if (!trackTableView.getSelectionModel().isSelected(trackTableView.getItems().size()))
                trackTableView.getSelectionModel().selectNext();
            else
                trackTableView.getSelectionModel().selectPrevious();
            items.remove(selectedTrack);
        }
    }

    /**
     * Binds the given track's properties to the control elements that are supposed to edit them. The bind will be done
     * bidirectionally, and you should call unbindTrack on the same track after it is no longer being edited.
     *
     * @param track a track whose properties are to be bind to the control elements of this editor.
     */
    private void bindTrack(Track track) {
        trackNameTextField.textProperty().bindBidirectional(track.nameProperty());
        trackXMLNameTextField.textProperty().bindBidirectional(track.xmlNameProperty());
        isOvalCheckBox.selectedProperty().bindBidirectional(track.isOvalProperty());
    }

    /**
     * Unbinds the given track's properties from the control elements of the editor. The bind will be unbound bidirectionally.
     * If a track was bound with bindTrack then you should unbind it with this method.
     *
     * @param track a track whose properties are to be unbound from the control elements of this editor.
     */
    private void unbindTrack(Track track) {
        trackNameTextField.textProperty().unbindBidirectional(track.nameProperty());
        trackXMLNameTextField.textProperty().unbindBidirectional(track.xmlNameProperty());
        isOvalCheckBox.selectedProperty().unbindBidirectional(track.isOvalProperty());
    }

    /**
     * Method used to disable all editing controls if no selection in the tableView is made.
     *
     * @param disable True if you want to disable all controls, false if you want to enable them.
     */
    private void setDisableControls(boolean disable) {
        trackNameTextField.setDisable(disable);
        trackXMLNameTextField.setDisable(disable);
        isOvalCheckBox.setDisable(disable);
        controlsAreDisabled = disable;
    }

    /**
     * Initializes all tooltips for the control elements
     */
    private void initTooltips() {
        addTrackButton.setTooltip(TooltipUtil.ADD_TRACK_TOOLTIP);
        removeTrackButton.setTooltip(TooltipUtil.REMOVE_TRACK_TOOLTIP);
        trackNameTextField.setTooltip(TooltipUtil.TRACK_NAME_TOOLTIP);
        trackXMLNameTextField.setTooltip(TooltipUtil.TRACK_XMLNAME_TOOLTIP);
        isOvalCheckBox.setTooltip(TooltipUtil.IS_OVAL_TOOLTIP);
    }
}