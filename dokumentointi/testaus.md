# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitila sekä manuaalisesti käyttöliittymässä.

## Yksikkö- ja intergaatiotestaus

### sovelluslogiikka

Pakkauksen [pasianssi.domain](https://github.com/juliagron/otm-harjoitustyo/tree/master/Pasianssi/src/main/java/pasianssi/domain) luokkia testaavat testiluokat [CardStackTest.java](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/test/java/pasianssi/domain/CardStackTest.java), [CardTest.java](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/test/java/pasianssi/domain/CardTest.java), [HighScoreTest.java](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/test/java/pasianssi/domain/HighScoreTest.java), [LegalMoveTest.java](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/test/java/pasianssi/domain/LegalMoveTest.java) ja [StartingSituationTest.java](https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/src/test/java/pasianssi/domain/StartingSituationTest.java).

### Testauskattavuus

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/jacoco.png" width="800">

Sovelluksen rivikattavuus on 85% ja haarautumakattavuus 90%.

Testaamatta jäi tiedon tallentamisesta vastaavat luokat.

## Järjestelmätestaus

Järjestelmätestaus on suoritettu manuaalisesti.

### Konfugurointi

Sovellusta on testattu sekä tilanteessa, jossa tietokanta on luotu etukäteen ja jossa se luodaan suoraan ohjelmasta.

## Sovellukseen jääneet laatuongelmat

Testauksessa ei tullut ilmi muita ongemia kuin pelinäkymän liikkuminen, jos kortin siirtää ruudun ulkopuolelle.
