package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje se, zda lze dosáhnout výhry s danými předmětmi.
     */
    @Test
    public void testVyhra() {
        assertEquals("trnitá_stezka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi silo");
        assertEquals(false, hra1.konecHry());
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber mačeta");
        hra1.zpracujPrikaz("jdi bažiny");
        assertEquals(false, hra1.konecHry());
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Zabiják");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("bojuj Zabiják");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi stará_chata");
        assertEquals(false, hra1.konecHry());
        assertEquals("stará_chata", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi skaliska");
        assertEquals(false, hra1.konecHry());
        assertEquals("skaliska", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi vraky_aut");
        assertEquals(false, hra1.konecHry());
        assertEquals("vraky_aut", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber starobylé_klíče");
        assertFalse(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("starobylé_klíče"));
        assertTrue(hra1.getHerniPlan().getBatoh().obsahujeVec("starobylé_klíče"));
        hra1.zpracujPrikaz("jdi skaliska");
        assertEquals(false, hra1.konecHry());
        assertEquals("skaliska", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi pohřebiště");
        assertEquals(false, hra1.konecHry());
        assertEquals("pohřebiště", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi tunel");
        assertEquals(false, hra1.konecHry());
        assertEquals("tunel", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi starý_podzemní_chrám");
        assertEquals(false, hra1.konecHry());
        assertEquals("starý_podzemní_chrám", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi krypta");
        assertEquals(false, hra1.konecHry());
        assertEquals("krypta", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej");
        assertEquals(true, hra1.konecHry());
    }
    
    /***************************************************************************
     * Testuje se, jak by hra skončila, pokud by hráč neměl vstoupil do močálů.
     */
    @Test
    public void testProhraMocaly() {
        assertEquals("trnitá_stezka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi silo");
        assertEquals(false, hra1.konecHry());
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi bažiny");  
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv Zabiják");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi močály");
        assertEquals(true, hra1.konecHry());
    }

    /***************************************************************************
     * Testuje se, jak by hra skončila, pokud by hráč neměl v batohu mačetu.
     */
    @Test
    public void testProhraZabijak(){
        assertEquals("trnitá_stezka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi silo");
        assertEquals(false, hra1.konecHry());
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber kudla");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("prozkoumej");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skřížené_zdi");
        assertEquals(false, hra1.konecHry());
        assertEquals("skřížené_zdi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("pouzij kudla");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi silo");
        assertEquals(false, hra1.konecHry());
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi bažiny");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("mluv Zabiják");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("bojuj Zabiják");
        assertEquals(true, hra1.konecHry());
    }
    
    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * 
     * Testuje i jaké věci nebo osoby jsou v místnosti a jaké věci jsou v batohu hráče.
     */
    @Test
    public void testPrubehHry() {
        assertEquals("trnitá_stezka", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        //příkaz jdi - vstup do propojeného postoru
        hra1.zpracujPrikaz("jdi silo");
        assertEquals(false, hra1.konecHry());
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        //příkaz jdi - vstup do nepropojeného či neznámého prostoru 
        hra1.zpracujPrikaz("jdi močály");
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi mrakodrap");
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        //příkaz seber - viditelné věci (sebere)
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("kudla"));
        hra1.zpracujPrikaz("seber kudla");
        assertFalse(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("kudla"));
        assertTrue(hra1.getHerniPlan().getBatoh().obsahujeVec("kudla"));
        
        //příkaz seber - skryté věci (nesebere)
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("mačeta"));
        hra1.zpracujPrikaz("seber mačeta");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("mačeta"));
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("mačeta"));
              
        //příkaz seber - nepřenosné věci (nesebere)
        hra1.zpracujPrikaz("jdi skřížené_zdi");
        assertEquals("skřížené_zdi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("otepi_slámy"));
        hra1.zpracujPrikaz("seber otepi_slámy");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("otepi_slámy"));
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("otepi_slámy"));
        
        //příkaz zahoď - věci v batohu (zahodí)
        hra1.zpracujPrikaz("jdi silo");
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi bažiny");
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getBatoh().obsahujeVec("kudla"));
        hra1.zpracujPrikaz("zahoď kudla");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("kudla"));
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("kudla"));
        
        //příkaz zahoď - neexistující předmět, resp. předmět není v batohu (nezahodí)
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("zapalovač"));
        hra1.zpracujPrikaz("zahoď zapalovač");
        assertFalse(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("zapalovač"));
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("zapalovač"));

        //příkaz prozkoumej - prozkoumá aktuální oblast a umožní sebrat nově objevené věci
        hra1.zpracujPrikaz("jdi silo");
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("mačeta"));
        hra1.zpracujPrikaz("seber mačeta");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("mačeta"));
        assertFalse(hra1.getHerniPlan().getBatoh().obsahujeVec("mačeta"));
        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("seber mačeta");
        assertFalse(hra1.getHerniPlan().getAktualniProstor().obsahujeVec("mačeta"));
        assertTrue(hra1.getHerniPlan().getBatoh().obsahujeVec("mačeta"));
        
        //příkaz bojuj - zabije zabijáka (za předpokladu, že je mačeta v batohu)
        Vec maceta = new Vec("mačeta",true,true);
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.getHerniPlan().getBatoh().vlozVec(maceta);
        hra1.zpracujPrikaz("jdi bažiny");
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.prohraMocal());
        assertEquals(false, hra1.prohraZabijak());
        assertEquals(false, hra1.vyhra());
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("bojuj Zabiják");
        assertEquals(false, hra1.prohraMocal());
        assertEquals(false, hra1.prohraZabijak());
        assertEquals(false, hra1.vyhra());
        assertEquals(false, hra1.konecHry());
        assertEquals("bažiny", hra1.getHerniPlan().getAktualniProstor().getNazev());
        
        //příkaz použij - dojde k vyrýpnutí cihly (za předpokladu, že je v batohu kudla a je cihla objevena)
        Vec kudla = new Vec("kudla",true,true);
        hra1.zpracujPrikaz("jdi silo");
        assertEquals("silo", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.getHerniPlan().getBatoh().vlozVec(kudla);
        hra1.zpracujPrikaz("jdi skřížené_zdi");
        assertEquals("skřížené_zdi", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("prozkoumej");
        hra1.zpracujPrikaz("použij kudla uvolněná_cihla");
        
    }
    
    public void testKonecHry(){
        assertEquals("prostor", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }

}
