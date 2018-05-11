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

![class/package diagram](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/class_package_diagram.png)

Suurin osa sovelluslogiikasta löytyy luokista *LegalMove* ja *StartingSituation*. *LegalMove* vastaa pelin säännöistä ja *StartingSituation* pelin alkutilanteesta.

## Tietojen pysyväistallennus

Pakkauksen pasianssi.dao luokat huolehtivat pelin tulosten tallentamisesta tietokantaan.

### Tietokanta

Tietokantaan tallennetaan peliin menneitä aikoja, jos ne ylettävät viiden parhaan tuloksen joukkoon.

Tietokanta sisältää yhen taulun, HighScore, johon tallennetaan nimi ja aika.

## Päätoiminnallisuudet

#### Pelikorttien laittaminen oikeille paikoille

Pelin aloitustilanteen sovelluslogiikka etenee seuraavan sekvenssikaavion mukaisesti:

![sequence diagram](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/sequence_diagram_1.png)

Käyttöliittymä luo kolmetoista korttipinoa oikeilla kordinaateilla. Sen jälkeen luodaan uusi aloitustianne, jolle annetaan äskettäin luodut pinot. Aloitustilanteessa luodaan kaikki 52 korttia. Metodi newDeal sekoittaa kortit ja katsoo, että pinot ovat tyhjiä, kääntää kortit väärinpäin, sijoittaa kortit oikeisiin pinoihin ja kääntää pöydälle olevien päälimmiset oikeinpäin. 

## Rakenteen heikkoudet

#### käyttöliittymä

Graafisen käyttöliittymän koodi olisi syytä jakaa useampaan metodiin, sillä tällä hetkellä metodit ovat aika pitkiä. Usean luokan käyttöä olisi myös syytä harkita.
