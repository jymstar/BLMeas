package com.example.blmeas.entity;

public class MaterialVO {

    private String selfno;
    private String materialId;
    private String materialName;
    private String describe;
    private String parameter;
    private String drawNo;
    private String color;
    private String status;
    private String forbidden;
    private String createTime;
    private String reviewTime;
    private String buyType;
    private String Unit;
    private String isBuy;
    private String isOut;
    private String warehouse;
    private String lossRate;
    private Integer leadTime;
    private java.math.BigDecimal subCost;
    private Integer isValid;

    // ===== Getter & Setter =====

    public String getSelfno() { return selfno; }
    public void setSelfno(String selfno) { this.selfno = selfno; }

    public String getMaterialId() { return materialId; }
    public void setMaterialId(String materialId) { this.materialId = materialId; }

    public String getMaterialName() { return materialName; }
    public void setMaterialName(String materialName) { this.materialName = materialName; }

    public String getDescribe() { return describe; }
    public void setDescribe(String describe) {this.describe = describe;}

    public String getParameter() { return parameter; }
    public void setParameter(String parameter) { this.parameter = parameter; }

    public String getDrawNo() { return drawNo; }
    public void setDrawNo(String drawNo) { this.drawNo = drawNo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getForbidden() { return forbidden; }
    public void setForbidden(String forbidden) { this.forbidden = forbidden; }

    public String getCreateTime() { return createTime; }
    public void setCreateTime(String createTime) { this.createTime = createTime; }

    public String getReviewTime() { return reviewTime; }
    public void setReviewTime(String reviewTime) { this.reviewTime = reviewTime; }

    public String getBuyType() { return buyType; }
    public void setBuyType(String buyType) { this.buyType = buyType; }

    public String getUnit() { return Unit; }
    public void setUnit(String Unit) { this.Unit = Unit; }

    public String getIsBuy() { return isBuy; }
    public void setIsBuy(String isBuy) { this.isBuy = isBuy; }

    public String getIsOut() { return isOut; }
    public void setIsOut(String isOut) { this.isOut = isOut; }

    public String getWarehouse() { return warehouse; }
    public void setWarehouse(String warehouse) { this.warehouse = warehouse; }

    public String getLossRate() { return lossRate; }
    public void setLossRate(String lossRate) { this.lossRate = lossRate; }

    public Integer getLeadTime() { return leadTime; }
    public void setLeadTime(Integer leadTime) { this.leadTime = leadTime; }

    public java.math.BigDecimal getSubCost() { return subCost; }
    public void setSubCost(java.math.BigDecimal subCost) { this.subCost = subCost; }

    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
}