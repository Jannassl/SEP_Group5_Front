package model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class OppituntiTest {

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
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setAlkuaika(date);
        assertEquals(date, oppitunti.getAlkuaika());
    }

    @Test
    void setAlkuaika() {
        Date date = new Date();
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setAlkuaika(date);
        assertEquals(date, oppitunti.getAlkuaika());
    }

    @Test
    void getLoppuaika() {
        Date date = new Date();
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setLoppuaika(date);
        assertEquals(date, oppitunti.getLoppuaika());
    }

    @Test
    void setLoppuaika() {
        Date date = new Date();
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setLoppuaika(date);
        assertEquals(date, oppitunti.getLoppuaika());
    }
}