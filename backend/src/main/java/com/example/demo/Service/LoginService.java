package com.example.demo.Service;

import com.example.demo.Dto.Request.LoginDto;
import com.example.demo.Dto.Request.OrgLoginDto;
import com.example.demo.Entity.KullaniciEntity;
import com.example.demo.Entity.OrganizatorEntity;
import com.example.demo.Repository.KullaniciRepository;
import com.example.demo.Repository.OrganizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Service
public class LoginService {
    private KullaniciRepository kullaniciRepository;
    private OrganizatorRepository organizatorRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(KullaniciRepository kullaniciRepository, OrganizatorRepository organizatorRepository, JwtService jwtService, BCryptPasswordEncoder passwordEncoder) {
        this.kullaniciRepository = kullaniciRepository;
        this.organizatorRepository = organizatorRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }
    public String login(LoginDto loginDto) {
        KullaniciEntity kullanici = kullaniciRepository.findByKullaniciAdi(loginDto.getKullaniciAdi())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Kullanıcı bulunamadı"));

        if (passwordEncoder.matches(loginDto.getSifre(), kullanici.getSifre())) {
            return jwtService.generateToken(kullanici);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Şifre yanlış");
    }


    public String login(OrgLoginDto orgLoginDto) {
        Optional<OrganizatorEntity> optionalOrganizator = organizatorRepository.findByEmail(orgLoginDto.getEmail());

        if (optionalOrganizator.isPresent()) {
            OrganizatorEntity organizator = optionalOrganizator.get();

            if (passwordEncoder.matches(orgLoginDto.getSifre(), organizator.getSifre())) {
                return jwtService.generateToken(organizator);
            }
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Organizatör girişi başarısız");
    }

}
