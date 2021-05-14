package com.example.demo.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "car_order")
public class CarOrder {

    @Id
    private Integer carOrderId;

    private boolean leased;

    private LocalDate date;
    //email
    private String username;

    private Integer carId;

    public Integer getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(Integer carOrderId) {
        this.carOrderId = carOrderId;
    }

    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }
}
