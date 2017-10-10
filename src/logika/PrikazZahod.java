package logika;

/*******************************************************************************
 *  Třída PrikazZahod implementuje pro hru příkaz zahod.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazZahod implements IPrikaz {
    private static final String NAZEV = "zahoď";
    private static final String PARAMETRY = "věc";
    private static final String POPIS = " zahodí věc z batohu  do prostoru";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     * 
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazZahod(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Vykonává příkaz "zahoď". V případě, že jsou spněny podmínky, že se věc nachází v batohu,
     * dojde k jejímu zahození do prostoru.
     * 
     * Pokud podmínky nejsou splněny, vrací se příslušné chybové hlášení.
     *  
     * @param prametr je název věci, která má být zahozena
     * @return zpráva vypíše se hráčovi  
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám zahodit.\n";
        }
        else if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden parametr.\n";
        }
        else {
            String nazevVeci = parametry[0];
            Prostor aktualniProstor = plan.getAktualniProstor();
            
            Vec odebirana = plan.getBatoh().zahodVec(nazevVeci);
            if (odebirana == null) {
                return "Tuto věc v batohu nemáš.\n";
            }
            else {
                aktualniProstor.vlozVec(odebirana);
                return "Zahodil jsi věc s názvem: " + nazevVeci + "\n"
                + aktualniProstor.popisVeci();
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     *  Metoda vrací paramtery příkazu
     *  
     *  @ return parametry prikazu
     */
    public String getParametry() {
        return PARAMETRY;
    }

    /**
     *  Metoda vrací popis příkazu
     *  
     *  @ return popis prikazu
     */
    public String getPopis() {
        return POPIS;
    }

}
