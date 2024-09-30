package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PoissaoloTest {

    @Test
    void getPoissaolo_id() {
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setPoissaolo_id(1L);
        assertEquals(1L, poissaolo.getPoissaolo_id());
    }

    @Test
    void setPoissaolo_id() {
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setPoissaolo_id(1L);
        assertEquals(1L, poissaolo.getPoissaolo_id());
    }

    @Test
    void getOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setOpiskelija(opiskelija);
        assertEquals(opiskelija, poissaolo.getOpiskelija());
    }

    @Test
    void setOpiskelija() {
        Opiskelija opiskelija = new Opiskelija();
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setOpiskelija(opiskelija);
        assertEquals(opiskelija, poissaolo.getOpiskelija());
    }

    @Test
    void getOppitunti() {
        Oppitunti oppitunti = new Oppitunti();
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setOppitunti(oppitunti);
        assertEquals(oppitunti, poissaolo.getOppitunti());
    }

    @Test
    void setOppitunti() {
        Oppitunti oppitunti = new Oppitunti();
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setOppitunti(oppitunti);
        assertEquals(oppitunti, poissaolo.getOppitunti());
    }

    @Test
    void getSyy() {
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setSyy("Sick");
        assertEquals("Sick", poissaolo.getSyy());
    }

    @Test
    void setSyy() {
        Poissaolo poissaolo = new Poissaolo();
        poissaolo.setSyy("Sick");
        assertEquals("Sick", poissaolo.getSyy());
    }

    @Test
    void testToString() {
        Opiskelija opiskelija = new Opiskelija();
        opiskelija.setOpiskelija_id(1L);
        Oppitunti oppitunti = new Oppitunti();
        oppitunti.setOppitunti_id(1L);
        Poissaolo poissaolo = new Poissaolo(opiskelija, oppitunti, "Sick");
        poissaolo.setPoissaolo_id(1L);
        String expected = "Poissaolo{poissaolo_id=1, opiskelija=1, oppitunti=1, syy='Sick'}";
        assertEquals(expected, poissaolo.toString());
    }
}