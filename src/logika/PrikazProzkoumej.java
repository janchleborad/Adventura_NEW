package logika;

/*******************************************************************************
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
class PrikazProzkoumej implements IPrikaz {
    private static final String NAZEV = "prozkoumej";
    private static final String PARAMETRY = "-";
    private static final String POPIS = " prozkoumá aktuální prostor";
    private HerniPlan plan;
    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param  plan herní plán, ve kterém se bude ve hře "chodit"
     *         hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazProzkoumej(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     * Vykonává příkaz "prozkoumej". Prohledá se aktuální prostor a 
     * dojde k zobrazení skrytých věcí. V závěrečném prostoru slouží
     * jako zavolá stavu konec hry (výhra).
     *  
     * @return zpráva vypíše se hráčovi a nově objevené předměty 
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
             return "Stačí napsat jen prozkoumej.\n";
        }
        else {
            if(plan.getAktualniProstor().getNazev().equals("krypta") ) {
                hra.setVyhra(true);
                hra.setKonecHry(true);
                return "Prozkoumal jsi kryptu a spilnil jsi svou misi. Konec hry.\n";
            }
            else {
                Prostor aktualniProstor = plan.getAktualniProstor();
                return aktualniProstor.objeveno() + "\n" + aktualniProstor.popisVeci() + "\n";
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
