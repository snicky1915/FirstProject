package com.example.FirstProject.controller;

import com.example.FirstProject.common.PaginationService;
import com.example.FirstProject.entity.Gr;
import com.example.FirstProject.service.GrService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GrController {

    private final GrService grService;
    private final PaginationService paginationService;

    public GrController(GrService grService, PaginationService paginationService) {
        this.grService = grService;
        this.paginationService = paginationService;
    }

    @PostMapping("/createGr")
    public String create(@RequestBody Gr req) { return grService.createEntity(req); }

    @PostMapping("/updateGr")
    public String update(@RequestBody Gr req) { return grService.updateEntity(req); }

    @PostMapping("/deleteGr")
    public String delete(@RequestBody Gr req) { return grService.deleteById(req.getGrId()); }

    @PostMapping("/gr/receive")
    public String receive(@RequestBody Gr req) {
        return grService.receiveGoods(req);
    }

    @GetMapping("/grs")
    public Page<Gr> getGrs(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "grId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Pageable pageable = paginationService.resolvePageable(page, size, sortBy, direction);
        return grService.getGrs(pageable);
    }
}
