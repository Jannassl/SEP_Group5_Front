// src/main/java/model/KirjautunutKayttaja.java
package model;

public class KirjautunutKayttaja {
    private static KirjautunutKayttaja instance;
    private Opettaja opettaja;

    private KirjautunutKayttaja() {}

    public static KirjautunutKayttaja getInstance() {
        if (instance == null) {
            instance = new KirjautunutKayttaja();
        }
        return instance;
    }

    public Opettaja getOpettaja() {
        return opettaja;
    }

    public void setOpettaja(Opettaja opettaja) {
        this.opettaja = opettaja;
    }

    public void clearOpettaja() {
        this.opettaja = null;
    }
}