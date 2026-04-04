package com.example.FirstProject.controller;

import com.example.FirstProject.dto.request.ScheduleRequest;
import com.example.FirstProject.dto.response.ScheduleResponse;
import com.example.FirstProject.service.SchedulerService;
import jakarta.validation.Valid;
import org.quartz.SchedulerException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@RestController
@RequestMapping("/api/schedules")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping
    public ScheduleResponse createSchedule(@Valid @RequestBody ScheduleRequest request) throws SchedulerException {
        return schedulerService.schedule(request);
    }

    @GetMapping
    public List<ScheduleResponse> getSchedules() throws SchedulerException {
        return schedulerService.getSchedules();
    }

    @GetMapping("/services")
    public TreeSet<String> getAvailableServices() {
        return schedulerService.getAvailableServices();
    }

    @PostMapping("/execute")
    public Map<String, String> executeNow(@RequestParam String serviceName) {
        String result = schedulerService.executeNow(serviceName);
        return Map.of(
                "serviceName", serviceName,
                "result", result
        );
    }

    @DeleteMapping("/{jobName}")
    public Map<String, Object> deleteSchedule(@PathVariable String jobName) throws SchedulerException {
        boolean deleted = schedulerService.delete(jobName);
        return Map.of(
                "jobName", jobName,
                "deleted", deleted
        );
    }
}
