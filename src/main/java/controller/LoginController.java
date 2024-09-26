// src/main/java/controller/LoginController.java
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import model.Opettaja;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField loginPassword;

    @FXML
    private TextField loginUsername;

    @FXML
    void attemptLogin(ActionEvent event) {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        // Validate the user
        Opettaja opettaja = validateUser(username, password);
        if (opettaja != null) {
            KirjautunutKayttaja.getInstance().setOpettaja(opettaja);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainMenu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) loginUsername.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle login failure (e.g., show an error message)
            System.out.println("Invalid username or password");
        }
    }

    private Opettaja validateUser(String username, String password) {
        // Implement your user validation logic here
        // For example, query the database to check if the user exists
        // This is a placeholder implementation
        if ("validUsername".equals(username) && "validPassword".equals(password)) {
            return new Opettaja("FirstName", "LastName", username, password);
        }
        return null;
    }
}