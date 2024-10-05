package view;

import javafx.scene.control.TableRow;
import model.Oppitunti;
import java.time.LocalDate;

public class CustomTableRow extends TableRow<Oppitunti> {
    @Override
    protected void updateItem(Oppitunti item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setStyle("");
        } else {
            LocalDate today = LocalDate.now();
            if (item.getAlkuaika().toLocalDate().equals(today)) {
                setStyle("-fx-background-color: lightblue;");
            } else {
                setStyle("");
            }
        }
    }
}