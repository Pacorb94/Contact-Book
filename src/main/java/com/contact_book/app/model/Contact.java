package com.contact_book.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.contact_book.app.request.ContactRequest;


@Entity
@Table(name = "contacts")
public class Contact implements Serializable {

	private static final long serialVersionUID = 3238245221612176248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 45, nullable = false)
	private String name;
	
	@Column(length = 3, nullable = false)
	private int age;
	
	@Column(length = 45, unique = true, nullable = false)
	private String email;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Contact() {

	}
	
	public void setAllProperties(ContactRequest request) {
		this.setName(request.getName().trim());
		this.setEmail(request.getEmail());
		this.setAge(request.getAge());
	}

	public long getId() {
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Método que actualiza la propiedad updatedAt
	 */
	@PreUpdate
	public void setUpdatedAt() {
		this.updatedAt = new Date();
	}
}