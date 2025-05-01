package com.example.demo.Dto.Request;

import com.example.demo.Entity.EtkinlikTurEntity;
import com.example.demo.Entity.SalonEntity;
import com.example.demo.Entity.SehirEntity;

import java.sql.Timestamp;
import java.util.List;

public class EtkinlikEkleDto {
    private List<SeansEkleDto> seansEkleDtoList;
    private EtkinlikTurEntity etkinlikTur;
    private SalonEntity salon;
    private SehirEntity sehir;
    private String etkinlikAdi;
    private String kapakFotografi;
    private String etkinlikAciklamasi;
    private int yasSiniri;
    private Timestamp etkinlikSuresi;
    private float biletFiyati;

    public EtkinlikEkleDto(SalonEntity salon,List<SeansEkleDto> seansEkleDtoList, EtkinlikTurEntity etkinlikTur, SehirEntity sehir, String etkinlikAdi, String kapakFotografi, String etkinlikAciklamasi, int yasSiniri, Timestamp etkinlikSuresi, float biletFiyati) {
        this.salon=salon;
        this.seansEkleDtoList = seansEkleDtoList;
        this.etkinlikTur = etkinlikTur;
        this.sehir = sehir;
        this.etkinlikAdi = etkinlikAdi;
        this.kapakFotografi = kapakFotografi;
        this.etkinlikAciklamasi = etkinlikAciklamasi;
        this.yasSiniri = yasSiniri;
        this.etkinlikSuresi = etkinlikSuresi;
        this.biletFiyati = biletFiyati;
    }

    public EtkinlikEkleDto() {
    }

    public SalonEntity getSalon() {
        return salon;
    }

    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public List<SeansEkleDto> getSeansEkleDtoList() {
        return seansEkleDtoList;
    }

    public void setSeansEkleDtoList(List<SeansEkleDto> seansEkleDtoList) {
        this.seansEkleDtoList = seansEkleDtoList;
    }

    public EtkinlikTurEntity getEtkinlikTur() {
        return etkinlikTur;
    }

    public void setEtkinlikTur(EtkinlikTurEntity etkinlikTur) {
        this.etkinlikTur = etkinlikTur;
    }

    public SehirEntity getSehir() {
        return sehir;
    }

    public void setSehir(SehirEntity sehir) {
        this.sehir = sehir;
    }

    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }

    public String getKapakFotografi() {
        return kapakFotografi;
    }

    public void setKapakFotografi(String kapakFotografi) {
        this.kapakFotografi = kapakFotografi;
    }

    public String getEtkinlikAciklamasi() {
        return etkinlikAciklamasi;
    }

    public void setEtkinlikAciklamasi(String etkinlikAciklamasi) {
        this.etkinlikAciklamasi = etkinlikAciklamasi;
    }

    public int getYasSiniri() {
        return yasSiniri;
    }

    public void setYasSiniri(int yasSiniri) {
        this.yasSiniri = yasSiniri;
    }

    public Timestamp getEtkinlikSuresi() {
        return etkinlikSuresi;
    }

    public void setEtkinlikSuresi(Timestamp etkinlikSuresi) {
        this.etkinlikSuresi = etkinlikSuresi;
    }

    public float getBiletFiyati() {
        return biletFiyati;
    }

    public void setBiletFiyati(float biletFiyati) {
        this.biletFiyati = biletFiyati;
    }
}
