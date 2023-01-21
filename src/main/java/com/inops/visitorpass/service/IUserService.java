package com.inops.visitorpass.service;

import java.util.List;

import com.inops.visitorpass.domain.ResetPassword;
import com.inops.visitorpass.entity.User;

public interface IUserService {

	public void saveUser(User user);

	public List<Object> isUserPresent(User user);
	
	public boolean resetPassword(ResetPassword restPassword);

}
