package logika;

/*******************************************************************************
 *  Třída PrikazPouzij implementuje pro hru příkaz pouzij.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazPouzij implements IPrikaz {
    private static final String NAZEV = "použij";
    private static final String PARAMETRY = "věc1";
    private static final String POPIS = " ";
    private HerniPlan plan;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     */    
    public PrikazPouzij(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Vykonává příkaz "použij". Příkaz umožňuje využívat určité přdměty
     * za splnění podmínek (prostor, jakost předmětu a na co bude předmět
     * využit).
     * 
     * Pokud nejsou splněny podmínky, vrací se příslušné chybové hlášení.
     *  
     * @param parametry jako název věci, která má být použita
     *                       název věci, na kterou má být použita
     * @return zpráva vypíše se hráčovi  
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Co mám použít?\n";
        }
        else if (parametry.length == 1) {           
            return "Na co mám věc použít?\n";
        }
        else if (parametry.length > 2) {
            return "Musíš použít pouze věc, kterou na něco využiješ.\n";
        }
        else {
            String coPouzit = parametry[0];
            String naCoPouzit = parametry[1];

            Prostor aktualniProstor = plan.getAktualniProstor();
            if (coPouzit.equals("kudla") && plan.getBatoh().obsahujeVec("kudla")) {
                if (aktualniProstor.getNazev().equals("skřížené_zdi")) {
                    if (aktualniProstor.obsahujeVec("uvolněná_cihla") && naCoPouzit.equals("uvolněná_cihla")) {
                        Vec cihla = plan.getAktualniProstor().zahodVec("uvolněná_cihla");
                        if (cihla.getJeSkryta() == true) {
                            plan.getAktualniProstor().vlozVec(cihla);
                            return "Na tohle je ti kudla k ničemu.\n";
                        }
                        else {
                            plan.getAktualniProstor().vlozVec(cihla);
                            return "Právě jsi vyrýpnul uvolněnou cihlu! Je na ní důležitý vzkaz,\n"
                            + "který ti usnadní tvé další putování. 'Pokračuj dále do staré chaty.\n"
                            + "dávej však pozor na močály - tudy cesta nevede! PS: Bez mačety se\n"
                            + "neobejdeš!' Po přečtení vzkazu bude cihla vrácena na původní místo,\n"
                            + "kdykoli se k ní však můžeš vrátit!\n";
                        }
                    }
                    else {
                        return "Na tohle je ti kudla k ničemu.\n";
                    }
                }
                else {
                    return "V této oblasti ti je kudla k ničemu.\n";
                }
            }
            else {
                return "Toto se použít nedá.\n";
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
