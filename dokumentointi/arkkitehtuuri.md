# Arkkitehtuurikuvaus

## Rakenne

Koodin pakkausrakenne on seuraava:

![package diagram](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/package_diagram.png)

Pakkaus pasianssi.ui sisältää käyttöliittymän, pasinassi.domain sovelluslogiikan ja pasianssi.dao pysyväitalletuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymä näyttää normaalilta Klondike-pasianssilta. Käyttöliittymä tehdään luokassa [pasianssi.ui.PasianssiUi](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/main/java/pasianssi/ui/PasianssiUi.java)

Käyttöliittymä on pyritty eristämää sovelluslogiikasta.

## Sovelluslogiikka

Sovelluslogiikan eri luokkien sihde toisiinsa:

![class/package diagram](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/class-package-diagram.png)

## Päätoiminnallisuudet

#### Pelikorttien laittaminen oikeille paikoille

![sequence diagram](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sequence_diagram_1.png)


