package com.company.billaris2.controllers;

import com.company.billaris2.config.RegisterRequest;
import com.company.billaris2.models.JwtRequest;
import com.company.billaris2.models.JwtResponse;
import com.company.billaris2.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<JwtResponse> signIn(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.signIn(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException{
        authService.refreshToken(request, response);
    }
}
