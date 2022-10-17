package com.example.demo.controler.dto;

import com.example.demo.repository.entity.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class createContactDto {

    private String familyName;

    private String firstName;

    private String picture;

    private String phoneNumber;

    public createContactDto() {
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


}
