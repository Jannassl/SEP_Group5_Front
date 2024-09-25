package model;

import javax.persistence.*;

@Entity
@Table(name = "huoltaja")
public class Huoltaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long huoltaja_id;

    @Column(nullable = false)
    private String etunimi;

    @Column(nullable = false)
    private String sukunimi;

    @Column(unique = true)
    private String sahkoposti;

    private String puhelinnumero;

    // Tyhjä konstruktori
    public Huoltaja() {}

    // Konstruktori kaikilla kentillä
    public Huoltaja(String etunimi, String sukunimi, String sahkoposti, String puhelinnumero) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sahkoposti = sahkoposti;
        this.puhelinnumero = puhelinnumero;
    }

    // Getterit ja setterit
    public Long getHuoltaja_id() {
        return huoltaja_id;
    }

    public void setHuoltaja_id(Long huoltaja_id) {
        this.huoltaja_id = huoltaja_id;
    }

    public String getEtunimi() {
        return etunimi;
    }

    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    public String getSukunimi() {
        return sukunimi;
    }

    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    public String getSahkoposti() {
        return sahkoposti;
    }

    public void setSahkoposti(String sahkoposti) {
        this.sahkoposti = sahkoposti;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "Huoltaja{" +
                "huoltaja_id=" + huoltaja_id +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", sahkoposti='" + sahkoposti + '\'' +
                ", puhelinnumero='" + puhelinnumero + '\'' +
                '}';
    }
}