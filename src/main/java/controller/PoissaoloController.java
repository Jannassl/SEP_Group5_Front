// PoissaoloController.java
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import model.Kurssi;
import model.Opiskelija;
import model.Oppitunti;
import service.KurssiService;
import service.OpiskelijaService;
import service.OppituntiService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PoissaoloController {
    @FXML private TableView<Kurssi> LectureTableView;
    @FXML private TableView<Opiskelija> StudentTableView;
    @FXML private TableColumn<Kurssi, String> courseNameColumn;
    @FXML private TableColumn<Kurssi, String> lectureDetailColumn;
    @FXML private TableColumn<Kurssi, String> lectureTimeColumn;
    @FXML private TableColumn<Kurssi, String> lectureLocation;
    @FXML private TableColumn<Opiskelija, Long> idColumn;
    @FXML private TableColumn<Opiskelija, String> firstNameColumn;
    @FXML private TableColumn<Opiskelija, String> lastNameColumn;
    @FXML private TextField LectureSearchField;
    @FXML private TextField StudentSearchField;
    @FXML private Button TakaisinButton;

    private KurssiService kurssiService;
    private OpiskelijaService opiskelijaService;
    private OppituntiService oppituntiService;
    private FilteredList<Kurssi> filteredLectureData;
    private FilteredList<Opiskelija> filteredStudentData;

    public PoissaoloController (){
        this.kurssiService = new KurssiService();
        this.oppituntiService = new OppituntiService();
        this.opiskelijaService = new OpiskelijaService();
    }

    @FXML
    private void initialize() {
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        lectureDetailColumn.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));
        lectureTimeColumn.setCellValueFactory(new PropertyValueFactory<>("alkupvm"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("opiskelija_id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
        loadAllCourses();
        loadOpiskelijat();

        filteredLectureData = new FilteredList<>(LectureTableView.getItems(), p -> true);
        LectureTableView.setItems(filteredLectureData);
        filteredStudentData = new FilteredList<>(StudentTableView.getItems(), p -> true);
        StudentTableView.setItems(filteredStudentData);

        StudentSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterStudentList(newValue));

        LectureSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterCourseData(newValue));
    }
    private void filterCourseData(String searchText) {
        filteredLectureData.setPredicate(kurssi -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            if (kurssi.getNimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (kurssi.getKuvaus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
    }

    private void loadOpiskelijat() {
        List<Opiskelija> opiskelijat = opiskelijaService.getAllOpiskelijat();
        ObservableList<Opiskelija> opiskelijaObservableList = FXCollections.observableArrayList(opiskelijat);
        StudentTableView.setItems(opiskelijaObservableList);
    }



    public void loadAllCourses() {
        List<Kurssi> kurssit = kurssiService.getAllKurssit();
        ObservableList<Kurssi> observableKurssit = FXCollections.observableArrayList(kurssit);
        LectureTableView.setItems(observableKurssit);

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
    private void filterStudentList(String searchText) {
        filteredStudentData.setPredicate(opiskelija -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            if (opiskelija.getEtunimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            } else if (opiskelija.getSukunimi().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        });
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
}