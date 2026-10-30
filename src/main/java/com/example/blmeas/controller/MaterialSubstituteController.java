package com.example.blmeas.controller;

import com.example.blmeas.entity.MaterialSubstitute;
import com.example.blmeas.entity.MaterialSubstituteVO;
import com.example.blmeas.service.MaterialSubstituteService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/substitute")
public class MaterialSubstituteController {

    private final MaterialSubstituteService service;

    public MaterialSubstituteController(MaterialSubstituteService service) {
        this.service = service;
    }

    /** 查询：双向 */
    @GetMapping
    public Map<String, Object> query(@RequestParam String materialId) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<MaterialSubstituteVO> list = service.queryByMaterialId(materialId);
            result.put("success", true);
            result.put("data", list);
            result.put("msg", "查询成功，共 " + list.size() + " 条替代关系");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    /** 新增 */
    @PostMapping
    public Map<String, Object> add(@RequestBody MaterialSubstitute entity) {
        Map<String, Object> result = new HashMap<>();
        String msg = service.addSubstitute(entity);
        if ("success".equals(msg)) {
            result.put("success", true);
            result.put("msg", "新增成功");
        } else {
            result.put("success", false);
            result.put("msg", msg);
        }
        return result;
    }

    /** 修改 */
    @PutMapping
    public Map<String, Object> update(@RequestBody MaterialSubstitute entity) {
        Map<String, Object> result = new HashMap<>();
        try {
            int rows = service.updateSubstitute(entity);
            result.put("success", rows > 0);
            result.put("msg", rows > 0 ? "修改成功" : "修改失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

    /** 删除 */
    @DeleteMapping
    public Map<String, Object> delete(@RequestParam Long id) {
        Map<String, Object> result = new HashMap<>();
        int rows = service.deleteSubstitute(id);
        result.put("success", rows > 0);
        result.put("msg", rows > 0 ? "删除成功" : "删除失败");
        return result;
    }

    /** 列表（维护页面） */

    /** 列表（维护页面）：支持关键字双向模糊查询 */
    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(defaultValue = "") String keyword) {
        List<MaterialSubstituteVO> data = service.selectValidList(keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);
        return result;
    }
}