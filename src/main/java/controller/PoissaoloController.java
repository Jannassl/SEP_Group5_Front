// PoissaoloController.java
package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import model.*;
import service.KurssiService;
import service.OpiskelijaService;
import service.OppituntiService;
import service.KurssiIlmoittautuminenService;
import view.CustomTableRow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class PoissaoloController {
    @FXML private TableView<Kurssi> LectureTableView;
    @FXML private TableView<Oppitunti> oppituntiTableView;
    @FXML private TableColumn<Kurssi, String> courseNameColumn;
    @FXML private TableColumn<Kurssi, Long> courseTeacherColumn;

    @FXML private TableColumn<Oppitunti, Long> idColumn;
    @FXML private TableColumn<Oppitunti, LocalDate> pvmColumn;
    @FXML private TableColumn<Oppitunti, String> alkuaikaColumn;
    @FXML private TableColumn<Oppitunti, String> loppuaikaColumn;
    @FXML private TableColumn<Oppitunti, Long> kurssiID;
    @FXML private TextField LectureSearchField;
    @FXML private TextField StudentSearchField;
    @FXML private Button TakaisinButton;
    @FXML private Button nameListButton;
     // Add this line

    private KurssiService kurssiService;
    private OpiskelijaService opiskelijaService;
    private OppituntiService oppituntiService;
    private FilteredList<Kurssi> filteredLectureData;
    private FilteredList<Oppitunti> filteredOppituntiData;

    public PoissaoloController() {
        this.kurssiService = new KurssiService();
        this.oppituntiService = new OppituntiService();
        this.opiskelijaService = new OpiskelijaService();
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("oppitunti_id"));
        alkuaikaColumn.setCellValueFactory(cellData -> {
            LocalDateTime alkuaika = cellData.getValue().getAlkuaika();
            return new SimpleStringProperty(alkuaika != null ? alkuaika.format(DateTimeFormatter.ofPattern("HH:mm")) : null);
        });
        loppuaikaColumn.setCellValueFactory(cellData -> {
            LocalDateTime loppuaika = cellData.getValue().getLoppuaika();
            return new SimpleStringProperty(loppuaika != null ? loppuaika.format(DateTimeFormatter.ofPattern("HH:mm")) : null);
        });

        pvmColumn.setCellValueFactory(cellData -> {
            LocalDateTime alkuaika = cellData.getValue().getAlkuaika();
            return new SimpleObjectProperty<>(alkuaika != null ? alkuaika.toLocalDate() : null);
        });

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("nimi"));
        courseTeacherColumn.setCellValueFactory(new PropertyValueFactory<>("opettajaId"));

        insertNames();
        loadOpiskelijat();

        filteredLectureData = new FilteredList<>(LectureTableView.getItems(), p -> true);
        LectureTableView.setItems(filteredLectureData);
        filteredOppituntiData = new FilteredList<>(oppituntiTableView.getItems(), p -> true);
        oppituntiTableView.setItems(filteredOppituntiData);

        StudentSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterOppituntiList(newValue));
        LectureSearchField.textProperty().addListener((observable, oldValue, newValue) -> filterCourseData(newValue));

        // Add listener to LectureTableView
        LectureTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterLecturesByCourse(newValue);
                nameListButton.setDisable(false); // Enable the button when a course is selected
            } else {
                nameListButton.setDisable(true); // Disable the button when no course is selected
            }
        });
        oppituntiTableView.setRowFactory(tv -> new CustomTableRow());

        nameListButton.setDisable(true); // Initially disable the button
    }

    private void filterCourseData(String searchText) {
        filteredLectureData.setPredicate(kurssi -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();
            return kurssi.getNimi().toLowerCase().contains(lowerCaseFilter);
        });
    }

    private void loadOpiskelijat() {
        List<Oppitunti> oppitunnit = oppituntiService.getAllOppitunnit();
        oppitunnit.sort(Comparator.comparing(Oppitunti::getOppitunti_id).reversed());
        ObservableList<Oppitunti> oppituntiObservableList = FXCollections.observableArrayList(oppitunnit);
        oppituntiTableView.setItems(oppituntiObservableList);
    }

    private void insertNames() {
        List<Kurssi> kurssit = kurssiService.getAllKurssit();
        ObservableList<Kurssi> kurssiObservableList = FXCollections.observableArrayList(kurssit);
        LectureTableView.setItems(kurssiObservableList);
    }

    private void filterLecturesByCourse(Kurssi selectedCourse) {
        filteredOppituntiData.setPredicate(oppitunti -> {
            if (selectedCourse == null) {
                return true;
            }
            return oppitunti.getKurssi().getKurssi_id().equals(selectedCourse.getKurssi_id());
        });
    }

    @FXML
    void CloseProgram(ActionEvent event) {
        KirjautunutKayttaja.getInstance().clearOpettaja();

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

    private void filterOppituntiList(String searchText) {
        filteredOppituntiData.setPredicate(oppitunti -> {
            if (searchText == null || searchText.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = searchText.toLowerCase();

            if (String.valueOf(oppitunti.getOppitunti_id()).contains(lowerCaseFilter)) {
                return true;
            }

            if (oppitunti.getAlkuaika() != null && oppitunti.getAlkuaika().toString().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }

            if (oppitunti.getLoppuaika() != null && oppitunti.getLoppuaika().toString().toLowerCase().contains(lowerCaseFilter)) {
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

    @FXML
    void openNamelist(ActionEvent event) {
        Kurssi selectedKurssi = LectureTableView.getSelectionModel().getSelectedItem();
        if (selectedKurssi != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentListPopup.fxml"));
                Parent root = loader.load();
                StudentListPopupController controller = loader.getController();
                controller.setSelectedKurssi(selectedKurssi);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Student List");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}