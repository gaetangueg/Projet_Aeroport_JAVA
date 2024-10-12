package com.example.ensea3dtp.Earth;

import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Interface_Earth extends Application {

    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;
    private final double zoomSpeed = 10.0;

    @Override
    public void start(Stage primaryStage) {
        // Création d'un objet Earth
        Earth earth = new Earth();

        // Création de la scène avec un objet Earth
        Scene scene = new Scene(earth, 800, 600);

        // Créer une caméra perspective
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000); // Reculer la caméra pour mieux voir la sphère
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);

        scene.setCamera(camera);

        // Ajout de gestionnaires d'événements pour la souris
        scene.addEventHandler(MouseEvent.ANY, event -> {
            // Clic sur la scène
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                mouseOldX = event.getSceneX();
                mouseOldY = event.getSceneY();
                System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
            }

            // Drag de la souris
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();

                // Déplacer la caméra le long de l'axe Z pour faire un zoom/dézoom
                double deltaY = mousePosY - mouseOldY;

                camera.getTransforms().add(new Translate(0, 0, deltaY * zoomSpeed));
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
            }
        });

        // Configure la fenêtre principale (Stage)
        primaryStage.setTitle("Affichage de la Terre avec zoom possible");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
