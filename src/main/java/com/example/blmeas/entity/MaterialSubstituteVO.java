package com.example.blmeas.entity;

import java.math.BigDecimal;

public class MaterialSubstituteVO {
    private Long id;
    private String originalMaterialId;
    private String substituteMaterialId;
    private String originalName;
    private String substituteName;
    private BigDecimal originalQty;
    private BigDecimal substituteQty;
    private Integer subType;
    private String subTypeName;
    private String remark;
    private Integer isValid;
    /** 查询方向：true=传入的是original，false=传入的是substitute */
    private Boolean isOriginalQuery;
    /** 换算系数 n = substituteQty / originalQty */
    private BigDecimal nRatio;
    /** 反算系数 1/n */
    private BigDecimal invNRatio;

    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getOriginalMaterialId() { return originalMaterialId; }
    public void setOriginalMaterialId(String originalMaterialId) { this.originalMaterialId = originalMaterialId; }
    public String getSubstituteMaterialId() { return substituteMaterialId; }
    public void setSubstituteMaterialId(String substituteMaterialId) { this.substituteMaterialId = substituteMaterialId; }
    public String getOriginalName() { return originalName; }
    public void setOriginalName(String originalName) { this.originalName = originalName; }
    public String getSubstituteName() { return substituteName; }
    public void setSubstituteName(String substituteName) { this.substituteName = substituteName; }
    public BigDecimal getOriginalQty() { return originalQty; }
    public void setOriginalQty(BigDecimal originalQty) { this.originalQty = originalQty; }
    public BigDecimal getSubstituteQty() { return substituteQty; }
    public void setSubstituteQty(BigDecimal substituteQty) { this.substituteQty = substituteQty; }
    public Integer getSubType() { return subType; }
    public void setSubType(Integer subType) { this.subType = subType; }
    public String getSubTypeName() { return subTypeName; }
    public void setSubTypeName(String subTypeName) { this.subTypeName = subTypeName; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public Integer getIsValid() { return isValid; }
    public void setIsValid(Integer isValid) { this.isValid = isValid; }
    public Boolean getIsOriginalQuery() { return isOriginalQuery; }
    public void setIsOriginalQuery(Boolean isOriginalQuery) { this.isOriginalQuery = isOriginalQuery; }
    public BigDecimal getNRatio() { return nRatio; }
    public void setNRatio(BigDecimal nRatio) { this.nRatio = nRatio; }
    public BigDecimal getInvNRatio() { return invNRatio; }
    public void setInvNRatio(BigDecimal invNRatio) { this.invNRatio = invNRatio; }
}