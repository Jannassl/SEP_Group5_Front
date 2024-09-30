package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpettajaTest {

    @Test
    void getOpettaja_id() {
        Opettaja opettaja = new Opettaja();
        opettaja.setOpettaja_id(1L);
        assertEquals(1L, opettaja.getOpettaja_id());
    }

    @Test
    void setOpettaja_id() {
        Opettaja opettaja = new Opettaja();
        opettaja.setOpettaja_id(1L);
        assertEquals(1L, opettaja.getOpettaja_id());
    }

    @Test
    void getEtunimi() {
        Opettaja opettaja = new Opettaja();
        opettaja.setEtunimi("John");
        assertEquals("John", opettaja.getEtunimi());
    }

    @Test
    void setEtunimi() {
        Opettaja opettaja = new Opettaja();
        opettaja.setEtunimi("John");
        assertEquals("John", opettaja.getEtunimi());
    }

    @Test
    void getSukunimi() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSukunimi("Doe");
        assertEquals("Doe", opettaja.getSukunimi());
    }

    @Test
    void setSukunimi() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSukunimi("Doe");
        assertEquals("Doe", opettaja.getSukunimi());
    }

    @Test
    void getSahkoposti() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", opettaja.getSahkoposti());
    }

    @Test
    void setSahkoposti() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", opettaja.getSahkoposti());
    }

    @Test
    void getSalasana() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSalasana("password123");
        assertEquals("password123", opettaja.getSalasana());
    }

    @Test
    void setSalasana() {
        Opettaja opettaja = new Opettaja();
        opettaja.setSalasana("password123");
        assertEquals("password123", opettaja.getSalasana());
    }

    @Test
    void testToString() {
        Opettaja opettaja = new Opettaja("John", "Doe", "john.doe@example.com", "password123");
        String expected = "Opettaja{opettaja_id=null, etunimi='John', sukunimi='Doe', sahkoposti='john.doe@example.com'}";
        assertEquals(expected, opettaja.toString());
    }
}