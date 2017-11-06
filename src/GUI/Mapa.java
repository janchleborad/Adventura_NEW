/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Main.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import utils.Observer;

/**
 *
 * @author Honysek
 */
public class Mapa extends AnchorPane implements Observer {
    
    public IHra hra;
    private Circle tecka;
    
    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    public void init() {
            
            ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"), 200, 200, false, true));
        
            tecka = new Circle(10, Paint.valueOf("red"));
            
            //this.setTopAnchor(tecka, 25.0);
            //this.setLeftAnchor(tecka, 100.0);
            
            this.getChildren().addAll(obrazekImageView, tecka);
            update();
    }
    
    public void newGame(IHra novaHra) {
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }
}