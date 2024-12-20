package com.example.ensea3dtp.Flight;

import java.time.LocalDateTime;

public class MainTestFlight {
    public static void main(String[] args) {
        // Création d'un objet Flight avec des données de test
        LocalDateTime departureTime = LocalDateTime.of(2024, 11, 15, 14, 30);
        LocalDateTime arrivalTime = LocalDateTime.of(2024, 11, 15, 18, 45);

        // Ajout du code IATA de l'aéroport de départ
        String departureIATA = "CDG"; // Exemple : Charles de Gaulle

        // Création d'un vol avec le nouveau constructeur
        Flight flight = new Flight("AF", "Air France", departureTime, arrivalTime, 123, departureIATA);

        // Affichage des informations du vol avec toString()
        System.out.println("Informations du vol (toString):");
        System.out.println(flight);

        // Test des méthodes getArrival(), getDeparture() et getDepartureIATA()
        System.out.println("\nTest des méthodes getArrival(), getDeparture(), et getDepartureIATA():");
        System.out.println("Heure d'arrivée : " + flight.getArrival());
        System.out.println("Heure de départ : " + flight.getDeparture());
        System.out.println("Code IATA de l'aéroport de départ : " + flight.getDepartureIATA());
    }
}
