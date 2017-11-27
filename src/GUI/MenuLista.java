/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;

/**
 *
 * @author Honysek
 */
public class MenuLista extends MenuBar {
    
    private IHra hra;
    private Main main;
    
    public MenuLista(IHra hra, Main main) {
        this.hra = hra;
        this.main = main;
        init();
    }
    
    private void init() {
    
        Menu novySoubor = new Menu("Adventura");
        Menu napoveda = new Menu("Help");
        
        MenuItem novaHra = new MenuItem("Nová hra", new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png"))));
        novaHra.setAccelerator(KeyCombination.keyCombination("CTRL+N"));
        
        MenuItem konecHry = new MenuItem("Konec hry", new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/stop.png"))));
        konecHry.setAccelerator(KeyCombination.keyCombination("CTRL+E"));
                
        novySoubor.getItems().addAll(novaHra, konecHry);
        
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem napovedaItem = new MenuItem("Nápověda");
        
        napoveda.getItems().addAll(oProgramu, napovedaItem);
        
        this.getMenus().addAll(novySoubor, napoveda);
        
        konecHry.setOnAction(e -> {
            System.exit(0);
        });
        
        novaHra.setOnAction(e -> {
            hra = new Hra();
            main.getMapa().novaHra(hra);
            main.getBatoh().novaHra(hra);
            main.getPanelVychodu().novaHra(hra);
            main.getVeciVProstoru().novaHra(hra);
            main.setHra(hra);
            main.getCentralText().setText(hra.vratUvitani());
        });
    
        oProgramu.setOnAction(e -> {
            Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
            
            oProgramuAlert.setTitle("O programu");
            oProgramuAlert.setHeaderText("Advenentura | 4IT115 | ZS 2017/2018 \n" +
                                         "Autor: Jan Chleborád \n" +
                                         "Verze: 2.0 (FINAL)");
            oProgramuAlert.setContentText("Aplikace je adventurou v textovým uživatelským rozhraním, která byla" +
                                          "zprácována v rámci předmětů 4IT101 a 4IT115 na Vysoké škole ekonomické. " +
                                          "Jednotlivé funkce a podrobnosti \n" +
                                          "jsou k nalezení na odkazu níže. \n" +
                                          "http://java.vse.cz/4it115/ZadaniPrvniUlohy");
            oProgramuAlert.initOwner(main.getStage());
            
            oProgramuAlert.showAndWait();
        });
        
        napovedaItem.setOnAction(e -> {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            
            WebView webView = new WebView();
            webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 625, 400));
            stage.show();
        });
    }
}