package com.sc.model;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TaskDetails implements Callable<String> {

	private int start;
	private int step;
	private Date creationDate;

	private int count;
	private final String identifier;
	
	private volatile boolean suspended = false; 

	public TaskDetails(int start, int step) {
		this.start = start;
		this.step = step;
		this.identifier = UUID.randomUUID().toString();
		this.count = this.start;
		this.creationDate = new Date();
	}

	public String call() throws Exception {
		while (true) {
			System.out.println("Count for" + this.getIdentifier() + ": " + count);
			count += step;
			Thread.sleep(1000);
			
			while(suspended) {
				synchronized(this) {
					this.wait();
				}
			}
		}
	}

}
