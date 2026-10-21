package com.example.blmeas.controller;

import com.example.blmeas.entity.Material;
import com.example.blmeas.mapper.MaterialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    @Autowired
    private MaterialMapper materialMapper;

    @GetMapping
    public List<Material> list() {
        return materialMapper.findAll();
    }
}
