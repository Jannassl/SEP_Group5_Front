package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "opintosuoritus")
public class Opintosuoritus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long suoritus_id;

    @ManyToOne
    @JoinColumn(name = "opiskelija_id", nullable = false)
    private Opiskelija opiskelija;

    @ManyToOne
    @JoinColumn(name = "kurssi_id", nullable = false)
    private Kurssi kurssi;

    @Column(nullable = false)
    private Integer arvosana;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date arvostelupvm;

    // Tyhjä konstruktori
    public Opintosuoritus() {}

    // Konstruktori ilman id:tä
    public Opintosuoritus(Opiskelija opiskelija, Kurssi kurssi, Integer arvosana, Date arvostelupvm) {
        this.opiskelija = opiskelija;
        this.kurssi = kurssi;
        this.arvosana = arvosana;
        this.arvostelupvm = arvostelupvm;
    }

    // Getterit ja setterit
    public Long getSuoritus_id() {
        return suoritus_id;
    }

    public void setSuoritus_id(Long suoritus_id) {
        this.suoritus_id = suoritus_id;
    }

    public Opiskelija getOpiskelija() {
        return opiskelija;
    }

    public void setOpiskelija(Opiskelija opiskelija) {
        this.opiskelija = opiskelija;
    }

    public Kurssi getKurssi() {
        return kurssi;
    }

    public void setKurssi(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    public Integer getArvosana() {
        return arvosana;
    }

    public void setArvosana(Integer arvosana) {
        this.arvosana = arvosana;
    }

    public Date getArvostelupvm() {
        return arvostelupvm;
    }

    public void setArvostelupvm(Date arvostelupvm) {
        this.arvostelupvm = arvostelupvm;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "Opintosuoritus{" +
                "suoritus_id=" + suoritus_id +
                ", opiskelija=" + (opiskelija != null ? opiskelija.getOpiskelija_id() : null) +
                ", kurssi=" + (kurssi != null ? kurssi.getKurssi_id() : null) +
                ", arvosana=" + arvosana +
                ", arvostelupvm=" + arvostelupvm +
                '}';
    }
}