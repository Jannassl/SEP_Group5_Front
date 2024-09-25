package model;

import javax.persistence.*;

@Entity
@Table(name = "poissaolo")
public class Poissaolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poissaolo_id;

    @ManyToOne
    @JoinColumn(name = "opiskelija_id", nullable = false)
    private Opiskelija opiskelija;

    @ManyToOne
    @JoinColumn(name = "oppitunti_id", nullable = false)
    private Oppitunti oppitunti;

    @Column(length = 500)
    private String syy;

    // Tyhjä konstruktori
    public Poissaolo() {}

    // Konstruktori ilman id:tä
    public Poissaolo(Opiskelija opiskelija, Oppitunti oppitunti, String syy) {
        this.opiskelija = opiskelija;
        this.oppitunti = oppitunti;
        this.syy = syy;
    }

    // Getterit ja setterit
    public Long getPoissaolo_id() {
        return poissaolo_id;
    }

    public void setPoissaolo_id(Long poissaolo_id) {
        this.poissaolo_id = poissaolo_id;
    }

    public Opiskelija getOpiskelija() {
        return opiskelija;
    }

    public void setOpiskelija(Opiskelija opiskelija) {
        this.opiskelija = opiskelija;
    }

    public Oppitunti getOppitunti() {
        return oppitunti;
    }

    public void setOppitunti(Oppitunti oppitunti) {
        this.oppitunti = oppitunti;
    }

    public String getSyy() {
        return syy;
    }

    public void setSyy(String syy) {
        this.syy = syy;
    }

    // toString-metodi
    @Override
    public String toString() {
        return "Poissaolo{" +
                "poissaolo_id=" + poissaolo_id +
                ", opiskelija=" + (opiskelija != null ? opiskelija.getOpiskelija_id() : null) +
                ", oppitunti=" + (oppitunti != null ? oppitunti.getOppitunti_id() : null) +
                ", syy='" + syy + '\'' +
                '}';
    }
}