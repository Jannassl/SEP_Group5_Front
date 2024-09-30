package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Oppitunti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PaivanOppitunnitList extends VBox {
    private List<Oppitunti> oppitunnit;
    private LocalDate date;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public PaivanOppitunnitList(List<Oppitunti> oppitunnit, LocalDate date) {
        this.oppitunnit = oppitunnit;
        this.date = date;
        setup();
    }

    private void setup() {
        this.setSpacing(5);
        this.setPrefWidth(200);
        this.setPrefHeight(600);

        Label dateLabel = new Label(date.format(DATE_FORMATTER));
        dateLabel.setStyle("-fx-font-weight: bold;");

        if (date.equals(LocalDate.now())) {
            this.setStyle("-fx-background-color: lightyellow;");
            dateLabel.setStyle(dateLabel.getStyle() + "-fx-text-fill: red;");
        }

        this.getChildren().add(dateLabel);

        for (Oppitunti oppitunti : oppitunnit) {
            OppituntiKalenteriItem item = new OppituntiKalenteriItem(oppitunti);
            this.getChildren().add(item);
        }
    }
}