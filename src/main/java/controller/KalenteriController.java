package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.KirjautunutKayttaja;
import model.Oppitunti;
import service.OppituntiService;
import view.PaivanOppitunnitList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class KalenteriController {

    @FXML
    public Button NewEvent;
    @FXML
    public ListView<PaivanOppitunnitList> dayListView;
    @FXML
    private Button PreviousWeekButton;
    @FXML
    private Button CurrentWeekButton;
    @FXML
    private Button NextWeekButton;
    @FXML
    private Button ProfiiliButton;
    @FXML
    private Button TakaisinButton;
    @FXML
    private Button LogOutButton;

    private OppituntiService oppituntiService;
    private LocalDate currentWeekStart;
    public KalenteriController() {
        this.oppituntiService = new OppituntiService();
    }

    @FXML
    public void initialize() {
        oppituntiService = new OppituntiService();
        currentWeekStart = LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        loadWeekView(currentWeekStart);
    }
    public List<Oppitunti> getTodayCourses() {
        LocalDate today = LocalDate.now();
        LocalDateTime dayStart = today.atStartOfDay();
        LocalDateTime dayEnd = today.plusDays(1).atStartOfDay();
        return oppituntiService.getOppitunnitBetweenDates(dayStart, dayEnd);
    }

    private void loadWeekView(LocalDate weekStart) {
        dayListView.getItems().clear();

        for (int i = 0; i < 7; i++) {
            LocalDate currentDate = weekStart.plusDays(i);
            LocalDateTime dayStart = currentDate.atStartOfDay();
            LocalDateTime dayEnd = currentDate.plusDays(1).atStartOfDay();

            List<Oppitunti> dayOppitunnit = oppituntiService.getOppitunnitBetweenDates(dayStart, dayEnd);
            PaivanOppitunnitList paivanLista = new PaivanOppitunnitList(dayOppitunnit, currentDate);
            dayListView.getItems().add(paivanLista);
        }
    }

    @FXML
    void showPreviousWeek(ActionEvent event) {
        currentWeekStart = currentWeekStart.minusWeeks(1);
        loadWeekView(currentWeekStart);
    }

    @FXML
    void showCurrentWeek(ActionEvent event) {
        currentWeekStart = LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1);
        loadWeekView(currentWeekStart);
    }

    @FXML
    void showNextWeek(ActionEvent event) {
        currentWeekStart = currentWeekStart.plusWeeks(1);
        loadWeekView(currentWeekStart);
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

    @FXML
    void newEvent(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lisaaOppitunti.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}