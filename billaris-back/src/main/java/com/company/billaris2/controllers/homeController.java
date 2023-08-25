package com.company.billaris2.controllers;

import com.company.billaris2.entities.User;
import com.company.billaris2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/home")
public class homeController {

    @Autowired
    private UserService userService;

    // http://localhost:8081/home/users
    @GetMapping("/users")
    public List<User> getUser(){

        System.out.println("getting users");
        return userService.getUsers();

    }

    @GetMapping("/current-user")
    public String getLoginUser(Principal principal){
        return principal.getName();
    }


    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages(){
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }
}
