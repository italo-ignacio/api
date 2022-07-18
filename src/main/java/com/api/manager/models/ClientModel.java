package com.api.manager.models;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TB_CLIENT")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="animals_id" )
    private List<AnimalModel> animals = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {return age;}

    public void setAge(int age) {
        this.age = age;
    }

    public List<AnimalModel> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalModel> animals) {
        this.animals = animals;
    }

}
