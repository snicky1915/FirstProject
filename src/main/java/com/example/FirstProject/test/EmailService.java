package com.example.FirstProject.test;

import com.example.FirstProject.scheduler.ExecutableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements ExecutableService {

    @Override
    public String execute() {
        String message = "EmailService executed. No email sending logic configured yet.";
        log.info(message);
        return message;
    }
}
