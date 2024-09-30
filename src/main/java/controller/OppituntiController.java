package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Kurssi;
import model.Opiskelija;
import service.KurssiService;

import java.util.List;

public class OppituntiController {

    public Button TakaisinButton;
    public Button CoursePageSearchButton;
    public TextField SearchTextField;
    public Button editSaveButton;
    public Button ProfiiliButton;
    public Button LogOutButton;
    @FXML private ListView<Kurssi> courseListView;
    @FXML private TextField kurssiField;
    @FXML private DatePicker pvmField;
    @FXML private TextField kellonaikaField;
    private KurssiService kurssiService;

    @FXML
    private void initialize() {
        kurssiService = new KurssiService();

        loadAllCourses();

        courseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayCourseInfo(newValue);
            }
        });

    }

    private void loadAllCourses() {
        System.out.println("loading courses");
        List<Kurssi> kurssit = kurssiService.getAllKurssit();
        ObservableList<Kurssi> observableKurssit = FXCollections.observableArrayList(kurssit);
        courseListView.setItems(observableKurssit);


    }

    public void handleEditSave(ActionEvent actionEvent) {
        Kurssi selectedKurssi = courseListView.getSelectionModel().getSelectedItem();

        if (selectedKurssi != null) {
            // TODO tallennus

        }
    }

    public void handleSearchStudent(ActionEvent actionEvent) {
    }

    public void navigateBackwards(ActionEvent actionEvent) {
    }

    public void CloseProgram(ActionEvent actionEvent) {
    }

    public void openProfiiliPage(ActionEvent actionEvent) {
    }


    private void displayCourseInfo(Kurssi kurssi) {
        kurssiField.setText(kurssi.getNimi());
    }


    private boolean validateKellonaika() {
        return false;
    }
}
