package logika;

/*******************************************************************************
 *  Třída PrikazBojuj implementuje pro hru příkaz bojuj.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazBojuj implements IPrikaz {
    private static final String NAZEV = "bojuj";
    private static final String PARAMETRY = "osoba1";
    private static final String POPIS = " ";
    private HerniPlan plan;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
     *         hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazBojuj(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Vykonává příkaz "bojuj". Při zavolání tohoto příkazu dojde k usmrcení osoby.
     * Osoba však musí být v prostoru a musí být zabitá konkrétním předmětem Některé
     * osoby zabít nelze.
     * 
     * Pokud je některá z podmínek porušena, vrací se příslušné chybové hlášení.
     * 
     * @param parametr je název osoby, se kterou má být zahájen boj
     * @return zpráva vypíše se hráčovi  
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "S kým mám bojovat?\n";
        }
        else if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden parametr.\n";
        }
        else {
            String parametr = parametry[0];

            if (plan.getAktualniProstor().obsahujeOsobu("Zabiják") && parametr.equals("Zabiják")) {
                if (plan.getBatoh().obsahujeVec("mačeta")) {
                    plan.getAktualniProstor().odeberOsobu("Zabiják");
                    return "Zabiják byl sťat mačetou. Nyní můžeš pokračovat dále.\n";
                }
                else {
                    hra.setProhraZabijak(true);
                    hra.setKonecHry(true);
                    return "Jsi mrtvý, konec.\n";
                }
            }
            else if (plan.getAktualniProstor().obsahujeOsobu("Poustevník") && parametr.equals("Poustevník")) {
                return "Poustevníka nemůžeš zabít, je nesmrtelný.\n";
            }
            else {
                return "Není tu nikdo, s kým lze bojovat.\n";
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
