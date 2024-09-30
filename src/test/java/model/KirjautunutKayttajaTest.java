// src/test/java/model/KirjautunutKayttajaTest.java
package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KirjautunutKayttajaTest {

    @BeforeEach
    void setUp() {
        KirjautunutKayttaja.resetInstance();
    }

    @Test
    void getInstance() {
        KirjautunutKayttaja instance1 = KirjautunutKayttaja.getInstance();
        KirjautunutKayttaja instance2 = KirjautunutKayttaja.getInstance();
        assertSame(instance1, instance2, "getInstance should return the same instance");
    }

    @Test
    void getOpettaja() {
        KirjautunutKayttaja instance = KirjautunutKayttaja.getInstance();
        assertNull(instance.getOpettaja(), "Initially, getOpettaja should return null");

        Opettaja opettaja = new Opettaja();
        instance.setOpettaja(opettaja);
        assertSame(opettaja, instance.getOpettaja(), "getOpettaja should return the set Opettaja");
    }

    @Test
    void setOpettaja() {
        KirjautunutKayttaja instance = KirjautunutKayttaja.getInstance();
        Opettaja opettaja = new Opettaja();
        instance.setOpettaja(opettaja);
        assertEquals(opettaja, instance.getOpettaja(), "setOpettaja should set the Opettaja correctly");
    }

    @Test
    void clearOpettaja() {
        KirjautunutKayttaja instance = KirjautunutKayttaja.getInstance();
        Opettaja opettaja = new Opettaja();
        instance.setOpettaja(opettaja);
        instance.clearOpettaja();
        assertNull(instance.getOpettaja(), "clearOpettaja should set the Opettaja to null");
    }
}