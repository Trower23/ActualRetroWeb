package com.dws.ActualRetro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initDatabase(){
        List<String> auxlist = new ArrayList<>();
        auxlist.add("USER");
        Users aux = new Users("Trower","Dostre", "user", "trower12@outlook.com", passwordEncoder.encode("pass"), "655844033");
        aux.setRoles(auxlist);
        userService.userRepository.save(aux);
        aux = new Users("Admin", "Root", "admin", "noemail@gmail.com", passwordEncoder.encode("adminpass"), "111111111");
        auxlist = new ArrayList<>();
        auxlist.add("USER");
        auxlist.add("ADMIN");
        aux.setRoles(auxlist);
        userService.userRepository.save(aux);
    }

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



    @GetMapping("/register")
    public String showRegisterForm(){
        return "register_form";
    }

}
