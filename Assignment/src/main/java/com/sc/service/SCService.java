package com.sc.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.sc.dto.CheckTask;

public interface SCService {

	String create(int start, int step);

	CheckTask check(String id);

	List<CheckTask> checkAll();

	ModelAndView render();

	void clear(String id);

	void pause(String id);

}
