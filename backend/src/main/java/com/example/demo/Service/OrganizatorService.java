package com.example.demo.Service;

import com.example.demo.Dto.Request.ChangePasswordDto;
import com.example.demo.Dto.Request.OrganizatorProfiliDto;
import com.example.demo.Dto.Request.OrganizatorProfiliDuzenleDto;
import com.example.demo.Entity.OrganizatorEntity;
import com.example.demo.Repository.OrganizatorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizatorService {
    private OrganizatorRepository organizatorRepository;

    @Autowired
    public OrganizatorService(OrganizatorRepository organizatorRepository)
    {
        this.organizatorRepository=organizatorRepository;
    }

    public OrganizatorProfiliDuzenleDto getOrganizatorProfiliDto(Long id)
    {
        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(id);
        if (organizator==null){
            throw new EntityNotFoundException("organizator bulunamadı");
        }else {
            return new OrganizatorProfiliDuzenleDto(organizator.getAdSoyad(), organizator.getEmail(), organizator.getIban(), organizator.getSirketAdresi(), organizator.getTelefonNumarasi());
        }
    }

    public boolean organizatorProfiliDuzenle(OrganizatorProfiliDuzenleDto organizatorProfiliDuzenleDto,Long id)
    {
        OrganizatorEntity organizator = organizatorRepository.findByOrganizatorID(id);
        if (organizator==null){
            return false;
        }else {
            organizator.setAdSoyad(organizatorProfiliDuzenleDto.getAdSoyad());
            organizator.setEmail(organizatorProfiliDuzenleDto.getEmail());
            organizator.setIban(organizatorProfiliDuzenleDto.getIban());
            organizator.setSirketAdresi(organizatorProfiliDuzenleDto.getSirketAdresi());
            organizator.setTelefonNumarasi(organizatorProfiliDuzenleDto.getTelefonNumarasi());

            organizatorRepository.save(organizator);
            return true;
        }
    }

    public Long findOrganizatorIDByUsername(String username){
        return organizatorRepository.findOrganizatorIDByEmail(username);
    }

    public boolean changePassword(ChangePasswordDto changePasswordDto,Long id)
    {
        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(id);
        if (organizator!=null){
            if (changePasswordDto.getEskiSifre().equals(organizator.getSifre()))
            {
                if (changePasswordDto.getYeniSifre().equals(changePasswordDto.getYeniSifreTekrar())) {
                    organizatorRepository.save(organizator);
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    public OrganizatorProfiliDto getOrganizatorProfili(Long id){
        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(id);
        if (organizator==null){
            throw new EntityNotFoundException("organizator bulunamadı");
        }else {
            return new OrganizatorProfiliDto(organizator.getAdSoyad(), organizator.getVergiNo(), organizator.getEmail(), organizator.getIban(), organizator.getSirketAdresi(), organizator.getTckNo(), organizator.getTelefonNumarasi());
        }
    }
}
