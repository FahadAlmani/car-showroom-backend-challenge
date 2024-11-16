package com.challenge.showrooms.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    @NotBlank(message = "VIN is required")
    @Size(max = 25, message = "VIN cannot exceed 25 characters")
    private String vin;
    @NotBlank(message = "Maker is required")
    @Size(max = 25, message = "Maker cannot exceed 25 characters")
    private String maker;
    @NotBlank(message = "Model is required")
    @Size(max = 25, message = "Model cannot exceed 25 characters")
    private String model;
    @NotBlank(message = "Model year is required")
    @Pattern(regexp = "^[0-9]{4}$", message = "Model year must be a 4-digit number")
    private String modelYear;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;
    private Long carShowroomId;
}
