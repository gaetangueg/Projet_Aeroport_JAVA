package com.example.ensea3dtp.Earth;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import java.io.File;
public class Earth extends Group {

    private Sphere sph; // Sphère représentant la Terre

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

        // Ajoute la sphère au groupe
        this.getChildren().add(sph);
    }
}
