package com.inops.visitorpass.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.entity.User;
import com.inops.visitorpass.repository.UserRepository;
import com.inops.visitorpass.service.IUserService;

//@Service("userServiceImpl")
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserRepository userRepository;

	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
       // user.setRole(Role.USER);
		userRepository.save(user);
	}

	@Override
	public List<Object> isUserPresent(User user) {
		boolean userExists = false;
		String message = null;
		Optional<User> existingUserEmail = userRepository.findByEmail(user.getEmail());
		if (existingUserEmail.isPresent()) {
			userExists = true;
			message = "Email Already Present!";
		}
		Optional<User> existingUserMobile = userRepository.findByMobile(user.getMobile());
		if (existingUserMobile.isPresent()) {
			userExists = true;
			message = "Mobile Number Already Present!";
		}
		if (existingUserEmail.isPresent() && existingUserMobile.isPresent()) {
			message = "Email and Mobile Number Both Already Present!";
		}
		System.out.println("existingUserEmail.isPresent() - " + existingUserEmail.isPresent()
				+ "existingUserMobile.isPresent() - " + existingUserMobile.isPresent());
		return Arrays.asList(userExists, message);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("USER_NOT_FOUND", email)));
	}

	@Override
	public boolean resetPassword(ResetPassword restPassword) {

		Optional<User> userOp = userRepository.findByEmail(restPassword.getEmailId());
		if (userOp.isPresent()) {

			if (bCryptPasswordEncoder.matches(restPassword.getOldPassword(), userOp.get().getPassword())) {
				String encodedPassword = bCryptPasswordEncoder.encode(restPassword.getNewPassword());
				User user = userOp.get();
				user.setPassword(encodedPassword);
				userRepository.save(user);
				return true;
			}
		}
		return false;

	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}