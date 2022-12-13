package com.cdac.Controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
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
import com.cdac.Entity.User;
import com.cdac.Services.EncryptService;
import com.cdac.Services.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	EncryptService encryptService;

	// Save or update user
	@PostMapping("/users/save")
	public ResponseEntity<User> save(@RequestBody User user) {
		String sp = user.getPassword();
		try {
			String encryptPassword = encryptService.toHexString(encryptService.getSHA(sp));
			user.setPassword(encryptPassword);
			user.setRole("member");
			User newUser = userService.save(user);
			return ResponseEntity.ok(newUser);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// save or update user(Admin + Trainer)
	@PostMapping("/users/saveAdTrain")
	public ResponseEntity<User> saveAdTrain(@RequestBody User user) {
		String sp = user.getPassword();
		try {
			String encryptPassword = encryptService.toHexString(encryptService.getSHA(sp));
			user.setPassword(encryptPassword);
			User newUser = userService.save(user);
			return ResponseEntity.ok(newUser);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	// get user by email
	@GetMapping("/users/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable("email") String email) {
		User user = userService.findByEmail(email);
		if (user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(user);
	}

	// get all users
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//forgot password
	@PostMapping("/users/updatePassword")
	public ResponseEntity<User> update(@RequestBody User user) {

		if (user.getEmail() != null) {
			User u = userService.findByEmail(user.getEmail());
			String sp = user.getPassword();
			try {
				String encryptPassword = encryptService.toHexString(encryptService.getSHA(sp));
				u.setPassword(encryptPassword);
				userService.save(u);
				return new ResponseEntity<User>(HttpStatus.OK);
			} catch (Exception e) {
				return ResponseEntity.notFound().build();
			}

		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);

	}

	// change password
	@PostMapping("/users/changePassword")
	public ResponseEntity<?> authenticateUser(@RequestBody String user) throws Exception {
		Object obj = JSONValue.parse(user); // import packages
		JSONObject jsonObject = (JSONObject) obj;
		String email = (String) jsonObject.get("email");
		String oldpassword = (String) jsonObject.get("oldPassword");
		String newpassword = (String) jsonObject.get("newPassword");

		User authuser = userService.findByEmail(email);
		if (authuser != null) {
			String sp = oldpassword;
			String encryptPassword = encryptService.toHexString(encryptService.getSHA(sp));
			if (authuser.getPassword().equals(encryptPassword)) {
				encryptPassword = encryptService.toHexString(encryptService.getSHA(newpassword));
				authuser.setPassword(encryptPassword);
				userService.save(authuser);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	// delete user
	@DeleteMapping("/users/delete/{user_id}")
	public void delete(@PathVariable("user_id") int id) {
		userService.deleteById(id);
	}
}
