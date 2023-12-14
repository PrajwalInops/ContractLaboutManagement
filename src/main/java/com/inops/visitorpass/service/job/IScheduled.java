package com.inops.visitorpass.service.job;

import com.inops.visitorpass.entity.ScheduledTask;

public interface IScheduled {

	void scheduleAll();
	
	void scheduleTask(ScheduledTask task);
	
	void startTask(ScheduledTask task);
	
	void resumeTask(long taskId);
	
	void pauseTask(long taskId);
	
	void cancelTask(long taskId);
}
