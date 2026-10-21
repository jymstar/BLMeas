package com.example.blmeas.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.example.blmeas.mapper.BomTreeSqlMapper;
import com.example.blmeas.entity.BomList;
import java.util.List;

@Service
public class BomTreeSqlService {

    @Resource
    private BomTreeSqlMapper bomTreeSqlMapper;

    public List<BomList> getBomTreeBySql(String rootFatherId) {
        return bomTreeSqlMapper.expandBomTree(rootFatherId);
    }
}