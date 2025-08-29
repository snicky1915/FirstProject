package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Gr;
import com.example.FirstProject.service.GrService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GrController {

    private final GrService grService;
    public GrController(GrService grService) { this.grService = grService; }

    @PostMapping("/createGr")
    public String create(@RequestBody Gr req) { return grService.createEntity(req); }

    @PostMapping("/updateGr")
    public String update(@RequestBody Gr req) { return grService.updateEntity(req); }

    @PostMapping("/deleteGr")
    public String delete(@RequestBody Gr req) { return grService.deleteById(req.getGrId()); }
}