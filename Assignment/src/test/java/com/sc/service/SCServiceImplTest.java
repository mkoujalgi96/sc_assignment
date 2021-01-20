package com.sc.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.sc.model.Task;
import com.sc.model.TaskDetails;

@SpringBootTest
public class SCServiceImplTest {
	
	@InjectMocks
	private SCServiceImpl service;
	
	@BeforeEach
	public void beforeEach() {
		Map<String, Task> tasks = new HashMap<String, Task>();
		Task task = new Task();
		task.setTaskDetails(new TaskDetails(1, 3));
		task.setExecutorService(Executors.newSingleThreadExecutor());
		tasks.put(DUMMY_IDENTIFIER, task);
		service.tasks = tasks;
	}
	
	public static final String DUMMY_IDENTIFIER = "dummyIdentifier";
	
	@Test
	public void pauseTest() {
		service.pause(DUMMY_IDENTIFIER);
	}
	
	@Test
	public void pauseNegativeTest() {
		service.pause("dummy");
	}
	
	@Test
	public void clearTest() {
		service.clear(DUMMY_IDENTIFIER);
	}
	
	@Test
	public void clearNegativeTest() {
		service.pause("dummy");
	}
	
	@Test
	public void renderTest() {
		assertNotNull(service.render());
	}
	
	@Test
	public void checkAllTest() {
		assertNotNull(service.checkAll());
	}
	
	@Test
	public void checkTest() {
		assertNotNull(service.check(DUMMY_IDENTIFIER));
	}
	
	@Test
	public void checkNegativeTest() {
		assertNull(service.check("dummy"));
	}
	
	@Test
	public void createTest() {
		assertNotNull(service.create(1, 2));
	}

}
