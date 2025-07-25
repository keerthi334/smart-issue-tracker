package com.keerthi.smartissuetracker.controller;

import com.keerthi.smartissuetracker.dto.AuthRequestDto;
import com.keerthi.smartissuetracker.dto.AuthResponseDto;
import com.keerthi.smartissuetracker.dto.UserRequestDto;
import com.keerthi.smartissuetracker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        try {
            AuthResponseDto response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public AuthResponseDto register(@Valid @RequestBody UserRequestDto dto) {
        return authService.register(dto);
    }

}
