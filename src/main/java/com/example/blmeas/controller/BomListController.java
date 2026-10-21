package com.example.blmeas.controller;

import com.example.blmeas.entity.BomList;
import com.example.blmeas.service.BomTreeService;
import com.example.blmeas.service.BomTreeSqlService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bomlist")
public class BomListController {

    private final BomTreeService bomTreeService;
    private final BomTreeSqlService bomTreeSqlService;

    // 注入两个服务（参数名与成员名区分，避免歧义）
    public BomListController(BomTreeService bomTreeService, BomTreeSqlService sqlService) {
        this.bomTreeService = bomTreeService;
        this.bomTreeSqlService = sqlService;
    }

    // 平铺去重接口（预期 44 行）— 应用层递归
    @GetMapping("/bom-tree")
    public List<BomList> bomTree(@RequestParam String fatherId) {
        return bomTreeService.getBomTree(fatherId);
    }

    // 新增：数据库 CTE 递归接口（预期 44 行）— 一条 SQL
    @GetMapping("/bom-tree-sql")
    public List<BomList> bomTreeBySql(@RequestParam String fatherId) {
        return bomTreeSqlService.getBomTreeBySql(fatherId);
    }
}