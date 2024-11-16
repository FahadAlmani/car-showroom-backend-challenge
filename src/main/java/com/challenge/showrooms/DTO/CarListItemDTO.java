package com.challenge.showrooms.DTO;

import lombok.Data;

@Data
public class CarListItemDTO {
    private Long id;
    private String vin;
    private String maker;
    private String model;
    private String modelYear;
    private Double price;
    private String carShowroomName;
    private String carShowroomContactNumber;
}
