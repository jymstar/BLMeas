package com.example.blmeas.service;

import com.example.blmeas.entity.MaterialSubstitute;
import com.example.blmeas.entity.MaterialSubstituteVO;
import com.example.blmeas.mapper.MaterialSubstituteMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaterialSubstituteService {

    private final MaterialSubstituteMapper mapper;

    public MaterialSubstituteService(MaterialSubstituteMapper mapper) {
        this.mapper = mapper;
    }

    /** 双向查询 */
    public List<MaterialSubstituteVO> queryByMaterialId(String materialId) {
        return mapper.queryByMaterialId(materialId);
    }

    /** 新增（带查重） */
    public String addSubstitute(MaterialSubstitute entity) {
        // 校验：不能自己替代自己
        if (entity.getOriginalMaterialId().equals(entity.getSubstituteMaterialId())) {
            return "不能自己替代自己";
        }
        // 校验：用量不能为0
        if (entity.getOriginalQty() == null || entity.getOriginalQty().compareTo(java.math.BigDecimal.ZERO) <= 0
                || entity.getSubstituteQty() == null || entity.getSubstituteQty().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return "用量必须大于0";
        }
        // 查重
        int exists = mapper.countExists(entity.getOriginalMaterialId(), entity.getSubstituteMaterialId());
        if (exists > 0) {
            return "该替代关系已存在（含反向）";
        }
        mapper.insert(entity);
        return "success";
    }

    /** 修改 */
    public int updateSubstitute(MaterialSubstitute entity) {
        return mapper.update(entity.getId(), entity.getOriginalQty(),
                entity.getSubstituteQty(), entity.getSubType(),
                entity.getRemark());
    }

    /** 软删除 */
    public int deleteSubstitute(Long id) {
        return mapper.softDelete(id);
    }

    /** 列表 */
    public List<MaterialSubstituteVO> listAll() {
        return mapper.listAll();
    }
    public List<MaterialSubstituteVO> selectValidList(String keyword) {
        return mapper.selectValidList(keyword);
    }

}