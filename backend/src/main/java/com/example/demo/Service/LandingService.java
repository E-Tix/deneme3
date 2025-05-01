package com.example.demo.Service;

import com.example.demo.Dto.Response.EtkinlikDetayDto;
import com.example.demo.Dto.Response.KoltukDurumDto;
import com.example.demo.Dto.Response.SehirDto;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LandingService {

    private final EtkinlikRepository etkinlikRepository;
    private final EtkinlikTurRepository etkinlikTurRepository;
    private final SehirRepository sehirRepository;
    private final EtkinlikSalonSeansRepository etkinlikSalonSeansRepository;
    private final KoltukRepository koltukRepository;

    @Autowired
    public LandingService(EtkinlikRepository etkinlikRepository, EtkinlikSalonSeansRepository etkinlikSalonSeansRepository, SehirRepository sehirRepository, EtkinlikTurRepository etkinlikTurRepository, KoltukRepository koltukRepository) {
        this.etkinlikSalonSeansRepository = etkinlikSalonSeansRepository;
        this.sehirRepository = sehirRepository;
        this.etkinlikTurRepository = etkinlikTurRepository;
        this.etkinlikRepository = etkinlikRepository;
        this.koltukRepository = koltukRepository;
    }

    // Etkinlikleri getir
    public List<EtkinlikEntity> getEtkinlikler(String etkinlikTurAdi, String sehirAdi) {
        if (sehirAdi == null && etkinlikTurAdi == null) {
            return etkinlikRepository.findAll(); // Tüm etkinlikleri getir
        } else if (sehirAdi == null) {
            EtkinlikTurEntity etkinlikTur = etkinlikTurRepository.findByEtkinlikTurAdi(etkinlikTurAdi);
            return etkinlikRepository.findByEtkinlikTur(etkinlikTur); // Etkinlik türüne göre filtrele
        } else {
            EtkinlikTurEntity etkinlikTur = etkinlikTurRepository.findByEtkinlikTurAdi(etkinlikTurAdi);
            SehirEntity sehir = sehirRepository.findBySehirAdi(sehirAdi);
            return etkinlikRepository.findBySehirAndEtkinlikTur(sehir, etkinlikTur); // Hem şehir hem tür ile filtrele
        }
    }

    // Etkinlik detaylarını getir
    public EtkinlikDetayDto getEtkinlik(Long id) {
        Optional<EtkinlikEntity> etkinlikOptional = etkinlikRepository.findById(id);

        if (etkinlikOptional.isPresent()) {
            EtkinlikEntity etkinlik = etkinlikOptional.get();
            List<EtkinlikSalonSeansEntity> etkinlikSalonSeansEntities = etkinlikSalonSeansRepository.findEtkinlikSalonSeansEntitiesByEtkinlik(etkinlik);

            return new EtkinlikDetayDto(
                    etkinlik.getEtkinlikID(),
                    etkinlik.getEtkinlikAdi(),
                    etkinlik.getBiletFiyati(),
                    etkinlik.getYasSiniri(),
                    etkinlik.getEtkinlikTur(),
                    etkinlikSalonSeansEntities,
                    etkinlik.getEtkinlikSuresi(),
                    etkinlik.getEtkinlikAciklamasi(),
                    etkinlik.getOrganizator()
            );
        } else {
            return null;
        }
    }

    public List<SehirDto> getSehirler(){
        List<SehirEntity> sehirler = sehirRepository.findAll();
        List<SehirDto> sehirlerDto = new ArrayList<>();
        for (SehirEntity sehir:sehirler){
            sehirlerDto.add(new SehirDto(sehir.getPlakaKodu(),sehir.getSehirAdi()));
        }
        return sehirlerDto;
    }

    /*public List<KoltukDurumDto> getKoltukDurumu(Long seansId) {
        // Seansa ait koltukları al
        EtkinlikSalonSeansEntity seans = etkinlikSalonSeansRepository.findById(seansId).orElse(null);

        if (seans == null) {
            // Eğer seans bulunmazsa, boş bir liste döndürülebilir
            return List.of();
        }

        List<KoltukEntity> koltuklar = koltukRepository.findBySeans(seans);

        // Koltuk bilgilerini KoltukDurumDto'ya dönüştür
        return koltuklar.stream()
                .map(koltuk -> new KoltukDurumDto(koltuk.getKoltukID(), koltuk.getKoltukNumarasi(), koltuk.isDolu()))
                .collect(Collectors.toList());
    }*/

}
