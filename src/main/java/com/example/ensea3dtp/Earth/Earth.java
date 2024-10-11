package com.example.ensea3dtp.Earth;

import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

public class Earth extends Group {

    private Sphere sph; // Sphère représentant la Terre

    // Constructeur
    public Earth() {
        // Crée une sphère de rayon 300 pixels
        sph = new Sphere(300);

        // Ajoute un matériau (couleur) pour voir la sphère
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLUE);
        sph.setMaterial(material);

        // Ajoute la sphère au groupe
        this.getChildren().add(sph);

        // Ajoute une source de lumière pour illuminer la sphère
        PointLight light = new PointLight();
        light.setTranslateX(-100);
        light.setTranslateY(-100);
        light.setTranslateZ(-800);
        this.getChildren().add(light);
    }
}
