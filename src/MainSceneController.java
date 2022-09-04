

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainSceneController {
    
    @FXML
    private TextField textfieldMediaInput;

    @FXML
    void btnDownloadClicked(ActionEvent event) {
        Stage mainWindow = (Stage) textfieldMediaInput.getScene().getWindow();

        // String title = textfieldMediaInput.getText();
        // mainWindow.setTitle(title);
        // execute selenium query



    }
    
}