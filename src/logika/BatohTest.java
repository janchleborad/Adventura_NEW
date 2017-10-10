/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code BatohTest} slouží ke komplexnímu otestování
 * třídy {@link BatohTest}.
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class BatohTest
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    //== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
    //== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    //== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================
    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
    //== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------
    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    /***************************************************************************
     * Metoda testuje vkládání věcí do batohu,
     * odebírání věcí z batohu a obsah batohu.
     */
    public void testBatohu(){
        Batoh batoh = new Batoh();
        Vec vec1 = new Vec("věc 1",true,true);
        Vec vec2 = new Vec("věc 2",true,true);
        Vec vec3 = new Vec("věc 3",true,true);
        Vec vec4 = new Vec("věc 4",true,true);
        assertEquals(true, batoh.vlozVec(vec1));
        assertEquals(true, batoh.vlozVec(vec2));
        assertEquals(true, batoh.vlozVec(vec3));
        assertEquals(false, batoh.vlozVec(vec4));
        batoh.zahodVec("věc 1");
        assertEquals(false, batoh.obsahujeVec("věc 1"));
        assertEquals(true, batoh.obsahujeVec("věc 2"));
        assertEquals(true, batoh.obsahujeVec("věc 3"));
        assertEquals(false, batoh.obsahujeVec("věc 4"));
    }


    //== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    //== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================
    //== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================
    //== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    //== INTERNÍ DATOVÉ TYPY ===================================================
    //== VLASTNÍ TESTY =========================================================
    //
    //     /********************************************************************
    //      *
    //      */
    //     @Test
    //     public void testXxx()
    //     {
    //     }
}
