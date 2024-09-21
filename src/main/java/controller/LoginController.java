package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField loginPassword;
    @FXML
    private TextField loginUsername;

    public LoginController() {
    }

    @FXML
    void attemptLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/mainMenu.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = (Stage)this.loginUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException var5) {
            IOException e = var5;
            e.printStackTrace();
        }

    }
}
