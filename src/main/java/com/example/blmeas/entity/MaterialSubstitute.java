package com.example.blmeas.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MaterialSubstitute {
    private Long id;
    private String originalMaterialId;
    private String substituteMaterialId;
    private BigDecimal originalQty;
    private BigDecimal substituteQty;
    private Integer subType;
    private String remark;
    private Integer isValid;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOriginalMaterialId() { return originalMaterialId; }
    public void setOriginalMaterialId(String originalMaterialId) { this.originalMaterialId = originalMaterialId; }
    public String getSubstituteMaterialId() { return substituteMaterialId; }
    public void setSubstituteMaterialId(String substituteMaterialId) { this.substituteMaterialId = substituteMaterialId; }
    public BigDecimal getOriginalQty() { return originalQty; }
    public void setOriginalQty(BigDecimal originalQty) { this.originalQty = originalQty; }
    public BigDecimal getSubstituteQty() { return substituteQty; }
    public void setSubstituteQty(BigDecimal substituteQty) { this.substituteQty = substituteQty; }
    public Integer getSubType() { return subType; }
    public void setSubType(Integer subType) { this.subType = subType; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public LocalDateTime getUpdateTime() { return updateTime; }
    public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}