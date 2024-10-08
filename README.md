# Oppilastietojärjestelmä

## Projektin kuvaus:

Tämä projekti on Java-pohjainen sovellus, joka käyttää JavaFX:ää käyttöliittymän toteuttamiseen. Projektia hallinnoidaan Mavenin avulla, joka hoitaa riippuvuuksien hallinnan ja rakentamisprosessit. Sovelluksessa on useita käyttöliittymäkomponentteja, jotka on tyylitelty CSS:llä
ja sen rakentamisessa on käytetty apuna Model-View-Controller (MVC) -arkkitehtuuria. Ohjelmamme tarkoitus on luoda keskitetty järjestelmä oppilaitoksille, missä käyttäjät voivat tehdä oleelliset hallinnolliset tehtävät. Ohjelman ideana on parantaa oppilaitoksen tehokkuutta ja käyttäjien tyytyväisyyttä.
Tämä ohjelma on erityisesti kehitetty koulun opettajille, jotta he voivat tehokkaasti ylläpitää ja hallinnoida opiskelijoihinsa liittyviä tietoja. Alusta on käytettävissä yksinomaan opettajille, joilla on käyttäjätili, varmistaen, että vain valtuutettu henkilöstö voi käyttää sitä. Käyttäjätilien hallinnasta vastaavat koulun ylläpitäjät, jotka valvovat järjestelmän käyttöoikeuksia ja turvallisuutta.

## Ajatus on, että vain koulun ylläpitäjät voivat luoda uusia käyttäjätilejä ja hallinnoida niitä.

## Työkalut ja teknologiat

- **Java**: Sovelluksen logiikan ensisijainen ohjelmointikieli..
- **JavaFX**: Käytetään käyttöliittymän rakentamiseen.
- **Maven**: Rakennusautomaatiotyökalu, jota käytetään projektin riippuvuuksien ja rakennusprosessien hallintaan.
- **JUnit 5**: Käytetään yksikkötestien kirjoittamiseen ja suorittamiseen.

## Projektin rakenne

- `src/main/java`: Sisältää pääasiallisen sovelluskoodin.
    - `controller`: Sisältää controller-luokat.
    - `model`: Sisältää dataan ja liiketoimintalogiikkaan liittyvät luokat.
    - `view`: Sisältää käyttöliittymään liittyvät luokat.
- `src/main/resources`: Sisältää resurssitiedostot, kuten FXML- ja CSS-tiedostot käyttöliittymälle.
- `src/test/java`: Sisältää testiluokat.
- `pom.xml`: Maven-konfiguraatiotiedosto

## Ominaisuuksia:
- Oppilastiedot
- Kurssitiedot --> lisääminen, poistaminen --> Oppilaan läsnäolot
- Kalenteri --> Saa näkyviin käyttäjän kalenterin
- Profiili --> Omat profiilitiedot ja sen muokkaus

## Kuinka ajaa projekti

### Esivaatimukset

- Java Development Kit (JDK) 11 tai uudempi
- Maven 3.6.0 tai uudempi
- IntelliJ IDEA tai jokin muu Java-IDE

### Askeleet projektin suorittamiseksi

1. **Kloonaa repositorio**:
   ```sh
   git clone <[repository-url](https://github.com/Jannassl/SEP_Group5_Front.git)>
   cd <SEP_Group5_Front>

Suorita ohjelma ajamalla Main.java


## [ER-kaavio](https://github.com/Jannassl/SEP_Group5_Front/blob/diagrams/Diagrams/OTPer.png)

## [Käyttötapauskaavio](https://github.com/Jannassl/SEP_Group5_Front/blob/diagrams/Diagrams/OTPcd.png)
