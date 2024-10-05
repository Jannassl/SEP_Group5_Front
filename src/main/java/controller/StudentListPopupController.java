package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Kurssi;
import model.KurssiIlmoittautuminen;
import service.KurssiIlmoittautuminenService;

import java.util.List;

public class StudentListPopupController {

    @FXML private TableView<KurssiIlmoittautuminen> studentTableView;
    @FXML private TableColumn<KurssiIlmoittautuminen, String> studentNameColumn;
    @FXML private Button saveAttendanceButton;

    private KurssiIlmoittautuminenService kurssiIlmoittautuminenService;
    private ObservableList<KurssiIlmoittautuminen> studentList;
    private Kurssi selectedKurssi;

    public StudentListPopupController() {
        this.kurssiIlmoittautuminenService = new KurssiIlmoittautuminenService();
    }

    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("opiskelijaNimi"));
    }

    public void setSelectedKurssi(Kurssi kurssi) {
        this.selectedKurssi = kurssi;
        loadStudents();
    }

    private void loadStudents() {
        if (selectedKurssi != null) {
            List<KurssiIlmoittautuminen> students = kurssiIlmoittautuminenService.getIlmoittautumisetByKurssiId(selectedKurssi.getKurssi_id());
            studentList = FXCollections.observableArrayList(students);
            studentTableView.setItems(studentList);
        }
    }

    @FXML
    void saveAttendance(ActionEvent event) {
        // Logic to save attendance if needed
    }
}