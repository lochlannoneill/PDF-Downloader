

import javafx.fxml.FXML;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {
    
    @FXML
    private TextField textfieldSearchbar;

    @FXML
    void btnDownloadClicked(ActionEvent event) throws java.io.IOException {
        // Stage mainWindow = (Stage) textfieldSearchbar.getScene().getWindow();
        // String title = textfieldSearchbar.getText();
        // mainWindow.setTitle(title);

        String url = textfieldSearchbar.getText();
        
        File out = new File("C:\\Users\\Lochlann\\Desktop\\downloaded (1).pdf");

        new Thread(new Download(url, out)).start();
    }
    
}