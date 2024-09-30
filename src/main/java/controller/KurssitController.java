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

import model.KirjautunutKayttaja;
import model.Kurssi;
import service.KurssiService;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.text.SimpleDateFormat;

public class KurssitController {

    @FXML private Button deleteCourseButton;
    @FXML private Button ProfiiliButton;
    @FXML private Button LogOutButton;
    @FXML private ListView<Kurssi> CourseListView;
    @FXML private TextField SearchTextField;
    @FXML private Button CoursePageSearchButton;
    @FXML private Button TakaisinButton;
    @FXML private Text tunnusField;
    @FXML private Text nimiField;
    @FXML private Text opettajaField;
    @FXML private Text aloitusPvmField;
    @FXML private Text lopetusPvmField;
    @FXML private TextArea kuvausTextArea;
    @FXML private Button editSaveButton;

    @FXML private Button createNewCourseButton;

    private KurssiService kurssiService;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @FXML
    private void initialize() {
        kurssiService = new KurssiService();
        loadAllCourses();

        CourseListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                displayCourseInfo(newValue);
            }
        });

        kuvausTextArea.setEditable(false);
    }

    private void loadAllCourses() {
        System.out.println("loading courses");
        List<Kurssi> kurssit = kurssiService.getAllKurssit();
        ObservableList<Kurssi> observableKurssit = FXCollections.observableArrayList(kurssit);
        CourseListView.setItems(observableKurssit);


    }

    @FXML
    private void handleEditSave(ActionEvent event) {
        Kurssi selectedKurssi = CourseListView.getSelectionModel().getSelectedItem();
        if (selectedKurssi != null) {
            openUusiKurssiWindow(event, selectedKurssi);
        } else {
            showAlert("Valitse kurssi ensin");
        }
    }

    @FXML
    private void openProfiiliPage() {
        // Implement profile page opening logic
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

    private void openUusiKurssiWindow(ActionEvent event, Kurssi kurssi) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lisaaKurssi.fxml"));
            Parent root = loader.load();

            UusiKurssiController controller = loader.getController();
            controller.initData(kurssi); // kurssi may be null for new course
            controller.setKurssitController(this);

            Stage stage =  (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(kurssi == null ? "Luo uusi kurssi" : "Muokkaa kurssia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Virhe avattaessa kurssi-ikkunaa");
        }
    }


    public void refreshCourses(Kurssi updatedKurssi) {
        loadAllCourses();
        if (updatedKurssi != null) {
            for (Kurssi kurssi : CourseListView.getItems()) {
                if (kurssi.getKurssi_id().equals(updatedKurssi.getKurssi_id())) {
                    CourseListView.getSelectionModel().select(kurssi);
                    displayCourseInfo(kurssi);
                    break;
                }
            }
        }
        CourseListView.refresh();
    }

    @FXML
    private void handleCreateNewCourse(ActionEvent event) {
        openUusiKurssiWindow(event,null); // Passing null indicates creating a new course
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

    public void handleDeleteCourse(ActionEvent actionEvent) {
        Kurssi selectedKurssi = CourseListView.getSelectionModel().getSelectedItem();
        kurssiService.deleteKurssi(selectedKurssi.getKurssi_id());
        refreshCourses(selectedKurssi);
    }
}