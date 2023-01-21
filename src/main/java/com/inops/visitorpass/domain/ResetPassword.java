package com.inops.visitorpass.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResetPassword {

	private String emailId;
	private String oldPassword;
	private String newPassword;
}
