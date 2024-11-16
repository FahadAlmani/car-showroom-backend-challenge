package com.challenge.showrooms.controller;

import com.challenge.showrooms.DTO.CarDTO;
import com.challenge.showrooms.DTO.CarListItemDTO;
import com.challenge.showrooms.DTO.CarShowroomDTO;
import com.challenge.showrooms.DTO.CarShowroomListItemDTO;
import com.challenge.showrooms.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO newCarDTO) {
        CarDTO createdCarDTO = carService.createCar(newCarDTO);

        return new ResponseEntity<>(createdCarDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{carShowroomId}/{offset}/{pageSize}")
    public ResponseEntity<Page<CarListItemDTO>> getAllShowrooms(@PathVariable Long carShowroomId,  @PathVariable Integer offset, @PathVariable Integer pageSize) {
        Page<CarListItemDTO> carListItemDTOList = carService.findAllCarsByShowroomId(carShowroomId,offset, pageSize);

        return new ResponseEntity<>(carListItemDTOList, HttpStatus.OK);
    }
}
