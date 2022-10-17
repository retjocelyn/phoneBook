package com.example.demo.controler;


import com.example.demo.controler.dto.CreateContactDto;
import com.example.demo.repository.entity.Contact;
import com.example.demo.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {

        this.contactService = contactService;
    }

    @GetMapping("/allContacts")
    public String getAllContacts(Model model){
        List<Contact> listContacts = contactService.findAll();
        model.addAttribute("contacts" ,listContacts);
        return "allContactsVue";
    }

    @GetMapping("/addContact")
    public String addContact( Model model){
        model.addAttribute("contact" ,new CreateContactDto());
        return "addContactForm";
    }

    @PostMapping("/addContact")
    public String addContact( CreateContactDto createContactDto){
       contactService.addContact(createContactDto);
        return "redirect:/users/hello";
    }


    @GetMapping("/editContact/{id}")
    public String editContactForm(Model model, @PathVariable String id){
        long toFindId = Long.parseLong(id);
       Contact contact=  contactService.findById(toFindId);
       CreateContactDto contactDto = new CreateContactDto();
       contactDto.setId(contact.getId());
       contactDto.setPicture(contact.getPicture());
       contactDto.setPhoneNumber(contact.getPhoneNumber());
       contactDto.setFamilyName(contact.getFamilyName());
       contactDto.setFirstName(contact.getFirstName());
       contactDto.setUser(contact.getUser());

        model.addAttribute("contact" ,contactDto);
        return "editContactForm";
    }

    @PostMapping("/editContact")
    public String editContact(CreateContactDto contactDto){
    contactService.editContact(contactDto);
    return "redirect:/contact/allContacts";
    }

    @PostMapping("/deleteContact")
    public String deleteContact(Contact contact){
        long id = contact.getId();
        contactService.deleteContact(id);
        return "redirect:/contact/allContacts";
    }

}
