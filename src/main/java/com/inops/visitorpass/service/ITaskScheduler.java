package com.inops.visitorpass.service;

import java.util.Date;

public interface ITaskScheduler {
	
	public void startTask(String taskName, Runnable taskRunnable, Date initialDelay, long period);
	
	public void stopTask(String taskName);
	
	public void cancelTask(String taskName);
	
	public boolean isTaskRunning(String taskName);
}
