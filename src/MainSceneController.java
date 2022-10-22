

import javafx.fxml.FXML;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class MainSceneController {

    ObservableList<String> fileTypes = FXCollections.observableArrayList(".pdf");
    private String path;
    private String selectedFileType;

    @FXML
    private TextField textfieldSearchbar;
    @FXML
    private TextField textfieldPath;
    @FXML
    private ComboBox<String> comboboxFiletype;
    @FXML
    private ProgressBar downloadProgressbar;
    @FXML
    private Label downloadProgressDescription;

    @FXML
    private void initialize() {
        path = System.getProperty("user.home") + File.separator + "Desktop";
        textfieldPath.setText(path);
        // path.replace("/", "\\");
        comboboxFiletype.setValue(".pdf");
        comboboxFiletype.setItems(fileTypes);
        textfieldSearchbar.requestFocus();
        selectedFileType = comboboxFiletype.getValue().toString();
        downloadProgressDescription.setText("Waiting for URL . . .");
    }

    @FXML
    void btnDownloadClicked(ActionEvent event) throws java.io.IOException {
        File out = new File(path + "\\download" + selectedFileType);
        String url = textfieldSearchbar.getText(); 
        new Thread(new Download(url, out, downloadProgressbar, downloadProgressDescription)).start();
        // downloadProgressDescription = Download.getDownloadDescription();
        // downloadProgressbar = Download.getDownloadProgressbar();
    }
    
}