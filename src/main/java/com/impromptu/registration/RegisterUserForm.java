package com.impromptu.registration;

import com.impromptu.constraints.FieldMatch;
import com.impromptu.users.User;

@FieldMatch(first="password", second="confirmPassword", message="The password fields must match")
public class RegisterUserForm extends User {
    
    private String confirmPassword;
    
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public User getUserAccount() {
	    return new User(this);
	}
}
