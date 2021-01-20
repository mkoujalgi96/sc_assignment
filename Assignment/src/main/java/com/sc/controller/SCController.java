package com.sc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sc.dto.CheckTask;
import com.sc.service.SCService;

@RestController
public class SCController {

	@Autowired
	private SCService service;

	@PostMapping("/sc_create")
	public ResponseEntity<String> create(@RequestParam(name = "start", required = true) int start,
			@RequestParam(name = "step", required = true) int step) {
		if (step <= 0) {
			return new ResponseEntity<String>("Step should be positive and non zero.", HttpStatus.BAD_REQUEST);
		}
		String identifier = service.create(start, step);
		return new ResponseEntity<String>(identifier, HttpStatus.CREATED);
	}

	@GetMapping("/sc_check")
	public ResponseEntity<CheckTask> check(@RequestParam(name = "id", required = true) String id) {
		CheckTask task = service.check(id);
		if (task == null) {
			return new ResponseEntity<CheckTask>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<CheckTask>(task, HttpStatus.OK);
		}

	}

	@GetMapping("/sc_checkall")
	public ResponseEntity<List<CheckTask>> checkAll() {
		List<CheckTask> task = service.checkAll();
		return new ResponseEntity<List<CheckTask>>(task, HttpStatus.OK);
	}

	@PostMapping("/sc_render")
	public ModelAndView render() {
		return service.render();
	}

	@PostMapping("/sc_clear")
	public ResponseEntity<Void> clear(@RequestParam(name = "id", required = true) String id) {
		service.clear(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/sc_pause")
	public ResponseEntity<Void> pause(@RequestParam(name = "id", required = true) String id) {
		service.pause(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
