package com.cdac.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.Entity.User;
import com.cdac.Services.EncryptService;
import com.cdac.Services.UserService;
import com.cdac.model.Response;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	EncryptService encryptService;

	@PostMapping("/users/authenticateUser")
	public ResponseEntity<?> authenticateUser(User user, HttpSession session) {
		User authuser = userService.findByEmail(user.getEmail());
		String sp = user.getPassword();
		String encryptPassword=null;
		try {
			encryptPassword = encryptService.toHexString(encryptService.getSHA(sp));
		}catch(Exception e) {
			
		}
		if (authuser != null && authuser.getRole().equals("admin")) {
			if (authuser.getPassword().equals(encryptPassword)) {
				return Response.success(authuser);
			}
		} else if (authuser != null && authuser.getRole().equals("trainer")) {
			if (authuser.getPassword().equals(encryptPassword)) {
				return Response.success(authuser);
			}
		} else if (authuser != null && authuser.getRole().equals("member")) {
			if (authuser.getPassword().equals(encryptPassword)) {
				return Response.success(authuser);
			}
		}
		return Response.error("404");
	}
}
