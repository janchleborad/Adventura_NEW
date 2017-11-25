/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import GUI.Mapa;
import GUI.MenuLista;
import GUI.PanelVychodu;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;
import GUI.*;
import javafx.scene.control.ContentDisplay;

/**
 *
 * @author Honysek
 */
public class Main extends Application {
    
    private static TextArea centralText;
    private static IHra hra;
    private TextField zadejPrikazTextArea;
    private Mapa mapa;
    private MenuLista menuLista;
    private Stage stage;
    private PanelVychodu panelVychodu;
    private GUI.Batoh batoh;
    private VeciVProstoru veciVProstoru;
    
    public void setHra(IHra hra) {
        this.hra = hra;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        
        hra = new Hra();
        
        mapa = new Mapa(hra);
        menuLista = new MenuLista(hra, this);
        
        BorderPane borderPane = new BorderPane();
        
        //Text s průběhem hry
        centralText = new TextArea();
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setMaxHeight(750);
        borderPane.setCenter(centralText);
        
        this.panelVychodu = new PanelVychodu(hra, centralText);
        this.batoh = new GUI.Batoh(hra, centralText);
        this.veciVProstoru = new VeciVProstoru(hra, centralText);
        
        //Label s textem zadej příkaz
        Label zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //Text area, dokteré píšeme příkazy
        zadejPrikazTextArea = new TextField("...");
        zadejPrikazTextArea.setOnAction(e -> {
            String vstupniPrikaz = zadejPrikazTextArea.getText ();
            String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
            
            centralText.appendText("\n" + vstupniPrikaz + "\n");
            centralText.appendText("\n" + odpovedHry + "\n");
            zadejPrikazTextArea.setText("");
            
            if(hra.konecHry()) {
                zadejPrikazTextArea.setEditable(false);
                centralText.appendText(hra.vratEpilog());
            }  
        });
        
        //Dolní lišta s elementy
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel, zadejPrikazTextArea);
        
        BorderPane pravaLista = new BorderPane();
        BorderPane horniBP = new BorderPane();
        horniBP.setCenter(panelVychodu);
        horniBP.setMaxHeight(100.0);
        Label panelVychoduLabel = new Label("Panel východů");
        panelVychoduLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        horniBP.setTop(panelVychoduLabel);

        BorderPane centerBP = new BorderPane();
        centerBP.setCenter(batoh);
        centerBP.setMaxHeight(200.0);
        Label batohLabel = new Label("Batoh");
        batohLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        centerBP.setTop(batohLabel);

        BorderPane dolniBP = new BorderPane();
        dolniBP.setCenter(veciVProstoru);
        dolniBP.setMaxHeight(200.0);
        Label veciVProstoruLabel = new Label("Věci v prostoru");
        veciVProstoruLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dolniBP.setTop(veciVProstoruLabel);

        pravaLista.setTop(horniBP);
        pravaLista.setCenter(centerBP);
        pravaLista.setBottom(dolniBP);
        
        borderPane.setLeft(mapa);
        borderPane.setBottom(dolniLista);
        borderPane.setRight(pravaLista);
        borderPane.setTop (menuLista);

        Scene scene = new Scene(borderPane, 1200, 750);
        primaryStage.setTitle("Adventura");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    
    public TextArea getCentralText() {
        return centralText;
    }

    public Mapa getMapa() {
        return mapa;
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args); //Zavolá metodu Start
        } 
        else {
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            } else {
                System.out.println("Neplatný parametr");
                System.exit(1);
            }
        }
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }
    
    public static IHra getHra() {
        return hra;
    }
    
    public static TextArea getTextArea() {
        return centralText;
    }
}
