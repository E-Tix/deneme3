package com.example.demo.Controller;

import com.example.demo.Dto.Request.ChangePasswordDto;
import com.example.demo.Dto.KullaniciProfiliDto;
import com.example.demo.Dto.Response.SehirDto;
import com.example.demo.Entity.BiletEntity;
import com.example.demo.Service.KullaniciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Profile")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @Autowired
    public KullaniciController(KullaniciService kullaniciService) {
        this.kullaniciService = kullaniciService;
    }

    @PostMapping("/ChangePassword")
    public boolean changePassword(@RequestBody ChangePasswordDto changePasswordDto) {

        Long userId = getCurrentUserId();
        return kullaniciService.changePassword(changePasswordDto, userId);
    }

    @GetMapping("/getTickets")
    public List<BiletEntity> getBiletler() {

        Long userId = getCurrentUserId();
        return kullaniciService.getBiletler(userId);
    }

    @GetMapping("/getUserProfile")
    public KullaniciProfiliDto getKullaniciProfili() {

        Long userId = getCurrentUserId();
        return kullaniciService.getKullaniciProfili(userId);
    }

    @PutMapping("/updateUserInfo")
    public boolean kullaniciProfiliDuzenle(@RequestBody KullaniciProfiliDto kullaniciProfiliDto) {

        Long userId = getCurrentUserId();
        return kullaniciService.kullaniciProfiliDuzenle(kullaniciProfiliDto, userId);
    }

    @PutMapping("/updateCity")
    public boolean kullaniciSehirDuzenle(SehirDto sehirDto){
        Long userId = getCurrentUserId();
        return kullaniciService.kullaniciSehirDuzenle(userId,sehirDto);
    }

    private Long getCurrentUserId() {
        // Bu metodu JWT'den ya da SecurityContext'ten ID çıkarmak için ayarlamalısın
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Kullanıcı doğrulanmamış");
        }
        // Sadece username varsa, repository'den ID çekebilirsin:
        String username = auth.getName();
        return kullaniciService.getUserIdByUsername(username); // Bu metodu serviste yazman gerekir
    }
}
