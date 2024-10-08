package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.Opiskelija;
import model.Kurssi;
import model.Opintosuoritus;
import service.OpiskelijaService;
import service.KurssiService;
import service.OpintosuoritusService;
import view.OpiskelijaKurssiItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentInfoController {
    @FXML
    private Button naytaKurssitButton;
    @FXML
    private ListView<OpiskelijaKurssiItem> opiskelijanKurssitList;
    @FXML
    private Label nimiLabel;
    @FXML
    private Label sahkopostiLabel;
    @FXML
    private Label puhelinnumeroLabel;
    @FXML
    private Label huoltajaLabel;
    @FXML
    private Button LogOutButton;
    @FXML
    private Button OppilasPageSearchButton;
    @FXML
    private Button ProfiiliButton;
    @FXML
    private Button TakaisinButton;
    @FXML
    private TableView<Opiskelija> StudentTableView;
    @FXML
    private TableColumn<Opiskelija, Long> idColumn;
    @FXML
    private TableColumn<Opiskelija, String> firstNameColumn;
    @FXML
    private TableColumn<Opiskelija, String> lastNameColumn;
    @FXML
    private TextField SearchTextField;

    private OpiskelijaService opiskelijaService;
    private KurssiService kurssiService;
    private OpintosuoritusService opintosuoritusService;
    private FilteredList<Opiskelija> filteredData;
    private boolean isShowingCourses = false;

    public StudentInfoController() {
        this.opiskelijaService = new OpiskelijaService();
        this.kurssiService = new KurssiService();
        this.opintosuoritusService = new OpintosuoritusService();
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("opiskelija_id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));

        loadOpiskelijat();

        filteredData = new FilteredList<>(StudentTableView.getItems(), p -> true);
        StudentTableView.setItems(filteredData);

        SearchTextField.textProperty().addListener((observable, oldValue, newValue) -> filterStudentList(newValue));

        StudentTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStudentDetails(newValue));
    }

    private void loadOpiskelijat() {
        List<Opiskelija> opiskelijat = opiskelijaService.getAllOpiskelijat();
        ObservableList<Opiskelija> opiskelijaObservableList = FXCollections.observableArrayList(opiskelijat);
        StudentTableView.setItems(opiskelijaObservableList);
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

    private void filterStudentList(String searchText) {
        filteredData.setPredicate(opiskelija -> {
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

    private void showStudentDetails(Opiskelija opiskelija) {
        if (opiskelija != null) {
            nimiLabel.setText("Nimi: " + opiskelija.getEtunimi() + " " + opiskelija.getSukunimi());
            sahkopostiLabel.setText("Sähköposti: " + opiskelija.getSahkoposti());
            puhelinnumeroLabel.setText("Puhelinnumero: " + opiskelija.getPuhelinnumero());
            if (opiskelija.getHuoltaja() != null) {
                huoltajaLabel.setText("Huoltaja: " + opiskelija.getHuoltaja().getEtunimi() + " " + opiskelija.getHuoltaja().getSukunimi());
            } else {
                huoltajaLabel.setText("Huoltaja: Ei määritetty");
            }
        } else {
            nimiLabel.setText("");
            sahkopostiLabel.setText("");
            puhelinnumeroLabel.setText("");
            huoltajaLabel.setText("");
        }
    }

    @FXML
    public void showCourses(ActionEvent actionEvent) {
        Opiskelija selectedOpiskelija = StudentTableView.getSelectionModel().getSelectedItem();
        if (selectedOpiskelija == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ei valittua opiskelijaa");
            alert.setHeaderText(null);
            alert.setContentText("Valitse ensin opiskelija listasta.");
            alert.showAndWait();
            return;
        }

        if (!isShowingCourses) {
            // Show courses
            List<Kurssi> opiskelijanKurssit = kurssiService.getKurssitByOpiskelija(selectedOpiskelija);
            List<OpiskelijaKurssiItem> kurssiItems = new ArrayList<>();

            for (Kurssi kurssi : opiskelijanKurssit) {
                Opintosuoritus opintosuoritus = opintosuoritusService.getOpintosuoritusByOpiskelijaAndKurssi(selectedOpiskelija, kurssi);
                kurssiItems.add(new OpiskelijaKurssiItem(kurssi, opintosuoritus, opintosuoritusService, selectedOpiskelija));
            }

            opiskelijanKurssitList.setItems(FXCollections.observableArrayList(kurssiItems));
            StudentTableView.setVisible(false);
            opiskelijanKurssitList.setVisible(true);
            naytaKurssitButton.setText("Takaisin");
        } else {
            // Show student list
            StudentTableView.setVisible(true);
            opiskelijanKurssitList.setVisible(false);
            naytaKurssitButton.setText("Näytä oppilaan kurssit");
        }

        isShowingCourses = !isShowingCourses;
    }
}