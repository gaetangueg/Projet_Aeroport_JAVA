package com.example.ensea3dtp.World;

import com.example.ensea3dtp.Aeroport.Aeroport;

public class MainTestWorld {

    public static void main(String[] args) {
        // Création de l'objet World en passant le chemin du fichier CSV
        World w = new World("src/main/java/com/example/ensea3dtp/Data/airport-codes_no_comma.csv");

        // Affiche le nombre d'aéroports chargés
        System.out.println("Found " + w.getList().size() + " airports.");

        // Trouve l'aéroport le plus proche de Paris (coordonnées : 48.866, 2.316)
        Aeroport paris = w.findNearestAirport(2.316, 48.866);
        if (paris != null) {
            System.out.println("Aéroport le plus proche de Paris : " + paris);

            // Calcul de la distance entre Paris et l'aéroport le plus proche
            double distance = paris.calculDistance(new Aeroport("TEMP", "Paris", "France", 48.866, 2.316));
            System.out.println("Distance entre Paris et l'aéroport le plus proche : " + distance);
        } else {
            System.out.println("Aucun aéroport trouvé à proximité de Paris.");
        }

        // Cherche l'aéroport avec le code IATA CDG (Charles de Gaulle)
        Aeroport cdg = w.findByCode("CDG");
        if (cdg != null) {
            System.out.println("Aéroport avec le code CDG : " + cdg);

            // Calcul de la distance entre Paris et CDG
            double distanceCDG = cdg.calculDistance(new Aeroport("TEMP", "Paris", "France", 48.866, 2.316));
            System.out.println("Distance entre Paris et CDG : " + distanceCDG);
        } else {
            System.out.println("Aucun aéroport trouvé avec le code CDG.");
        }
    }
}
