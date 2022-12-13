package com.cdac.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.cdac.Entity.GymMember;
import com.cdac.Services.GymMemberService;

@CrossOrigin
@RestController
public class GymMemberController {

	@Autowired
	private GymMemberService gymMemberService;

	@GetMapping("/gymmembers/getEnddate/{user_id}")
	public Date findAll(@PathVariable("user_id") int id) {
		List<GymMember> list = gymMemberService.findAll();
		for (GymMember gymMember : list) {
			int tid = gymMember.getUser().getUser_id();
			if (tid == id) {
				return gymMember.getEndDate();
			}
		}
		return null;
	}

	// get all GymMembers
	@GetMapping("/gymmembers")
	public ResponseEntity<List<GymMember>> findAll() throws IOException {
		List<GymMember> list = gymMemberService.findAll();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(new Date());

		// URL url = new URL("http://localhost:8080/sendmail");
		JSONObject email = new JSONObject();
		//String subject = " Termination of GYM Membership ";

		for (GymMember gymMember : list) {

			if (dateFormat.format(gymMember.getEndDate()).equals(formattedDate)) {

				email.put("to", gymMember.getUser().getEmail());
				email.put("subject", " Expiry of GYM Membership ");
				email.put("message", "Dear " + gymMember.getUser().getEmail()
						+ ",\n\nYour subscription is expiring today. \n\nPlease renew it... \n\n\nRegards,\nTeam Gym Management");
				EmailController es = new EmailController();

				try {
					es.sendEmail(email);
				} catch (Exception e) {
					System.out.println("Email not sent");
				}

				gymMemberService.deleteById(gymMember.getMemberId());
			}
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
