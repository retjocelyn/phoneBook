package com.example.demo;

import com.example.demo.repository.ContactRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Contact;
import com.example.demo.repository.entity.User;
import com.example.demo.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class Demo7Application implements CommandLineRunner{

    StorageService storageService;

    private UserRepository userRepository;

    private ContactRepository contactRepository;

    public Demo7Application(StorageService storageService,UserRepository userRepository,ContactRepository contactRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(Demo7Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setPicture("");
        user.setPassword(passwordEncoder.encode("boris"));
        user.setFamilyName("sauvage");
        user.setEmail("boris@gmail.com");
        user.setFirstName("boris");
       userRepository.save(user);

        // ##############
       Contact contact1 = new Contact();
       contact1.setFirstName("clara");
       contact1.setFamilyName("delman");
       contact1.setPhoneNumber("0684078976");
       contact1.setPicture("https://resize-elle.ladmedia.fr/rcrop/1024,1024/img/var/plain_site/storage/images/beaute/soins/tendances/" +
               "yoga-du-visage-3864572/93177659-1-fre-FR/Et-si-on-se-mettait-au-yoga-du-visage.jpg");
       contact1.setUser(user);
       contactRepository.save(contact1);

        // ##############
        Contact contact2 = new Contact();
        contact2.setFirstName("giselle");
        contact2.setFamilyName("fletkman");
        contact2.setPhoneNumber("06840785874");
        contact2.setPicture("https://resize-elle.ladmedia.fr/rcrop/1024,1024/img/var/plain_site/storage/images/beaute/soins/tendances/" +
                "yoga-du-visage-3864572/93177659-1-fre-FR/Et-si-on-se-mettait-au-yoga-du-visage.jpg");
        contact2.setUser(user);
        contactRepository.save(contact2);
    }



}
