package com.example.blmeas.controller;

import com.example.blmeas.entity.BomWhereUsedVO;
import com.example.blmeas.service.BomWhereUsedService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bom")
public class BomWhereUsedController {

    private final BomWhereUsedService whereUsedService;

    public BomWhereUsedController(BomWhereUsedService whereUsedService) {
        this.whereUsedService = whereUsedService;
    }

    @GetMapping("/where-used")
    public Map<String, Object> whereUsed(@RequestParam String sonId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<BomWhereUsedVO> list = whereUsedService.getWhereUsed(sonId);
            result.put("success", true);
            result.put("data", list);
            result.put("msg", "查询成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }
}