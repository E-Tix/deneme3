package com.example.demo.Repository;

import com.example.demo.Entity.EtkinlikEntity;
import com.example.demo.Entity.SinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SinemaRepository extends JpaRepository<SinemaEntity,Long> {

    Optional<SinemaEntity>findByEtkinlik(EtkinlikEntity etkinlik);
    Optional<SinemaEntity>findByImdbPuani(float IMDBPuani);

}
