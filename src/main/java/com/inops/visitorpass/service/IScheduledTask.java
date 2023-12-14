package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import com.inops.visitorpass.entity.ScheduledTask;

public interface IScheduledTask {
	
	Optional<ScheduledTask> findById(long id);

	Optional<List<ScheduledTask>> findAll();

	ScheduledTask save(ScheduledTask scheduledTask);

	void delete(ScheduledTask scheduledTask);

	void deleteAll(List<ScheduledTask> scheduledTasks);

}
