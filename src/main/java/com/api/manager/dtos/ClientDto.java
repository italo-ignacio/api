package com.api.manager.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ClientDto {
    @NotBlank(message = "name can not be be null ")
    private String name;

    private int age;
    @NotBlank(message = "tel can not be be null ")
    private String tel;
    @NotBlank(message = "email can not be be null ")
    @Email(message = "Invalid email")
    private String email;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
