package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kullaniciBilet",uniqueConstraints = @UniqueConstraint(columnNames = {"bilet_biletid","kullanici_kullaniciid"}))
public class KullaniciBiletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kullaniciBiletID;

    @ManyToOne
    private KullaniciEntity kullanici;

    @OneToOne
    private BiletEntity bilet;

    public KullaniciBiletEntity(Long kullaniciBiletID, KullaniciEntity kullanici, BiletEntity bilet) {
        this.kullaniciBiletID = kullaniciBiletID;
        this.kullanici = kullanici;
        this.bilet = bilet;
    }

    public KullaniciBiletEntity(KullaniciEntity kullanici, BiletEntity bilet) {
        this.kullanici = kullanici;
        this.bilet = bilet;
    }

    public KullaniciBiletEntity(){}

    public Long getKullaniciBiletID() {
        return kullaniciBiletID;
    }

    public void setKullaniciBiletID(Long kullaniciBiletID) {
        this.kullaniciBiletID = kullaniciBiletID;
    }

    public KullaniciEntity getKullanici() {
        return kullanici;
    }

    public void setKullanici(KullaniciEntity kullanici) {
        this.kullanici = kullanici;
    }

    public BiletEntity getBilet() {
        return bilet;
    }

    public void setBilet(BiletEntity bilet) {
        this.bilet = bilet;
    }
}
