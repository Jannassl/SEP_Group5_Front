package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HuoltajaTest {

    @Test
    void getHuoltaja_id() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setHuoltaja_id(1L);
        assertEquals(1L, huoltaja.getHuoltaja_id());
    }

    @Test
    void setHuoltaja_id() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setHuoltaja_id(1L);
        assertEquals(1L, huoltaja.getHuoltaja_id());
    }

    @Test
    void getEtunimi() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setEtunimi("John");
        assertEquals("John", huoltaja.getEtunimi());
    }

    @Test
    void setEtunimi() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setEtunimi("John");
        assertEquals("John", huoltaja.getEtunimi());
    }

    @Test
    void getSukunimi() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setSukunimi("Doe");
        assertEquals("Doe", huoltaja.getSukunimi());
    }

    @Test
    void setSukunimi() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setSukunimi("Doe");
        assertEquals("Doe", huoltaja.getSukunimi());
    }

    @Test
    void getSahkoposti() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", huoltaja.getSahkoposti());
    }

    @Test
    void setSahkoposti() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setSahkoposti("john.doe@example.com");
        assertEquals("john.doe@example.com", huoltaja.getSahkoposti());
    }

    @Test
    void getPuhelinnumero() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setPuhelinnumero("123456789");
        assertEquals("123456789", huoltaja.getPuhelinnumero());
    }

    @Test
    void setPuhelinnumero() {
        Huoltaja huoltaja = new Huoltaja();
        huoltaja.setPuhelinnumero("123456789");
        assertEquals("123456789", huoltaja.getPuhelinnumero());
    }

    @Test
    void testToString() {
        Huoltaja huoltaja = new Huoltaja("John", "Doe", "john.doe@example.com", "123456789");
        huoltaja.setHuoltaja_id(1L);
        String expected = "Huoltaja{huoltaja_id=1, etunimi='John', sukunimi='Doe', sahkoposti='john.doe@example.com', puhelinnumero='123456789'}";
        assertEquals(expected, huoltaja.toString());
    }
}