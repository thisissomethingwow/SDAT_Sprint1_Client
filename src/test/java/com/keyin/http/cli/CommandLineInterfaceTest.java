package com.keyin.http.cli;

import com.keyin.domain.Aircraft;
import com.keyin.domain.Airport;
import com.keyin.domain.City;
import com.keyin.http.cli.CommandLineInterface;
import com.keyin.http.client.RESTClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandLineInterfaceTest {

    @Mock
    private RESTClient mockRestClient;

    private CommandLineInterface cli;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cli = new CommandLineInterface(mockRestClient);
    }

    @Test
    void testGenerateCityReport_withCities() {
        //Arrange
        City city = new City(1L, "Test City", "Test Province", 500000);
        when(mockRestClient.getAllCities()).thenReturn(List.of(city));

        //Act
        cli.generateCityReport();

        //Assert
        verify(mockRestClient, times(1)).getAllCities();
        assertNotNull(city);
        assertEquals("Test City", city.getName());
    }

    @Test
    void testGenerateCityReport_noCities() {
        //Arrange
        when(mockRestClient.getAllCities()).thenReturn(Collections.emptyList());

        //Act
        cli.generateCityReport();

        //Assert
        verify(mockRestClient, times(1)).getAllCities();
    }

    @Test
    void testGenerateAirportReport_withAirports() {
        //Arrange
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setCode("TST");
        when(mockRestClient.getAllAirports()).thenReturn(List.of(airport));

        //Act
        cli.generateAirportReport();

        //Assert
        verify(mockRestClient, times(1)).getAllAirports();
        assertEquals("Test Airport", airport.getName());
    }

    @Test
    void testGenerateAirportReport_noAirports() {
        //Arrange
        when(mockRestClient.getAllAirports()).thenReturn(Collections.emptyList());

        //Act
        cli.generateAirportReport();

        //Assert
        verify(mockRestClient, times(1)).getAllAirports();
    }

    @Test
    void testGenerateAirportReportByCity_withAirports() {
        //Arrange
        Long cityId = 1L;
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setCode("TST");
        when(mockRestClient.getAirportsByCity(cityId)).thenReturn(List.of(airport));

        //Act
        cli.generateAirportReportByCity(cityId);

        //Assert
        verify(mockRestClient, times(1)).getAirportsByCity(cityId);
        assertEquals("Test Airport", airport.getName());
    }

    @Test
    void testGenerateAirportReportByCity_noAirports() {
        //Arrange
        Long cityId = 1L;
        when(mockRestClient.getAirportsByCity(cityId)).thenReturn(Collections.emptyList());

        //Act
        cli.generateAirportReportByCity(cityId);

        //Assert
        verify(mockRestClient, times(1)).getAirportsByCity(cityId);
    }

    @Test
    void testGenerateAircraftReport_withAircraft() {
        //Arrange
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setModel("Boeing 747");
        aircraft.setAirlineName("Test Airline");
        when(mockRestClient.getAllAircraft()).thenReturn(List.of(aircraft));

        //Act
        cli.generateAircraftReport();

        //Assert
        verify(mockRestClient, times(1)).getAllAircraft();
        assertEquals("Boeing 747", aircraft.getModel());
    }

    @Test
    void testGenerateAircraftReport_noAircraft() {
        //Arrange
        when(mockRestClient.getAllAircraft()).thenReturn(Collections.emptyList());

        //Act
        cli.generateAircraftReport();

        //Assert
        verify(mockRestClient, times(1)).getAllAircraft();
    }

    @Test
    void testGenerateAuthorizedAirportsForAircraft_withAirports() {
        //Arrange
        Long aircraftId = 1L;
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setCode("TST");
        when(mockRestClient.getAuthorizedAirportsForAircraft(aircraftId)).thenReturn(List.of(airport));

        //Act
        cli.generateAuthorizedAirportsForAircraft(aircraftId);

        //Assert
        verify(mockRestClient, times(1)).getAuthorizedAirportsForAircraft(aircraftId);
        assertEquals("Test Airport", airport.getName());
    }

    @Test
    void testGenerateAuthorizedAirportsForAircraft_noAirports() {
        //Arrange
        Long aircraftId = 1L;
        when(mockRestClient.getAuthorizedAirportsForAircraft(aircraftId)).thenReturn(Collections.emptyList());

        //Act
        cli.generateAuthorizedAirportsForAircraft(aircraftId);

        //Assert
        verify(mockRestClient, times(1)).getAuthorizedAirportsForAircraft(aircraftId);
    }

    @Test
    void testGenerateAirportsUsedByPassengers_withAirports() {
        //Arrange
        Long passengerId = 1L;
        Airport airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        when(mockRestClient.getAirportsUsedByPassengers(passengerId)).thenReturn(List.of(airport));

        //Act
        cli.generateAirportsUsedByPassengers(passengerId);

        //Assert
        verify(mockRestClient, times(1)).getAirportsUsedByPassengers(passengerId);
        assertEquals("Test Airport", airport.getName());
    }

    @Test
    void testGenerateAirportsUsedByPassengers_noAirports() {
        //Arrange
        Long passengerId = 1L;
        when(mockRestClient.getAirportsUsedByPassengers(passengerId)).thenReturn(Collections.emptyList());

        //Act
        cli.generateAirportsUsedByPassengers(passengerId);

        //Assert
        verify(mockRestClient, times(1)).getAirportsUsedByPassengers(passengerId);
    }
}
