package com.example.demo.Repository;


import com.example.demo.Entity.SehirEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SehirRepository extends JpaRepository<SehirEntity,Long> {

    Optional<SehirEntity> findByPlakaKodu(Long plakaKodu);
    SehirEntity findBySehirAdi(String sehirAdi);

}
