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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.VBox;

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
        borderPane.setCenter(centralText);
        
        this.panelVychodu = new PanelVychodu(hra, centralText);
        this.batoh = new GUI.Batoh(hra, centralText);
        this.veciVProstoru = new VeciVProstoru(hra, centralText);
        
        //Label s textem zadej příkaz
        Label zadejPrikazLabel = new Label("Zadej příkaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        //Text area, dokteré píšeme příkazy
        zadejPrikazTextArea = new TextField();
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
        
        //Pravá lišta s elementy
        FlowPane pravaLista = new FlowPane(Orientation.VERTICAL);
        pravaLista.setStyle("-fx-background-color: #FFFFFF");
        BorderPane horniBP = new BorderPane();
        horniBP.setCenter(panelVychodu);
        horniBP.setMaxHeight(194);
        Label panelVychoduLabel = new Label("Panel východů");
        panelVychoduLabel.setMinHeight(30);
        panelVychoduLabel.setMaxHeight(30);
        panelVychoduLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        horniBP.setTop(panelVychoduLabel);

        BorderPane centerBP = new BorderPane();
        centerBP.setCenter(batoh);
        centerBP.setMinHeight(240);
        centerBP.setMaxHeight(240);
        Label batohLabel = new Label("Batoh");
        batohLabel.setMinHeight(30);
        batohLabel.setMaxHeight(30);
        batohLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        centerBP.setTop(batohLabel);

        BorderPane dolniBP = new BorderPane();
        dolniBP.setCenter(veciVProstoru);
        Label veciVProstoruLabel = new Label("Věci v prostoru");
        veciVProstoruLabel.setMinHeight(30);
        veciVProstoruLabel.setMaxHeight(30);
        veciVProstoruLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dolniBP.setTop(veciVProstoruLabel);

        pravaLista.getChildren().addAll(horniBP, centerBP, dolniBP);
        
        VBox mapaPane = new VBox();
        mapaPane.getChildren().add(mapa);
        mapaPane.setAlignment(Pos.CENTER);
        mapaPane.setStyle("-fx-background-color: #FFFFFF");
        mapaPane.setMargin(mapa, new Insets(10, 25, 10, 25));
        
        borderPane.setLeft(mapaPane);
        borderPane.setBottom(dolniLista);
        borderPane.setRight(pravaLista);
        borderPane.setTop (menuLista);

        Scene scene = new Scene(borderPane, 1250, 800);
        primaryStage.setTitle("Adventura");
        
        primaryStage.setScene(scene);
        primaryStage.show();
        zadejPrikazTextArea.requestFocus();
    }

    public PanelVychodu getPanelVychodu() {
        return panelVychodu;
    }

    public GUI.Batoh getBatoh() {
        return batoh;
    }

    public VeciVProstoru getVeciVProstoru() {
        return veciVProstoru;
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
