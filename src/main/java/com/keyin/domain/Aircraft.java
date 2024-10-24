package com.keyin.domain;

public class Aircraft {
    private Long id;
    private String model;
    private int capacity;
    private String airline_name;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAirlineName() {
        return airline_name;
    }

    public void setAirlineName(String airline_name) {
        this.airline_name = airline_name;
    }
}