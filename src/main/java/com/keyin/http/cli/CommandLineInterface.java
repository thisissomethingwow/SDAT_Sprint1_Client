package com.keyin.http.cli;

import com.keyin.http.client.RESTClient;
import com.keyin.domain.City;
import com.keyin.domain.Airport;
import com.keyin.domain.Aircraft;

import java.util.List;
import java.util.Scanner;

public class CommandLineInterface {

    private final RESTClient restClient;

    public CommandLineInterface() {
        this.restClient = new RESTClient("http://localhost:8080");  // Adjust your server URL as needed
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Flight CLI Application!");

        while (true) {
            System.out.println("\nAvailable commands:");
            System.out.println("1. List all cities");
            System.out.println("2. List all airports");
            System.out.println("3. List airports by city");
            System.out.println("4. List all aircraft");
            System.out.println("5. List aircraft by passenger");
            System.out.println("6. List airports used by passengers");
            System.out.println("7. Exit");
            System.out.print("\nEnter your command: ");

            int command = scanner.nextInt();

            switch (command) {
                case 1:
                    generateCityReport();
                    break;
                case 2:
                    generateAirportReport();
                    break;
                case 3:
                    System.out.print("Enter City ID: ");
                    Long cityId = scanner.nextLong();
                    generateAirportReportByCity(cityId);
                    break;
                case 4:
                    generateAircraftReport();
                    break;
                case 5:
                    System.out.print("Enter Passenger ID: ");
                    Long passengerId = scanner.nextLong();
                    generateAircraftReportByPassenger(passengerId);
                    break;
                case 6:
                    System.out.print("Enter Passenger ID: ");
                    Long passengerIdForAirports = scanner.nextLong();
                    generateAirportsUsedByPassengers(passengerIdForAirports);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command! Please enter a valid option.");
            }
        }
    }

    private void generateCityReport() {
        List<City> cities = restClient.getAllCities();
        if (cities == null || cities.isEmpty()) {
            System.out.println("No cities found.");
            return;
        }

        StringBuilder report = new StringBuilder("City Report:\n");
        for (City city : cities) {
            report.append("City ID: ").append(city.getId())
                    .append(", Name: ").append(city.getName())
                    .append(", Province: ").append(city.getProvince())
                    .append(", Population: ").append(city.getPopulation())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    private void generateAirportReport() {
        List<Airport> airports = restClient.getAllAirports();
        if (airports == null || airports.isEmpty()) {
            System.out.println("No airports found.");
            return;
        }

        StringBuilder report = new StringBuilder("Airport Report:\n");
        for (Airport airport : airports) {
            report.append("Airport ID: ").append(airport.getId())
                    .append(", Name: ").append(airport.getName())
                    .append(", Code: ").append(airport.getCode())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    private void generateAirportReportByCity(Long cityId) {
        List<Airport> airports = restClient.getAirportsByCity(cityId);
        if (airports == null || airports.isEmpty()) {
            System.out.println("No airports found for city ID: " + cityId);
            return;
        }

        StringBuilder report = new StringBuilder("Airports in City ID: " + cityId + "\n");
        for (Airport airport : airports) {
            report.append("Airport ID: ").append(airport.getId())
                    .append(", Name: ").append(airport.getName())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    private void generateAircraftReport() {
        List<Aircraft> aircraft = restClient.getAllAircraft();
        if (aircraft == null || aircraft.isEmpty()) {
            System.out.println("No aircraft found.");
            return;
        }

        StringBuilder report = new StringBuilder("Aircraft Report:\n");
        for (Aircraft plane : aircraft) {
            report.append("Aircraft ID: ").append(plane.getId())
                    .append(", Airline: ").append(plane.getAirlineName())
                    .append(", Type: ").append(plane.getModel())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    private void generateAircraftReportByPassenger(Long passengerId) {
        List<Aircraft> aircraft = restClient.getAircraftByPassenger(passengerId);
        if (aircraft == null || aircraft.isEmpty()) {
            System.out.println("No aircraft found for passenger ID: " + passengerId);
            return;
        }

        StringBuilder report = new StringBuilder("Aircraft used by Passenger ID: " + passengerId + "\n");
        for (Aircraft plane : aircraft) {
            report.append("Aircraft ID: ").append(plane.getId())
                    .append(", Type: ").append(plane.getModel())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    private void generateAirportsUsedByPassengers(Long passengerId) {
        List<Airport> airports = restClient.getAirportsUsedByPassengers(passengerId);
        if (airports == null || airports.isEmpty()) {
            System.out.println("No airports found for passenger ID: " + passengerId);
            return;
        }

        StringBuilder report = new StringBuilder("Airports used by Passenger ID: " + passengerId + "\n");
        for (Airport airport : airports) {
            report.append("Airport ID: ").append(airport.getId())
                    .append(", Name: ").append(airport.getName())
                    .append("\n");
        }

        System.out.println(report.toString());
    }

    // Main method to run the application
    public static void main(String[] args) {
        CommandLineInterface cli = new CommandLineInterface();
        cli.run();  // Start the CLI
    }
}
