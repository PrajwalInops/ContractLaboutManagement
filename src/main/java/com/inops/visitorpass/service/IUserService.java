package com.inops.visitorpass.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.entity.User;

public interface IUserService extends UserDetailsService {

	public void saveUser(User user);

	public List<Object> isUserPresent(User user);
	
	public boolean resetPassword(ResetPassword restPassword);
	
	Optional<List<User>> findAll();

}
