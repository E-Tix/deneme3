package com.example.demo.Repository;

import com.example.demo.Entity.OrganizatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OrganizatorRepository extends JpaRepository<OrganizatorEntity,Long> {
    OrganizatorEntity findByEmailAndSifre(String email,String sifre);
    OrganizatorEntity findByOrganizatorID(Long organizatorID);
    Optional<OrganizatorEntity> findByAdSoyad(String adSoyad);
    Optional<OrganizatorEntity> findByEmail(String email);//tek bir org döndürmesi lazım
    Optional<OrganizatorEntity> findBySifre(String sifre);
    Long findOrganizatorIDByEmail(String username);
}
