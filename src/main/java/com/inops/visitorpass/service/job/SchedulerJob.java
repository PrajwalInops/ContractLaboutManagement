package com.inops.visitorpass.service.job;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.inops.visitorpass.service.ITaskScheduler;

import lombok.RequiredArgsConstructor;

@Service("schedulerJob")
@RequiredArgsConstructor
public class SchedulerJob {

	private final ITaskScheduler taskSchedulerService;
	
	 //@Scheduled(fixedRate = 10000) // Execute every 10 seconds
	    public void runTask() {
	        if (taskSchedulerService.isTaskRunning("myTask")) {
	            // Task is already running, do not start a new instance
	            return;
	        }

	        Runnable task = () -> {
	            // Your task logic here
	            System.out.println("My Task is running...");
	        };

	        taskSchedulerService.startTask("myTask", task, new Date(), 5000); // Start the task with a 5-second delay
	    }
}
