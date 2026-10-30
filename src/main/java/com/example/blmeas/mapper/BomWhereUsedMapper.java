package com.example.blmeas.mapper;

import com.example.blmeas.entity.BomWhereUsedVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BomWhereUsedMapper {
    List<BomWhereUsedVO> getWhereUsed(@Param("sonId") String sonId);
}