package com.example.blmeas.controller;

import com.example.blmeas.common.PageResult;
import com.example.blmeas.entity.Material;
import com.example.blmeas.entity.MaterialVO;
import com.example.blmeas.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping("/listAll")
    public PageResult<MaterialVO> listAll(
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "") String code,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String spec,
            @RequestParam(defaultValue = "") String draw,
            @RequestParam(defaultValue = "") String attr,
            @RequestParam(defaultValue = "") String status) {

        // page=0, size=0 → Service层识别为"不分页，返回全量"
        return materialService.listMaterialWithFilters(kw, code, name, spec, draw, attr, status);
    }


    @GetMapping("/list")
    public PageResult<MaterialVO> list(
            @RequestParam(defaultValue = "") String kw,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "100") int size) { // 默认100对齐前端
        return materialService.listMaterial(kw, page, size);
    }

    @PutMapping("/update")
    public String update(@RequestBody Material material,
                         @RequestParam(defaultValue = "edit") String mode) {
        return materialService.saveMaterial(material, mode);
    }

    @PostMapping("/invalidate")
    public String invalidate(@RequestParam String materialId) {
        return materialService.invalidateMaterial(materialId);
    }
}