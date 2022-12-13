package com.cdac.Controller;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.cdac.Services.EmailService;

@RestController
public class EmailController {

	// @Autowired
	EmailService emailService = new EmailService();

	public ResponseEntity<?> sendEmail(JSONObject email) {

		String message = (String) email.get("message");
		String to = (String) email.get("to");
		String subject = (String) email.get("subject");
		//System.out.println(to);

		emailService.sendEmail(subject, message, to);
		// System.out.println("Email sent ");

		return ResponseEntity.ok("Done....");
	}

}
