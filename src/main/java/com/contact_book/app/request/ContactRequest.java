package com.contact_book.app.request;

import javax.validation.constraints.*;

public class ContactRequest {
	
	@NotBlank(message = "name is required")
	@Size(min = 1, max = 45, message = "name must be between 1 and 45 characters")
	private String name;

	@NotNull(message = "age is required")
	@Min(value = 18, message = "age should not be less than 18")
	private int age;

	@NotBlank(message = "email is required")
	@Email(message = "Wrong email")
	@Size(max = 45, message = "email should not be greater than 45 characters")
	private String email;

	public ContactRequest() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
