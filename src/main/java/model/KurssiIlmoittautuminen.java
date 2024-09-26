package model;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "kurssi_ilmoittautuminen")
public class KurssiIlmoittautuminen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ilmoittautuminen_id;

    @ManyToOne
    @JoinColumn(name = "opiskelija_id", nullable = false)
    private Opiskelija opiskelija;

    @ManyToOne
    @JoinColumn(name = "kurssi_id", nullable = false)
    private Kurssi kurssi;

    @Temporal(TemporalType.DATE)
    @Column(name = "ilmoittautuminen_pvm", nullable = false)
    private Date ilmoittautuminen_pvm;

    // Tyhjä konstruktori
    public KurssiIlmoittautuminen() {}

    // Konstruktori kaikilla kentillä
    public KurssiIlmoittautuminen(Opiskelija opiskelija, Kurssi kurssi, Date ilmoittautuminen_pvm) {
        this.opiskelija = opiskelija;
        this.kurssi = kurssi;
        this.ilmoittautuminen_pvm = ilmoittautuminen_pvm;
    }

    // Getterit ja setterit
    public Long getIlmoittautuminen_id() {
        return ilmoittautuminen_id;
    }

    public void setIlmoittautuminen_id(Long ilmoittautuminen_id) {
        this.ilmoittautuminen_id = ilmoittautuminen_id;
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

    public Date getIlmoittautuminen_pvm() {
        return ilmoittautuminen_pvm;
    }

    public void setIlmoittautuminen_pvm(Date ilmoittautuminen_pvm) {
        this.ilmoittautuminen_pvm = ilmoittautuminen_pvm;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "KurssiIlmoittautuminen{" +
                "ilmoittautuminen_id=" + ilmoittautuminen_id +
                ", opiskelija=" + (opiskelija != null ? opiskelija.getOpiskelija_id() : null) +
                ", kurssi=" + (kurssi != null ? kurssi.getKurssi_id() : null) +
                ", ilmoittautuminen_pvm=" + ilmoittautuminen_pvm +
                '}';
    }
}