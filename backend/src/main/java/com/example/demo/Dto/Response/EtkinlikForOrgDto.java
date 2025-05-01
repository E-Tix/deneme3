package com.example.demo.Dto.Response;

import com.example.demo.Entity.OrganizatorEntity;

public class EtkinlikForOrgDto {
    private Long id;
    private String kapakFotografi;
    private String etkinlikAdi;

    public EtkinlikForOrgDto(Long id, String kapakFotografi, String etkinlikAdi) {
        this.id = id;
        this.kapakFotografi = kapakFotografi;
        this.etkinlikAdi = etkinlikAdi;
    }

    public EtkinlikForOrgDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKapakFotografi() {
        return kapakFotografi;
    }

    public void setKapakFotografi(String kapakFotografi) {
        this.kapakFotografi = kapakFotografi;
    }

    public String getEtkinlikAdi() {
        return etkinlikAdi;
    }

    public void setEtkinlikAdi(String etkinlikAdi) {
        this.etkinlikAdi = etkinlikAdi;
    }
}
