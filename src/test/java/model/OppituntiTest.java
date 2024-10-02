package model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class OppituntiTest {

    // Utility method to convert java.util.Date to java.time.LocalDateTime
    private LocalDateTime convertUtilToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Test
    void getOppitunti_id() {
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setOppitunti_id(1L);
        assertEquals(1L, oppitunti.getOppitunti_id());
    }

    @Test
    void setOppitunti_id() {
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setOppitunti_id(1L);
        assertEquals(1L, oppitunti.getOppitunti_id());
    }

    @Test
    void getKurssi() {
        Kurssi kurssi = new Kurssi();
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setKurssi(kurssi);
        assertEquals(kurssi, oppitunti.getKurssi());
    }

    @Test
    void setKurssi() {
        Kurssi kurssi = new Kurssi();
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setKurssi(kurssi);
        assertEquals(kurssi, oppitunti.getKurssi());
    }

    @Test
    void getAlkuaika() {
        Date date = new Date();
        LocalDateTime localDateTime = convertUtilToLocalDateTime(date);
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setAlkuaika(localDateTime);
        assertEquals(localDateTime, oppitunti.getAlkuaika());
    }

    @Test
    void setAlkuaika() {
        Date date = new Date();
        LocalDateTime localDateTime = convertUtilToLocalDateTime(date);
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setAlkuaika(localDateTime);
        assertEquals(localDateTime, oppitunti.getAlkuaika());
    }

    @Test
    void getLoppuaika() {
        Date date = new Date();
        LocalDateTime localDateTime = convertUtilToLocalDateTime(date);
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setLoppuaika(localDateTime);
        assertEquals(localDateTime, oppitunti.getLoppuaika());
    }

    @Test
    void setLoppuaika() {
        Date date = new Date();
        LocalDateTime localDateTime = convertUtilToLocalDateTime(date);
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setLoppuaika(localDateTime);
        assertEquals(localDateTime, oppitunti.getLoppuaika());
    }
}