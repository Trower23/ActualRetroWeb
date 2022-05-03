package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginerror")
    public String loginerror(){
        return "loginerror";
    }

    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String mail, @RequestParam String password, @RequestParam String phone){
        Users auxUser=new Users(name, surname, username, mail, passwordEncoder.encode(password), phone);
        userService.userRepository.save(auxUser);
        return "register_success";
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "register_form";
    }

}
