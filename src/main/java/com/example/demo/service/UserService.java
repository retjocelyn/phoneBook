package com.example.demo.service;

import com.example.demo.controler.dto.CreateUserDto;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private StorageService storageService;

    public UserService(UserRepository userRepository, StorageService storageService) {
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    public void createUser(CreateUserDto createUserDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MultipartFile pictureFile = createUserDto.getPictureFile();
        storageService.save(pictureFile);
        createUserDto.setPicture("http://localhost:8080/images/" + pictureFile.getOriginalFilename());
        User newUser = new User();
        newUser.setEmail(createUserDto.getEmail());
        newUser.setFamilyName(createUserDto.getFamilyName());
        newUser.setFirstName(createUserDto.getFirstName());
        newUser.setPicture(createUserDto.getPicture());
        newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        this.userRepository.save(newUser);
    }

    public User findById(Long id ) {
        return this.userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public void editUser(CreateUserDto createUserDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MultipartFile pictureFile = createUserDto.getPictureFile();
        storageService.save(pictureFile);
        createUserDto.setPicture("http://localhost:8080/images/" + pictureFile.getOriginalFilename());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userToModify  = this.userRepository.findUserByEmail(auth.getName());
        userToModify.setFirstName(createUserDto.getFirstName());
        userToModify.setFamilyName(createUserDto.getFamilyName());
        userToModify.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        userToModify.setEmail(createUserDto.getEmail());
        this.userRepository.save(userToModify);
    }

    public User findByName(String email) {
       return userRepository.findUserByEmail(email);
    }

    public void deleteUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userToDelete  = this.userRepository.findUserByEmail(auth.getName());
        userRepository.delete(userToDelete);
    }
}
