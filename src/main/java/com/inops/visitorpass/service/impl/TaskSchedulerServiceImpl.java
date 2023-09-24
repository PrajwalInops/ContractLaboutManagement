package com.inops.visitorpass.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.inops.visitorpass.service.ITaskScheduler;

import lombok.RequiredArgsConstructor;

@Service("taskSchedulerService")
@RequiredArgsConstructor
public class TaskSchedulerServiceImpl implements ITaskScheduler {

	private final ThreadPoolTaskScheduler taskScheduler;
	private final Map<String, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();

	@Override
	public void startTask(String taskName, Runnable taskRunnable, Date initialDelay, long period) {
		ScheduledFuture<?> future = taskScheduler.scheduleWithFixedDelay(taskRunnable, initialDelay, period);
		taskFutures.put(taskName, future);
	}

	@Override
	public void stopTask(String taskName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancelTask(String taskName) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTaskRunning(String taskName) {
		// TODO Auto-generated method stub
		return false;
	}

}
