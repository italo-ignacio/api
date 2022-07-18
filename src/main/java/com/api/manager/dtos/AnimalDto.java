package com.api.manager.dtos;

import com.api.manager.models.ClientModel;

import javax.validation.constraints.NotNull;

public class AnimalDto {
    @NotNull(message = "name can not be be null ")
    private String name;
    @NotNull(message = "breed can not be be null ")
    private String breed;
    @NotNull(message = "name can not be be null ")
    private String species;
    @NotNull(message = "appearance can not be be null ")
    private String appearance;
    @NotNull(message = "age can not be be null, type : int ")
    private int age;
    @NotNull(message = "weight can not be be null, type : float ")
    private float weight;
    @NotNull(message = "client:id can not be be null ")
    private ClientModel client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }
}
