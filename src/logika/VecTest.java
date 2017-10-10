/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída {@code VecTest} slouží ke komplexnímu otestování
 * třídy {@link VecTest}.
 *
 * @author    jméno autora
 * @version   0.00.000
 */
public class VecTest
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
     * Testuje funkčnost přenositelnosti věci.
     */
    public void testVeci(){
        Vec vec1 = new Vec("nazev",true,true);
        assertEquals(true,vec1.getJeSkryta());
        vec1.setJeSkryta(false);
        assertEquals(false,vec1.getJeSkryta());
        Vec vec2 = new Vec("nazev",true,true);
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
