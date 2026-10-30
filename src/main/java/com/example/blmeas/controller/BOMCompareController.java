package com.example.blmeas.controller;

import com.example.blmeas.service.BOMCompareService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bom")
public class BOMCompareController {

    private final BOMCompareService compareService;

    // 构造器注入（解决弱警告，推荐）
    public BOMCompareController(BOMCompareService compareService) {
        this.compareService = compareService;
    }

    @GetMapping("/compare")
    public Map<String, Object> compare(
            @RequestParam String mode,
            @RequestParam String materialIdA,
            @RequestParam(required = false) String versionA,
            @RequestParam String materialIdB,
            @RequestParam(required = false) String versionB) {

        if ("top".equals(mode)) {
            return compareService.compareTop(materialIdA, versionA, materialIdB, versionB);
        } else if ("deep".equals(mode)) {
            return compareService.compareDeep(materialIdA, materialIdB);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("msg", "mode 参数错误，仅支持 top / deep");
            return error;
        }
    }

    @GetMapping("/versions")
    public Map<String, Object> getVersions(@RequestParam String materialId) {
        return compareService.getVersions(materialId);
    }
}