package com.api.manager.dtos;

import com.api.manager.models.ClientModel;

import javax.validation.constraints.NotNull;

public class ServiceDto {
    @NotNull(message = "name can not be be null ")
    private String name;
    @NotNull(message = "description can not be be null ")
    private String description;
    @NotNull(message = "time can not be be null ")
    private String time;
    @NotNull(message = "price can not be be null ")
    private float price;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
