package com.challenge.showrooms.Repository;

import com.challenge.showrooms.model.CarShowroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarShowroomRepository extends JpaRepository<CarShowroom,Long> {
    Optional<CarShowroom> findByIdAndDeletedFalse(Long carShowroomId);
    Page<CarShowroom> findAllByDeletedFalse(Pageable pageable);
    Optional<CarShowroom> findByCommercialRegistrationNumber(String commercialRegistrationNumber);
}
