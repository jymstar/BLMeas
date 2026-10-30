package com.example.blmeas.entity;

public class BomWhereUsedVO {
    /** 成品（第0层）：编码+名称 */
    private String col0;
    /** 第1层：编码+名称 */
    private String col1;
    /** 第2层：编码+名称 */
    private String col2;
    /** 第3层：编码+名称 */
    private String col3;
    /** 第4层：编码+名称 */
    private String col4;
    /** 第5层：编码+名称 */
    private String col5;
    /** 查询物料（第6层）：编码+名称 */
    private String col6;
    /** BOM版本（备用，取路径中最深那条） */
    private String bomVer;

    // getter / setter
    public String getCol0() { return col0; }
    public void setCol0(String col0) { this.col0 = col0; }
    public String getCol1() { return col1; }
    public void setCol1(String col1) { this.col1 = col1; }
    public String getCol2() { return col2; }
    public void setCol2(String col2) { this.col2 = col2; }
    public String getCol3() { return col3; }
    public void setCol3(String col3) { this.col3 = col3; }
    public String getCol4() { return col4; }
    public void setCol4(String col4) { this.col4 = col4; }
    public String getCol5() { return col5; }
    public void setCol5(String col5) { this.col5 = col5; }
    public String getCol6() { return col6; }
    public void setCol6(String col6) { this.col6 = col6; }
    public String getBomVer() { return bomVer; }
    public void setBomVer(String bomVer) { this.bomVer = bomVer; }
}