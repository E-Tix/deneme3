package com.example.demo.Repository;


import com.example.demo.Entity.SalonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<SalonEntity,Long> {

    Optional<SalonEntity> findBySalonID(Long salonId);

    Optional<SalonEntity> findBySalonAdi(String salonAdi);

}
