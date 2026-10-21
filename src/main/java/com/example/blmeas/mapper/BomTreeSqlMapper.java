package com.example.blmeas.mapper;

import com.example.blmeas.entity.BomList;
import org.apache.ibatis.annotations.Mapper;   // ← 加这行
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper                                          // ← 加这个注解
public interface BomTreeSqlMapper {

    List<BomList> expandBomTree(@Param("rootFatherId") String rootFatherId);
}