package com.cdac.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.Workoutplan;
import com.cdac.Services.WorkoutplanService;

@RestController
@CrossOrigin
public class WorkoutplanController {

	@Autowired
	private WorkoutplanService workoutplanService;

	// get all workoutsplans
	@GetMapping("/workouts")
	public ResponseEntity<List<Workoutplan>> findAll() {
		List<Workoutplan> list = workoutplanService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// save or update workoutsplan
	@PostMapping("/workouts/save")
	public ResponseEntity<Workoutplan> save(@RequestBody Workoutplan workoutplan) {
		Workoutplan dp = workoutplanService.save(workoutplan);
		return ResponseEntity.ok(dp);
	}

	// delete workoutplan
	@DeleteMapping("/workouts/delete/{workoutid}")
	public void delete(@PathVariable("workoutid") int id) {
		workoutplanService.deleteById(id);
	}
}
