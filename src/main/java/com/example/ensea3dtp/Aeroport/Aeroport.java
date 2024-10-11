package com.example.ensea3dtp.Aeroport;


public class Aeroport {
    private String IATA;
    private String name;
    private String country;
    private double latitude;
    private double longitude;

    // Constructeur
    public Aeroport(String IATA, String name, String country, double latitude, double longitude) {
        this.IATA = IATA;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters pour accéder aux attributs
    public String getIATA() {
        return IATA;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // Méthode calculDistance qui prend un autre objet Aeroport en paramètre
    public double calculDistance(Aeroport a) {
        double lat1 = this.latitude;
        double lon1 = this.longitude;
        double lat2 = a.getLatitude();
        double lon2 = a.getLongitude();

        // Calcul de la norme (distance simplifiée)
        double distance = Math.pow(lat2 - lat1, 2) +
                          Math.pow((lon2 - lon1) * Math.cos(Math.toRadians((lat2 + lat1) / 2)), 2);

        return distance;
    }


    // Surcharge de la méthode toString pour afficher les informations de l'aéroport
    @Override
    public String toString() {
        return "Aeroport{" +
                "IATA='" + IATA + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
