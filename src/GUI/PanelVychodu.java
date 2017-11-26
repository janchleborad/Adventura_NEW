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
import utils.Observer;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import logika.Hra;
import logika.IHra;
import logika.Prostor;

/**
 *
 * @author Honysek
 */
public class PanelVychodu extends ListView implements Observer {
    
    private HerniPlan plan;
    private IHra hra;
    private ObservableList<String> dataVychodu;
    private TextArea centralText;
    
    public PanelVychodu(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
        this.centralText = centralText;
        plan.registerObserver(this);
        init();
        update();
    }
    
    private void init() {
        dataVychodu = FXCollections.observableArrayList();
        this.setItems(dataVychodu);
        this.setOnMouseClicked(e -> {
            String polozka = this.getSelectionModel().getSelectedItems().toString();
            int konecPolozky = polozka.length() - 1;
            String odpoved = hra.zpracujPrikaz("jdi " + polozka.substring(1, konecPolozky));
            centralText.appendText("\n\n" + odpoved + "\n");
        });
        this.setFixedCellSize(40);
        this.setMaxHeight(164);
        this.setMinHeight(164);
    }
     
    @Override
    public void update() {
        dataVychodu.clear();
        Collection<Prostor> vychody = plan.getAktualniProstor().getVychody();
        for (Prostor vychod : vychody) {
            dataVychodu.add(vychod.getNazev());
        }
    }

    @Override
    public void novaHra(IHra hra) {
        this.plan.removeObserver(this);
        this.hra = hra;
        this.plan = hra.getHerniPlan();
        plan.registerObserver(this);
        update();
    } 
}
