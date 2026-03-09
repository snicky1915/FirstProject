package com.example.FirstProject.client;

import com.example.FirstProject.dto.DiflowRequestVO;
import com.example.FirstProject.dto.PersonBodyVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "diflowFeignClient", url = "${diflow.client.url}")
public interface DiflowFeignClient {

    @PostMapping(value = "${diflow.client.path:/api/socket/call}")
    ResponseEntity<Void> callSocket(@RequestBody DiflowRequestVO<PersonBodyVO> request);
}
