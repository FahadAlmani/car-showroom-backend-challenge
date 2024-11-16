package com.challenge.showrooms.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

@Entity
@Data
@SQLDelete(sql = "UPDATE car SET deleted = true WHERE id=?")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String vin;
    private String maker;
    private String model;
    private String modelYear;
    private Double price;
    private boolean deleted = false;
    @ManyToOne
    private CarShowroom carShowroom;
}