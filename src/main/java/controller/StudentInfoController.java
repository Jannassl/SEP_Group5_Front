package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StudentInfoController {

    @FXML
    private Button LogOutButton;

    @FXML
    private Button OppilasPageSearchButton;

    @FXML
    private Button ProfiiliButton;

    @FXML
    private Button TakaisinButton;

    @FXML
    void CloseProgram(ActionEvent event) {

    }

    @FXML
    void navigateBackwards(ActionEvent event) {

    }

    @FXML
    void openProfiiliPage(ActionEvent event) {

    }

    @FXML
    void searchOppilas(ActionEvent event) {

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
