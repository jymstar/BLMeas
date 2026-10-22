package com.example.blmeas.mapper;

import com.example.blmeas.entity.BomList;
import com.example.blmeas.entity.BomTreeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.math.BigDecimal;

@Mapper
public interface BomTreeSqlMapper {

    List<BomList> expandBomTree(@Param("rootFatherId") String rootFatherId);


    //必须是两个参数，且加 @Param
    List<BomTreeVO> getBomTree(
            @Param("fatherId") String fatherId,
            @Param("defaultLossRate") BigDecimal defaultLossRate,
            @Param("orderQty") BigDecimal orderQty
    );
    // 增删改
    int insertBom(BomList bom);
    int updateBom(BomList bom);
    int deleteBom(@Param("bomId") String bomId);
}