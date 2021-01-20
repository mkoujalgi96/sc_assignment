package com.sc.model;

import java.util.concurrent.ExecutorService;

import lombok.Data;

@Data
public class Task {

	private ExecutorService executorService;
	private TaskDetails taskDetails;
}
