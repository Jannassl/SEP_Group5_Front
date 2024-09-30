// src/test/java/model/KurssiTest.java
package model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KurssiTest {

    @Test
    void getKurssi_id() {
        Kurssi kurssi = new Kurssi();
        kurssi.setKurssi_id(1L);
        assertEquals(1L, kurssi.getKurssi_id());
    }

    @Test
    void setKurssi_id() {
        Kurssi kurssi = new Kurssi();
        kurssi.setKurssi_id(1L);
        assertEquals(1L, kurssi.getKurssi_id());
    }

    @Test
    void getNimi() {
        Kurssi kurssi = new Kurssi();
        kurssi.setNimi("Test Course");
        assertEquals("Test Course", kurssi.getNimi());
    }

    @Test
    void setNimi() {
        Kurssi kurssi = new Kurssi();
        kurssi.setNimi("Test Course");
        assertEquals("Test Course", kurssi.getNimi());
    }

    @Test
    void getKuvaus() {
        Kurssi kurssi = new Kurssi();
        kurssi.setKuvaus("Test Description");
        assertEquals("Test Description", kurssi.getKuvaus());
    }

    @Test
    void setKuvaus() {
        Kurssi kurssi = new Kurssi();
        kurssi.setKuvaus("Test Description");
        assertEquals("Test Description", kurssi.getKuvaus());
    }

    @Test
    void getAlkupvm() {
        Date date = new Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setAlkupvm(date);
        assertEquals(date, kurssi.getAlkupvm());
    }

    @Test
    void setAlkupvm() {
        Date date = new Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setAlkupvm(date);
        assertEquals(date, kurssi.getAlkupvm());
    }

    @Test
    void getLoppupvm() {
        Date date = new Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setLoppupvm(date);
        assertEquals(date, kurssi.getLoppupvm());
    }

    @Test
    void setLoppupvm() {
        Date date = new Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setLoppupvm(date);
        assertEquals(date, kurssi.getLoppupvm());
    }

    @Test
    void getOpettaja() {
        Opettaja opettaja = new Opettaja();
        Kurssi kurssi = new Kurssi();
        kurssi.setOpettaja(opettaja);
        assertEquals(opettaja, kurssi.getOpettaja());
    }

    @Test
    void setOpettaja() {
        Opettaja opettaja = new Opettaja();
        Kurssi kurssi = new Kurssi();
        kurssi.setOpettaja(opettaja);
        assertEquals(opettaja, kurssi.getOpettaja());
    }

    @Test
    void testToString() {
        Opettaja opettaja = new Opettaja();
        opettaja.setOpettaja_id(1L);
        Kurssi kurssi = new Kurssi("Test Course", "Test Description", new Date(), new Date(), opettaja);
        String expected = "Kurssi{kurssi_id=null, nimi='Test Course', kuvaus='Test Description', alkupvm=" + kurssi.getAlkupvm() + ", loppupvm=" + kurssi.getLoppupvm() + ", opettaja=1}";
        assertEquals(expected, kurssi.toString());
    }
}