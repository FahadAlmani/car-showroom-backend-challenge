package com.challenge.showrooms.controller;

import com.challenge.showrooms.DTO.CarShowroomDTO;
import com.challenge.showrooms.DTO.CarShowroomListItemDTO;
import com.challenge.showrooms.service.CarShowroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-showrooms")
@RequiredArgsConstructor
public class CarShowroomController {

    private final CarShowroomService carShowroomService;

    @PostMapping
    public ResponseEntity<CarShowroomDTO> createCarShowroom(@Valid @RequestBody CarShowroomDTO newCarShowroomDTO) {
        CarShowroomDTO createdShowroomDTO = carShowroomService.createCarShowroom(newCarShowroomDTO);

        return new ResponseEntity<>(createdShowroomDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<CarShowroomListItemDTO>> getAllShowrooms(@PathVariable Integer offset, @PathVariable Integer pageSize, @PathVariable String field) {
        Page<CarShowroomListItemDTO> carShowroomListItemListDTO = carShowroomService.findAllCarShowrooms(offset, pageSize, field);

        return new ResponseEntity<>(carShowroomListItemListDTO, HttpStatus.OK);
    }

    @GetMapping("/{carShowroomId}")
    public ResponseEntity<CarShowroomDTO> getAllShowrooms(@PathVariable Long carShowroomId) {
        CarShowroomDTO carShowroomDTO = carShowroomService.findSpecificCarShowroom(carShowroomId);

        return new ResponseEntity<>(carShowroomDTO, HttpStatus.OK);
    }

    @PutMapping("/{carShowroomId}")
    public ResponseEntity<CarShowroomDTO> updateCarShowroom(@PathVariable Long carShowroomId,@Valid @RequestBody CarShowroomDTO updatedCarShowroomDTO){
        CarShowroomDTO carShowroomDTO = carShowroomService.updateCarShowroom(carShowroomId,updatedCarShowroomDTO);

        return new ResponseEntity<>(carShowroomDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{carShowroomId}")
    public ResponseEntity<CarShowroomDTO> deleteCarShowroom(@PathVariable Long carShowroomId){
        CarShowroomDTO carShowroomDTO = carShowroomService.deleteCarShowroom(carShowroomId);

        return new ResponseEntity<>(carShowroomDTO, HttpStatus.OK);
    }
}
