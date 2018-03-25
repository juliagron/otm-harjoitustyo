package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenToimiiOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiOikein() {
        kortti.lataaRahaa(200);
        kortti.otaRahaa(10);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaEiVieSaldoaNegatiiviseksi() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahaEiRiitaOtaMetodissa() {
        assertFalse(kortti.otaRahaa(20));
    }
    
    @Test
    public void rahaRiittaaOtaMetodissa() {
        assertTrue(kortti.otaRahaa(5));
    }
}
