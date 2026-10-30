package com.example.blmeas.service;

import com.example.blmeas.mapper.BOMCompareMapper;
import com.example.blmeas.service.BomTreeSqlService;   // ← 修1：加import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.lang.reflect.Method;                        // ← 修2：反射需要

@Service
public class BOMCompareService {

    private final BOMCompareMapper compareMapper;
    private final BomTreeSqlService bomTreeSqlService;

    @Autowired(required = false)
    public BOMCompareService(BOMCompareMapper compareMapper, BomTreeSqlService bomTreeSqlService) {
        this.compareMapper = compareMapper;
        this.bomTreeSqlService = bomTreeSqlService;
    }

    // ========== 版本列表 ==========
    public Map<String, Object> getVersions(String materialId) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("versions", compareMapper.selectDistinctVersions(materialId));
        return res;
    }

    // ========== 模式一：顶层对比 ==========
    public Map<String, Object> compareTop(String matA, String verA, String matB, String verB) {
        Map<String, Object> res = new HashMap<>();
        if (verA == null || verA.isEmpty()) {
            List<String> vs = compareMapper.selectDistinctVersions(matA);
            verA = vs != null && !vs.isEmpty() ? vs.get(0) : "";
        }
        if (verB == null || verB.isEmpty()) {
            List<String> vs = compareMapper.selectDistinctVersions(matB);
            verB = vs != null && !vs.isEmpty() ? vs.get(0) : "";
        }

        List<Map<String, Object>> listA = compareMapper.selectDirectChildren(matA, verA);
        List<Map<String, Object>> listB = compareMapper.selectDirectChildren(matB, verB);

        res.put("success", true);
        res.put("mode", "top");
        res.put("actualVersionA", verA);
        res.put("actualVersionB", verB);
        res.put("listA", listA);
        res.put("listB", listB);
        res.put("diff", diffList(listA, listB));
        return res;
    }

    // ========== 模式二：递归全量对比 ==========
    public Map<String, Object> compareDeep(String matA, String matB) {
        Map<String, Object> res = new HashMap<>();
        if (bomTreeSqlService == null) {
            res.put("success", false);
            res.put("msg", "BomTreeSqlService 未注入，请检查该类是否加了 @Service");
            return res;
        }

        List<?> treeA;
        List<?> treeB;
        try {
            // 修3：实际方法签名是 (String, BigDecimal, BigDecimal)
            Method method = bomTreeSqlService.getClass().getMethod(
                    "getBomTree", String.class, BigDecimal.class, BigDecimal.class);
            treeA = (List<?>) method.invoke(bomTreeSqlService, matA, BigDecimal.ZERO, BigDecimal.ONE);
            treeB = (List<?>) method.invoke(bomTreeSqlService, matB, BigDecimal.ZERO, BigDecimal.ONE);
        } catch (NoSuchMethodException e) {
            // 兜底：试 getBomTreeBySql(String)
            try {
                Method method2 = bomTreeSqlService.getClass().getMethod("getBomTreeBySql", String.class);
                treeA = (List<?>) method2.invoke(bomTreeSqlService, matA);
                treeB = (List<?>) method2.invoke(bomTreeSqlService, matB);
            } catch (Exception ex) {
                res.put("success", false);
                res.put("msg", "未找到递归方法，请检查 BomTreeSqlService 方法名");
                ex.printStackTrace();
                return res;
            }
        } catch (Exception e) {
            res.put("success", false);
            res.put("msg", "递归查询异常: " + e.getMessage());
            e.printStackTrace();
            return res;
        }

        List<Map<String, Object>> flatA = flattenTree(treeA);
        List<Map<String, Object>> flatB = flattenTree(treeB);

        res.put("success", true);
        res.put("mode", "deep");
        res.put("listA", flatA);
        res.put("listB", flatB);
        res.put("diff", diffList(flatA, flatB));
        return res;
    }

    // ========== 比对算法 ==========
    private List<Map<String, Object>> diffList(List<Map<String, Object>> listA, List<Map<String, Object>> listB) {
        List<Map<String, Object>> diff = new ArrayList<>();
        Map<String, Map<String, Object>> mapA = new HashMap<>();
        Map<String, Map<String, Object>> mapB = new HashMap<>();

        for (Map<String, Object> row : listA) {
            String key = String.valueOf(row.get("childMaterialId"));
            mapA.put(key, row);
        }
        for (Map<String, Object> row : listB) {
            String key = String.valueOf(row.get("childMaterialName")); // 注意：这里之前用childMaterialId，保持一致
            // ↑ 等等，上面写错了，应该是 childMaterialId
            // 实际应该是：
            // String key = String.valueOf(row.get("childMaterialId"));
            // 但下面重新遍历时我直接修正
        }
        // 修正：重新建mapB用正确key
        mapB.clear();
        for (Map<String, Object> row : listB) {
            String key = String.valueOf(row.get("childMaterialId"));
            mapB.put(key, row);
        }

        // 找删除（在A不在B）
        for (String key : mapA.keySet()) {
            if (!mapB.containsKey(key)) {
                Map<String, Object> row = new HashMap<>(mapA.get(key));
                row.put("diffType", "deleted");
                row.put("inA", true);
                row.put("inB", false);
                diff.add(row);
            }
        }
        // 找新增（在B不在A）
        for (String key : mapB.keySet()) {
            if (!mapA.containsKey(key)) {
                Map<String, Object> row = new HashMap<>(mapB.get(key));
                row.put("diffType", "added");
                row.put("inA", false);
                row.put("inB", true);
                Map<String, Object> aRow = findByName(mapA, String.valueOf(row.get("childMaterialName")));
                if (aRow != null) row.put("substituteHint", "疑似替代 " + aRow.get("childMaterialId"));
                diff.add(row);
            }
        }
        // 找变更（都在，用量/损耗不同）
        for (String key : mapA.keySet()) {
            if (mapB.containsKey(key)) {
                Map<String, Object> a = mapA.get(key);
                Map<String, Object> b = mapB.get(key);
                Object usageA = a.get("usageQty");
                Object usageB = b.get("usageQty");
                Object lossA = a.get("lossRate");
                Object lossB = b.get("lossRate");
                boolean usageChanged = !Objects.equals(usageA, usageB);
                boolean lossChanged = !Objects.equals(lossA, lossB);
                if (usageChanged || lossChanged) {
                    Map<String, Object> row = new HashMap<>(b);
                    row.put("diffType", "changed");
                    row.put("inA", true);
                    row.put("inB", true);
                    row.put("usageChanged", usageChanged);
                    row.put("lossChanged", lossChanged);
                    row.put("changed", true);
                    row.put("usageA", usageA);
                    row.put("usageB", usageB);
                    row.put("lossA", lossA);
                    row.put("lossB", lossB);
                    diff.add(row);
                }
            }
        }
        return diff;
    }

    private Map<String, Object> findByName(Map<String, Map<String, Object>> map, String name) {
        for (Map<String, Object> row : map.values()) {
            if (name != null && name.equals(row.get("childMaterialName"))) return row;
        }
        return null;
    }

    // 递归结果转平铺
    private List<Map<String, Object>> flattenTree(List<?> tree) {
        List<Map<String, Object>> flat = new ArrayList<>();
        if (tree == null) return flat;
        for (Object obj : tree) {
            Map<String, Object> row = new HashMap<>();
            row.put("childMaterialId", getField(obj, "子项物料编码"));
            row.put("childMaterialName", getField(obj, "子项物料名称"));
            row.put("specParameter", getField(obj, "规格型号"));
            // 修4：BigDecimal → double（null安全）
            Object u = getField(obj, "usageQty");
            row.put("usageQty", u instanceof BigDecimal ? ((BigDecimal) u).doubleValue() : u);
            Object l = getField(obj, "lossRate");
            row.put("lossRate", l instanceof BigDecimal ? ((BigDecimal) l).doubleValue() : l);
            row.put("unit", getField(obj, "单位"));
            row.put("materialAttr", getField(obj, "子项物料属性"));
            flat.add(row);
            // 修5：BomTreeVO 没有 children 字段，递归展开去掉（不会NPE但没必要）
        }
        return flat;
    }

    private Object getField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field f = obj.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            try {
                String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                return obj.getClass().getMethod(getter).invoke(obj);
            } catch (Exception ex) {
                return null;
            }
        }
    }
}