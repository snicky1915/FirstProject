package com.example.FirstProject.controller;

import com.example.FirstProject.common.PaginationService;
import com.example.FirstProject.entity.Gi;
import com.example.FirstProject.service.GiService;
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
public class GiController {

    private final GiService giService;
    private final PaginationService paginationService;

    public GiController(GiService giService, PaginationService paginationService) {
        this.giService = giService;
        this.paginationService = paginationService;
    }

    @PostMapping("/createGi")
    public String create(@RequestBody Gi req) { return giService.createEntity(req); }

    @PostMapping("/updateGi")
    public String update(@RequestBody Gi req) { return giService.updateEntity(req); }

    @PostMapping("/deleteGi")
    public String delete(@RequestBody Gi req) { return giService.deleteById(req.getGiId()); }

    @PostMapping("/gi/issue")
    public String issue(@RequestBody Gi req) {
        return giService.issueGoods(req);
    }

    @GetMapping("/gis")
    public Page<Gi> getGis(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "giId") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Pageable pageable = paginationService.resolvePageable(page, size, sortBy, direction);
        return giService.getGis(pageable);
    }
}
