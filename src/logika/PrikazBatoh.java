package logika;

/*******************************************************************************
 * Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private static final String PARAMETRY = "-";
    private static final String POPIS = " ukáže obsah batohu";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazBatoh(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Metoda vrací seznam věcí, kterě jsou v batohu.
     *
     * @return obsah batohu
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Stačí napsat jen batoh.";
        }
        else {
            return plan.getBatoh().obsahBatohu();
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
