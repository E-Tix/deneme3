package com.example.demo.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;

@Entity
@Table(name = "Bilet")
public class BiletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long biletID;

    @Column(name = "odendiMi",nullable = false)
    private Boolean odendiMi = false;

    @Column(name = "odenenMiktar")
    private Float odenenMiktar;

    @OneToOne(mappedBy = "bilet")
    private KullaniciBiletEntity kullaniciBilet;

    @CreationTimestamp
    @Column(name = "olusturulmaZamani")
    private Timestamp olusturmaZamani;


    public BiletEntity(Long biletID, Boolean odendiMi, Float odenenMiktar, Timestamp olusturmaZamani) {
        this.biletID = biletID;
        this.odendiMi = odendiMi;
        this.odenenMiktar = odenenMiktar;
        this.olusturmaZamani = olusturmaZamani;
    }

    public BiletEntity(Boolean odendiMi,Float odenenMiktar)
    {
        this.odendiMi=odendiMi;
        this.odenenMiktar=odenenMiktar;
    }

    public BiletEntity()
    {
        //abcde
    }

    public Long getBiletID() {
        return biletID;
    }

    public void setBiletID(Long biletID) {
        this.biletID = biletID;
    }

    public Boolean getOdendiMi() {
        return odendiMi;
    }

    public void setOdendiMi(Boolean odendiMi) {
        this.odendiMi = odendiMi;
    }

    public Float getOdenenMiktar() {
        return odenenMiktar;
    }

    public void setOdenenMiktar(Float odenenMiktar) {
        this.odenenMiktar = odenenMiktar;
    }

    public Timestamp getOlusturmaZamani() {
        return olusturmaZamani;
    }

    public void setOlusturmaZamani(Timestamp olusturmaZamani) {
        this.olusturmaZamani = olusturmaZamani;
    }
}
