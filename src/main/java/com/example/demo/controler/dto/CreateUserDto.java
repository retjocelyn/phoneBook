package com.example.demo.controler.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CreateUserDto {

    private long id;
    private String familyName;

    private String firstName;

    private  MultipartFile pictureFile;

    private  String picture;

    private  String phoneNumber;

    @NotEmpty
    @Email(message = "Email should be valid")
    private String email;

    @Size(min = 2, message = "Password must be a minimum of 2 characteres in length")
    private String password;


    public CreateUserDto() {
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


    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
