package com.keyin.domain;



public class City {
    private long id;
    private String name;
    private String province;
    private int population;

    // Default constructor
    public City() {
    }

    // Constructor with all fields
    public City(long id, String name, String province, int population) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.population = population;
    }

    // Constructor without ID (for new objects)
    public City(String name, String province, int population) {
        this.name = name;
        this.province = province;
        this.population = population;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
