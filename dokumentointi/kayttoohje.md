# Käyttöohje

Lataa tiedosto [pasianssi.jar](https://github.com/juliagron/otm-harjoitustyo/releases/tag/viikko5)

## Muut tiedostot

Ohjelma ei tarvitse muita tiedostoja

## Ohjelman käynnistäminen

Ohjelman käynnistäminen komentoriviltä tapahtuu komennolla

    java -jar pasianssi.jar

tai tuplaklikkaamalla tiedostoa

## Pelaaminen

Sovellus avautuu aloitustilanteeseen:

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/solitaire_start.png" width="500">

Pelaaminen ohjelmassa tapahtuu hiirtä käyttämällä. Käsipakan klikkaus kääntää kortin/kortteja sen viereen. Jos käsipakka on tyhjä, siirretään kaikki kortit sen vierestä takaisin käsipakkaan.

### Valikkorivi

Sovelluksen ylälaidassa sijaitsee kaksi pudotusvalikkoa: *Game* ja *Draw*

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/menu_game.jpg" width="300">	 <img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/menu_draw.jpg" width="300">

#### Valikko *Game*

Valikossa *Game* valittavana on kolme vaihtoehtoa: *New Deal*, *Same Deal* ja *Quit*.

*New Deal* jakaa kortit uudestaan sekoittaen ne, kun taas *Same Deal* jakaa kortit samaan aloitustilanteeseen. Vaihtoehto *Quit* sulkee sovelluksen.

#### Valikko *Draw*

Valikossa *Draw* on kaksi vaihtoehtoa: *Draw one* ja *Draw three*. Tällä valikolla voi vaihtoehtojen nimien mukaisesti valita nostetaanko pakasta yksi vai kolme korttia kerrallaan.
