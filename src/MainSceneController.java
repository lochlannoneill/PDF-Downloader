

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

    public static Object setDownloadProgressbar;
    ObservableList<String> fileTypes = FXCollections.observableArrayList(".pdf");
    private String path;
    private String fileName;
    private String fileType;

    @FXML
    private TextField textfieldSearchbar;
    @FXML
    private TextField textfieldPath;
    @FXML
    private TextField textfieldFileName;
    @FXML
    private ComboBox<String> comboboxFiletype;
    @FXML
    private ProgressBar downloadProgressbar;
    @FXML
    private Label downloadProgressDescription;

    @FXML
    private void initialize() {
        path = System.getProperty("user.home") + File.separator + "Desktop"  + File.separator;
        textfieldPath.setText(path);
        // path.replace("/", "\\");
        comboboxFiletype.setValue(".pdf");
        comboboxFiletype.setItems(fileTypes);
        textfieldSearchbar.requestFocus();
        // fileType = comboboxFiletype.getValue().toString();
        downloadProgressDescription.setText("Waiting for URL . . .");
    }

    @FXML
    void btnDownloadClicked(ActionEvent event) throws java.io.IOException {
        fileName = textfieldFileName.getText();
        fileType = comboboxFiletype.getValue().toString();
        File file = new File(path + fileName + fileType);
        String url = textfieldSearchbar.getText();
        if(url == "") {
            downloadProgressDescription.setText("You must enter a URL");
        } else {
            new Thread(new Download(url, file, downloadProgressbar, downloadProgressDescription)).start();
            downloadProgressDescription.setText("Downloading " + file);
        }
    }
    
}