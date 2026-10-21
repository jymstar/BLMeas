package com.example.blmeas.entity;

public class BomList {
    private String fatherId;
    private String sonId;
    private String bomVer;
    private String dataStatus;
    private Double fenzi;
    private Double fenmu;

    // 按需补充 getter/setter
    public String getFatherId() { return fatherId; }
    public void setFatherId(String fatherId) { this.fatherId = fatherId; }
    public String getSonId() { return sonId; }
    public void setSonId(String sonId) { this.sonId = sonId; }
    public String getBomVer() { return bomVer; }
    public void setBomVer(String bomVer) { this.bomVer = bomVer; }
    public String getDataStatus() { return dataStatus; }
    public void setDataStatus(String dataStatus) { this.dataStatus = dataStatus; }
    public Double getFenzi() { return fenzi; }
    public void setFenzi(Double fenzi) { this.fenzi = fenzi; }
    public Double getFenmu() { return fenmu; }
    public void setFenmu(Double fenmu) { this.fenmu = fenmu; }
}