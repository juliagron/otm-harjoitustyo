/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juliagro
 */
public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void kassassaOikeaMaaraRahaa() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaMyyty() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaLounaitaMyyty() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKateisostoKassanRaha() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisostoKassanRaha() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiKateisostoVaihtoraha() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
    }
    
    @Test
    public void syoMaukkaastiKateisostoVaihtoraha() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void edullistenMaaraKasvaaKateisostossa() {
        kassapaate.syoEdullisesti(500);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaittenMaaraKasvaaKateisostossa() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassassaEiMuutostaKunKateinenEiRiitaEdulliseen() {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassassaEiMuutostaKunKateinenEiRiitaMaukkaaseen() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kaikkiRahaTakaisinEdullinenVahallaRahalla() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }
    
    @Test
    public void kaikkiRahaTakaisinMaukasVahallaRahalla() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
    }
    
    @Test
    public void eiMyytyjaLounaitaEdullinenVahallaRahalla() {
        kassapaate.syoEdullisesti(200);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void eiMyytyjaLounaitaMaukasVahallaRahalla() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinSaldoVaheneeEdulisissa() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void kortinSaldoVaheneeMaukkaissa() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void palautetaanTrueKunRiittavastiRahaaEdullinen() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palautetaanTrueKunRiittavastiRahaaMaukas() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void lounaidenMaaraKasvaaEdullinen() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luonaidenMaaraKasvaaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void saldoEiVaheneVahallaRahallaEdullinen() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void saldoEiVaheneVahallaRahallaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void lounaatEiMuutuVahallaSaldollaEdullinen() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void lounaatEiMuutuVahallaSaldollaMaukas() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void palautetaanFalseVahallaSaldollaEdullinen() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palautetaanFalseVahallaSaldollaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassanRahatEiMuutuKortillaEdullinen() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahatEiMuutuKortillaMaukas() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoMuuttuuLadatessaKortille() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kassanRahaMuuttuuLadatessaKortille() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
}
