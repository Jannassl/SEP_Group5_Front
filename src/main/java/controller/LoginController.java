package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import model.Opettaja;
import service.OpettajaService;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField loginPassword;

    @FXML
    private TextField loginUsername;

    private final OpettajaService opettajaService = new OpettajaService();

    @FXML
    void attemptLogin(ActionEvent event) {
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        // Validate the user using OpettajaService
        Opettaja opettaja = opettajaService.login(username, password);

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
            showAlert("Login Failed", "Invalid Credentials", "The username or password you entered is incorrect.");
        }

    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


}