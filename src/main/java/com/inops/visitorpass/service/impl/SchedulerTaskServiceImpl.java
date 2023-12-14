package com.inops.visitorpass.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inops.visitorpass.entity.ScheduledTask;
import com.inops.visitorpass.repository.ScheduledTaskRepository;
import com.inops.visitorpass.service.IScheduledTask;

import lombok.RequiredArgsConstructor;

@Service("schedulerTaskService")
@RequiredArgsConstructor
public class SchedulerTaskServiceImpl implements IScheduledTask {

	private final ScheduledTaskRepository scheduledTaskRepository;

	@Override
	public Optional<ScheduledTask> findById(long id) {
		return scheduledTaskRepository.findById(id);
	}

	@Override
	public Optional<List<ScheduledTask>> findAll() {
		return Optional.of(scheduledTaskRepository.findAll());
	}

	@Override
	public ScheduledTask save(ScheduledTask scheduledTask) {
		return scheduledTaskRepository.save(scheduledTask);
	}

	@Override
	public void delete(ScheduledTask scheduledTask) {
		scheduledTaskRepository.delete(scheduledTask);
	}

	@Override
	public void deleteAll(List<ScheduledTask> scheduledTasks) {
		scheduledTaskRepository.deleteAll(scheduledTasks);
	}

}
