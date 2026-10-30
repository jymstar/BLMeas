package com.example.blmeas.mapper;

import com.example.blmeas.entity.Material;
import com.example.blmeas.entity.MaterialVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MaterialMapper {
    List<MaterialVO> listMaterial(@Param("kw") String kw, @Param("offset") int offset, @Param("size") int size);
    int countMaterial(@Param("kw") String kw);
    MaterialVO selectByMaterialId(@Param("materialId") String materialId);
    int insertMaterial(Material material);
    int updateMaterial(Material material);
    int invalidate(@Param("materialId") String materialId);

    List<MaterialVO> findListWithFilters(@Param("kw") String kw,
                                         @Param("code") String code,
                                         @Param("name") String name,
                                         @Param("spec") String spec,
                                         @Param("draw") String draw,
                                         @Param("attr") String attr,
                                         @Param("status") String status);

    int countWithFilters(@Param("kw") String kw,
                         @Param("code") String code,
                         @Param("name") String name,
                         @Param("spec") String spec,
                         @Param("draw") String draw,
                         @Param("attr") String attr,
                         @Param("status") String status);



}