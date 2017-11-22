/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import logika.HerniPlan;
import utils.ObserverZmenaProstoru;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logika.Prostor;

/**
 *
 * @author Honysek
 */
public class PanelVychodu implements ObserverZmenaProstoru {
    
    private HerniPlan plan;
    private ObservableList<Button> tlacitka;
    
    public PanelVychodu(HerniPlan plan) {
        this.plan = plan;
        plan.registerObserver(this);
    }
    
    private void init() {
        tlacitka = FXCollections.observableArrayList();
    }
    
    public void AktualizujTlacitka() {
        Collection<Prostor> vychody = plan.getAktualniProstor().getVychody();
        for (Prostor vychod:vychody) {
            Button tlacitko = new Button(vychod.getNazev());
            tlacitko.setPrefSize(100, 30);
            tlacitko.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                   String text = Main.getHra().zpracujPrikaz("jdi " + tlacitko.getText());
                   Main.getTextArea().appendText("\n" + "jdi " + tlacitko.getText() + "\n");
                   Main.getTextArea().appendText("\n" + text + "\n");
                }
            });
            //if (Main.getHra().) 
            //tlacitko.setDisable(true); - pokud chci přestat používat tlačítka po konci hry
            tlacitka.add(tlacitko);
        }
    }
           
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
