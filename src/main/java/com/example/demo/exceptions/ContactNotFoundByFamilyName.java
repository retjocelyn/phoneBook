package com.example.demo.exceptions;

public class ContactNotFoundByFamilyName extends RuntimeException {

    public ContactNotFoundByFamilyName(String familyName) {
        super("Contact with familyname " + familyName + " does not exist");
    }

}
