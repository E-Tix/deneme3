package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Koltuk")
public class KoltukEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long koltukID;

    
    @ManyToOne
    private SalonEntity salon;
    
    @Column(name = "satir",nullable = false)
    private int satir;

    @Column(name = "sutun",nullable = false)
    private int sutun;


    public KoltukEntity(Long koltukID, SalonEntity salon, int satir, int sutun) {
        this.koltukID = koltukID;
        this.salon = salon;
        this.satir = satir;
        this.sutun = sutun;
    }
    
    public KoltukEntity(){}

    public Long getKoltukID() {
        return koltukID;
    }

    public void setKoltukID(Long koltukID) {
        this.koltukID = koltukID;
    }

    public SalonEntity getSalon() {
        return salon;
    }

    public void setSalon(SalonEntity salon) {
        this.salon = salon;
    }

    public int getSatir() {
        return satir;
    }

    public void setSatir(int satir) {
        this.satir = satir;
    }

    public int getSutun() {
        return sutun;
    }

    public void setSutun(int sutun) {
        this.sutun = sutun;
    }
}
