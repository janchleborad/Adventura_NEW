package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Jan Chleborád
 *@version    pro školní rok 2015/2016, upraveno prosince 2016
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private static final String PARAMETRY = "prostor";
    private static final String POPIS = " přejde do vedlejšího prostoru";
    private HerniPlan plan;
    private Hra hra;

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     *         hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení. Dále vypisuje hlášení o stavech
     *  místností.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                   do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Zadej jméno východu.\n";
        }
        else if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden parametr.\n";
        }
        else {
            String smer = parametry[0];

            Prostor aktualniProstor = plan.getAktualniProstor();
            Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

            if (sousedniProstor == null) {
                return "Tam se odsud jít nedá!\n";
            }
            else if (sousedniProstor.getJeZivy()) {
                if (!aktualniProstor.obsahujeOsobu("Zabiják")) {
                    sousedniProstor.setJeZivy(false);
                    plan.setAktualniProstor(sousedniProstor);
                    return "Zabiják je poražen. Můžeš pokračovat dále.\n" + sousedniProstor.dlouhyPopis() + "\n";
                }
                else {
                    return "Zabiják hlídá průchod k dalším prostorům, nelze ho obejít.\n";
                }
            }
            else if (sousedniProstor.getJeZamknuto()) {
                if (plan.getBatoh().obsahujeVec("starobylé_klíče")) {
                    sousedniProstor.setJeZamknuto(false);
                    plan.setAktualniProstor(sousedniProstor);
                    Vec odebrana = plan.getBatoh().zahodVec("starobylé_klíče");
                    return "Pomocí klíčů jsi odemkl kryptu.\n" + sousedniProstor.dlouhyPopis();
                }
                else {
                    return "Bez klíčů se do krypty nedostaneš.\n";
                }
            }
            else if (smer.equals("močály")) {
                hra.setProhraMocal(true);
                hra.setKonecHry(true);
                return "Jsi mrtvý. Konec hry.\n";
            }
            else {
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis();
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
