package com.contact_book.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contact_book.app.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	
	//Al usar findBy... ya está configurado para buscar
	Contact findByEmail(String email);
	
	@Transactional
	@Query("delete from contacts co where co.email=?1")
	String deleteByEmail(String email);
}
