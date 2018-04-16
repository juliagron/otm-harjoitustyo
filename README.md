# Klondike-pasianssi

Harjoitustyö kurssille Ohjelmistotekniikan menetelmät. Työn tarkoituksena on tehdä toimiva Klondike-pasianssi

## Dokumentointi
[Vaatimusmäärittely](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/juliagron/otm-harjoitustyo/blob/master/dokumentointi/tuntikirjanpito.md)

## Komentorivikomennot

### Testaus
Testit suoritetaan komennolla

    mvn test

Testikattavuusraportti luodaan komennolla

    mvn test jacoco:report

Kattavuusrapporttia voi katsoa avaamalla selaimella tiedoston *target/site/jacoco/index.html*

### Checkstyle

Tiedostoon [checkstyle.xml] (https://github.com/juliagron/otm-harjoitustyo/blob/master/Pasianssi/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

    mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto *target/site/checkstyle.html*
