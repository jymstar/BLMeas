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
}