package com.example.ensea3dtp.Flight;

import java.time.LocalDateTime;// Import pour utiliser les objets de date et heure

public class Flight {
    private String airLineCode;// Code de la compagnie aérienne
    private String airlineName;// Nom complet de la compagnie aérienne
    private LocalDateTime arrivalTime;// Heure d'arrivée prévue
    private LocalDateTime departureTime;// Heure de départ prévue
    private int number;// Numéro du vol

    // Constructeur
    public Flight(String airLineCode, String airlineName, LocalDateTime departureTime, LocalDateTime arrivalTime, int number) {
        this.airLineCode = airLineCode;
        this.airlineName = airlineName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.number = number;
    }

    // Méthode getArrival()
    public LocalDateTime getArrival() {
        return arrivalTime;
    }

    // Méthode getDeparture()
    public LocalDateTime getDeparture() {
        return departureTime;
    }

    // Surcharge de la méthode toString pour afficher les informations du vol
    @Override
    public String toString() {
        return "Flight{" +
                "airLineCode='" + airLineCode + '\'' +
                ", airlineName='" + airlineName + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", number=" + number +
                '}';
    }
}

