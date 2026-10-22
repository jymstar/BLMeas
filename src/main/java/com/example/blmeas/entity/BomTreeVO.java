package com.example.blmeas.entity;

import java.math.BigDecimal;

public class BomTreeVO {

    private String bomMid;
    private String fatherId;
    private String fMaterialName;
    private Integer 层级;
    private String 展开项次;
    private Integer 子项项次;
    private String 子项物料编码;
    private String 子项物料名称;
    private String 规格型号;
    private String 单位;
    private BigDecimal 用量分子;
    private BigDecimal 用量分母;
    private BigDecimal 无损耗数量;
    private BigDecimal 含损耗数量;
    private String 子项物料属性;
    private BigDecimal 单层用量;
    private BigDecimal 本层损耗率;

    // getter / setter 全部生成
    public String getBomMid() { return bomMid; }
    public void setBomMid(String bomMid) { this.bomMid = bomMid; }
    public String getFatherId() { return fatherId; }
    public void setFatherId(String fatherId) { this.fatherId = fatherId; }
    public String getFMaterialName() { return fMaterialName; }
    public void setFMaterialName(String fMaterialName) { this.fMaterialName = fMaterialName; }
    public Integer get层级() { return 层级; }
    public void set层级(Integer 层级) { this.层级 = 层级; }
    public String get展开项次() { return 展开项次; }
    public void set展开项次(String 展开项次) { this.展开项次 = 展开项次; }
    public Integer get子项项次() { return 子项项次; }
    public void set子项项次(Integer 子项项次) { this.子项项次 = 子项项次; }
    public String get子项物料编码() { return 子项物料编码; }
    public void set子项物料编码(String 子项物料编码) { this.子项物料编码 = 子项物料编码; }
    public String get子项物料名称() { return 子项物料名称; }
    public void set子项物料名称(String 子项物料名称) { this.子项物料名称 = 子项物料名称; }
    public String get规格型号() { return 规格型号; }
    public void set规格型号(String 规格型号) { this.规格型号 = 规格型号; }
    public String get单位() { return 单位; }
    public void set单位(String 单位) { this.单位 = 单位; }
    public BigDecimal get用量分子() { return 用量分子; }
    public void set用量分子(BigDecimal 用量分子) { this.用量分子 = 用量分子; }
    public BigDecimal get用量分母() { return 用量分母; }
    public void set用量分母(BigDecimal 用量分母) { this.用量分母 = 用量分母; }
    public BigDecimal get无损耗数量() { return 无损耗数量; }
    public void set无损耗数量(BigDecimal 无损耗数量) { this.无损耗数量 = 无损耗数量; }
    public BigDecimal get含损耗数量() { return 含损耗数量; }
    public void set含损耗数量(BigDecimal 含损耗数量) { this.含损耗数量 = 含损耗数量; }
    public String get子项物料属性() { return 子项物料属性; }
    public void set子项物料属性(String 子项物料属性) { this.子项物料属性 = 子项物料属性; }
}