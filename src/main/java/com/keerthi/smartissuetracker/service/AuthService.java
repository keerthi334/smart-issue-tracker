package com.keerthi.smartissuetracker.service;

import com.keerthi.smartissuetracker.dto.AuthRequestDto;
import com.keerthi.smartissuetracker.dto.AuthResponseDto;
import com.keerthi.smartissuetracker.dto.UserRequestDto;
import com.keerthi.smartissuetracker.entity.User;
import com.keerthi.smartissuetracker.repository.UserRepository;
import com.keerthi.smartissuetracker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponseDto login(AuthRequestDto request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Email and password must be provided");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("Login email: " + request.getEmail());
        System.out.println("Encoded DB password: " + user.getPassword());
        System.out.println("Matches: " + passwordEncoder.matches(request.getPassword(), user.getPassword()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
    public AuthResponseDto register(UserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with this email");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        // 🔐 Encode the password before saving
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER"); // or "ADMIN", based on your logic

        userRepository.save(user);

        // 🎟️ Generate JWT token for the user
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }

}
