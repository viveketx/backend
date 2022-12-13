package com.cdac.Services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdac.Entity.User;
import com.cdac.Repository.UserRepo;
import com.cdac.dao.UserDao;

@Transactional
@Service
public class UserService implements UserRepo {

	@Autowired
	private UserDao userDao;

	@Override
	public User findById(int user_id) {
		Optional<User> b = userDao.findById(user_id);
		return b.orElse(null);
	}
	

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User save(User u) {
		return userDao.save(u);
	}

	@Override
	public void deleteById(int user_id) {
            userDao.deleteById(user_id);

	}

	@Override
	public User authenticate(String email, String password) {
		User user = findByEmail(email);
		if (user != null && user.getPassword().equals(password))
			return user;
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public List<User> findByRole(String role) {
		return userDao.findByRole(role);
	}

}
