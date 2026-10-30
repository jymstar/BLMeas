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
    private Integer isValid;  // 1=有效, 0=无效
    private String leadTime;
    private String nodeCost;

    public String getBomId() { return bomId; }
    public void setBomId(String bomId) { this.bomId = bomId; }
    public String getFatherId() { return fatherId; }
    public void setFatherId(String fatherId) { this.fatherId = fatherId; }
    public String getFMaterialName() { return fMaterialName; }
    public void setFMaterialName(String fMaterialName) { this.fMaterialName = fMaterialName; }
    public String getSonId() { return sonId; }
    public void setSonId(String sonId) { this.sonId = sonId; }
    public String getSMaterialName() { return sMaterialName; }
    public void setSMaterialName(String sMaterialName) { this.sMaterialName = sMaterialName; }
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
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public String getLeadTime() { return leadTime; }
    public void setLeadTime(String leadTime) { this.leadTime = leadTime; }
    public String getNodeCost() { return nodeCost; }
    public void setNodeCost(String nodeCost) { this.nodeCost = nodeCost; }

}