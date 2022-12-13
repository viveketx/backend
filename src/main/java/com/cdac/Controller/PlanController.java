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

import com.cdac.Entity.Plan;
import com.cdac.Services.PlanService;

@CrossOrigin
@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	// get all plan
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> findAll() {
		List<Plan> list = planService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	// save or update plans
	@PostMapping("/plans/save")
	public ResponseEntity<Plan> save(@RequestBody Plan pl) {
		Plan newPlan = planService.save(pl);
		return ResponseEntity.ok(newPlan);
	}

	// delete plans
	@DeleteMapping("/plans/delete/{subplanId}")
	public void delete(@PathVariable("subplanId") int id) {
		planService.deleteById(id);
	}
}
