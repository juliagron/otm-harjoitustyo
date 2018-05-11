# Käyttöohje

Lataa tiedosto [pasianssi.jar](https://github.com/juliagron/otm-harjoitustyo/releases/tag/viikko5)

## Muut tiedostot

Ohjelma ei tarvitse muita tiedostoja

## Ohjelman käynnistäminen

Ohjelman käynnistäminen komentoriviltä tapahtuu komennolla

    java -jar pasianssi.jar

tai tuplaklikkaamalla tiedostoa

## Käyttöliittymän käyttö

Sovellus avautuu aloitustilanteeseen:

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/starting_situation.png" width="500">

Pelaaminen ohjelmassa tapahtuu hiirtä käyttämällä. Käsipakan klikkaus kääntää kortin/kortteja sen viereen. Jos käsipakka on tyhjä, siirretään kaikki kortit sen vierestä takaisin käsipakkaan. Kuvapuoli ylöpäin olevia kortteja voi raahata klikkaamalla niitä hiirellä. Korttien siirto noudattaa normaalin Klondike-pasianssin sääntöjä.

### Valikkorivi

Sovelluksen ylälaidassa sijaitsee kolme valikkoa: *Game*, *All up* ja *High scores*

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/menus.png" width="300">


#### Valikko *Game*

Valikossa *Game* valittavana on kolme vaihtoehtoa: *New Deal*, *Same Deal* ja *Quit*.

<img src="https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/kuvat/game_menu.jpg" width="300">

*New Deal* jakaa kortit uudestaan sekoittaen ne, kun taas *Same Deal* jakaa kortit samaan aloitustilanteeseen. Vaihtoehto *Quit* sulkee sovelluksen.

#### Valikko *All up*

Valikkoa *All up* painamalla kaikki kortit menevät lopetuspinoihin oikeille paikoilleen, jos käsipakka ja sen vieressä oleva pakka ovat tyhjiä ja pöydällä olevat kortit ovat kaikki oikeinpäin.

#### Valikko *High scores*

Klikkaamalla tätä valikkoa voi katsoa viisi parasta tulosta - tai kaikki jos niitä on alle viisi - jota pelissä on tullut. Valikko avaa alert-ikkunan, johon tulokset kirjataan. Ikkunassa on nappula *Cancel*, jota painamalla ikkuna suljetaan.

### Pelaaminen

Pelin pelaaminen tapahtuu hiirtä käyttämällä. Klikkaamalla ruudun vasemmassa ylälaidassa olevaa käsipakkaa voidaan kortteja kääntää yksi kerrallaan sen viereen. Oikeinpäin olevia kortteja voidaan raahata toisiin pinoihin sääntöjen mukaisesti. Tuplaklikkaus siirtää yhden kortin kerrallaan sopivaan lopetuspinoon.

#### Säännöt

Pelissä pätee normaalit Klondike-pasianssin säännöt. Sääntöjä ovat:

- vain kuninkaan voi pelata tyhjään pinoon pöydälle
- kortit sijoitetaan pöydällä alenevassa järjestyksessä päällekkäin
- korttien väri pöydällä on joka toinen musta, joka toinen punainen
- lopetuspinon kaikki kortit ovat samaa maata
- lopetuspinon aloittaa ässä, jonka jälkeen kortit sijoitetaan ylenevässä järjestyksessä päällekkäin

### Lopetus

Kun kaikki kortit ovat lopetuspinoissa, ajanlasku lopetetaan. Jos lopputulos yltää viiden parhaan joukkoon, kysytään pelaajalta nimi, jolla tulos talennetaan tietokantaan. Tämän jälkee viisi parasta tulosta tulostetaan ruudulle.
Jos tulos ei yllä viiden parhaan joukkoon, tulostetaan ruudulle "Your score: *pelin loppuaika*".
