package com.example.ensea3dtp.Aeroport;

public class MainTestAeroport {

    public static void main(String[] args) {
        // Test de la classe Aeroport avec un objet
        Aeroport orly = new Aeroport("ORY", "Orly", "France", 48.726, 2.365);
        
        // Affichage des informations de l'aéroport pour vérifier que tout fonctionne
        System.out.println(orly);

        // Création d'un autre aéroport pour tester
        Aeroport cdg = new Aeroport("CDG", "Charles de Gaulle", "France", 49.0097, 2.5479);
        
        // Affichage des informations du second aéroport
        System.out.println(cdg);
    }
}
