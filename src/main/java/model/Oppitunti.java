package model;


import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "oppitunti")
public class Oppitunti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oppitunti_id;

    @ManyToOne
    @JoinColumn(name = "kurssi_id")
    private Kurssi kurssi;

    @Temporal(TemporalType.TIMESTAMP)
    private Date alkuaika;

    @Temporal(TemporalType.TIMESTAMP)
    private Date loppuaika;

    // Getterit ja setterit
    public Long getOppitunti_id() {
        return oppitunti_id;
    }

    public void setOppitunti_id(Long oppitunti_id) {}

    public Kurssi getKurssi() {
        return kurssi;
    }

    public void setKurssi(Kurssi kurssi) {
        this.kurssi = kurssi;
    }

    public Date getAlkuaika() {
        return alkuaika;
    }

    public void setAlkuaika(Date alkuaika) {
        this.alkuaika = alkuaika;
    }

    public Date getLoppuaika() {
        return loppuaika;
    }

    public void setLoppuaika(Date loppuaika) {
        this.loppuaika = loppuaika;
    }
}
