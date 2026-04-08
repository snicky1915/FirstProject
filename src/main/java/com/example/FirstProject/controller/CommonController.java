package com.example.FirstProject.controller;

import com.example.FirstProject.common.CommonUtils;
import com.example.FirstProject.dto.request.CommonQueryRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/common")
public class CommonController {

    private final CommonUtils commonUtils;

    public CommonController(CommonUtils commonUtils) {
        this.commonUtils = commonUtils;
    }

    // Endpoint chung de nhan query search va tra du lieu dang JSON.
    @PostMapping("/query")
    public Map<String, Object> executeQuery(@Valid @RequestBody CommonQueryRequest request) {
        return commonUtils.executeQuery(request.getQuery());
    }
}
