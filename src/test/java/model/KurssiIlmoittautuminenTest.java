// src/test/java/model/KurssiIlmoittautuminenTest.java
package model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KurssiIlmoittautuminenTest {

    @Test
    void getIlmoittautuminen_id() {
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setIlmoittautuminen_id(1L);
        assertEquals(1L, ilmoittautuminen.getIlmoittautuminen_id());
    }

    @Test
    void setIlmoittautuminen_id() {
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setIlmoittautuminen_id(1L);
        assertEquals(1L, ilmoittautuminen.getIlmoittautuminen_id());
    }

    @Test
    void getOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setOpiskelija(opiskelija);
        assertEquals(opiskelija, ilmoittautuminen.getOpiskelija());
    }

    @Test
    void setOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setOpiskelija(opiskelija);
        assertEquals(opiskelija, ilmoittautuminen.getOpiskelija());
    }

    @Test
    void getKurssi() {
        Kurssi kurssi = new Kurssi();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setKurssi(kurssi);
        assertEquals(kurssi, ilmoittautuminen.getKurssi());
    }

    @Test
    void setKurssi() {
        Kurssi kurssi = new Kurssi();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setKurssi(kurssi);
        assertEquals(kurssi, ilmoittautuminen.getKurssi());
    }

    @Test
    void getIlmoittautuminen_pvm() {
        Date date = new Date();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setIlmoittautuminen_pvm(date);
        assertEquals(date, ilmoittautuminen.getIlmoittautuminen_pvm());
    }

    @Test
    void setIlmoittautuminen_pvm() {
        Date date = new Date();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen();
        ilmoittautuminen.setIlmoittautuminen_pvm(date);
        assertEquals(date, ilmoittautuminen.getIlmoittautuminen_pvm());
    }

    @Test
    void testToString() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpiskelija_id(1L);
        Kurssi kurssi = new Kurssi();
        kurssi.setKurssi_id(1L);
        Date date = new Date();
        KurssiIlmoittautuminen ilmoittautuminen = new KurssiIlmoittautuminen(opiskelija, kurssi, date);
        String expected = "KurssiIlmoittautuminen{ilmoittautuminen_id=null, opiskelija=1, kurssi=1, ilmoittautuminen_pvm=" + date + "}";
        assertEquals(expected, ilmoittautuminen.toString());
    }
}