package com.example.ensea3dtp.JsonFlightFillerOracle;

import com.example.ensea3dtp.Flight.Flight; // Pour la classe Flight
import com.example.ensea3dtp.World.World;
import com.example.ensea3dtp.ApiFlightManager.ApiFlightManager;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.StringReader;

public class JsonFlightFillerOracle {
    private ArrayList<Flight> list = new ArrayList<>();
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    // Constructeur pour analyser le JSON et remplir la liste de vols
    public JsonFlightFillerOracle(String jsonString, World w) {
        try {
            // Lecture du JSON en tant que InputStream
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();

            // Extraction du tableau "data" contenant les informations des vols
            JsonArray results = obj.getJsonArray("data");

            // Parcours de chaque objet JSON représentant un vol
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    // Extraction des informations nécessaires pour créer un vol
                    String airLineCode = result.getJsonObject("flight").getString("iata", null);
                    String airlineName = result.getJsonObject("airline").getString("name", null);
                    int number = result.getJsonObject("flight").getInt("number", 0);
                    String departureIATA = result.getJsonObject("departure").getString("iata", null);

                    // Conversion des horaires en LocalDateTime
                    LocalDateTime departureTime = LocalDateTime.parse(
                            result.getJsonObject("departure").getString("scheduled", null), formatter);
                    LocalDateTime arrivalTime = LocalDateTime.parse(
                            result.getJsonObject("arrival").getString("scheduled", null), formatter);

                    // Création de l'objet Flight et ajout à la liste
                    Flight flight = new Flight(airLineCode, airlineName, departureTime, arrivalTime, number, departureIATA);
                    list.add(flight);

                } catch (Exception e) {
                    e.printStackTrace(); // Gestion des erreurs pour un vol spécifique
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestion des erreurs globales
        }
    }

    // Méthode pour remplir les vols à partir d'une réponse API
    public void fillFlightsFromApi(String jsonResponse) {
        try {
            System.out.println("Parsing du JSON...");
            JsonReader rdr = Json.createReader(new StringReader(jsonResponse));
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");

            if (results != null) {
                for (JsonObject flightData : results.getValuesAs(JsonObject.class)) {
                    try {
                        String airLineCode = flightData.getJsonObject("flight").getString("iata", null);
                        String airlineName = flightData.getJsonObject("airline").getString("name", null);
                        int number = flightData.getJsonObject("flight").getInt("number", 0);
                        String departureIATA = flightData.getJsonObject("departure").getString("iata", null);

                        LocalDateTime departureTime = LocalDateTime.parse(
                                flightData.getJsonObject("departure").getString("scheduled", null), formatter);
                        LocalDateTime arrivalTime = LocalDateTime.parse(
                                flightData.getJsonObject("arrival").getString("scheduled", null), formatter);

                        Flight flight = new Flight(airLineCode, airlineName, departureTime, arrivalTime, number, departureIATA);
                        list.add(flight);
                        System.out.println("Vol ajouté : " + flight);
                    } catch (Exception e) {
                        System.out.println("Erreur lors du traitement d'un vol : " + e.getMessage());
                    }
                }
            } else {
                System.out.println("Erreur : Pas de données trouvées dans 'data'.");
            }
        } catch (Exception e) {
            System.out.println("Erreur globale lors de l'analyse du JSON : " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Méthode pour afficher les informations de chaque vol
    public void displayFlight() {
        for (Flight flight : list) {
            System.out.println(flight.toString());
        }
    }

    // Méthode pour obtenir la liste des vols
    public ArrayList<Flight> getList() {
        return list;
    }
}
