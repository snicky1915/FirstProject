package com.example.FirstProject.service;

import com.example.FirstProject.dto.DiflowRequestHeaderVO;
import com.example.FirstProject.dto.DiflowRequestVO;
import com.example.FirstProject.dto.PersonBodyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SocketCoreService {

    public void handle(DiflowRequestVO<PersonBodyVO> request) {
        DiflowRequestHeaderVO header = new DiflowRequestHeaderVO();
        header.setCid("123");
        PersonBodyVO personBodyVO = new PersonBodyVO("nam",12);

        DiflowRequestVO<PersonBodyVO> request1 =
                DiflowRequestVO.<PersonBodyVO>builder()
                        .header(header)
                        .body(personBodyVO)
                        .build();
        log.info(String.valueOf(request1));
    }
}
