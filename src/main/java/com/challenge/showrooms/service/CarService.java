package com.challenge.showrooms.service;

import com.challenge.showrooms.DTO.CarDTO;
import com.challenge.showrooms.DTO.CarListItemDTO;
import com.challenge.showrooms.Repository.CarRepository;
import com.challenge.showrooms.Repository.CarShowroomRepository;
import com.challenge.showrooms.model.Car;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarShowroomRepository carShowroomRepository;
    private final ModelMapper modelMapper;

    public CarDTO createCar(CarDTO newCarDto) {
        Optional<Car> existCar = carRepository.findById(newCarDto.getId());

        if (existCar.isPresent()) {
            throw new IllegalArgumentException("Car already exist");
        }

        carShowroomRepository.findByIdAndDeletedFalse(newCarDto.getCarShowroomId())
                .orElseThrow(() -> new IllegalArgumentException("Car Showroom with ID " + newCarDto.getCarShowroomId() + " not found"));

        Car newCarShowroom = carRepository.save(modelMapper.map(newCarDto, Car.class));
        return modelMapper.map(newCarShowroom, CarDTO.class);
    }

    public Page<CarListItemDTO> findAllCarsByShowroomId(Long carShowroomId, Integer offset, Integer pageSize) {
        // todo: add dynamic filtering
        PageRequest pageRequest = PageRequest.of(offset, pageSize);
        Page<Car> carList = carRepository.findAllByCarShowroomIdAndDeletedFalse(carShowroomId,pageRequest);

        return carList.map(car -> modelMapper.map(car, CarListItemDTO.class));
    }
}
