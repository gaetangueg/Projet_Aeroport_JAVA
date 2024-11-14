package com.example.ensea3dtp.Flight;

import java.time.LocalDateTime;

public class Flight {
    private String airLineCode;
    private String airlineName;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int number;

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

