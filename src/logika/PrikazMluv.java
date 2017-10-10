package logika;

/*******************************************************************************
 *  Třída PrikazMluv implementuje pro hru příkaz mluv.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazMluv implements IPrikaz {
    private static final String NAZEV = "mluv";
    private static final String PARAMETRY = "osoba";
    private static final String POPIS = " zobrazí dialog s osobou";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Vykonává příkaz "mluv". Umožňuje zobrazit dialog postav.
     * Dialog se zobrazí za splnění podmínek, že je postava v
     * prostoru.
     * 
     * Pokud není splněna podmínka, vrací se příslušné chybové hlášení.
     *  
     * @param paramtr je název osoby, se kterou má být mluveno
     * @return zpráva vypíše se hráčovi  
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám mluvit?\n";
        }
        else if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden parametr.\n";
        }
        else {
            String parametr = parametry[0];
            if (plan.getAktualniProstor().obsahujeOsobu(parametr)) {
                Osoba osoba = plan.getAktualniProstor().vyberOsobu(parametr);
                return osoba.getText();
            }
            else {
                return "Taková osoba tu není.\n";
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
