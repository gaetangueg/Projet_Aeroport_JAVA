package com.example.ensea3dtp.Earth;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import com.example.ensea3dtp.Aeroport.Aeroport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Earth extends Group {

    private Sphere sph; // Sphère représentant la Terre
    private Group earthGroup; // Groupe contenant la sphère Terre et ses enfants (comme la sphère rouge)
    private Rotate ry; // Rotation autour de l'axe Y
    private Sphere currentRedSphere; // Sphère rouge actuellement affichée
    private final double rayonDeTerre = 300;

    public Earth() {
        // Initialiser le groupe qui contiendra la Terre
        earthGroup = new Group();

        // Crée une sphère de rayon 300 pixels
        sph = new Sphere(300);

        // Utilisation de File pour obtenir le chemin absolu de l'image
        String imagePath = new File("src/main/java/com/example/ensea3dtp/Data/earth_lights_4800.png").toURI().toString();

        // Charge l'image depuis le système de fichiers
        Image earthImage = new Image(imagePath);

        // Vérifie si l'image a été correctement chargée
        if (earthImage.isError()) {
            System.out.println("Erreur : l'image n'a pas été trouvée.");
            return;
        }

        // Crée un matériau PhongMaterial et applique la texture de la Terre
        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(earthImage);

        // Applique le matériau à la sphère
        sph.setMaterial(earthMaterial);

        // Ajoute la sphère Terre au groupe
        earthGroup.getChildren().add(sph);

        // Initialiser l'objet Rotate pour la rotation autour de l'axe Y
        ry = new Rotate(0, Rotate.Y_AXIS);
        earthGroup.getTransforms().add(ry);

        // Ajoute le groupe au groupe principal
        this.getChildren().add(earthGroup);

        // Ajout de l'animation de rotation
        addRotationAnimation();
    }

    // Méthode pour ajouter une animation de rotation continue
    private void addRotationAnimation() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                // Calcule l'angle de rotation pour faire un tour en 15 secondes
                double angle = (time / 1_000_000_000.0) * (360.0 / 15.0); // Un tour en 15 secondes
                ry.setAngle(angle % 360); // Réduit l'angle entre 0 et 360 degrés
            }
        };
        animationTimer.start();
    }

    // Crée une sphère colorée à l'emplacement de l'aéroport
    public Sphere createSphere(Aeroport a, Color color) {
        // Récupère la longitude et la latitude de l'aéroport
        double longitude = a.getLongitude();
        double latitude = a.getLatitude();

        // Crée une petite sphère pour représenter l'aéroport
        Sphere sphere = new Sphere(5); // Rayon de la sphère
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        sphere.setMaterial(material);

        // Place la sphère sur le rayon de la Terre, initialement sur l'axe Z négatif
        sphere.setTranslateZ(-sph.getRadius());

        // Rotation pour ajuster la position en fonction de la longitude
        Rotate rPhi = new Rotate(
                -latitude, // Angle de rotation basé sur la latitude
                -sphere.getTranslateX(), // Pivot X
                -sphere.getTranslateY(), // Pivot Y
                -sphere.getTranslateZ(), // Pivot Z
                Rotate.Y_AXIS // Axe de rotation : Y
        );
        sphere.getTransforms().add(rPhi);

        // Rotation pour ajuster la position en fonction de la latitude
        Rotate rTheta = new Rotate(
                -longitude * 60.0 / 90.0, // Ajustement de l'angle pour la longitude
                -sphere.getTranslateX(), // Pivot X
                -sphere.getTranslateY(), // Pivot Y
                -sphere.getTranslateZ(), // Pivot Z
                Rotate.X_AXIS // Axe de rotation : X
        );
        sphere.getTransforms().add(rTheta);

        return sphere;
    }

    // Méthode pour afficher une sphère rouge à l'emplacement de l'aéroport
    public void displayRedSphere(Aeroport a) {
        // Si une sphère rouge est déjà présente, la supprimer
        if (currentRedSphere != null) {
            earthGroup.getChildren().remove(currentRedSphere);
        }

        // Crée une nouvelle sphère rouge
        currentRedSphere = createSphere(a, Color.RED);
        earthGroup.getChildren().add(currentRedSphere); // Ajouter la sphère rouge au groupe Terre
    }

    private final List<Sphere> yellowSpheres = new ArrayList<>();

    public void clearYellowBalls() {
        for (Sphere sphere : yellowSpheres) {
            earthGroup.getChildren().remove(sphere);
        }
        yellowSpheres.clear();
    }

    public void displayYellowBall(Aeroport aeroport) {
        Sphere yellowSphere = createSphere(aeroport, Color.YELLOW);
        earthGroup.getChildren().add(yellowSphere);
        yellowSpheres.add(yellowSphere);
    }

}
