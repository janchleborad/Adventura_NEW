/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 * Instance třídy Vec představují věci vyskytující se ve hře.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private boolean jeSkryta;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor ....
     */
    public Vec(String nazev, boolean prenositelnost, boolean jeSkryta)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.jeSkryta = jeSkryta;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Metoda vrací jméno věci.
     * 
     * @return String jméno věci
     */
    public String getNazev(){
        return nazev;
    }

    /**
     * Metoda zjišťuje, zda je věc přenositelná.
     * 
     * @return vrací true, pokud se věc dá přenést, false pokud ne
     */
    public boolean jePrenositelna(){
        return prenositelnost;
    }

    /**
     * Metoda zjišťuje, zda je věc skrytá.
     * 
     * @return vrací true, pokud je skrytá, false pokud ne
     */
    public boolean getJeSkryta(){
        return jeSkryta;
    }

    /**
     * Metoda nastaví skrytost věci.
     * 
     * @retrurn true je skrytá, flase pokud ne
     */
    public void setJeSkryta(boolean jeSkryta){
        this.jeSkryta = jeSkryta;
    }
   
}
