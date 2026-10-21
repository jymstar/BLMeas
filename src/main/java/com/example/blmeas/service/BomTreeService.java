package com.example.blmeas.service;

import jakarta.annotation.Resource;
import java.util.*;
import org.springframework.stereotype.Service;
import com.example.blmeas.mapper.BomListMapper;
import com.example.blmeas.entity.BomList;

@Service
public class BomTreeService {

    @Resource
    private BomListMapper bomListMapper;

    public List<BomList> getBomTree(String rootFatherId) {
        List<BomList> result = new ArrayList<>();
        // 对齐 SQL 初始 cycle_key = 根编码
        expandRecursively(rootFatherId, rootFatherId, result, 0, null);
        return result;
    }

    private void expandRecursively(String fatherId, String cycleKey,
                                   List<BomList> result, int level,
                                   String lastSonId) {
        if (level >= 10) return; // 对齐 SQL: Level_no < 10

        String latestVer = bomListMapper.findLatestBomVer(fatherId);
        if (latestVer == null) return;

        List<BomList> children = bomListMapper.findByFatherIdAndVer(fatherId, latestVer);

        // 【新增】同父+同版+同子项去重（对齐 SQL 根节点项次=1 的隐式去重）
        Set<String> localSonSet = new HashSet<>();

        for (BomList child : children) {
            String sonId = child.getSonId();
            if (sonId == null || sonId.trim().isEmpty()) continue;
            String trimmedSon = sonId.trim();

            // 1. 同层去重：同一父件同版本下，相同子项只取第一行
            String localKey = fatherId + "|" + latestVer + "|" + trimmedSon;
            if (localSonSet.contains(localKey)) continue;
            localSonSet.add(localKey);

            // 2. 精确环检测（对齐 SQL: cycle_key NOT LIKE %|sonId%）
            // 拆分成数组精确匹配，避免 contains 子串误判
            String[] paths = cycleKey.split("\\|");
            boolean inCycle = false;
            for (String p : paths) {
                if (p.equals(trimmedSon)) { inCycle = true; break; }
            }
            if (inCycle) continue; // 形成环，跳过（对齐 SQL 防环）

            // 3. 添加当前子项
            result.add(child);

            // 4. 拼接新路径并下钻（对齐 CONCAT(cycle_key, '|', 子项)）
            String newCycleKey = cycleKey + "|" + trimmedSon;
            expandRecursively(trimmedSon, newCycleKey, result, level + 1, trimmedSon);
        }
    }
}