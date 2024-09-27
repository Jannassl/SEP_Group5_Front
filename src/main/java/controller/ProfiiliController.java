package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import javafx.scene.image.Image;
import model.Opettaja;

import java.io.IOException;

public class ProfiiliController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button LogOutButton;
    @FXML
    private Label OpettajaIdLabel;
    @FXML
    private Label EtunimiLabel;
    @FXML
    private Label SukunimiLabel;
    @FXML
    private Label SahkopostiLabel;



    @FXML
    private Button TakaisinButton;

    public void initialize() {
        Image image = new Image(getClass().getResource("/defaultProfilePic.jpg").toExternalForm());
        imageView.setImage(image);
        Opettaja currentOpettaja = KirjautunutKayttaja.getInstance().getOpettaja();
        if(currentOpettaja != null){
            OpettajaIdLabel.setText("Opettaja ID: "+ currentOpettaja.getOpettaja_id());
            EtunimiLabel.setText("Nimi: "+ currentOpettaja.getEtunimi());
            SukunimiLabel.setText("Sukunimi: "+ currentOpettaja.getSukunimi());
            SahkopostiLabel.setText("Sähköposti: "+ currentOpettaja.getSahkoposti());

        }
    }

    @FXML
    void CloseProgram(ActionEvent event) {
        KirjautunutKayttaja.getInstance().clearOpettaja(); // Clear the logged-in user

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void navigateBackwards(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
