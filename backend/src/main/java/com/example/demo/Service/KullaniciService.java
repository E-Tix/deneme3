package com.example.demo.Service;

import com.example.demo.Dto.Request.ChangePasswordDto;
import com.example.demo.Dto.KullaniciProfiliDto;
import com.example.demo.Dto.Response.SehirDto;
import com.example.demo.Entity.BiletEntity;
import com.example.demo.Entity.KullaniciEntity;
import com.example.demo.Entity.SehirEntity;
import com.example.demo.Repository.BiletRepository;
import com.example.demo.Repository.KullaniciBiletRepository;
import com.example.demo.Repository.KullaniciRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KullaniciService {


    private KullaniciRepository kullaniciRepository;
    private BiletRepository biletRepository;
    private KullaniciBiletRepository kullaniciBiletRepository;

    @Autowired
    public KullaniciService(KullaniciRepository kullaniciRepository,BiletRepository biletRepository,KullaniciBiletRepository kullaniciBiletRepository){
        this.kullaniciRepository=kullaniciRepository;
        this.kullaniciBiletRepository=kullaniciBiletRepository;
        this.biletRepository=biletRepository;
    }

    public boolean kullaniciEkle(KullaniciEntity kullanici)
    {
        kullaniciRepository.save(kullanici);
        return true;
    }

    public boolean changePassword(ChangePasswordDto changePasswordDto,Long id){
        KullaniciEntity kullanici = kullaniciRepository.findByKullaniciID(id);
        if (kullanici!=null) {
            if (changePasswordDto.getEskiSifre().equals(kullanici.getSifre())) {
                if (changePasswordDto.getYeniSifre().equals(changePasswordDto.getYeniSifreTekrar())) {
                    kullanici.setSifre(changePasswordDto.getYeniSifre());
                    kullaniciRepository.save(kullanici);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else {
            return false;
        }
    }

    public List<BiletEntity> getBiletler(Long id)
    {
        return kullaniciBiletRepository.findBiletlerByKullanici(id);
    }

    public KullaniciProfiliDto getKullaniciProfili(Long id)
    {
        KullaniciEntity kullanici = kullaniciRepository.findByKullaniciID(id);
        if (kullanici==null){
            throw new EntityNotFoundException("kullanıcı bulunamadı");
        }
        return new KullaniciProfiliDto(kullanici.getAdSoyad(), kullanici.getEmail(), kullanici.getSehir(), kullanici.getTelNo());
    }
    public long getUserIdByUsername(String username){
        return kullaniciRepository.getUserIdByKullaniciAdi(username);
    }

    public boolean kullaniciProfiliDuzenle(KullaniciProfiliDto kullaniciProfiliDto,Long id)
    {
        KullaniciEntity kullanici=kullaniciRepository.findByKullaniciID(id);
        if (kullanici==null)
        {
            return false;
        }else {
            kullanici.setAdSoyad(kullaniciProfiliDto.getAdSoyad());
            kullanici.setEmail(kullaniciProfiliDto.getEmail());
            kullanici.setSehir(kullaniciProfiliDto.getSehir());
            kullanici.setTelNo(kullaniciProfiliDto.getTelNo());
            kullaniciRepository.save(kullanici);

            return true;
        }
    }

    public boolean kullaniciSehirDuzenle(Long userId, SehirDto sehirDto){
        KullaniciEntity kullanici = kullaniciRepository.findByKullaniciID(userId);
        kullanici.setSehir(new SehirEntity(sehirDto.getPlakaKodu(), sehirDto.getSehirAdi()));
        kullaniciRepository.save(kullanici);
        return true;
    }
}
