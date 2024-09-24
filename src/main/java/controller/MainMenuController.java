//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button KalenteriButton;
    @FXML
    private Button KurssitButton;
    @FXML
    private ListView<?> ListViewMainMenu;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button OppilaatButton;
    @FXML
    private Button ProfiiliButton;
    @FXML
    private Button TapahtumatButton;

    public MainMenuController() {
    }

    @FXML
    void CloseProgram(ActionEvent event) {
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
