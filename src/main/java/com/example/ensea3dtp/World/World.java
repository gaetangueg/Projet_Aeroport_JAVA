package com.example.ensea3dtp.World;


import com.example.ensea3dtp.Aeroport.Aeroport; // Pour la classe Aeroport

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.Math;

public class World {

    protected ArrayList<Aeroport> list;

    // Constructeur
    public World(String fileName) {
        list = new ArrayList<>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();

            while (s != null) {
                s = s.replaceAll("\"", "");
                String[] fields = s.split(",");

                if (fields[1].equals("large_airport")) {
                    try {
                        list.add(new Aeroport(
                                fields[9], fields[2], fields[5],
                                Double.parseDouble(fields[11]),
                                Double.parseDouble(fields[12])
                        ));
                    } catch (NumberFormatException e) {
                        System.out.println("Erreur dans la conversion des coordonnées : " + e.getMessage());
                    }
                }
                s = buf.readLine();
            }

            buf.close();
        } catch (Exception e) {
            System.out.println("Erreur lors de la lecture du fichier : " + fileName);
            e.printStackTrace();
        }
    }

    public double distance(double longitude, double latitude, Aeroport ap) {
        double apLat = ap.getLatitude();
        double apLong = ap.getLongitude();
        return Math.sqrt(Math.pow((apLat - latitude), 2) +
                Math.pow(((apLong - longitude) * Math.cos((apLat + latitude) / 2)), 2)) * 300;
    }

    public Aeroport findNearestAirport(double longitude, double latitude) {
        if (list.isEmpty()) {
            System.out.println("Erreur : La liste des aéroports est vide.");
            return null;
        }

        int idx_min = 0;
        double distance_min = distance(longitude, latitude, list.get(0));

        for (int i = 1; i < list.size(); i++) {
            if (distance(latitude, longitude, list.get(i)) < distance_min) {
                distance_min = distance(latitude, longitude, list.get(i));
                idx_min = i;
            }
        }
        return list.get(idx_min);
    }

    public Aeroport findByCode(String iata_code) {
        if (list.isEmpty()) {
            System.out.println("Aucun aéroport disponible dans la liste.");
            return null;
        }

        for (Aeroport aeroport : list) {
            if (aeroport.getIATA().equals(iata_code)) {
                System.out.println("Aéroport trouvé avec le code IATA : " + iata_code);
                return aeroport;
            }
        }

        System.out.println("Aucun aéroport trouvé avec le code IATA : " + iata_code);
        return null;
    }

    public ArrayList<Aeroport> getList() {
        return list;
    }
}
