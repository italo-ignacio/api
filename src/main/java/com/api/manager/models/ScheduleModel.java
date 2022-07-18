package com.api.manager.models;

import javax.persistence.*;

@Entity
@Table(name="TB_SCHEDULE")
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String  date;

    @Column(nullable = false)
    private boolean paid;

    @Column(nullable = false)
    private boolean concluded;

    @OneToOne
    @MapsId
    @JoinColumn(name = "service_id")
    private ServiceModel service;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


}
