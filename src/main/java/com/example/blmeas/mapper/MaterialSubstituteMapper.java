package com.example.blmeas.mapper;

import com.example.blmeas.entity.MaterialSubstitute;
import com.example.blmeas.entity.MaterialSubstituteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MaterialSubstituteMapper {

    /** 双向查询：传A或B都能查到 */
    List<MaterialSubstituteVO> queryByMaterialId(@Param("materialId") String materialId);

    /** 查重：A→B 或 B→A 是否已存在 */
    int countExists(@Param("materialId1") String materialId1, @Param("materialId2") String materialId2);

    /** 新增 */
    int insert(MaterialSubstitute entity);

    /** 修改 */
    int update(@Param("id") Long id,
               @Param("originalQty") java.math.BigDecimal originalQty,
               @Param("substituteQty") java.math.BigDecimal substituteQty,
               @Param("subType") Integer subType,
               @Param("remark") String remark);

    /** 软删除 */
    int softDelete(@Param("id") Long id);

    /** 列表（维护页面用，可选分页） */
    List<MaterialSubstituteVO> listAll();
    List<MaterialSubstituteVO> selectValidList(@Param("keyword") String keyword);

}