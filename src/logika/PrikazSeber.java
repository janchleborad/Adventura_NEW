/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/*******************************************************************************
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   prosinec 2016
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private static final String PARAMETRY = "věc";
    private static final String POPIS = " pokusí se sebrat věc v místnosti";
    private HerniPlan plan;

    //== Konstruktory a tovární metody =============================================

    /**
     *  Konstruktor třídy
     *  
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     *  Provádí příkaz "seber". Sebere věc z prostoru a vloží do batohu za předpokladu,
     *  že je věc přenositelná, není skrytá a v batohu je místo.
     *  
     *  @param parametry název sbírané věci
     *  @return          zpráva, která je vypsána hráči  
     */
    public String proved(String... parametry){
        if(parametry.length == 0){
            return "Co mám sebrat?\n";
        }        
        else if (parametry.length > 1) {
            return "Můžeš zadat pouze jeden parametr.\n";
        }
        else {
            String nazevSbiraneVeci = parametry[0];        
            Prostor aktualniProstor = plan.getAktualniProstor();
            String text = "";

            if (aktualniProstor.obsahujeVec(nazevSbiraneVeci)){
                Vec sbiranaVec = aktualniProstor.zahodVec(nazevSbiraneVeci);
                if (sbiranaVec == null){ 
                    text =  "Tuto věc nelze sebrat.\n";
                }
                else if (!sbiranaVec.jePrenositelna()) {
                    aktualniProstor.vlozVec(sbiranaVec);
                    text =  "Tuto věc nelze sebrat.\n";
                }
                else if (sbiranaVec.getJeSkryta()) {
                    aktualniProstor.vlozVec(sbiranaVec);
                    text =  "Tuto věc nelze sebrat.\n";
                }
                else if (nazevSbiraneVeci.equals("uvolněná_cihla")) {
                    aktualniProstor.vlozVec(sbiranaVec);
                    text =  "Cihla je to sice hezká, ale je těžká a bude ti k ničemu!\n";
                }
                else{
                    if (plan.getBatoh().vlozVec(sbiranaVec) && sbiranaVec.jePrenositelna()){
                        text = "\n" + "Byla sebrána věc " + sbiranaVec.getNazev()
                        + " a byla vložena do batohu.\n" + aktualniProstor.popisVeci() + "\n";
                    }                             
                    else{
                        aktualniProstor.vlozVec(sbiranaVec);                       
                        text =  "Batoh je plný.\n";
                    }
                }
            }
            else {
                text = "To tady není.\n";
            }
            return text;
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
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
