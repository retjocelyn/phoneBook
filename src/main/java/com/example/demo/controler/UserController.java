package com.example.demo.controler;

import com.example.demo.controler.dto.CreateUserDto;
import com.example.demo.repository.entity.User;
import com.example.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/signin")
    public String displaySignInForm(Model model) {
        model.addAttribute("user", new User());
        return "signin";
    }
    @GetMapping("/hello")
    public String displayHome() {
        return "hello";
    }

    @GetMapping("/register")
    public String displayRegisterForm(Model model) {
        model.addAttribute("user" ,new CreateUserDto());
        return "userRegisterForm";
    }

    @PostMapping("/register")
    public String userRegister(CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
        return "redirect:/signin";
    }

    @GetMapping("/edit/{id}")
    public String displayEditForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User foundUser = userService.findByName(auth.getName());
        CreateUserDto userDto = new CreateUserDto();
        userDto.setId(foundUser.getId());
        userDto.setEmail(foundUser.getEmail());
        userDto.setFamilyName(foundUser.getFamilyName());
        userDto.setFirstName(foundUser.getFirstName());
        userDto.setPicture(foundUser.getPicture());
        userDto.setPassword(foundUser.getPassword());
        model.addAttribute("user" ,userDto);
        return "userEditForm";
    }

    @PostMapping("/edit/{id}")
    public String EditUser(CreateUserDto createUserDto) {
       userService.editUser(createUserDto);
        return "redirect:/users/hello";
    }

    @GetMapping("/showProfile")
    public String showProfile(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User foundUser = userService.findByName(auth.getName());
        model.addAttribute("user" ,foundUser);
        return "profile";
    }
    @GetMapping("/delete")
    public String deleteUser() {
        userService.deleteUser();
        return "redirect:/users/hello";
    }
}
