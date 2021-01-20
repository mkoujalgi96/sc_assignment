package com.sc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckTask {

	private int currentCount;
	private Date creationTime;
	private int stepTime;
}
