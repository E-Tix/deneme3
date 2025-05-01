package com.example.demo.Repository;

import com.example.demo.Entity.EtkinlikEntity;
import com.example.demo.Entity.EtkinlikTurEntity;
import com.example.demo.Entity.OrganizatorEntity;
import com.example.demo.Entity.SehirEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtkinlikRepository extends JpaRepository<EtkinlikEntity,Long> {

    EtkinlikEntity findByEtkinlikID(Long id);

    List<EtkinlikEntity> findByOrganizator(OrganizatorEntity organizator);

    List<EtkinlikEntity> findBySehirAndEtkinlikTur(SehirEntity sehir,EtkinlikTurEntity etkinlikTur);

    List<EtkinlikEntity> findByEtkinlikTur(EtkinlikTurEntity etkinlikTur);

    Optional<EtkinlikEntity> findByTarihiGectiMi(boolean tarihiGectiMi);

    Optional<EtkinlikEntity> findByYasSiniri(int yas);

    List<EtkinlikEntity> findBySehir(SehirEntity sehir);

    List<EtkinlikEntity> findByOlusturulmaTarihi(Timestamp sure);

    Optional<EtkinlikEntity> findByEtkinlikAdi(String etkinlikAdi);

}
