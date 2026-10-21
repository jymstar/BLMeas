package com.example.blmeas.mapper;

import com.example.blmeas.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MaterialMapper {
    List<Material> findAll();
}