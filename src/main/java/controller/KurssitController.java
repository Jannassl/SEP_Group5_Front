package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class KurssitController {

    @FXML
    private Button ProfiiliButton;

    @FXML
    private Button LogOutButton;

    @FXML
    private ListView<String> CourseListView;

    @FXML
    private TextField SearchTextField;

    @FXML
    private Button CoursePageSearchButton;

    @FXML
    private Button TakaisinButton;

    @FXML
    private Text tunnusText;

    @FXML
    private Text nimiText;

    @FXML
    private Text opettajaText;

    @FXML
    private Text aloitusPvmText;

    @FXML
    private Text lopetusPvmText;

    @FXML
    private TextArea kuvausTextArea;

    @FXML
    private void initialize() {

    }

    @FXML
    private void openProfiiliPage() {

    }

    @FXML
    private void CloseProgram() {

    }

    @FXML
    private void searchCourse() {

    }

    @FXML
    private void navigateBackwards() {

    }

    public void updateCourseInfo(String tunnus, String nimi, String opettaja, String aloitusPvm, String lopetusPvm, String kuvaus) {
        tunnusText.setText(tunnus);
        nimiText.setText(nimi);
        opettajaText.setText(opettaja);
        aloitusPvmText.setText(aloitusPvm);
        lopetusPvmText.setText(lopetusPvm);
        kuvausTextArea.setText(kuvaus);
    }
}