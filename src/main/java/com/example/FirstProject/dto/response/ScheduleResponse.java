package com.example.FirstProject.dto.response;

public class ScheduleResponse {

    private final String jobName;
    private final String serviceName;
    private final String cronExpression;
    private final String status;

    public ScheduleResponse(String jobName, String serviceName, String cronExpression, String status) {
        this.jobName = jobName;
        this.serviceName = serviceName;
        this.cronExpression = cronExpression;
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public String getStatus() {
        return status;
    }
}
