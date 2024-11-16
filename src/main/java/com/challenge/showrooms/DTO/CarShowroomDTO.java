package com.challenge.showrooms.DTO;

import com.challenge.showrooms.model.CarShowroom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
public class CarShowroomDTO {

    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot be longer than 100 characters")
    private String name;
    @NotBlank(message = "Commercial registration number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Commercial registration number must be 10 digits")
    private String commercialRegistrationNumber;
    @Size(max = 100, message = "Manager name cannot be longer than 100 characters")
    private String managerName;
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[0-9]{1,15}$", message = "Contact number must be numeric and up to 15 digits")
    private String contactNumber;
    @Size(max = 255, message = "Address cannot be longer than 255 characters")
    private String address;
    private List<CarDTO> carList;
}
