package com.example.demo.Controller;

import com.example.demo.Dto.KullaniciProfiliDto;
import com.example.demo.Dto.Response.EtkinlikDetayDto;
import com.example.demo.Dto.Response.KoltukDurumDto;
import com.example.demo.Dto.Response.SehirDto;
import com.example.demo.Entity.EtkinlikEntity;
import com.example.demo.Service.LandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mainPage")
public class LandingController {
    private final LandingService landingService;

    @Autowired
    public LandingController(LandingService landingService) {
        this.landingService = landingService;
    }

    @GetMapping("/getSehir")//bir alttakine entegre edilebilir
    public List<SehirDto> getSehirler(){
        return landingService.getSehirler();
    }

    @GetMapping("/")
    public List<EtkinlikEntity> getEtkinlikler(
            @RequestParam(required = false) String etkinlikTurAdi,
            @RequestParam(required = false) String sehirAdi
    ) {
        return landingService.getEtkinlikler(etkinlikTurAdi, sehirAdi);
    }

    @GetMapping("/{eventId}")
    public EtkinlikDetayDto getEtkinlik(@PathVariable Long eventId) {
        return landingService.getEtkinlik(eventId); // sadece seansList ve bu seansların salonu dönsün
    }

    /*@GetMapping("/koltukDurumu/{seansId}")
    public List<KoltukDurumDto> getKoltukDurumu(@PathVariable Long seansId) {
        return landingService.getKoltukDurumu(seansId);
    }*/
}
