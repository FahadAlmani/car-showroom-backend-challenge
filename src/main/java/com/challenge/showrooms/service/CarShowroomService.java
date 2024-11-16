package com.challenge.showrooms.service;

import com.challenge.showrooms.DTO.CarShowroomDTO;
import com.challenge.showrooms.DTO.CarShowroomListItemDTO;
import com.challenge.showrooms.Repository.CarShowroomRepository;
import com.challenge.showrooms.model.CarShowroom;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarShowroomService {
    private final CarShowroomRepository carShowroomRepository;
    private final ModelMapper modelMapper;

    public CarShowroomDTO createCarShowroom(CarShowroomDTO newCarShowroomDTO) {
        Optional<CarShowroom> existCarShowroom = carShowroomRepository.findByCommercialRegistrationNumber(newCarShowroomDTO.getCommercialRegistrationNumber());

        if (existCarShowroom.isPresent()) {
            throw new IllegalArgumentException("Commercial registration number must be unique");
        }

        CarShowroom newCarShowroom = carShowroomRepository.save(modelMapper.map(newCarShowroomDTO, CarShowroom.class));
        return modelMapper.map(newCarShowroom, CarShowroomDTO.class);
    }

    public Page<CarShowroomListItemDTO> findAllCarShowrooms(Integer offset, Integer pageSize, String field) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC, field));
        Page<CarShowroom> carShowroomList = carShowroomRepository.findAllByDeletedFalse(pageRequest);
        return carShowroomList.map(carShowroom -> modelMapper.map(carShowroom, CarShowroomListItemDTO.class));
    }

    public CarShowroomDTO findSpecificCarShowroom(Long carShowroomId) {
        CarShowroom carShowroom = carShowroomRepository.findByIdAndDeletedFalse(carShowroomId).orElse(null);
        return modelMapper.map(carShowroom, CarShowroomDTO.class);
    }

    public CarShowroomDTO updateCarShowroom(Long carShowroomId, CarShowroomDTO updatedCarShowroomDTO) {
        CarShowroom existCarShowroom = carShowroomRepository.findByIdAndDeletedFalse(carShowroomId)
                .orElseThrow(() -> new IllegalArgumentException("Car Showroom with ID " + carShowroomId + " not found"));

        existCarShowroom.setContactNumber(updatedCarShowroomDTO.getContactNumber());
        existCarShowroom.setManagerName(updatedCarShowroomDTO.getManagerName());
        existCarShowroom.setAddress(updatedCarShowroomDTO.getAddress());

        CarShowroom updatedCarShowroom = carShowroomRepository.save(existCarShowroom);
        return modelMapper.map(updatedCarShowroom, CarShowroomDTO.class);
    }

    public CarShowroomDTO deleteCarShowroom(Long carShowroomId) {
        CarShowroom existCarShowroom = carShowroomRepository.findById(carShowroomId)
                .orElseThrow(() -> new IllegalArgumentException("Car Showroom with ID " + carShowroomId + " not found"));

        carShowroomRepository.deleteById(carShowroomId);

        return modelMapper.map(existCarShowroom, CarShowroomDTO.class);
    }

}
