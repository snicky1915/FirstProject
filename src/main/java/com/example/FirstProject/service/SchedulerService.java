package com.example.FirstProject.service;

import com.example.FirstProject.dto.request.ScheduleRequest;
import com.example.FirstProject.dto.response.ScheduleResponse;
import com.example.FirstProject.scheduler.ExecutableServiceRegistry;
import com.example.FirstProject.scheduler.ExecuteServiceJob;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class SchedulerService {

    private static final String JOB_GROUP = "service-execution-jobs";
    private static final String TRIGGER_GROUP = "service-execution-triggers";

    private final Scheduler scheduler;
    private final ExecutableServiceRegistry executableServiceRegistry;

    public SchedulerService(Scheduler scheduler, ExecutableServiceRegistry executableServiceRegistry) {
        this.scheduler = scheduler;
        this.executableServiceRegistry = executableServiceRegistry;
    }

    public ScheduleResponse schedule(ScheduleRequest request) throws SchedulerException {
        validateRequest(request);

        JobKey jobKey = JobKey.jobKey(request.getJobName(), JOB_GROUP);
        TriggerKey triggerKey = TriggerKey.triggerKey(request.getJobName(), TRIGGER_GROUP);

        JobDetail jobDetail = JobBuilder.newJob(ExecuteServiceJob.class)
                .withIdentity(jobKey)
                .usingJobData(ExecuteServiceJob.SERVICE_NAME_KEY, request.getServiceName())
                .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerKey)
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(request.getCronExpression()))
                .build();

        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }

        scheduler.scheduleJob(jobDetail, trigger);

        return new ScheduleResponse(
                request.getJobName(),
                request.getServiceName(),
                request.getCronExpression(),
                "SCHEDULED"
        );
    }

    public String executeNow(String serviceName) {
        return executableServiceRegistry.execute(serviceName);
    }

    public boolean delete(String jobName) throws SchedulerException {
        return scheduler.deleteJob(JobKey.jobKey(jobName, JOB_GROUP));
    }

    public List<ScheduleResponse> getSchedules() throws SchedulerException {
        List<ScheduleResponse> schedules = new ArrayList<>();
        Set<JobKey> jobKeys = scheduler.getJobKeys(org.quartz.impl.matchers.GroupMatcher.jobGroupEquals(JOB_GROUP));

        for (JobKey jobKey : jobKeys) {
            List<? extends org.quartz.Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (org.quartz.Trigger trigger : triggers) {
                String cronExpression = trigger instanceof CronTrigger cronTrigger
                        ? cronTrigger.getCronExpression()
                        : "N/A";
                String serviceName = scheduler.getJobDetail(jobKey)
                        .getJobDataMap()
                        .getString(ExecuteServiceJob.SERVICE_NAME_KEY);
                schedules.add(new ScheduleResponse(jobKey.getName(), serviceName, cronExpression, "SCHEDULED"));
            }
        }
        return schedules;
    }

    public TreeSet<String> getAvailableServices() {
        return executableServiceRegistry.getAvailableServiceNames();
    }

    private void validateRequest(ScheduleRequest request) {
        executableServiceRegistry.getAvailableServiceNames().stream()
                .filter(name -> name.equals(request.getServiceName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("serviceName không hợp lệ: " + request.getServiceName()));

        if (!CronExpression.isValidExpression(request.getCronExpression())) {
            throw new IllegalArgumentException("cronExpression không hợp lệ: " + request.getCronExpression());
        }
    }
}
