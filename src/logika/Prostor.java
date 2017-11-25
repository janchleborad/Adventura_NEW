package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;
import utils.Observer;
import utils.Subject;

/**
 * Třída Prostor - popisuje jednotlivé prostory (místnosti) hry.
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Chleborád
 * @version pro školní rok 2015/2016, upraveno prosinec 2016
 */
public class Prostor implements Subject {
    private String nazev;
    private String popis;
    private Set<Prostor> vychody;
    private Map<String, Vec> veciVProstoru;
    private Map<String, Osoba> osobyVProstoru;
    private boolean jeZamknuto = false;
    private boolean jeZivy = false;
    private double posLeft;
    private double posTop;
    
    private List<Observer> listObserveru = new ArrayList<>();

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        this.posLeft = posLeft;
        this.posTop = posTop;
        vychody = new HashSet<>();
        veciVProstoru = new HashMap<>();
        osobyVProstoru = new HashMap<>();
    }

    public Map<String, Vec> getVeciVProstoru() {
        return veciVProstoru;
    }
    
    public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * Metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr.
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Metoda pro zajišťující, zda je prostor zamknutý.
     *
     * @return hodnota true pro zamknutý, false pro odemknutý.
     */
    public boolean getJeZamknuto() {
        return jeZamknuto;       
    }

    /**
     * Metoda zajišťující, zda je prostor zamknutý (odkazováno na živost postavy).
     * 
     * @return hodnata true pro život, hodnota false pro neživost.
     */
    public boolean getJeZivy() {
        return jeZivy;       
    }

    /**
     * Metoda nastaví zamknuto/odemknuto prostoru.
     */
    public void setJeZamknuto(boolean jeZamknuto) {
        this.jeZamknuto = jeZamknuto;       
    }

    /**
     * Metoda nastaví živost/neživost prostoru.
     */
    public void setJeZivy(boolean jeZivy) {
        this.jeZivy = jeZivy;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Právě se nacházíš v: " + nazev + popis + "\n"
        + popisVeci() + "\n"
        + popisOsob() + "\n"
        + popisVychodu() + "\n";
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Dále můžeš prokračovat do: ";
        for (Prostor sousedni : vychody) {
            vracenyText += sousedni.getNazev() + " ";
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje věci, které se nachází v prostoru.
     *
     * @return Popis věcí - názvů věcí v prostoru
     */
    public String popisVeci(){
        String vracenyText;
        if (veciVProstoru.values().isEmpty()){
            vracenyText = "Zde nejsou žádné věci.";
        } else {
        
            vracenyText = "Jsou zde tyto věci: ";
            for (Vec nazev : veciVProstoru.values()) {
                if (nazev.getJeSkryta()) {
                    continue;
                }
                vracenyText += nazev.getNazev() + " ";
            }
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje osoby, které se nachází v prostoru.
     *
     * @return Popis osob - názvů osob v prostoru
     */
    public String popisOsob() {
        String vracenyText;
        if (veciVProstoru.values().isEmpty()){
            vracenyText = "Zde nejsou žádné osoby.";
        } else {
        
            vracenyText = "Jsou zde tyto osoby: ";
            for (String nazev : osobyVProstoru.keySet()) {
                vracenyText += nazev + " ";
            }
        }
    return vracenyText;
    }

    /**
     * Vrací textové řetězce, které popisují věci, které jsou/byly v prostoru.
     *
     * @return Popis věcí - názvů věcí v prostoru
     */
    public String objeveno() {
        String text = "";
        if (veciVProstoru.isEmpty()) {
            text = "Nebyly nalezeny žádné nové věci.\n";
        }
        else {
            text = "Byly nalezeny nové věci: ";
            int pocitadlo = 0;
            for (Vec nazevVeci : veciVProstoru.values()) {
                if (nazevVeci.getJeSkryta()) {
                    nazevVeci.setJeSkryta(false);
                    text+= nazevVeci.getNazev() + " ";
                    pocitadlo ++;
                }
                else {
                    continue; 
                }
            }
            if (pocitadlo == 0) {
                text = "Nebyly nalezeny žádné nové věci.\n";
            }
        }
        return text;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
            .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
            .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Metoda vloží věc do prostoru.
     */ 
    public void vlozVec(Vec vec){
        veciVProstoru.put(vec.getNazev(),vec);
        notifyObservers();
    }

    /**
     * Metoda zahodí věc do prostoru.
     */ 
    public Vec zahodVec(String nazev){
        Vec zahozenaVec = null;
        if (veciVProstoru.containsKey(nazev)) {
            zahozenaVec = veciVProstoru.get(nazev);
            veciVProstoru.remove(nazev);
            notifyObservers();
        }
        return zahozenaVec;  
    }  

    /**
     * Metoda vloží osobu do prostoru.
     */ 
    public void vlozOsobu(Osoba osoba){
        osobyVProstoru.put(osoba.getNazev(),osoba);
    }

    /**
     * Metoda odebere osobu z prostoru.
     */ 
    public Osoba odeberOsobu(String nazev){
        Osoba odebranaOsoba = null;
        if (osobyVProstoru.containsKey(nazev)) {
            odebranaOsoba = osobyVProstoru.get(nazev);
            osobyVProstoru.remove(nazev);
        }
        return odebranaOsoba;  
    } 

    /**
     * Metoda najde osobu.
     */ 
    public Osoba vyberOsobu(String nazev){
        Osoba vybranaOsoba = null;
        if (osobyVProstoru.containsKey(nazev)) {
            vybranaOsoba = osobyVProstoru.get(nazev);
        }
        return vybranaOsoba;  
    } 

    /**
     * Zjistí, zda se daná věc nachází v prostoru.
     * 
     * @return vrátí true, pokud je věc v daném prostoru.
     */
    public boolean obsahujeVec(String nazev){
        return veciVProstoru.containsKey(nazev);
    }

    /**
     * Zjistí, zda se daná osoba nachází v prostoru.
     * 
     * @return vrátí true, pokud je osoba v daném prostoru.
     */
    public boolean obsahujeOsobu(String nazev){
        return osobyVProstoru.containsKey(nazev);
    }
    
    @Override
    public void registerObserver(Observer observer) {
        listObserveru.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}
