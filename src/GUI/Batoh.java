/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import logika.IHra;
import logika.Vec;
import utils.Observer;

/**
 *
 * @author asdf
 */
public class Batoh extends TilePane implements Observer {

    private IHra hra;
    private TextArea centralText;
    private logika.Batoh batoh;

    public Batoh(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        this.batoh = this.hra.getHerniPlan().getBatoh();
        
        hra.getHerniPlan().getBatoh().registerObserver(this);
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
        this.getChildren().clear();
        
        for (Vec vec : batoh.getVeciVBatohu().values()) {
            ImageView obrazekVeci = new ImageView(new Image(Batoh.class.getResourceAsStream("/zdroje/" + vec.getNazev() + ".jpg")));
            
            obrazekVeci.setOnMouseClicked(e -> {
                String odpoved = hra.zpracujPrikaz("zahoď " + vec.getNazev());
                centralText.appendText("\n\n" + odpoved + "\n");
            });
            
            this.getChildren().add(obrazekVeci);
        }
    }

    @Override
    public void novaHra() {
        
    }
    
}
