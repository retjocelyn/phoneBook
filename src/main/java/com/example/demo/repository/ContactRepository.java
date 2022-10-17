package com.example.demo.repository;

import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {



}
