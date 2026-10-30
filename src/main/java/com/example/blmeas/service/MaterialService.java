package com.example.blmeas.service;

import com.example.blmeas.common.PageResult;
import com.example.blmeas.entity.Material;
import com.example.blmeas.entity.MaterialVO;
import com.example.blmeas.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // ← 加这一行 import
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    public PageResult<MaterialVO> listMaterialWithFilters(
            String kw, String code, String name, String spec, String draw, String attr, String status) {

        List<MaterialVO> list = materialMapper.findListWithFilters(
                kw, code, name, spec, draw, attr, status);
        int total = materialMapper.countWithFilters(
                kw, code, name, spec, draw, attr, status);

        PageResult<MaterialVO> result = new PageResult<>();
        result.setList(list);
        result.setTotal(total);
        return result;
    }

    // 分页：默认100条，与前端一致
    public PageResult<MaterialVO> listMaterial(String kw, int page, int size) {
        if (size <= 0) size = 100; // 强制对齐前端每页100
        int offset = (page - 1) * size;
        List<MaterialVO> list = materialMapper.listMaterial(kw, offset, size);
        int total = materialMapper.countMaterial(kw);
        return new PageResult<>(total, page, size, list);
    }

    // 保存：mode="edit"真实修改，mode="copy"复制新建

    @Transactional  // ← 加在 saveMaterial 上面
    public String saveMaterial(Material material, String mode) {
        String now = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
        material.setReviewTime(now);

        // 属性联动（适配 String 类型，传 "1"/"0"）
        String attr = material.getBuyType();
        if ("外购".equals(attr)) {
            material.setIsBuy("1");
            material.setIsOut("0");
        } else if ("委外".equals(attr)) {
            material.setIsBuy("0");
            material.setIsOut("1");
        } else { // 自制
            material.setIsBuy("0");
            material.setIsOut("0");
        }

        if ("copy".equals(mode)) {
            // 复制新建：清空自增，插入新记录
            material.setSelfno(null);
            material.setCreateTime(now);
            material.setStatus("重新审核");
            material.setForbidden("否");
            material.setIsValid(1);
            materialMapper.insertMaterial(material);
            return "success";
        } else {
            // 真实修改：仅更新，不插入
            MaterialVO exist = materialMapper.selectByMaterialId(material.getMaterialId());
            if (exist == null) return "物料不存在";
            // 保留原创建时间，更新审核时间
            material.setCreateTime(exist.getCreateTime());
            // 若前端改了关键字段，可置状态为"重新审核"，这里按你图：修改后重新审核
            material.setStatus("重新审核");
            materialMapper.updateMaterial(material);
            return "success";
        }
    }

    // 逻辑删除
    @Transactional  // ← 加在 saveMaterial 上面
    public String invalidateMaterial(String materialId) {
        MaterialVO exist = materialMapper.selectByMaterialId(materialId);
        if (exist == null) return "物料不存在";
        materialMapper.invalidate(materialId);
        return "success";
    }
}