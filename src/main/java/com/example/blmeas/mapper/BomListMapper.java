package com.example.blmeas.mapper;

import com.example.blmeas.entity.BomList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BomListMapper {

    // 原有方法（保留）
    List<BomList> findByFatherId(@Param("fatherId") String fatherId);

    // 新增：找最新版本（对应 Service 第31行）
    String findLatestBomVer(@Param("fatherId") String fatherId);

    // 新增：按父ID+精确版本查（对应 Service 第35行，必须加这个！）
    List<BomList> findByFatherIdAndVer(@Param("fatherId") String fatherId, @Param("bomVer") String bomVer);
}