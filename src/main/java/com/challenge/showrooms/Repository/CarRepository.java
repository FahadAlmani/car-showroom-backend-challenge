package com.challenge.showrooms.Repository;

import com.challenge.showrooms.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Page<Car> findAllByCarShowroomIdAndDeletedFalse(Long id, PageRequest pageRequest);
}