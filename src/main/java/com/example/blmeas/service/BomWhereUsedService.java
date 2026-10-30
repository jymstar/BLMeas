package com.example.blmeas.service;

import com.example.blmeas.entity.BomWhereUsedVO;
import com.example.blmeas.mapper.BomWhereUsedMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BomWhereUsedService {

    private final BomWhereUsedMapper whereUsedMapper;

    public BomWhereUsedService(BomWhereUsedMapper whereUsedMapper) {
        this.whereUsedMapper = whereUsedMapper;
    }

    public List<BomWhereUsedVO> getWhereUsed(String sonId) {
        return whereUsedMapper.getWhereUsed(sonId);
    }
}