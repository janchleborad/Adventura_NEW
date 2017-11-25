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

/**
 *
 * @author asdf
 */
public class VeciVProstoru extends TilePane implements Observer {

    private IHra hra;
    private TextArea centralText;
    private Collection<Vec> veciVProstoru;
    private Batoh batoh;

    public VeciVProstoru(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        this.veciVProstoru = hra.getHerniPlan().getAktualniProstor().getVeciVProstoru().values();
        this.batoh = this.hra.getHerniPlan().getBatoh();
        
        hra.getHerniPlan().getBatoh().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.registerObserver(this);
        }
        
        init();
        update();
    }
    
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
        hra.getHerniPlan().getBatoh().removeObserver(this);
        hra.getHerniPlan().removeObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.removeObserver(this);
        }
        this.hra = hra;
        hra.getHerniPlan().getBatoh().registerObserver(this);
        hra.getHerniPlan().registerObserver(this);
        for (Prostor prostor : hra.getHerniPlan().getProstory()) {
            prostor.registerObserver(this);
        }
       update();
    } 
}
