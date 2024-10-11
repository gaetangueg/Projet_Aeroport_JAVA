module com.example.ensea3dtp {
    requires javafx.controls; // Permet d'utiliser les composants graphiques de JavaFX
    requires javafx.fxml; // Si tu utilises des fichiers FXML

    // Ouvre le package Travail_Maison pour permettre à JavaFX d'accéder à la classe Interface
    opens com.example.ensea3dtp.Travail_Maison to javafx.graphics;

    // Exporte les packages contenant des classes que tu veux rendre accessibles
    exports com.example.ensea3dtp.Aeroport;
    exports com.example.ensea3dtp.World;
}
