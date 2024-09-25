package model;

import javax.persistence.*;

@Entity
@Table(name = "opettaja")
public class Opettaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opettaja_id;

    @Column(nullable = false)
    private String etunimi;

    @Column(nullable = false)
    private String sukunimi;

    @Column(unique = true, nullable = false)
    private String sahkoposti;

    @Column(nullable = false)
    private String salasana;

    // Tyhjä konstruktori
    public Opettaja() {}

    // Konstruktori kaikilla kentillä
    public Opettaja(String etunimi, String sukunimi, String sahkoposti, String salasana) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sahkoposti = sahkoposti;
        this.salasana = salasana;
    }

    // Getterit ja setterit
    public Long getOpettaja_id() {
        return opettaja_id;
    }

    public void setOpettaja_id(Long opettaja_id) {
        this.opettaja_id = opettaja_id;
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

    public String getSalasana() {
        return salasana;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    @Override
    public String toString() {
        return "Opettaja{" +
                "opettaja_id=" + opettaja_id +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", sahkoposti='" + sahkoposti + '\'' +
                '}';
    }
}