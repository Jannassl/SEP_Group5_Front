package model;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class KurssiTest {

    // Example method to convert java.util.Date to java.sql.Date
    private Date convertUtilToSql(java.util.Date utilDate) {
        return new Date(utilDate.getTime());
    }

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
        java.util.Date utilDate = new java.util.Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setAlkupvm(convertUtilToSql(utilDate));
        assertEquals(convertUtilToSql(utilDate), kurssi.getAlkupvm());
    }

    @Test
    void setAlkupvm() {
        java.util.Date utilDate = new java.util.Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setAlkupvm(convertUtilToSql(utilDate));
        assertEquals(convertUtilToSql(utilDate), kurssi.getAlkupvm());
    }

    @Test
    void getLoppupvm() {
        java.util.Date utilDate = new java.util.Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setLoppupvm(convertUtilToSql(utilDate));
        assertEquals(convertUtilToSql(utilDate), kurssi.getLoppupvm());
    }

    @Test
    void setLoppupvm() {
        java.util.Date utilDate = new java.util.Date();
        Kurssi kurssi = new Kurssi();
        kurssi.setLoppupvm(convertUtilToSql(utilDate));
        assertEquals(convertUtilToSql(utilDate), kurssi.getLoppupvm());
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
        java.util.Date utilDate = Calendar.getInstance().getTime();
        Kurssi kurssi = new Kurssi("Test Course", "Test Description", convertUtilToSql(utilDate), convertUtilToSql(utilDate), opettaja);
        String expected = "Kurssi{kurssi_id=null, nimi='Test Course', kuvaus='Test Description', alkupvm=" + kurssi.getAlkupvm() + ", loppupvm=" + kurssi.getLoppupvm() + ", opettaja=1}";
        assertEquals(expected, kurssi.toString());
    }
}