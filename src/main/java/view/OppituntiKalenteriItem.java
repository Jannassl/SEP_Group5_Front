package view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Oppitunti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class OppituntiKalenteriItem extends VBox {
    private final Oppitunti oppitunti;
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private static final Duration DAY_DURATION = Duration.ofHours(11); // 11 hours (07:00 - 18:00) adjustable

    public OppituntiKalenteriItem(Oppitunti oppitunti) {
        this.oppitunti = oppitunti;
        setup();
    }

    private void setup() {
        Label kurssiLabel = new Label(oppitunti.getKurssi().getNimi());
        Label aikaLabel = new Label(formatTimeRange(oppitunti.getAlkuaika(), oppitunti.getLoppuaika()));

        double listHeight = 600;

        this.getChildren().addAll(kurssiLabel, aikaLabel);
        this.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-padding: 5px;");

        double heightPercentage = calculateHeightPercentage();
        this.setPrefHeight(heightPercentage * listHeight);
    }

    private String formatTimeRange(LocalDateTime start, LocalDateTime end) {
        return start.format(TIME_FORMAT) + " - " + end.format(TIME_FORMAT);
    }

    private double calculateHeightPercentage() {
        Duration oppituntiDuration = Duration.between(oppitunti.getAlkuaika(), oppitunti.getLoppuaika());
        return (double) oppituntiDuration.toMinutes() / DAY_DURATION.toMinutes();
    }
}