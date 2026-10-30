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
    int invalidate(String bomId);
    int restore(String bomId);
    List<BomList> list(@Param("keyword") String keyword, @Param("isValid") Integer isValid);
    List<BomList> listWithPage(@Param("keyword") String keyword,
                               @Param("isValid") Integer isValid,
                               @Param("offset") int offset,
                               @Param("size") int size);

}