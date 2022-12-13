package com.cdac.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.GymMember;
import com.cdac.Entity.Plan;
import com.cdac.Entity.User;
import com.cdac.Services.GymMemberService;
import com.cdac.Services.PlanService;
import com.cdac.Services.UserService;
import com.cdac.model.GymMemberDTO;

@RestController
@CrossOrigin
@RequestMapping("/gym")
public class GymMemberDTOController {

	@Autowired
	private GymMemberService gymMemberService;

	@Autowired
	private UserService userService;

	@Autowired
	private PlanService planService;

	@PostMapping("/gymmemberdtos")
	public ResponseEntity<?> saveDetails(GymMemberDTO dto) { // data input as form-data, so no
																// @RequestBody

		User u = userService.findById(dto.getUser_id());

		Plan p = planService.findById(dto.getSubplanId());
		int duration = p.getDuration();
		GymMember b = GymMemberDTO.toEntity(dto);

		b.setUser(u);
		b.setPlan(p);
		b.setJoinDate(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, duration);
		b.setEndDate(calendar.getTime());
		GymMember newGymMember = gymMemberService.save(b);

		GymMemberDTO newDto = GymMemberDTO.fromEntity(newGymMember);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("status", "success");
		result.put("data", newDto);
		return ResponseEntity.ok(result);

	}
}
