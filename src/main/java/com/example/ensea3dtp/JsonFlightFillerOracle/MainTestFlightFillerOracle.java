package com.example.ensea3dtp.JsonFlightFillerOracle;

import com.example.ensea3dtp.World.World;
import java.io.BufferedReader;
import java.io.FileReader;

public class MainTestFlightFillerOracle {
    public static void main(String[] args) {
        try {
            System.out.println("Initialisation de la classe World...");
            World w = new World("src/main/java/com/example/ensea3dtp/Data/airport-codes_no_comma.csv");

            System.out.println("Lecture du fichier JSON...");
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/example/ensea3dtp/JsonFlightFillerOracle/JsonOrly.txt"));
            String test = br.readLine(); // Lire la première ligne contenant le JSON

            System.out.println("Fichier JSON lu : " + test);

            System.out.println("Initialisation de JsonFlightFillerOracle...");
            JsonFlightFillerOracle jsonFlightFillerOracle = new JsonFlightFillerOracle(test, w);

            System.out.println("Affichage des vols...");
            jsonFlightFillerOracle.displayFlight();

            br.close();
            System.out.println("Fin du programme.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
