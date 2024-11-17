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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarShowroomRepository carShowroomRepository;
    private final ModelMapper modelMapper;

    public CarDTO createCar(CarDTO newCarDto) {
        carShowroomRepository.findByIdAndDeletedFalse(newCarDto.getCarShowroomId())
                .orElseThrow(() -> new IllegalArgumentException("Car Showroom with ID " + newCarDto.getCarShowroomId() + " not found"));

        Car newCar = carRepository.save(modelMapper.map(newCarDto, Car.class));
        return modelMapper.map(newCar, CarDTO.class);
    }

    public Page<CarListItemDTO> findAllCarsByShowroomId(Long carShowroomId, Integer offset, Integer pageSize, String field, String value) {
        Specification<Car> specification = prepareCustomFilter(carShowroomId, field, value);
        PageRequest pageRequest = PageRequest.of(offset, pageSize);

        Page<Car> carList = carRepository.findAll(specification, pageRequest);

        return carList.map(car -> modelMapper.map(car, CarListItemDTO.class));
    }

    private Specification<Car> prepareCustomFilter(Long carShowroomId, String field, String value) {
        if(!field.equalsIgnoreCase("none")) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.and(criteriaBuilder.equal(root.get(field), value),
                            criteriaBuilder.equal(root.get("carShowroom").get("id"), carShowroomId),
                            criteriaBuilder.equal(root.get("deleted"), false));
        }else {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.and(criteriaBuilder.equal(root.get("carShowroom").get("id"), carShowroomId),
                            criteriaBuilder.equal(root.get("deleted"), false));
        }
    }
}
