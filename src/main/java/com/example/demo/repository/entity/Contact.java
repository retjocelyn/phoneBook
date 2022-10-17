package com.example.demo.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contact {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String familyName;

    private String firstName;

    private String picture;

   private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name="familyStatus",
            joinColumns=@JoinColumn(name="contactId"),
            inverseJoinColumns=@JoinColumn(name="procheId")
    )
    private List<Contact> contactList;

    @ManyToMany
    @JoinTable(name="familyStatus",
            joinColumns=@JoinColumn(name="procheId"),
            inverseJoinColumns=@JoinColumn(name="contactId")
    )

    private List<User> friendOf;
    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
