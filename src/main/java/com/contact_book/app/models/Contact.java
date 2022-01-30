package com.contact_book.app.models;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

	private static final long serialVersionUID = 3238245221612176248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 45, nullable = false)
	private String name;

	@Column(length = 10, nullable = false)
	private int age;

	@Column(length = 45, nullable = false)
	private String email;

	public Long getId() {
		return id;
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