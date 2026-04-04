package com.example.FirstProject.scheduler;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeSet;

@Component
public class ExecutableServiceRegistry {

    private final Map<String, ExecutableService> serviceMap;

    public ExecutableServiceRegistry(Map<String, ExecutableService> serviceMap) {
        this.serviceMap = serviceMap;
    }

    public String execute(String serviceName) {
        ExecutableService service = serviceMap.get(serviceName);
        if (service == null) {
            throw new IllegalArgumentException("Không tìm thấy service với tên: " + serviceName);
        }
        return service.execute();
    }

    public TreeSet<String> getAvailableServiceNames() {
        return new TreeSet<>(serviceMap.keySet());
    }
}
