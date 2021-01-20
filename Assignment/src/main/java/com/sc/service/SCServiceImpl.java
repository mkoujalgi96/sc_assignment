package com.sc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.sc.dto.CheckTask;
import com.sc.model.Task;
import com.sc.model.TaskDetails;

@Service
public class SCServiceImpl implements SCService {

	Map<String, Task> tasks = new HashMap<String, Task>();

	@Async
	public String create(int start, int step) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		TaskDetails taskDetails = new TaskDetails(start, step);
		service.submit(taskDetails);
		Task task = new Task();
		task.setExecutorService(service);
		task.setTaskDetails(taskDetails);
		tasks.put(taskDetails.getIdentifier(), task);
		return taskDetails.getIdentifier();
	}

	public CheckTask check(String id) {
		TaskDetails taskDetails = tasks.get(id) != null ? tasks.get(id).getTaskDetails() : null;
		if (taskDetails != null) {
			return new CheckTask(taskDetails.getCount(), taskDetails.getCreationDate(), taskDetails.getStep());
		} else {
			return null;
		}
	}

	public List<CheckTask> checkAll() {
		List<CheckTask> allTasks = new ArrayList<CheckTask>();
		for (Map.Entry<String, Task> entry : tasks.entrySet()) {
			TaskDetails taskDetails = entry.getValue() != null ? entry.getValue().getTaskDetails() : null;
			if (taskDetails != null) {
				allTasks.add(
						new CheckTask(taskDetails.getCount(), taskDetails.getCreationDate(), taskDetails.getStep()));
			}
		}
		return allTasks;
	}

	public ModelAndView render() {
		List<TaskDetails> taskList = new ArrayList<TaskDetails>();
		Map<String, Object> model = new HashMap<String, Object>();
		for (Map.Entry<String, Task> entry : tasks.entrySet()) {
			TaskDetails taskDetails = entry.getValue() != null ? entry.getValue().getTaskDetails() : null;
			if (taskDetails != null) {
				taskList.add(taskDetails);
			}
		}
		model.put("taskList", taskList);
		return new ModelAndView("file", model);
	}

	public void clear(String id) {
		TaskDetails task = tasks.get(id) != null ? tasks.get(id).getTaskDetails() : null;
		if (task != null) {
			task.setCount(0);
		}
		ExecutorService service = tasks.get(id) != null ? tasks.get(id).getExecutorService() : null;
		if (service != null) {
			service.shutdownNow();
		}
	}

	public void pause(String id) {
		TaskDetails task = tasks.get(id) != null ? tasks.get(id).getTaskDetails() : null;
		if (task != null) {
			task.setSuspended(true);
		}
	}

}
