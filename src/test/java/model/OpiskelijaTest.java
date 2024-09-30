package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpiskelijaTest {

    @Test
    void getOpiskelija_id() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpiskelija_id(1L);
        assertEquals(1L, opiskelija.getOpiskelija_id());
    }

    @Test
    void setOpiskelija_id() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpiskelija_id(1L);
        assertEquals(1L, opiskelija.getOpiskelija_id());
    }

    @Test
    void getEtunimi() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setEtunimi("John");
        assertEquals("John", opiskelija.getEtunimi());
    }

    @Test
    void setEtunimi() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setEtunimi("John");
        assertEquals("John", opiskelija.getEtunimi());
    }

    @Test
    void getSukunimi() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setSukunimi("Doe");
        assertEquals("Doe", opiskelija.getSukunimi());
    }

    @Test
    void setSukunimi() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setSukunimi("Doe");
        assertEquals("Doe", opiskelija.getSukunimi());
    }

    @Test
    void getSahkoposti() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", opiskelija.getSahkoposti());
    }

    @Test
    void setSahkoposti() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", opiskelija.getSahkoposti());
    }

    @Test
    void getPuhelinnumero() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setPuhelinnumero("123456789");
        assertEquals("123456789", opiskelija.getPuhelinnumero());
    }

    @Test
    void setPuhelinnumero() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setPuhelinnumero("123456789");
        assertEquals("123456789", opiskelija.getPuhelinnumero());
    }

    @Test
    void getHuoltaja() {
        Huoltaja huoltaja = new Huoltaja();
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setHuoltaja(huoltaja);
        assertEquals(huoltaja, opiskelija.getHuoltaja());
    }

    @Test
    void setHuoltaja() {
        Huoltaja huoltaja = new Huoltaja();
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setHuoltaja(huoltaja);
        assertEquals(huoltaja, opiskelija.getHuoltaja());
    }

    @Test
    void getOpintosuoritukset() {
        List<Opintosuoritus> opintosuoritukset = new ArrayList<>();
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpintosuoritukset(opintosuoritukset);
        assertEquals(opintosuoritukset, opiskelija.getOpintosuoritukset());
    }

    @Test
    void setOpintosuoritukset() {
        List<Opintosuoritus> opintosuoritukset = new ArrayList<>();
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpintosuoritukset(opintosuoritukset);
        assertEquals(opintosuoritukset, opiskelija.getOpintosuoritukset());
    }

    @Test
    void testToString() {
        Opiskelija opiskelija = new Opiskelija("John", "Doe", "john.doe@example.com", "123456789");
        opiskelija.setOpiskelija_id(1L);
        String expected = "Opiskelija{opiskelija_id=1, etunimi='John', sukunimi='Doe', sahkoposti='john.doe@example.com', puhelinnumero='123456789'}";
        assertEquals(expected, opiskelija.toString());
    }
}