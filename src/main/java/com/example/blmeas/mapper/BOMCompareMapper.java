package com.example.blmeas.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BOMCompareMapper {

    /**
     * 模式一：按物料编码+版本号直查顶层子项
     */
    List<Map<String, Object>> selectDirectChildren(
            @Param("materialId") String materialId,
            @Param("version") String version);

    /**
     * 查询某物料编码下的所有可用版本号（去重）
     */
    List<String> selectDistinctVersions(@Param("materialId") String materialId);
}
