package com.example.demo.service;

import com.example.demo.controler.dto.CreateContactDto;
import com.example.demo.exceptions.ContactNotFoundException;
import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

private ContactRepository contactRepository;

private UserRepository userRepository;

    public ContactService(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    public List<Contact> findAll(){
        return (List<Contact>) this.contactRepository.findAll();
    }

    public void addContact(CreateContactDto createContactDto) {
        Contact newContact = new Contact();
        newContact.setFamilyName(createContactDto.getFamilyName());
        newContact.setFirstName(createContactDto.getFirstName());
        newContact.setPicture(createContactDto.getPicture());
        newContact.setPhoneNumber(createContactDto.getPhoneNumber());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUserByEmail(auth.getName());
        newContact.setUser(user);
        contactRepository.save(newContact);
    }

    public void deleteContact(Long id) {
        Contact contact = contactRepository
                .findById(id)
                .orElseThrow(()-> new ContactNotFoundException(id));

        this.contactRepository.delete(contact);
    }

    public Contact findById(long id) {
        Contact contact = contactRepository
                .findById(id)
                .orElseThrow(()-> new ContactNotFoundException(id));
        return contact;
    }

    public void editContact(CreateContactDto contactDto) {
        Contact contactToModify = contactRepository.findById(contactDto.getId())
                .orElseThrow(()-> new ContactNotFoundException(contactDto.getId()));

        contactToModify.setFamilyName(contactDto.getFamilyName());
        contactToModify.setFirstName(contactDto.getFirstName());
        contactToModify.setPicture(contactDto.getPicture());
        contactToModify.setPhoneNumber(contactDto.getPhoneNumber());
        contactRepository.save(contactToModify);
    }
}
