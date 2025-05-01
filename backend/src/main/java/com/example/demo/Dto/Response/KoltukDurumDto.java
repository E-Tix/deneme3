package com.example.demo.Dto.Response;

public class KoltukDurumDto {
    private Long koltukId;
    private String koltukNo;
    private boolean doluMu;

    // getter, setter, constructor

    public KoltukDurumDto(Long koltukId, String koltukNo, boolean doluMu) {
        this.koltukId = koltukId;
        this.koltukNo = koltukNo;
        this.doluMu = doluMu;
    }
    public Long getKoltukId() {
        return koltukId;
    }
    public void setKoltukId(Long koltukId) {
        this.koltukId = koltukId;
    }
    public String getKoltukNo() {
        return koltukNo;
    }
    public void setKoltukNo(String koltukNo) {
        this.koltukNo = koltukNo;
    }
    public boolean isDoluMu() {
        return doluMu;
    }
    public void setDoluMu(boolean doluMu) {
        this.doluMu = doluMu;
    }
}
