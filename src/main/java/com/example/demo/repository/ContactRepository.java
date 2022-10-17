package com.example.demo.repository;

import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


    Contact findContactsByUserIdAndFamilyName(long id,String keyword);

    List<Contact> findContactsByUserIdOrderByFamilyNameDesc(long id);
}
