/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;
import java.util.Map;
import java.util.HashMap;

/*******************************************************************************
 * Instance třídy Batoh představují inventář s omezenou kapacitou.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
public class Batoh{
    private Map <String, Vec> veciVBatohu;      // věci v batohu
    private static final int MAX_OBSAH = 3;     // maximální kapacita

    /**
     * Konstruktor třídy.
     */
    public Batoh(){
        veciVBatohu = new HashMap<String, Vec>();
    }

    /**
     * Metoda zjistí, zda se věc vejde do batohu a případně věc vloží.
     *
     * @param vec   věc, která se má přidat
     * @return      vrátí true, pokud se vejde do batohu, false, pokud se nevejde
     */
    public boolean vlozVec(Vec vec){
        if(veciVBatohu.size() < MAX_OBSAH) {
            veciVBatohu.put(vec.getNazev(), vec);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Metoda odebere věc z batohu.
     * 
     * @param vec  věc, která sa má odebrat z batohu
     * @return     vrátí jméno vyhozené věci, pokud se ji podařilo vyhodit
     */   
    public Vec zahodVec(String nazev){
        Vec zahozenaVec = null;
        if (veciVBatohu.containsKey(nazev)) {
            zahozenaVec = veciVBatohu.get(nazev);
            veciVBatohu.remove(nazev);
        }
        return zahozenaVec;  
    }  

    /**
     * Metoda zjišťuje, zda se daná věc vyskytuje v batohu.
     *  
     * @param vec věc, na kterou se ptáme
     */   
    public boolean obsahujeVec(String nazevVeci){
        return veciVBatohu.containsKey(nazevVeci);
    }

    /**
     * Metoda zjistí obsah batohu.
     * Jednotlivé věci jsou odděleny mezerou.
     *  
     * @return seznam věcí v batohu
     */   
    public String obsahBatohu(){
        String veci = "Věci nacházející se v batohu: ";
        for(String nazevVeci : veciVBatohu.keySet()){
            veci += nazevVeci + " " ;
        }
        return veci;
    }

}