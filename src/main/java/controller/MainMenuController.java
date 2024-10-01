//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import model.Kurssi;
import model.Oppitunti;
import service.KurssiService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MainMenuController {
    @FXML
    private Button KalenteriButton;
    @FXML
    private Button KurssitButton;
    @FXML
    private ListView<String> ListViewMainMenu;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button OppilaatButton;
    @FXML
    private Button ProfiiliButton;
    @FXML
    private Button TapahtumatButton;
    private KurssiService kurssiService;

    public MainMenuController() {
        this.kurssiService = new KurssiService();
    }

    @FXML
    public void initialize() {
        KalenteriController kalenteriController = new KalenteriController();
        List<Oppitunti> todayCourses = kalenteriController.getTodayCourses();

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Oppitunti oppitunti : todayCourses) {
            items.add(oppitunti.getKurssi().getNimi());
        }

        ListViewMainMenu.setItems(items);
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
    void openKalenteriPage(ActionEvent event) {
    }

    @FXML
    void openKurssitPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kurssit.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openOppilaatPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentInfo.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openProfiiliPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/profiili.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openTapahtumatPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kalenteri.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /* NÄIN LISÄTÄÄN LISTVIEW KOHTAAN SISÄLTÖÄ
    @FXML
    private ListView<String> listViewMainMenu;

    @FXML
    public void initialize() {
        // Create an observable list of items
        ObservableList<String> items = FXCollections.observableArrayList(
            "Item 1", "Item 2", "Item 3"
        );

        // Add items to the ListView
        listViewMainMenu.setItems(items);
    }
     */
}
