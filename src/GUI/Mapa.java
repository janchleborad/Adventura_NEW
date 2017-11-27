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

/*******************************************************************************
 * Instance třídy Mapa představují plánek, na kterém se pomocí tečky zobrazuje
 * aktuální poloha (místnost).
 *
 * @author    Jan Chleborád
 * @version   1.00.000
 * @created   listopad 2017
 */
public class Mapa extends AnchorPane implements Observer {
    
    public IHra hra;
    private Circle tecka;
    
    /**
     * Konstruktor třídy.
     * @param hra instance hry
     */
    public Mapa(IHra hra) {
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    
    /**
     * Inicializační metoda pro nalinkování obrázku a vzhledu tečky.
     */
    public void init() {
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa.jpg"), 461, 585, false, true));
        tecka = new Circle(10, Paint.valueOf("black"));  
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
        
    @Override
    public void update() {
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop());
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft());
    }

    @Override
    public void novaHra(IHra hra) {
        this.hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
}