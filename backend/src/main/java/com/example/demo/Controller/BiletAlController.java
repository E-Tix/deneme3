package com.example.demo.Controller;

import com.example.demo.Dto.Request.BiletAlDto;
import com.example.demo.Service.BiletAlService;
import com.example.demo.Repository.KullaniciRepository;
import com.example.demo.Entity.KullaniciEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biletAl")
public class BiletAlController {
    private final BiletAlService biletAlService;
    private final KullaniciRepository kullaniciRepository;

    @Autowired
    public BiletAlController(BiletAlService biletAlService, KullaniciRepository kullaniciRepository) {
        this.biletAlService = biletAlService;
        this.kullaniciRepository = kullaniciRepository;
    }

    @PostMapping("/")
    public boolean biletAl(@RequestBody BiletAlDto biletAlDto) {
        Long userId = getCurrentUserId();
        return biletAlService.biletAl(biletAlDto, userId);
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        KullaniciEntity kullanici = kullaniciRepository.findByKullaniciAdi(username)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
        return kullanici.getKullaniciID();
    }
}
