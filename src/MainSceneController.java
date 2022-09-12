

import javafx.fxml.FXML;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.ProgressBar;

public class MainSceneController {
    
    @FXML
    private TextField textfieldSearchbar;
    private ProgressBar downloadProgressbar;

    @FXML
    void btnDownloadClicked(ActionEvent event) throws java.io.IOException {
        // Stage mainWindow = (Stage) textfieldSearchbar.getScene().getWindow();
        // String title = textfieldSearchbar.getText();
        // mainWindow.setTitle(title);

        String path = "C:\\Users\\Lochlann\\Desktop\\";
        File out = new File(path + "download.mp4");
        String url = textfieldSearchbar.getText(); 
        // new Thread(new Download(url, out, downloadProgressbar)).start();
        new Thread(new Download(url, out)).start();
    }
    
}