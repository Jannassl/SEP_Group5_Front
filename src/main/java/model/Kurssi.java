// src/main/java/model/Kurssi.java
package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "kurssi")
public class Kurssi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kurssi_id;

    @Column(nullable = false)
    private String nimi;

    @Column(length = 1000)
    private String kuvaus;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date alkupvm;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date loppupvm;

    @ManyToOne
    @JoinColumn(name = "opettaja_id", nullable = false)
    private Opettaja opettaja;

    @OneToMany(mappedBy = "kurssi", fetch = FetchType.EAGER) // Fetch eagerly
    private List<Oppitunti> oppitunnit;

    // Constructors
    public Kurssi() {}

    public Kurssi(String nimi, String kuvaus, Date alkupvm, Date loppupvm, Opettaja opettaja) {
        this.nimi = nimi;
        this.kuvaus = kuvaus;
        this.alkupvm = alkupvm;
        this.loppupvm = loppupvm;
        this.opettaja = opettaja;
    }

    // Getters and Setters
    public Long getKurssi_id() {
        return kurssi_id;
    }

    public void setKurssi_id(Long kurssi_id) {
        this.kurssi_id = kurssi_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public java.sql.Date getAlkupvm() {
        return (java.sql.Date) alkupvm;
    }

    public void setAlkupvm(Date alkupvm) {
        this.alkupvm = alkupvm;
    }

    public java.sql.Date getLoppupvm() {
        return (java.sql.Date) loppupvm;
    }

    public void setLoppupvm(Date loppupvm) {
        this.loppupvm = loppupvm;
    }

    public Opettaja getOpettaja() {
        return opettaja;
    }

    public void setOpettaja(Opettaja opettaja) {
        this.opettaja = opettaja;
    }

    public List<Oppitunti> getOppitunnit() {
        return oppitunnit;
    }

    public void setOppitunnit(List<Oppitunti> oppitunnit) {
        this.oppitunnit = oppitunnit;
    }

    public Oppitunti getOppitunti() {
        if (oppitunnit != null && !oppitunnit.isEmpty()) {
            return oppitunnit.get(0); // Assuming you want the first Oppitunti
        }
        return null;
    }

    // toString method
    @Override
    public String toString() {
        return "Kurssi{" +
                "kurssi_id=" + kurssi_id +
                ", nimi='" + nimi + '\'' +
                ", kuvaus='" + kuvaus + '\'' +
                ", alkupvm=" + alkupvm +
                ", loppupvm=" + loppupvm +
                ", opettaja=" + (opettaja != null ? opettaja.getOpettaja_id() : null) +
                '}';
    }
}