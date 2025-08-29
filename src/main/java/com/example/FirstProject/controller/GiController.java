package com.example.FirstProject.controller;

import com.example.FirstProject.entity.Gi;
import com.example.FirstProject.service.GiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GiController {

    private final GiService giService;
    public GiController(GiService giService) { this.giService = giService; }

    @PostMapping("/createGi")
    public String create(@RequestBody Gi req) { return giService.createEntity(req); }

    @PostMapping("/updateGi")
    public String update(@RequestBody Gi req) { return giService.updateEntity(req); }

    @PostMapping("/deleteGi")
    public String delete(@RequestBody Gi req) { return giService.deleteById(req.getGiId()); }
}