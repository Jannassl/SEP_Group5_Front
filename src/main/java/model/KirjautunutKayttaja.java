// src/main/java/model/KirjautunutKayttaja.java
package model;

public class KirjautunutKayttaja {
    private static KirjautunutKayttaja instance;
    private Opettaja opettaja;

    private KirjautunutKayttaja() {
        this.opettaja = null; // Ensure opettaja is initialized to null
        System.out.println("KirjautunutKayttaja constructor called. opettaja: " + this.opettaja);
    }

    public static KirjautunutKayttaja getInstance() {
        if (instance == null) {
            instance = new KirjautunutKayttaja();
        }
        return instance;
    }

    public Opettaja getOpettaja() {
        System.out.println("getOpettaja called. opettaja: " + this.opettaja);
        return opettaja;
    }

    public void setOpettaja(Opettaja opettaja) {
        this.opettaja = opettaja;
    }

    public void clearOpettaja() {
        this.opettaja = null;
    }

    // Reset method for testing purposes
    public static void resetInstance() {
        instance = null;
    }
}