package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Kurssi;
import model.Opintosuoritus;
import model.Opiskelija;
import service.OpintosuoritusService;

import java.util.Date;

public class OpiskelijaKurssiItem extends HBox {
    private Kurssi kurssi;
    private Opintosuoritus opintosuoritus;
    private OpintosuoritusService opintosuoritusService;
    private Opiskelija opiskelija;

    public OpiskelijaKurssiItem(Kurssi kurssi, Opintosuoritus opintosuoritus, OpintosuoritusService opintosuoritusService, Opiskelija opiskelija) {
        this.kurssi = kurssi;
        this.opintosuoritus = opintosuoritus;
        this.opintosuoritusService = opintosuoritusService;
        this.opiskelija = opiskelija;

        setSpacing(10);
        setPadding(new Insets(5));

        Label kurssiLabel = new Label(kurssi.getNimi());
        this.getChildren().add(kurssiLabel);

        if (opintosuoritus != null) {
            Label arvosanaLabel = new Label("Arvosana: " + opintosuoritus.getArvosana());
            Label paivaLabel = new Label("Päivä: " + opintosuoritus.getArvostelupvm().toString());
            this.getChildren().addAll(arvosanaLabel, paivaLabel);
        } else {
            Button addButton = new Button("Lisää arvosana");
            addButton.setOnAction(e -> showAddGradePopup());
            this.getChildren().add(addButton);
        }
    }

    private void showAddGradePopup() {
        Dialog<Opintosuoritus> dialog = new Dialog<>();
        dialog.setTitle("Lisää arvosana");
        dialog.setHeaderText("Lisää arvosana kurssille " + kurssi.getNimi());

        ButtonType saveButtonType = new ButtonType("Tallenna", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField arvosanaField = new TextField();
        arvosanaField.setPromptText("Arvosana");
        DatePicker datePicker = new DatePicker();

        grid.add(new Label("Arvosana:"), 0, 0);
        grid.add(arvosanaField, 1, 0);
        grid.add(new Label("Päivämäärä:"), 0, 1);
        grid.add(datePicker, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    int arvosana = Integer.parseInt(arvosanaField.getText());
                    Date pvm = java.sql.Date.valueOf(datePicker.getValue());
                    Opintosuoritus newOpintosuoritus = new Opintosuoritus(opiskelija, kurssi, arvosana, pvm);
                    return opintosuoritusService.createOpintosuoritus(newOpintosuoritus);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Virheellinen syöte");
                    alert.setHeaderText(null);
                    alert.setContentText("Arvosanan täytyy olla numero.");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(result -> {
            if (result != null) {
                this.opintosuoritus = result;
                this.getChildren().clear();
                Label kurssiLabel = new Label(kurssi.getNimi());
                Label arvosanaLabel = new Label("Arvosana: " + opintosuoritus.getArvosana());
                Label paivaLabel = new Label("Päivä: " + opintosuoritus.getArvostelupvm().toString());
                this.getChildren().addAll(kurssiLabel, arvosanaLabel, paivaLabel);
            }
        });
    }
}