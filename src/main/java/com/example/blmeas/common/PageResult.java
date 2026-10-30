package com.example.blmeas.common;

import java.util.List;

public class PageResult<T> {

    private int total;   // 总记录数
    private int page;    // 当前页码
    private int size;    // 每页条数
    private List<T> list; // 当前页数据

    // 无参构造（MyBatis/JSON序列化需要）
    public PageResult() {}

    // 全参构造
    public PageResult(int total, int page, int size, List<T> list) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.list = list;
    }

    // Getter / Setter
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }
}