package com.example.blmeas.service;
import java.math.BigDecimal;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.blmeas.mapper.BomTreeSqlMapper;
import com.example.blmeas.entity.BomList;
import com.example.blmeas.entity.BomTreeVO;
import java.util.List;

@Service
public class BomTreeSqlService {

    @Resource
    private BomTreeSqlMapper bomTreeSqlMapper;

    public List<BomList> getBomTreeBySql(String rootFatherId) {
        return bomTreeSqlMapper.expandBomTree(rootFatherId);
    }


    public List<BomTreeVO> getBomTree(String fatherId, BigDecimal defaultLossRate, BigDecimal orderQty) {
        return bomTreeSqlMapper.getBomTree(fatherId, defaultLossRate, orderQty);
    }
    // 增删改
    public int add(BomList bom) {
        return bomTreeSqlMapper.insertBom(bom);
    }

    public int update(BomList bom) {
        return bomTreeSqlMapper.updateBom(bom);
    }

    public int delete(String bomId) {
        return bomTreeSqlMapper.deleteBom(bomId);
    }
    // ===== 新增：软删除/恢复/列表查询 =====
    public int invalidate(String bomId) {
        return bomTreeSqlMapper.invalidate(bomId);
    }

    public int restore(String bomId) {
        return bomTreeSqlMapper.restore(bomId);
    }

    public List<BomList> list(String keyword, Integer isValid, int page, int size) {
        int offset = (page - 1) * size;
        return bomTreeSqlMapper.listWithPage(keyword, isValid, offset, size);
    }


}