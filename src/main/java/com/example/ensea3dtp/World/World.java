package com.example.ensea3dtp.World;


import com.example.ensea3dtp.Aeroport.Aeroport; // Pour la classe Aeroport

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.Math;

public class World {
    // Liste contenant tous les aéroports
    protected ArrayList<Aeroport> list;

    // Constructeur qui charge les aéroports depuis un fichier CSV
    public World(String fileName) {
        list = new ArrayList<>();
        try {
            // Ouverture et lecture du fichier
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();// Lecture de la première ligne

            while (s != null) {
                // Suppression des guillemets pour faciliter la manipulation
                s = s.replaceAll("\"", "");
                String[] fields = s.split(",");// Découpage des données en colonnes

                // Vérification du type d'aéroport : on ne garde que les grands aéroports ("large_airport")
                if (fields[1].equals("large_airport")) {
                    try {
                        // Ajout de l'aéroport dans la liste après conversion des coordonnées
                        list.add(new Aeroport(
                                fields[9],  // Code IATA
                                fields[2],// Nom de l'aéroport
                                fields[5],// Pays
                                Double.parseDouble(fields[11]),// Latitude
                                Double.parseDouble(fields[12]) // Longitude
                        ));
                    } catch (NumberFormatException e) {
                        // Gestion des erreurs si les coordonnées ne sont pas valides
                        System.out.println("Erreur dans la conversion des coordonnées : " + e.getMessage());
                    }
                }
                s = buf.readLine();// Lecture de la ligne suivante
            }

            buf.close(); // Fermeture du fichier
        } catch (Exception e) {
            // Gestion des erreurs lors de la lecture du fichier
            System.out.println("Erreur lors de la lecture du fichier : " + fileName);
            e.printStackTrace();
        }
    }

    // Calcul de la distance entre un point donné (longitude, latitude) et un aéroport
    public double distance(double longitude, double latitude, Aeroport ap) {
        double apLat = ap.getLatitude();// Latitude de l'aéroport
        double apLong = ap.getLongitude(); // Longitude de l'aéroport

        // Calcul de la distance avec une approximation prenant en compte la courbure de la Terre
        return Math.sqrt(Math.pow((apLat - latitude), 2) +
                Math.pow(((apLong - longitude) * Math.cos((apLat + latitude) / 2)), 2)) * 300;
    }


    // Trouve l'aéroport le plus proche d'un point donné (longitude, latitude)
    public Aeroport findNearestAirport(double longitude, double latitude) {
        if (list.isEmpty()) {
            // Vérification si la liste des aéroports est vide
            System.out.println("Erreur : La liste des aéroports est vide.");
            return null;
        }

        // Initialisation avec le premier aéroport de la liste
        int idx_min = 0;
        double distance_min = distance(longitude, latitude, list.get(0));

        // Parcours de la liste pour trouver l'aéroport avec la distance minimale
        for (int i = 1; i < list.size(); i++) {
            if (distance(latitude, longitude, list.get(i)) < distance_min) {
                distance_min = distance(latitude, longitude, list.get(i));// Mise à jour de la distance minimale
                idx_min = i;// Mise à jour de l'index de l'aéroport le plus proche
            }
        }
        return list.get(idx_min);// Retourne l'aéroport le plus proche
    }

    // Recherche d'un aéroport par son code IATA
    public Aeroport findByCode(String iata_code) {
        if (list.isEmpty()) {
            // Vérification si la liste des aéroports est vide
            System.out.println("Aucun aéroport disponible dans la liste.");
            return null;
        }

        // Parcours de la liste pour trouver l'aéroport correspondant au code IATA
        for (Aeroport aeroport : list) {
            if (aeroport.getIATA().equals(iata_code)) {
                System.out.println("Aéroport trouvé avec le code IATA : " + iata_code);
                return aeroport;// Retourne l'aéroport trouvé
            }
        }
        // Si aucun aéroport correspondant n'est trouvé
        System.out.println("Aucun aéroport trouvé avec le code IATA : " + iata_code);
        return null;
    }

    // Retourne la liste complète des aéroports
    public ArrayList<Aeroport> getList() {
        return list;
    }
}
