package com.example.ensea3dtp.Travail_Maison;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Interface extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Titre de la fenêtre
        primaryStage.setTitle("Hello World");

        // Création du root node (Group)
        Group root = new Group();

        // Création d'un label (texte)
        Label label = new Label("Bienvenue sur JavaFX!");

        // Positionnement du texte
        label.setLayoutX(250);  // Position en X
        label.setLayoutY(150);  // Position en Y

        // Création d'un bouton
        Button button = new Button("Cliquez-moi");
        button.setLayoutX(250);  // Position en X
        button.setLayoutY(200);  // Position en Y

        // Action lorsque le bouton est cliqué
        button.setOnAction(event -> {
            System.out.println("Le bouton a été cliqué !");
        });

        // Ajout du label et du bouton au root
        root.getChildren().addAll(label, button);

        // Création d'un Pane pour contenir les éléments
        Pane pane = new Pane(root);

        // Création de la scène
        Scene theScene = new Scene(pane, 600, 400);

        // Ajout de la scène à la fenêtre
        primaryStage.setScene(theScene);

        // Affichage de la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
