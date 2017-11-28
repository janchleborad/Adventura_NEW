package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída logiky aplikace. Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Chleborád
 *@version    pro školní rok 2015/2016, upraveno prosinec 2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private boolean prohraMocal = false;
    private boolean prohraZabijak = false;
    private boolean vyhra = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznamu platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(herniPlan, this));
    }

    public SeznamPrikazu getPlatnePrikazy() {
        return platnePrikazy;
    }
    
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítej!\n" +
        "Já jsem Flinn a právě se nacházíme ve Skotsku. Hledáme tu bájný „Kalich života“,\n" +
        "který by se zde, podle legendy, měl nacházet, pomoz mi v hledání důležitých\n" +
        "informací, na základě nichž budeme schopni najít tento kalich. Buď však připraven,\n" +
        "že to nebude procházka růžovým sadem, narazíme jistě na ponurá místa a možná na\n" +
        "nebezpečné postavy. Jsi připraven? Pojďme vyrazit!\n" +
        "\n" +
        "Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
        "\n" +
        herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        if (prohraMocal) {
            return "Právě ses utopil v močále. Močály jsou zdrádná lokalita, neměl jsi sem\n" +
                   "s Flinnem vůbec chodit. Příště najdi indície, které tě budou varovat!\n";
        }
        else if (prohraZabijak) {
            return "Zabil tě zabiják. Není radno bojovat jen holýma rukama. Příště bude bezpečnější,\n" +
                   "když použiješ mačetu.\n";
        }
        else if (vyhra) {
            return "Výborně! Zvládli jsme to. Bohužel jsme kalich života nenašli, ale alešpoň jsme dokázali\n"
            + "odhalit dlouho zpochybňovanou pravdu, která nebyla až dodnes zřejmá. Snad bude příští\n"
            + "hon za poklady a taji legend úspěšnější!";
        }
        else {
            return "Doufám, že sis hru užil(a) a došel(a) jsi až do samého konce. Děkuji!\n";
        }
    }

    /** 
     * Metoda zjišťuje, zda je hra u konce.
     * 
     * @return vrací true, pokud je konec hry, false pokud ne
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zjišťuje, zda je výhra.
     * 
     * @return vrací true, pokud má nastat výhra
     */ 
    public boolean vyhra(){
        return vyhra;
    }
    
    /**
     * Metoda nastaví stav výhry.
     * 
     * @param vyhra pravda nebo nepravda pro výhru
     */ 
    public void setVyhra(boolean vyhra){
        this.vyhra = vyhra;
    }

    /**
     * Metoda zjišťuje stav prohry v močále.
     * 
     * @return vrací true, pokud má nastat prohra v močále
     */ 
    public boolean prohraMocal(){
        return prohraMocal;
    }

    /**
     * Metoda zjišťuje stav prohry při zabití zabijákem.
     * 
     * @return vrací true, pokud má nastat prohra při zabití zabijákem
     */ 
    public boolean prohraZabijak(){
        return prohraZabijak;
    }
    
    /**
     * Metoda nastaví stav prohry v močálu.
     * 
     * @param prohraMocal pravda nebo nepravda pro prohru
     */ 
    public void setProhraMocal(boolean prohraMocal){
        this.prohraMocal = prohraMocal;
    }

    /**
     * Metoda nastaví stav prohry při zabití zabijákem.
     * 
     * @param prohraZabijak pravda nebo nepravda pro prohru
     */ 
    public void setProhraZabijak(boolean prohraZabijak){
        this.prohraZabijak = prohraZabijak;
    }
    
    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        if (!konecHry) {
            String [] slova = radek.split("[ \t]+");
            String slovoPrikazu = slova[0];
            String []parametry = new String[slova.length-1];
            for(int i=0 ;i<parametry.length;i++){
                parametry[i]= slova[i+1];   
            }
            String textKVypsani=" .... ";
            if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
                IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
                textKVypsani = prikaz.proved(parametry);
            }
            else {
                textKVypsani="Nevím, co tím myslíš. Tento příkaz neznám.\n";
            }
            return textKVypsani;
        }
        return "Pro pokračování je třeba spustit novou hru.";
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false = hra pokračuje, true = hra končí
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
}
