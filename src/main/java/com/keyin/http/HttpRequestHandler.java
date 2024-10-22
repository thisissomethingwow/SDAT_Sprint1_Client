package com.keyin.http;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.net.URI;
import java.util.Optional;

public class HttpRequestHandler {
    private HttpClient httpClient;

    // constructor to initialize httpClient
    public HttpRequestHandler() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Method to send a GET request
    public Optional<String> sendGetRequest(String url, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        // add headers if provided
        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }
        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = sendHttpRequest(request);
        return Optional.ofNullable(response.body());
    }

    // method to send a post request with a json body
    public Optional<String> sendPostRequest(String url, String jsonBody, Map<String, String> headers ) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .header("Content-Type", "application/json");

        // Add additional headers if provided
        if (headers != null) {
            headers.forEach(requestBuilder::header); // Add headers to the request
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = sendHttpRequest(request);
        return Optional.ofNullable(response.body());
    }

    // method to send a delete request
    public Optional<String> sendDeleteRequest(String url, Map<String, String> headers) throws IOException, InterruptedException {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE();

        // Add headers if provided
        if (headers != null) {
            headers.forEach(requestBuilder::header); // shorthand for headers.forEach((k, v) -> requestBuilder.header(k, v));
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = sendHttpRequest(request);
        return Optional.ofNullable(response.body());
    }

    // private helper method to send an http request and handle the response
    private HttpResponse<String> sendHttpRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // handle various response codes and print messages
        switch (response.statusCode()) {
            case 200:
            case 201:
                System.out.println("Request successful with status code: " + response.statusCode());
                break;
            case 204:
                return response;
            case 400:
                System.out.println("Bad Request: " + response.body());
            case 401:
                System.out.println("Unauthorized: " + response.body());
            case 404:
                System.out.println("Not Found: " + response.body());
            case 500:
                System.out.println("Internal Server Error: " + response.body());
            default:
                System.out.println("Failed with status code: " + response.statusCode());
        }
        return response;

    }



}
