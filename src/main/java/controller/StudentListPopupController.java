package controller;

import com.mysql.cj.conf.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.Kurssi;
import model.KurssiIlmoittautuminen;
import model.Oppitunti;
import model.Poissaolo;
import service.KurssiIlmoittautuminenService;
import service.PoissaoloService;

import java.util.List;

public class StudentListPopupController {

    @FXML private TableView<KurssiIlmoittautuminen> studentTableView;
    @FXML private TableColumn<KurssiIlmoittautuminen, String> studentNameColumn;
    @FXML private TableColumn<KurssiIlmoittautuminen, Boolean> attendanceColumn;
    @FXML private Button saveAttendanceButton;

    private KurssiIlmoittautuminenService kurssiIlmoittautuminenService;
    private PoissaoloService poissaoloService;
    private ObservableList<KurssiIlmoittautuminen> studentList;
    private Kurssi selectedKurssi;
    private Long oppituntiId;
    private Oppitunti selectedOppitunti;

    public StudentListPopupController() {
        this.kurssiIlmoittautuminenService = new KurssiIlmoittautuminenService();
        this.poissaoloService = new PoissaoloService();
    }
    public void setSelectedKurssiAndOppitunti(Kurssi kurssi, Oppitunti oppitunti) {
        this.selectedKurssi = kurssi;
        this.selectedOppitunti = oppitunti;
        loadStudents();
    }

    @FXML
    private void initialize() {
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("opiskelijaNimi"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("attending"));

        attendanceColumn.setCellFactory(CheckBoxTableCell.forTableColumn(new Callback<Integer, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Integer index) {
                KurssiIlmoittautuminen ilmoittautuminen = studentTableView.getItems().get(index);
                javafx.beans.property.BooleanProperty property = new SimpleBooleanProperty(ilmoittautuminen.isAttending());

                property.addListener((observable, oldValue, newValue) -> {
                    ilmoittautuminen.setAttending(newValue);
                });

                return property;
            }
        }));

        studentTableView.setEditable(true);
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
        for (KurssiIlmoittautuminen ilmoittautuminen : studentList) {
            if (!ilmoittautuminen.isAttending()) { // If the student is not attending
                Poissaolo poissaolo = new Poissaolo();
                poissaolo.setOpiskelija(ilmoittautuminen.getOpiskelija());
                poissaolo.setOppitunti(selectedOppitunti); // Use the selected Oppitunti
                poissaolo.setSyy("Poissa");
                poissaoloService.createPoissaolo(poissaolo);
            }
        }
    }
}