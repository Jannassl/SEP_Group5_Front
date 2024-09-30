package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import javafx.scene.image.Image;
import model.Opettaja;
import service.OpettajaService;

import java.io.IOException;

public class ProfiiliController {

    @FXML
    private ImageView imageView;
    @FXML
    private Button ProfiiliMuokkaaButton;
    @FXML
    private Button ProfiiliTallennaButton;
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
    private TextField EtunimiTextField;
    @FXML
    private TextField SukunimiTextField;
    @FXML
    private TextField SahkopostiTextField;
    @FXML
    private Button TakaisinButton;

    private final OpettajaService opettajaService = new OpettajaService();

    public void initialize() {
        Image image = new Image(getClass().getResource("/defaultProfilePic.jpg").toExternalForm());
        imageView.setImage(image);
        Opettaja currentOpettaja = KirjautunutKayttaja.getInstance().getOpettaja();
        if (currentOpettaja != null) {
            OpettajaIdLabel.setText("Opettaja ID: " + currentOpettaja.getOpettaja_id());
            EtunimiLabel.setText("Etunimi: " + currentOpettaja.getEtunimi());
            SukunimiLabel.setText("Sukunimi: " + currentOpettaja.getSukunimi());
            SahkopostiLabel.setText("Sähköposti: " + currentOpettaja.getSahkoposti());
        }
    }

    @FXML
    public void muokkaaProfiilia(ActionEvent event) {
        EtunimiTextField.setText(KirjautunutKayttaja.getInstance().getOpettaja().getEtunimi());
        SukunimiTextField.setText(KirjautunutKayttaja.getInstance().getOpettaja().getSukunimi());
        SahkopostiTextField.setText(KirjautunutKayttaja.getInstance().getOpettaja().getSahkoposti());

        EtunimiTextField.setVisible(true);
        SukunimiTextField.setVisible(true);
        SahkopostiTextField.setVisible(true);

        ProfiiliMuokkaaButton.setVisible(false);
        ProfiiliTallennaButton.setVisible(true);
    }

    @FXML
    public void tallennaMuutokset(ActionEvent event) {
        Opettaja currentOpettaja = KirjautunutKayttaja.getInstance().getOpettaja();

        if (!EtunimiTextField.getText().isEmpty()) {
            currentOpettaja.setEtunimi(EtunimiTextField.getText());
        }
        if (!SukunimiTextField.getText().isEmpty()) {
            currentOpettaja.setSukunimi(SukunimiTextField.getText());
        }
        if (!SahkopostiTextField.getText().isEmpty()) {
            currentOpettaja.setSahkoposti(SahkopostiTextField.getText());
        }

        // Update the Opettaja in the database
        opettajaService.updateOpettaja(currentOpettaja.getOpettaja_id(), currentOpettaja);

        EtunimiLabel.setText("Etunimi: " + currentOpettaja.getEtunimi());
        SukunimiLabel.setText("Sukunimi: " + currentOpettaja.getSukunimi());
        SahkopostiLabel.setText("Sähköposti: " + currentOpettaja.getSahkoposti());

        EtunimiTextField.setVisible(false);
        SukunimiTextField.setVisible(false);
        SahkopostiTextField.setVisible(false);

        ProfiiliMuokkaaButton.setVisible(true);
        ProfiiliTallennaButton.setVisible(false);
    }

    @FXML
    public void CloseProgram(ActionEvent event) {
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
    public void navigateBackwards(ActionEvent event) {
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