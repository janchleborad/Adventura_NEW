/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Collection;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.IHra;
import logika.Prostor;
import logika.Vec;
import utils.Observer;
import logika.Batoh;
import logika.Hra;

/*******************************************************************************
 * Instance třídy VeciVProstoru představují seznam věcí, které se nacházív 
 * aktuální prostoru.
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   listopad 2017
 */
public class VeciVProstoru extends TilePane implements Observer {

    private IHra hra;
    private TextArea centralText;
    private Collection<Vec> veciVProstoru;

    /**
     * Konstruktor třídy.
     * @param hra instance hry
     * @param centralText instance centralText, kde se vypisující odpovědi
     */
    public VeciVProstoru(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        this.veciVProstoru = hra.getHerniPlan().getAktualniProstor().getVeciVProstoru().values();
        
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.registerObserver(this);
        }
        init();
        update();
    }
    
    /**
     * Inicializační metoda pro zpřehlednění.
     */
    public void init(){
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setVgap(10);
        this.setHgap(10);
        this.setPrefColumns(2);
    }
    
    @Override
    public void update() {
        veciVProstoru = hra.getHerniPlan().getAktualniProstor().getVeciVProstoru().values();
        this.getChildren().clear();

        
        for (Vec vec : veciVProstoru) {
            
            if (vec.getJeSkryta()){
                continue;
            }
            
            ImageView obrazekVeci = new ImageView(new Image(Batoh.class.getResourceAsStream("/zdroje/" + vec.getNazev() + ".jpg")));
            
            obrazekVeci.setOnMouseClicked(e -> {
                String odpoved = hra.zpracujPrikaz("seber " + vec.getNazev());
                centralText.appendText("\n\n" + odpoved + "\n");
            });
            
            this.getChildren().add(obrazekVeci);
        }
    }

    @Override
    public void novaHra(IHra hra) {
        hra.getHerniPlan().removeObserver(this);
        hra.getHerniPlan().removeObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.removeObserver(this);
        }
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.registerObserver(this);
        }
       update();
    } 
}
