package com.example.FirstProject.controller;

import com.example.FirstProject.dto.DiflowRequestVO;
import com.example.FirstProject.dto.PersonBodyVO;
import com.example.FirstProject.service.SocketCoreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/socket")
public class SocketController {

    private final SocketCoreService socketCoreService;

    public SocketController(SocketCoreService socketCoreService) {
        this.socketCoreService = socketCoreService;
    }

    @PostMapping("/call")
    public ResponseEntity<Void> callSocket(
            @RequestBody @Valid DiflowRequestVO<PersonBodyVO> request) {

        socketCoreService.handle(request);
        return ResponseEntity.ok().build();
    }
}
