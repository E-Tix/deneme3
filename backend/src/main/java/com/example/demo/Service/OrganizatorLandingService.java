package com.example.demo.Service;

import com.example.demo.Dto.Request.EtkinlikEkleDto;
import com.example.demo.Dto.Request.EtkinlikGuncelleDto;
import com.example.demo.Dto.Request.SeansDuzenleDto;
import com.example.demo.Dto.Response.EtkinlikForOrgDetayDto;
import com.example.demo.Dto.Response.EtkinlikForOrgDto;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizatorLandingService {
    private OrganizatorRepository organizatorRepository;
    private EtkinlikRepository etkinlikRepository;
    private SeansRepository seansRepository;
    private SalonRepository salonRepository;
    private EtkinlikSalonSeansRepository etkinlikSalonSeansRepository;

    @Autowired
    public OrganizatorLandingService(EtkinlikSalonSeansRepository etkinlikSalonSeansRepository,OrganizatorRepository organizatorRepository, EtkinlikRepository etkinlikRepository, SeansRepository seansRepository, SalonRepository salonRepository) {
        this.etkinlikSalonSeansRepository=etkinlikSalonSeansRepository;
        this.organizatorRepository = organizatorRepository;
        this.etkinlikRepository = etkinlikRepository;
        this.seansRepository = seansRepository;
        this.salonRepository = salonRepository;
    }

    public boolean etkinlikEkle(EtkinlikEkleDto etkinlikEkleDto,Long id){

        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(id);

        if (organizator==null){
            return false;
        }

        EtkinlikEntity etkinlik=new EtkinlikEntity(organizator,etkinlikEkleDto.getEtkinlikTur(),etkinlikEkleDto.getSehir(), etkinlikEkleDto.getEtkinlikAdi(), etkinlikEkleDto.getEtkinlikAciklamasi(), etkinlikEkleDto.getKapakFotografi(), etkinlikEkleDto.getYasSiniri(), etkinlikEkleDto.getEtkinlikSuresi(),etkinlikEkleDto.getBiletFiyati());
        etkinlik=etkinlikRepository.save(etkinlik);
        SalonEntity salon=etkinlikEkleDto.getSalon();
        SeansEntity seans;

        while (!etkinlikEkleDto.getSeansEkleDtoList().isEmpty())
        {
            seans=seansRepository.save(new SeansEntity(etkinlikEkleDto.getSeansEkleDtoList().getLast().getTarih()));
            etkinlikSalonSeansRepository.save(new EtkinlikSalonSeansEntity(etkinlik,salon,seans));
            etkinlikEkleDto.getSeansEkleDtoList().removeLast();
        }

        return true;
    }

    public EtkinlikGuncelleDto getEtkinlikGuncelleDto(Long orgId,Long eventId){

        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(orgId);
        EtkinlikEntity etkinlik=etkinlikRepository.findByEtkinlikID(eventId);
        List<EtkinlikSalonSeansEntity> etkinlikSalonSeansEntity=etkinlikSalonSeansRepository.findEtkinlikSalonSeansEntitiesByEtkinlik(etkinlik);
        SalonEntity salon=etkinlikSalonSeansEntity.getFirst().getSalon();
        List<SeansDuzenleDto> seansDuzenleDtoList=new ArrayList<>();

        while (!etkinlikSalonSeansEntity.isEmpty())
        {
            seansDuzenleDtoList.add(new SeansDuzenleDto(etkinlikSalonSeansEntity.getLast().getSeans().getSeansID(),etkinlikSalonSeansEntity.getLast().getSeans().getTarih()));
            etkinlikSalonSeansEntity.removeLast();
        }

        if (organizator!=null){
            return new EtkinlikGuncelleDto(etkinlik.getEtkinlikID(),salon,seansDuzenleDtoList,etkinlik.getEtkinlikTur(),etkinlik.getSehir(), etkinlik.getEtkinlikAdi(), etkinlik.getKapakFotografi(), etkinlik.getEtkinlikAciklamasi(), etkinlik.getYasSiniri(),etkinlik.getEtkinlikSuresi(),etkinlik.getBiletFiyati());
        }else {
            throw new EntityNotFoundException("organizator bulunamadı");
        }
    }

    public boolean etkinlikGuncelle(Long orgId,EtkinlikGuncelleDto etkinlikGuncelleDto){
        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(orgId);
        EtkinlikEntity etkinlik=etkinlikRepository.findByEtkinlikID(etkinlikGuncelleDto.getEtkinlikId());

        if (!etkinlik.getOrganizator().equals(organizator))
        {
            return false;
        }

        List<EtkinlikSalonSeansEntity> etkinlikSalonSeansEntityList = etkinlikSalonSeansRepository.findEtkinlikSalonSeansEntitiesByEtkinlik(etkinlik);
        SalonEntity salon=etkinlikSalonSeansEntityList.getFirst().getSalon();

        while (!etkinlikGuncelleDto.getSeansDuzenleDtoList().isEmpty())
        {
            SeansEntity seans=seansRepository.findBySeansID(etkinlikGuncelleDto.getSeansDuzenleDtoList().getLast().getSeansId());
            seans.setTarih(etkinlikGuncelleDto.getSeansDuzenleDtoList().getLast().getTarih());
            seansRepository.save(seans);
            etkinlikGuncelleDto.getSeansDuzenleDtoList().removeLast();
        }

        while (!etkinlikGuncelleDto.getSeansEkleDtoList().isEmpty())
        {
            SeansEntity seans=new SeansEntity(etkinlikGuncelleDto.getSeansEkleDtoList().getLast().getTarih());
            seansRepository.save(seans);
            etkinlikGuncelleDto.getSeansEkleDtoList().removeLast();
        }

        etkinlik.setBiletFiyati(etkinlikGuncelleDto.getBiletFiyati());
        etkinlik.setEtkinlikAdi(etkinlikGuncelleDto.getEtkinlikAdi());
        etkinlik.setEtkinlikTur(etkinlikGuncelleDto.getEtkinlikTur());
        etkinlik.setSehir(etkinlikGuncelleDto.getSehir());
        etkinlik.setEtkinlikAciklamasi(etkinlikGuncelleDto.getEtkinlikAciklamasi());
        etkinlik.setKapakFotografi(etkinlikGuncelleDto.getKapakFotografi());
        etkinlik.setEtkinlikSuresi(etkinlikGuncelleDto.getEtkinlikSuresi());

        etkinlikRepository.save(etkinlik);

        return true;
    }

    /*public boolean etkinlikSil(Long eventId){
        EtkinlikEntity etkinlik=etkinlikRepository.findByEtkinlikID(eventId);
        etkinlikRepository.delete(etkinlik);
        return true;
    }*/

    public EtkinlikForOrgDetayDto getEtkinlik(Long eventId){
        EtkinlikEntity etkinlik=etkinlikRepository.findByEtkinlikID(eventId);
        if (etkinlik!=null)
        {
            return new EtkinlikForOrgDetayDto();
        }else {
            throw new EntityNotFoundException("etkinlik bulunamadı");
        }
    }

    public List<EtkinlikForOrgDto> GetEtkinlikler(Long orgId){
        OrganizatorEntity organizator=organizatorRepository.findByOrganizatorID(orgId);
        List<EtkinlikEntity> etkinlikEntities = etkinlikRepository.findByOrganizator(organizator);
        List<EtkinlikForOrgDto> etkinlikForOrgDtoList=new ArrayList<>();
        while (!etkinlikEntities.isEmpty()){
            etkinlikForOrgDtoList.add(new EtkinlikForOrgDto(etkinlikEntities.getLast().getEtkinlikID(),etkinlikEntities.getLast().getKapakFotografi(),etkinlikEntities.getLast().getEtkinlikAdi()));
            etkinlikEntities.removeLast();
        }
        return etkinlikForOrgDtoList;
    }
}
