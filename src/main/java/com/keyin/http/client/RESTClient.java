package com.keyin.http.client;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.City;
import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RESTClient {

    private String serverURL;
    private HttpClient client;
    private ObjectMapper objectMapper;

    public RESTClient(String serverURL) {
        this.serverURL = serverURL;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    // Send HTTP request
    private HttpResponse<String> httpSender(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Response received successfully.");
        } else {
            System.out.println("Error Status Code: " + response.statusCode());
        }
        return response;
    }

    // Get all cities
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/cities"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            cities = objectMapper.readValue(response.body(), new TypeReference<List<City>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve cities: " + e.getMessage());
        }
        return cities;
    }

    // Get all airports
    public List<Airport> getAllAirports() {
        List<Airport> airports = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/airports"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            airports = objectMapper.readValue(response.body(), new TypeReference<List<Airport>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve airports: " + e.getMessage());
        }
        return airports;
    }

    // Get airports by city
    public List<Airport> getAirportsByCity(Long cityId) {
        List<Airport> airports = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/airports/" + cityId + "/airports"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            airports = objectMapper.readValue(response.body(), new TypeReference<List<Airport>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve airports by city: " + e.getMessage());
        }
        return airports;
    }


    // Get all aircraft
    public List<Aircraft> getAllAircraft() {
        List<Aircraft> aircraft = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/aircraft"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            aircraft = objectMapper.readValue(response.body(), new TypeReference<List<Aircraft>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve aircraft: " + e.getMessage());
        }
        return aircraft;
    }

    // Get aircraft by passenger
    public List<Aircraft> getAircraftByPassenger(Long passengerId) {
        List<Aircraft> aircraft = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/passengers/" + passengerId + "/aircraft"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            aircraft = objectMapper.readValue(response.body(), new TypeReference<List<Aircraft>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve aircraft by passenger: " + e.getMessage());
        }
        return aircraft;
    }

    // Get airports used by passengers
    public List<Airport> getAirportsUsedByPassengers(Long passengerId) {
        List<Airport> airports = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serverURL + "/passengers/" + passengerId + "/airports"))
                .build();
        try {
            HttpResponse<String> response = httpSender(request);
            airports = objectMapper.readValue(response.body(), new TypeReference<List<Airport>>() {});
        } catch (IOException | InterruptedException e) {
            System.out.println("Failed to retrieve airports used by passengers: " + e.getMessage());
        }
        return airports;
    }
}
