package com.example.demo.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDetailRequestModel {
	@NotNull(message="First name cannot be blank")
	@Size(min=2,max=200,message="first name must be between 10 to 200 character long")
	private String firstName;
	@NotNull(message="Last name cannot be blank")
	private String lastName;
	@NotNull(message="Email cannot be blank")
	private String email;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
