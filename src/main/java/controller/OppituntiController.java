package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Kurssi;
import model.Oppitunti;
import service.KurssiService;
import service.OppituntiService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private OppituntiService oppituntiService;

    @FXML
    private void initialize() {
        kurssiService = new KurssiService();
        oppituntiService = new OppituntiService();

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

    public boolean handleEditSave(ActionEvent actionEvent) {
        Kurssi selectedKurssi = courseListView.getSelectionModel().getSelectedItem();

        if (selectedKurssi != null) {
            if (!validateInputs()) {
                return false;
            }

            LocalDate date = pvmField.getValue();
            String[] kellonajat = kellonaikaField.getText().split("-");
            LocalTime alkuaika = LocalTime.parse(kellonajat[0].trim());
            LocalTime loppuaika = LocalTime.parse(kellonajat[1].trim());

            LocalDateTime alkuaikaDateTime = LocalDateTime.of(date, alkuaika);
            LocalDateTime loppuaikaDateTime = LocalDateTime.of(date, loppuaika);

            Oppitunti oppitunti = new Oppitunti();
            oppitunti.setKurssi(selectedKurssi);
            oppitunti.setAlkuaika(alkuaikaDateTime);
            oppitunti.setLoppuaika(loppuaikaDateTime);

            oppituntiService.createOppitunti(oppitunti);

            pvmField.setValue(null);
            kellonaikaField.clear();
            navigateBackwards(actionEvent);
            return true;
        }
        return false;
    }

    private boolean validateInputs() {
        if (pvmField.getValue() == null) {
            System.out.println("Please select a date.");
            return false;
        }
        return validateKellonaika();
    }

    public void handleSearchStudent(ActionEvent actionEvent) {
    }

    public void navigateBackwards(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kalenteri.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CloseProgram(ActionEvent actionEvent) {

    }

    public void openProfiiliPage(ActionEvent actionEvent) {

    }

    private void displayCourseInfo(Kurssi kurssi) {
        kurssiField.setText(kurssi.getNimi());
    }

    private boolean validateKellonaika() {
        String kellonaikaText = kellonaikaField.getText();
        if (!kellonaikaText.contains("-")) {
            System.out.println("Invalid time format. Use HH:mm-HH:mm");
            return false;
        }

        String[] kellonajat = kellonaikaText.split("-");
        if (kellonajat.length != 2) {
            System.out.println("Invalid time format. Use HH:mm-HH:mm");
            return false;
        }

        try {
            LocalTime alkuaika = LocalTime.parse(kellonajat[0].trim());
            LocalTime loppuaika = LocalTime.parse(kellonajat[1].trim());

            if (loppuaika.isBefore(alkuaika) || loppuaika.equals(alkuaika)) {
                System.out.println("End time must be after start time.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid time format. Use HH:mm-HH:mm");
            return false;
        }

        return true;
    }
}