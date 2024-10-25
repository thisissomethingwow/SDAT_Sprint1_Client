package com.keyin.http.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RESTClientTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    private RESTClient restClient;
    private static final String TEST_SERVER_URL = "http://localhost:8080";

    @BeforeEach
    void setUp() {
        restClient = new RESTClient(TEST_SERVER_URL, mockHttpClient);
    }

    @Test
    void getAllCities_Success() throws IOException, InterruptedException {
        // Arrange
        String jsonResponse = "[{\"id\":1,\"name\":\"St. John's\",\"state\":\"NL\",\"population\":100000}]";
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(jsonResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        List<City> cities = restClient.getAllCities();

        // Assert
        assertNotNull(cities);
        assertEquals(1, cities.size());
        assertEquals("St. John's", cities.get(0).getName());
    }

    @Test
    void getAllCities_Error() throws IOException, InterruptedException {
        // Arrange
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("Connection error"));

        // Act
        List<City> cities = restClient.getAllCities();

        // Assert
        assertTrue(cities.isEmpty());
    }

    @Test
    void getAllAirports_Success() throws IOException, InterruptedException {
        // Arrange
        String jsonResponse = "[{\"id\":1,\"name\":\"St. John's International\",\"code\":\"YYT\"}]";
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(jsonResponse);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        List<Airport> airports = restClient.getAllAirports();

        // Assert
        assertNotNull(airports);
        assertEquals(1, airports.size());
        assertEquals("YYT", airports.get(0).getCode());
    }

    @Test
    void getAllAirports_Error() throws IOException, InterruptedException {
        // Arrange
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenThrow(new IOException("Connection error"));

        // Act
        List<Airport> airports = restClient.getAllAirports();

        // Assert
        assertTrue(airports.isEmpty());
    }
}