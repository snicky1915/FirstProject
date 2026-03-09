package com.example.FirstProject.service;

import com.example.FirstProject.client.DiflowFeignClient;
import com.example.FirstProject.dto.DiflowRequestHeaderVO;
import com.example.FirstProject.dto.DiflowRequestVO;
import com.example.FirstProject.dto.PersonBodyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SocketCoreService {

    private final DiflowFeignClient diflowFeignClient;

    public SocketCoreService(DiflowFeignClient diflowFeignClient) {
        this.diflowFeignClient = diflowFeignClient;
    }

    public void handle(DiflowRequestVO<PersonBodyVO> request) {
        DiflowRequestHeaderVO inputHeader = request.getHeader();
        DiflowRequestHeaderVO header = DiflowRequestHeaderVO.builder()
                .cid(inputHeader.getCid())
                .tid(inputHeader.getTid())
                .tgt(inputHeader.getTgt())
                .build();

        PersonBodyVO inputBody = request.getBody();
        PersonBodyVO body = PersonBodyVO.builder()
                .ten(inputBody.getTen())
                .tuoi(inputBody.getTuoi())
                .build();

        DiflowRequestVO<PersonBodyVO> outboundRequest =
                DiflowRequestVO.<PersonBodyVO>builder()
                        .header(header)
                        .body(body)
                        .build();

        log.info("Calling external service with request: {}", outboundRequest);
        diflowFeignClient.callSocket(outboundRequest);
    }
}
