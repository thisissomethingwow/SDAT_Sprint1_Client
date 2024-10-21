package com.keyin.client;

public class ClientApp {
    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();

        try {
            String airports = apiClient.getAirports();
            System.out.println("Airports: " + airports);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
