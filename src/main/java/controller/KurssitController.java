package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.Kurssi;
import service.KurssiService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;

public class KurssitController {

    @FXML private Button ProfiiliButton;
    @FXML private Button LogOutButton;
    @FXML private ListView<Kurssi> CourseListView;
    @FXML private TextField SearchTextField;
    @FXML private Button CoursePageSearchButton;
    @FXML private Button TakaisinButton;
    @FXML private Text tunnusField;
    @FXML private TextField nimiField;
    @FXML private Text opettajaField;
    @FXML private TextField aloitusPvmField;
    @FXML private TextField lopetusPvmField;
    @FXML private TextArea kuvausTextArea;
    @FXML private Button editSaveButton;

    private KurssiService kurssiService;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private boolean isEditing = false;


    @FXML
    private void initialize() {
        kurssiService = new KurssiService();
        loadAllCourses();

        CourseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayCourseInfo(newValue);
            }
        });

        setFieldsEditable(false);
    }

    private void setFieldsEditable(boolean editable) {
        nimiField.setEditable(editable);
        aloitusPvmField.setEditable(editable);
        lopetusPvmField.setEditable(editable);
        kuvausTextArea.setEditable(editable);
    }

    private void loadAllCourses() {
        List<Kurssi> kurssit = kurssiService.getAllKurssit();
        ObservableList<Kurssi> observableKurssit = FXCollections.observableArrayList(kurssit);
        CourseListView.setItems(observableKurssit);
    }

    @FXML
    private void handleEditSave() {
        if (isEditing) {
            // Save functionality
            saveCourseChanges();
            setFieldsEditable(false);
            editSaveButton.setText("Muokkaa");
        } else {
            // Edit functionality
            setFieldsEditable(true);
            editSaveButton.setText("Tallenna");
        }
        isEditing = !isEditing;
    }

    @FXML
    private void openProfiiliPage() {
        // Implement profile page opening logic
    }

    @FXML
    private void CloseProgram() {
        // Implement closing program logic
        System.exit(0);
    }

    @FXML
    private void searchCourse() {
        String searchTerm = SearchTextField.getText().trim().toLowerCase();
        if (searchTerm.isEmpty()) {
            loadAllCourses();
        } else {
            List<Kurssi> allKurssit = kurssiService.getAllKurssit();
            List<Kurssi> filteredKurssit = allKurssit.stream()
                    .filter(kurssi -> kurssi.getNimi().toLowerCase().contains(searchTerm))
                    .toList();
            CourseListView.setItems(FXCollections.observableArrayList(filteredKurssit));
        }
    }

    @FXML
    private void navigateBackwards(ActionEvent event) {
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

    private void saveCourseChanges() {
        Kurssi selectedKurssi = CourseListView.getSelectionModel().getSelectedItem();
        if (selectedKurssi != null) {
            try {
                selectedKurssi.setNimi(nimiField.getText());
                selectedKurssi.setAlkupvm(dateFormat.parse(aloitusPvmField.getText()));
                selectedKurssi.setLoppupvm(dateFormat.parse(lopetusPvmField.getText()));
                selectedKurssi.setKuvaus(kuvausTextArea.getText());

                kurssiService.updateKurssi(selectedKurssi.getKurssi_id(), selectedKurssi);
                loadAllCourses();
            } catch (ParseException e) {
                showAlert("Väärä formaatti päivämäärälle käytä dd.MM.yyyy");
            }
        }
    }

    private void displayCourseInfo(Kurssi kurssi) {
        tunnusField.setText(String.valueOf(kurssi.getKurssi_id()));
        nimiField.setText(kurssi.getNimi());
        opettajaField.setText(kurssi.getOpettaja().getNimi());
        aloitusPvmField.setText(dateFormat.format(kurssi.getAlkupvm()));
        lopetusPvmField.setText(dateFormat.format(kurssi.getLoppupvm()));
        kuvausTextArea.setText(kurssi.getKuvaus());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tapahtui virhe");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}