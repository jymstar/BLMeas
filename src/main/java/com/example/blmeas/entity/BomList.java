package com.example.blmeas.entity;

public class BomList {
    private String bomId;
    private String fatherId;
    private String fMaterialName;
    private String sonId;
    private String sMaterialName;
    private String bomVer;
    private String dataStatus;
    private Integer sonNo;
    private String unit;
    private Double fenzi;
    private Double fenmu;
    private Double lossRate;

    public String getBomId() { return bomId; }
    public void setBomId(String bomId) { this.bomId = bomId; }
    public String getFatherId() { return fatherId; }
    public void setFatherId(String fatherId) { this.fatherId = fatherId; }
    public String getFMateriaName() { return fMaterialName; }
    public void setFMateriaName(String fMaterialName) { this.fMaterialName = fMaterialName; }
    public String getSonId() { return sonId; }
    public void setSonId(String sonId) { this.sonId = sonId; }
    public String getSMateriaName() { return sMaterialName; }
    public void setSMateriaName(String sMaterialName) { this.sMaterialName = sMaterialName; }
    public String getBomVer() { return bomVer; }
    public void setBomVer(String bomVer) { this.bomVer = bomVer; }
    public String getDataStatus() { return dataStatus; }
    public void setDataStatus(String dataStatus) { this.dataStatus = dataStatus; }
    public Integer getSonNo() { return sonNo; }
    public void setSonNo(Integer sonNo) { this.sonNo = sonNo; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Double getFenzi() { return fenzi; }
    public void setFenzi(Double fenzi) { this.fenzi = fenzi; }
    public Double getFenmu() { return fenmu; }
    public void setFenmu(Double fenmu) { this.fenmu = fenmu; }
    public Double getLossRate() { return lossRate; }
    public void setLossRate(Double lossRate) { this.lossRate = lossRate; }
}