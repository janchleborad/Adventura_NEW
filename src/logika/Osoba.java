/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;



/*******************************************************************************
 * Instance třídy Osoba představují postavy vyskytující se ve hře.
 * Tato třída je součástí jednoduché textové hry.
 * 
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
public class Osoba
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String text;
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor nastaví jméno postavy a dialog postav.
     */
    public Osoba(String nazev, String text){
        this.nazev = nazev;
        this.text = text;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    
    /**
     * Metoda vrací název postavy.
     * 
     * @return   String název postavy.
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Metoda vrací dialog postav.
     * 
     * @return   String text dialogu
     */
    public String getText(){
        return text;
    }
    //== Soukromé metody (instancí i třídy) ========================================

}
