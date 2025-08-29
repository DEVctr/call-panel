package com.devctr.call_panel.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devctr.call_panel.exception.UserAlreadyExistsException;
import com.devctr.call_panel.model.dto.LoginRequest;
import com.devctr.call_panel.model.entity.Attendant;
import com.devctr.call_panel.security.JwtUtil;
import com.devctr.call_panel.service.AttendantService;

@RestController
@RequestMapping("/auth")
public class AttendantController {
    private final AttendantService attendantService;
    private final AuthenticationManager authenticationManager;
     private final JwtUtil jwtUtil;

    public AttendantController(AuthenticationManager authenticationManager,
                               AttendantService attendantService,
                               JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.attendantService = attendantService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createAttendant(@RequestBody Attendant attendant) {
        if (attendantService.existsByEmail(attendant.getEmail())) {
            throw new UserAlreadyExistsException("Login already exists.");
        }
        
        Attendant user = attendantService.saveUser(attendant, Optional.empty());

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails user = attendantService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(Map.of("token", token));
    }
}