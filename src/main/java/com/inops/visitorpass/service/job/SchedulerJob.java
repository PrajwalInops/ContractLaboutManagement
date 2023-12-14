package com.inops.visitorpass.service.job;

import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class SchedulerJob {

	private String cronExpression;
		
	private ScheduledFuture<?> scheduledFuture;

	@Scheduled(cron = "#{schedulerJob.cronExpression}") // Execute every 10 seconds
	public void runTask() {

		// Your task logic here
		System.out.println("My Task is running...");

	}
	
	public void changeSchedule(String newCronExpression) {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	
	
}
