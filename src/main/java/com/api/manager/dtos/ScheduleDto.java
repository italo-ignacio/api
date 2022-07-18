package com.api.manager.dtos;

import com.api.manager.models.AnimalModel;
import com.api.manager.models.ServiceModel;

import javax.validation.constraints.NotNull;


public class ScheduleDto {
    @NotNull(message = "name can not be be null ")
    private String date;
    @NotNull(message = "paid can not be be null ")
    private boolean paid;
    @NotNull(message = "concluded can not be be null ")
    private boolean concluded;
    @NotNull(message = "service:id can not be be null ")
    private ServiceModel service;
    @NotNull(message = "animal:id can not be be null ")
    private AnimalModel animal;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public ServiceModel getService() {
        return service;
    }

    public void setService(ServiceModel service) {
        this.service = service;
    }

    public AnimalModel getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalModel animal) {
        this.animal = animal;
    }
}
