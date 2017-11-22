package logika;

import java.util.ArrayList;
import java.util.List;
import utils.Observer;
import utils.ObserverZmenaProstoru;
import utils.SubjektZmenaProstoru;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Chleborád
 *@version    pro školní rok 2015/2016, upraveno prosinec 2016
 */
public class HerniPlan implements SubjektZmenaProstoru {
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    
    private List<ObserverZmenaProstoru> listObserveru = new ArrayList<ObserverZmenaProstoru>();

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        batoh = new Batoh();
    }

    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor trnitaStezka = new Prostor("trnitá_stezka","\nAkorát jsme na trnité stezce, podle mapy, kteou mi dal táta,\n"
                                                         + "bychom se tudy měli prodrat k silu.\n", 75,32);
        Prostor silo = new Prostor("silo","\nKonečně, to musí být to opuštěné silo, které je zakreslené na mapě. Určitě\n"
                                        + "bychom se tu měli porozhlédnout. Ale dávej si bacha, prý tu straší! Až tu budeme\n"
                                        + "hotoví, měli bychom se vydat směrem ke skříženým zdem.\n", 298,32);
        Prostor skrizeneZdi = new Prostor("skřížené_zdi","\nTady mapa od táty končí, jestli se nám nepodaří najít nové indície,\n"
                                                       + "nevím, jak budeme moci pokračovat dále.\n", 520,32);
        Prostor baziny = new Prostor("bažiny","\nDávej pozor! Už jsme v bažinách. Támhle musí být ten hlídač, jak se jen\n" 
                                            + "jmenoval... Už vím, Zabiják! Musíme ho dostat, jinak se nedostaneme do\n"
                                            + "staré chaty živí a zdraví!\n", 298,119);
        Prostor mocaly = new Prostor ("močály","\nTo ne! To jsou močály. Jsme v koncích!\n", 70,130);//to do
        Prostor strasidelnaVrba = new Prostor("strašidelná vrba","\nZajímavé místo, ale půjdme dál, není tu nic zajímavého.", 520,119);
        Prostor staraChata = new Prostor("stará_chata","\nKoukej, támhle někdo je, promluv s ním a prohledej to tu,\n"
                                                     + "já počkám venku.\n", 298,209);
        Prostor skaliska = new Prostor("skaliska","\nTady to bude o život, ale podívej, vraky aut, o kterých mluvil\n"
                                                + "poustevník, jsou hned za tímhle štítem, tak pojďme.\n", 298,293);
        Prostor vrakyAut = new Prostor("vraky_aut","\nMusíme to tu prohledat, jistě tu bude budou další indície!\n", 520,293);
        Prostor pohrebiste = new Prostor("pohřebiště","\nO pohřebišti se zmiňoval poustevník naposled, dál však cestu\n"
                                                    + "neznal. Ale podívej se, támhle je ukazatel se směrovkami. Vydáme se do\n"
                                                    + "tunelu, to musí být přístupová cesta do starého podzemního chrámu.\n", 75,293);
        Prostor tunel = new Prostor("tunel","\nRychle odtud pryč, hemží se to tady havětí!\n", 75,368);
        Prostor staryPodzemniChram = new Prostor("starý_podzemní_chrám","\nDívej, to nemůže být pravda! Vypadá to tu tak prázdně,\n"
                                                                      + "musel tu být už už někdo před námi a všechno odnést.\n"
                                                                      + "pojďme do krypty, to je naše poslední naděje.\n", 75,439);
        Prostor krypta = new Prostor ("krypta","\nHledej všude! Tady v kryptě je mnoho skrytých míst, kde by se kalich mohl\n" 
                                             + "nacházet. Musíme najít odpoveď na mnoho let zpochybňovaný mýtus o kalichu života.\n", 75,439);//to do
                                       

        // přiřazují se průchody mezi prostory (sousedící prostory)
        trnitaStezka.setVychod(silo);
        silo.setVychod(trnitaStezka);
        silo.setVychod(skrizeneZdi);
        silo.setVychod(baziny);
        skrizeneZdi.setVychod(silo);
        baziny.setVychod(silo);
        baziny.setVychod(mocaly);
        baziny.setVychod(strasidelnaVrba);
        baziny.setVychod(staraChata);
        mocaly.setVychod(baziny);
        strasidelnaVrba.setVychod(baziny);
        staraChata.setVychod(baziny);
        staraChata.setVychod(skaliska);
        skaliska.setVychod(staraChata);
        skaliska.setVychod(pohrebiste);
        skaliska.setVychod(vrakyAut);
        vrakyAut.setVychod(skaliska);
        pohrebiste.setVychod(skaliska);
        pohrebiste.setVychod(tunel);
        tunel.setVychod(pohrebiste);
        tunel.setVychod(staryPodzemniChram);
        staryPodzemniChram.setVychod(tunel);
        staryPodzemniChram.setVychod(krypta);
        krypta.setVychod(staryPodzemniChram);

        // osoby, které se nacházejí ve hře
        Osoba poustevnik = new Osoba("Poustevník","TY: Dobrý den, kdo jste? Jsme tu s přítelem, abychom\n"
            + "našli pravdu o bájném kalichu života.\n"
            + "POUSTEVNÍK: Jsem Poustevník, žiju tu již 200 let, takových jako jste vy, už tu bylo mnoho!\n"
            + "TY: Můžete mi prosím prozdradit, kam máme pokračovat? Jsme ztracení!\n"
            + "POUSTEVNÍK: Musíte najít vraky aut, a poté se vydat na pohřebiště. Víc vám nepordadím.\n"
            + "TY: I tak vám děkuji! Nashledanou.\n");
        Osoba zabijak = new Osoba("Zabiják","FLINN: Hej, potřebujeme se dostat do staré chaty.\n"
            + "TY: Kudy máme jít?\n"
            + "ZABIJÁK: Tak tam půjdete jen přes mou mrtvolu! HaHa.\n"
            + "TY: Dobře, budeme bojovat. Tak se ukaž!\n");

        // přiřazují se prostory, ve kterých se budou osoby nacházet
        baziny.vlozOsobu(zabijak);
        staraChata.vlozOsobu(poustevnik);

        // vytvářejí se jednotlivé věci
        Vec maceta = new Vec("mačeta",true,true);
        Vec kudla = new Vec("kudla",true,false);
        Vec lano = new Vec("lano",true,false);
        Vec uvolnenaCihla = new Vec("uvolněná_cihla",true,true);
        Vec otepiSlamy = new Vec("otepi_slámy",false,false);
        Vec dutyKmen = new Vec("dutý_kmen",false,false);
        Vec padlo = new Vec("pádlo",true,true);
        Vec notes = new Vec("notes",true,false);
        Vec skrin = new Vec("skříň",false,false); 
        Vec stul = new Vec("stůl",false,false);
        Vec police = new Vec("police",false,false);
        Vec starobyleKlice = new Vec("starobylé_klíče",true,true);
        Vec pneumatiky = new Vec("pneumatiky",false,false);
        Vec sadaNaradi = new Vec("sada_nářadí",true,true);
        Vec balvany = new Vec("balvany",false,false);
        Vec ukazatel = new Vec("ukazatel",false,false);
        Vec nahrobky = new Vec("náhrobky",false,false);
        Vec otevrenaRakev = new Vec("otevřená_rakev",false,true);
        Vec lavice = new Vec("lavice",false,false);
        Vec oltar = new Vec("oltář",false,false);
              
        // přiřazují se prostory, ve kterých se budou věci nacházet
        silo.vlozVec(maceta);
        silo.vlozVec(kudla);
        silo.vlozVec(lano);
        skrizeneZdi.vlozVec(uvolnenaCihla);
        skrizeneZdi.vlozVec(otepiSlamy);
        strasidelnaVrba.vlozVec(dutyKmen);
        strasidelnaVrba.vlozVec(padlo);
        staraChata.vlozVec(notes);
        staraChata.vlozVec(skrin);
        staraChata.vlozVec(stul);
        staraChata.vlozVec(police);
        vrakyAut.vlozVec(starobyleKlice);
        vrakyAut.vlozVec(pneumatiky);
        vrakyAut.vlozVec(sadaNaradi);
        skaliska.vlozVec(balvany);
        pohrebiste.vlozVec(ukazatel);
        pohrebiste.vlozVec(nahrobky);
        pohrebiste.vlozVec(otevrenaRakev);
        staryPodzemniChram.vlozVec(lavice);
        staryPodzemniChram.vlozVec(oltar);
     
        // hra začíná na trnité stezsce
        aktualniProstor = trnitaStezka;     

        // zamknuté oblasti
        staraChata.setJeZivy(true);
        krypta.setJeZamknuto(true);
    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     *  Metoda vrací odkaz na věci, které se nacházejí v batohu.
     *
     *@return     batoh
     */

    public Batoh getBatoh() {
        return batoh;
    }

    
    @Override
    public void registerObserver(ObserverZmenaProstoru observer) {
        listObserveru.add(observer);
    }

    @Override
    public void unregisterObserver(ObserverZmenaProstoru observer) {
        listObserveru.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (ObserverZmenaProstoru listObserveruItem : listObserveru) {
            listObserveruItem.update();
        }
    }
}