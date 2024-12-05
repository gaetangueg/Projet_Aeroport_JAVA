package com.example.ensea3dtp.Earth;

import com.example.ensea3dtp.World.World;
import com.example.ensea3dtp.Aeroport.Aeroport;
import com.example.ensea3dtp.ApiFlightManager.ApiFlightManager;
import com.example.ensea3dtp.JsonFlightFillerOracle.JsonFlightFillerOracle;
import com.example.ensea3dtp.Flight.Flight;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Interface_Earth extends Application {

    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;
    private final double zoomSpeed = 10.0;

    // Crée un objet World pour contenir les aéroports
    private World world;

    @Override
    public void start(Stage primaryStage) {
        // Charger la liste des aéroports depuis le CSV
        world = new World("src/main/java/com/example/ensea3dtp/Data/airport-codes_no_comma.csv");

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
            // Clic gauche pour enregistrer la position de la souris
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED && event.getButton() == MouseButton.PRIMARY) {
                mouseOldX = event.getSceneX();
                mouseOldY = event.getSceneY();
                System.out.println("Clicked on : (" + event.getSceneX() + ", " + event.getSceneY() + ")");
            }

            // Déplacement avec la souris pour zoomer/dézoomer
            if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                mousePosX = event.getSceneX();
                mousePosY = event.getSceneY();

                // Zoom ou dézoom avec le mouvement vertical
                double deltaY = mousePosY - mouseOldY;

                camera.getTransforms().add(new Translate(0, 0, deltaY * zoomSpeed));
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
            }

            // Clic droit pour récupérer les coordonnées avec PickResult
            if (event.getButton() == MouseButton.SECONDARY && event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                PickResult pickResult = event.getPickResult();

                // Vérifier si le clic a eu lieu sur un objet
                if (pickResult.getIntersectedNode() != null) {
                    // Récupérer les coordonnées de texture (X et Y)
                    Point2D textureCoordinates = pickResult.getIntersectedTexCoord();

                    if (textureCoordinates != null) {
                        double x = textureCoordinates.getX(); // Coordonnée X (normalisée entre 0 et 1)
                        double y = textureCoordinates.getY(); // Coordonnée Y (normalisée entre 0 et 1)

                        // Conversion des coordonnées en longitude et latitude
                        double longitude = 360 * (x - 0.5);
                        //double latitude = 2 * Math.toDegrees(Math.atan(Math.exp((0.5 - y) / 0.2678))) - 90;
                        double latitude = 180* (0.5 - y);
                                System.out.println("Latitude : " + latitude + ", Longitude : " + longitude);

                        // Recherche de l'aéroport le plus proche
                        Aeroport nearestAirport = world.findNearestAirport(longitude, latitude);
                        if (nearestAirport != null) {
                            // Affichage de l'aéroport le plus proche dans la console
                            System.out.println("Aéroport le plus proche : " + nearestAirport);
                            // Afficher une sphère rouge sur l'aéroport le plus proche
                            earth.displayRedSphere(nearestAirport);

                            // Récupération des données de l'API
                            ApiFlightManager apiManager = new ApiFlightManager();
                            String jsonResponse = apiManager.fetchFlightData(nearestAirport);

                            System.out.println("Réponse JSON brute : " + jsonResponse);

                            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                                // Analyse et remplissage des vols dynamiquement
                                JsonFlightFillerOracle filler = new JsonFlightFillerOracle("", world);
                                filler.fillFlightsFromApi(jsonResponse); // Appel explicite à fillFlightsFromApi

                                earth.clearYellowBalls(); // Nettoyer les anciens points jaunes

                                // Affichage des boules jaunes pour les aéroports de départ
                                for (Flight flight : filler.getList()) {
                                    System.out.println("Traitement du vol : " + flight);
                                    Aeroport departure = world.findByCode(flight.getDepartureIATA());
                                    if (departure != null) {
                                        System.out.println("Aéroport trouvé pour le code IATA " + flight.getDepartureIATA() + " : " + departure);
                                        earth.displayYellowBall(departure);
                                    } else {
                                        System.out.println("Aucun aéroport trouvé pour le code IATA : " + flight.getDepartureIATA());
                                    }
                                }
                            } else {
                                System.out.println("Erreur : Impossible de récupérer les données de l'API.");
                            }
                        } else {
                            System.out.println("Aucun aéroport trouvé à proximité.");
                        }
                    } else {
                        System.out.println("Erreur : Impossible de récupérer les coordonnées de texture.");
                    }
                } else {
                    System.out.println("Erreur : Aucun point d'intersection trouvé.");
                }
            }
        });

        // Configure la fenêtre principale (Stage)
        primaryStage.setTitle("Affichage de la Terre avec zoom et clic droit");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}