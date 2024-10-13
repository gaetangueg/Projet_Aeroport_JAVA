package com.example.ensea3dtp.Earth;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import com.example.ensea3dtp.Aeroport.Aeroport;

import java.io.File;

public class Earth extends Group {

    private Sphere sph; // Sphère représentant la Terre
    private Rotate ry; // Rotation autour de l'axe Y
    private Sphere currentRedSphere; // Sphère rouge actuellement affichée
    private final double rayonDeTerre = 300;

    public Earth() {
        // Crée une sphère de rayon 300 pixels
        sph = new Sphere(300);

        // Utilisation de File pour obtenir le chemin absolu de l'image
        String imagePath = new File("src/main/java/com/example/ensea3dtp/Data/earth_lights_4800.png").toURI().toString();

        // Charge l'image depuis le système de fichiers
        Image earthImage = new Image(imagePath);

        if (earthImage.isError()) {
            System.out.println("Erreur : l'image n'a pas été trouvée.");
            return;
        }

        // Crée un matériau PhongMaterial et applique la texture de la Terre
        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(earthImage);

        // Applique le matériau à la sphère
        sph.setMaterial(earthMaterial);

        // Initialiser l'objet Rotate pour la rotation autour de l'axe Y
        ry = new Rotate(0, Rotate.Y_AXIS);
        sph.getTransforms().add(ry);

        // Ajoute la sphère au groupe
        this.getChildren().add(sph);

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

    // Crée une petite sphère rouge à l'emplacement de l'aéroport
    public Sphere createSphere(Aeroport a, Color color) {
        double longitude = a.getLongitude();
        double latitude = a.getLatitude();
        double cos = Math.cos(Math.toRadians(longitude - 13));

        // Calcul des coordonnées 3D
        double x = rayonDeTerre * (cos * Math.sin(Math.toRadians(latitude)));
        double y = -rayonDeTerre * (Math.sin(Math.toRadians(longitude - 13)));
        double z = -rayonDeTerre * (cos * Math.cos(Math.toRadians(latitude)));

        Sphere sphere = new Sphere(5); // Créer une sphère de rayon 5
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(color);
        sphere.setMaterial(material);

        // Appliquer les transformations à la sphère pour la positionner sur la Terre
        sphere.getTransforms().add(new Translate(x, y, z));

        // Ajoutez un AnimationTimer pour faire tourner la Terre (et la sphère rouge)
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long time) {
                // Calcule l'angle de rotation pour faire un tour en 15 secondes
                double angle = (time / 1_000_000_000.0) * (360.0 / 15.0); // Un tour en 15 secondes

                // Applique une rotation sur l'axe Y à la sphère rouge pour qu'elle tourne en suivant l'aeroport
                currentRedSphere.getTransforms().setAll(
                        new Rotate(angle % 360, Rotate.Y_AXIS), // Rotation sur l'axe Y
                        new Translate(x, y, z) // Positionner à nouveau à la bonne place
                );
            }
        };
        animationTimer.start();

        return sphere;
    }




    // Méthode pour afficher une sphère rouge à l'emplacement de l'aéroport
    public void displayRedSphere(Aeroport a) {
        // Si une sphère rouge est déjà présente, la supprimer
        if (currentRedSphere != null) {
            this.getChildren().remove(currentRedSphere);
        }

        // Crée une nouvelle sphère rouge
        currentRedSphere = createSphere(a, Color.RED);
        this.getChildren().add(currentRedSphere); // Ajouter la nouvelle sphère rouge
    }
}
