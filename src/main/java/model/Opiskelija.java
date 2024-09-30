package model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "opiskelija")
public class Opiskelija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opiskelija_id;

    @Column(nullable = false)
    private String etunimi;

    @Column(nullable = false)
    private String sukunimi;

    @Column(unique = true, nullable = false)
    private String sahkoposti;

    private String puhelinnumero;

    @ManyToOne
    @JoinColumn(name = "huoltaja_id")
    private Huoltaja huoltaja;

    @OneToMany(mappedBy = "opiskelija", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Opintosuoritus> opintosuoritukset;

    // Tyhjä konstruktori
    public Opiskelija() {}

    // Konstruktori ilman id:tä ja suhteita
    public Opiskelija(String etunimi, String sukunimi, String sahkoposti, String puhelinnumero) {
        this.etunimi = etunimi;
        this.sukunimi = sukunimi;
        this.sahkoposti = sahkoposti;
        this.puhelinnumero = puhelinnumero;
    }

    // Getterit ja setterit
    public Long getOpiskelija_id() {
        return opiskelija_id;
    }

    public void setOpiskelija_id(Long opiskelija_id) {
        this.opiskelija_id = opiskelija_id;
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

    public Huoltaja getHuoltaja() {
        return huoltaja;
    }

    public void setHuoltaja(Huoltaja huoltaja) {
        this.huoltaja = huoltaja;
    }

    public List<Opintosuoritus> getOpintosuoritukset() {
        return opintosuoritukset;
    }

    public void setOpintosuoritukset(List<Opintosuoritus> opintosuoritukset) {
        this.opintosuoritukset = opintosuoritukset;
    }


    public String getNimi() {
        return etunimi + " " + sukunimi;
    }

    // toString-metodi (voit halutessasi jättää pois suhdekentät)
    @Override
    public String toString() {
        return "Opiskelija{" +
                "opiskelija_id=" + opiskelija_id +
                ", etunimi='" + etunimi + '\'' +
                ", sukunimi='" + sukunimi + '\'' +
                ", sahkoposti='" + sahkoposti + '\'' +
                ", puhelinnumero='" + puhelinnumero + '\'' +
                '}';
    }
}