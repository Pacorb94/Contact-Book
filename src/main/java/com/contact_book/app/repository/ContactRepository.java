package com.contact_book.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.contact_book.app.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

	// Al usar findBy... ya está configurado para buscar
	@Transactional(readOnly = true)
	public Optional<Contact> findByEmail(String email);

	@Transactional
	@Query("delete from Contact co where co.email = ?1")
	public void deleteByEmail(String email);
}
