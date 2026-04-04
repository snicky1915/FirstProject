package com.example.FirstProject.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ExecuteServiceJob implements Job {

    public static final String SERVICE_NAME_KEY = "serviceName";

    @Autowired
    private ExecutableServiceRegistry executableServiceRegistry;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        String serviceName = dataMap.getString(SERVICE_NAME_KEY);

        try {
            String result = executableServiceRegistry.execute(serviceName);
            log.info("Quartz executed service '{}' with result: {}", serviceName, result);
        } catch (Exception e) {
            log.error("Quartz failed while executing service '{}'", serviceName, e);
            throw new JobExecutionException(e);
        }
    }
}
