package com.example.blmeas.controller;

import java.math.BigDecimal;
import com.example.blmeas.entity.BomList;
import com.example.blmeas.entity.BomTreeVO;
import com.example.blmeas.service.BomTreeService;
import com.example.blmeas.service.BomTreeSqlService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bomlist")
@CrossOrigin(origins = "*")
public class BomListController {

    private final BomTreeService bomTreeService;
    private final BomTreeSqlService bomTreeSqlService;

    public BomListController(BomTreeService bomTreeService, BomTreeSqlService sqlService) {
        this.bomTreeService = bomTreeService;
        this.bomTreeSqlService = sqlService;
    }

    // 平铺去重接口（应用层递归）
    @GetMapping("/bom-tree")
    public List<BomList> bomTree(@RequestParam String fatherId) {
        return bomTreeService.getBomTree(fatherId);
    }

    // 数据库 CTE 递归接口（原版，返回 BomList）
    @GetMapping("/bom-tree-sql")
    public List<BomList> bomTreeBySql(@RequestParam String fatherId) {
        return bomTreeSqlService.getBomTreeBySql(fatherId);
    }

    // 新增：CTE + 双用量 + 展开项次（返回 BomTreeVO）
    @GetMapping("/bom-tree-full")
    public List<BomTreeVO> getBomTree(
            @RequestParam("fatherId") String fatherId,
            @RequestParam(value = "defaultLossRate", defaultValue = "0.5") BigDecimal defaultLossRate,
            @RequestParam(value = "orderQty", defaultValue = "10000") BigDecimal orderQty) {
        return bomTreeSqlService.getBomTree(fatherId, defaultLossRate, orderQty);
    }
    // ===== 增删改 =====

    @PostMapping("/add")
    public String add(@RequestBody BomList bom) {
        // 新增时默认有效
        if (bom.getIsValid() == null) {
            bom.setIsValid(1);
        }
        bomTreeSqlService.add(bom);
        return "success";
    }

    @PutMapping("/update")
    public String update(@RequestBody BomList bom) {
        bomTreeSqlService.update(bom);
        return "success";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String bomId) {
        bomTreeSqlService.delete(bomId);
        return "success";
    }
    // ★★★ 新增：软删除（无效化）★★★
    @PostMapping("/invalidate")
    public String invalidate(@RequestParam String bomId) {
        bomTreeSqlService.invalidate(bomId);  // UPDATE bom_list SET is_valid = 0 WHERE bom_id = ?
        return "success";
    }
    // ===== 新增：update 接口 =====


    // ★★★ 新增：恢复有效 ★★★
    @PostMapping("/restore")
    public String restore(@RequestParam String bomId) {
        bomTreeSqlService.restore(bomId);  // UPDATE bom_list SET is_valid = 1 WHERE bom_id = ?
        return "success";
    }

    // ★★★ 新增：列表查询（带状态过滤）★★★


    @GetMapping("/list")
    public List<BomList> list(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer isValid,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "50") Integer size) {
        return bomTreeSqlService.list(keyword, isValid, page, size);
    }


}