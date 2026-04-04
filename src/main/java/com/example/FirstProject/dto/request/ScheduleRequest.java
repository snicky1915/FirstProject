package com.example.FirstProject.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ScheduleRequest {

    @NotBlank(message = "jobName không được để trống")
    private String jobName;

    @NotBlank(message = "serviceName không được để trống")
    private String serviceName;

    @NotBlank(message = "cronExpression không được để trống")
    private String cronExpression;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }
}
