package model;


import javax.persistence.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "oppitunti")
public class Oppitunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oppitunti_id;

    @ManyToOne
    @JoinColumn(name = "kurssi_id")
    private Kurssi kurssi;

    @Column(name = "alkuaika")
    private LocalDateTime alkuaika;

    @Column(name = "loppuaika")
    private LocalDateTime loppuaika;
    @Column(name= "sijainti")
    private String sijainti;

    // Getterit ja setterit
    public Long getOppitunti_id() {
        return oppitunti_id;
    }

    public void setOppitunti_id(Long oppitunti_id) {
        this.oppitunti_id = oppitunti_id;
    }

    public Kurssi getKurssi() {
        return kurssi;
    }

    public void setKurssi(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    public LocalDateTime getAlkuaika() {
        return alkuaika;
    }

    public void setAlkuaika(LocalDateTime alkuaika) {
        this.alkuaika = alkuaika;
    }

    public LocalDateTime getLoppuaika() {
        return loppuaika;
    }

    public void setLoppuaika(LocalDateTime loppuaika) {
        this.loppuaika = loppuaika;
    }

    public void setSijainti(String sijainti) {
        this.sijainti = sijainti;
    }

    public String getSijainti() {
        return sijainti;
    }
}
