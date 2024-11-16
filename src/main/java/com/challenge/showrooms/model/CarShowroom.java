package com.challenge.showrooms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

@Entity
@Data
@SQLDelete(sql = "UPDATE car_showroom SET deleted = true WHERE id=?")
public class CarShowroom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String commercialRegistrationNumber;
    private String managerName;
    private String contactNumber;
    private String address;
    private boolean deleted = false;
}
