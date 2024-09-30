package model;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class OpintosuoritusTest {

    @Test
    void getSuoritus_id() {
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setSuoritus_id(1L);
        assertEquals(1L, opintosuoritus.getSuoritus_id());
    }

    @Test
    void setSuoritus_id() {
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setSuoritus_id(1L);
        assertEquals(1L, opintosuoritus.getSuoritus_id());
    }

    @Test
    void getOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setOpiskelija(opiskelija);
        assertEquals(opiskelija, opintosuoritus.getOpiskelija());
    }

    @Test
    void setOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setOpiskelija(opiskelija);
        assertEquals(opiskelija, opintosuoritus.getOpiskelija());
    }

    @Test
    void getKurssi() {
        Kurssi kurssi = new Kurssi();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setKurssi(kurssi);
        assertEquals(kurssi, opintosuoritus.getKurssi());
    }

    @Test
    void setKurssi() {
        Kurssi kurssi = new Kurssi();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setKurssi(kurssi);
        assertEquals(kurssi, opintosuoritus.getKurssi());
    }

    @Test
    void getArvosana() {
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setArvosana(5);
        assertEquals(5, opintosuoritus.getArvosana());
    }

    @Test
    void setArvosana() {
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setArvosana(5);
        assertEquals(5, opintosuoritus.getArvosana());
    }

    @Test
    void getArvostelupvm() {
        Date date = new Date();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setArvostelupvm(date);
        assertEquals(date, opintosuoritus.getArvostelupvm());
    }

    @Test
    void setArvostelupvm() {
        Date date = new Date();
        Opintosuoritus opintosuoritus = new Opintosuoritus();
        opintosuoritus.setArvostelupvm(date);
        assertEquals(date, opintosuoritus.getArvostelupvm());
    }

    @Test
    void testToString() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpiskelija_id(1L);
        Kurssi kurssi = new Kurssi();
        kurssi.setKurssi_id(1L);
        Date date = new Date();
        Opintosuoritus opintosuoritus = new Opintosuoritus(opiskelija, kurssi, 5, date);
        String expected = "Opintosuoritus{suoritus_id=null, opiskelija=1, kurssi=1, arvosana=5, arvostelupvm=" + date + "}";
        assertEquals(expected, opintosuoritus.toString());
    }
}