package com.example.ensea3dtp.JsonFlightFillerOracle;
import com.example.ensea3dtp.Flight.Flight; // Pour la classe Flight
import com.example.ensea3dtp.World.World;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class JsonFlightFillerOracle {
    private ArrayList<Flight> list = new ArrayList<>();

    // Constructeur pour analyser le JSON et remplir la liste de vols
    public JsonFlightFillerOracle(String jsonString, World w) {

/*        try {
            // Lecture du JSON en tant que InputStream
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();

            // Extraction du tableau "data" contenant les informations des vols
            JsonArray results = obj.getJsonArray("data");

            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        }
}
