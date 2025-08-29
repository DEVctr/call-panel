package com.devctr.call_panel.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devctr.call_panel.model.entity.Attendant;
import com.devctr.call_panel.repository.AttendantRepository;

@Service
public class AttendantService implements UserDetailsService {
    private final AttendantRepository attendantRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AttendantService(AttendantRepository attendantRepository) {
        this.attendantRepository = attendantRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Attendant> user = attendantRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(),
                user.get().getPassword(),
                Collections.emptyList()
        );
    }

    public Attendant saveUser(Attendant attendant, Optional<Long> existingAttendantId) {
        Attendant user = existingAttendantId.isPresent()
            ? attendantRepository.findById(existingAttendantId.get()).orElseThrow(() -> new IllegalArgumentException("User not found"))
            : new Attendant();

        user.setName(attendant.getName());
        user.setEmail(attendant.getEmail());
        user.setCounter(attendant.getCounter());

        if (attendant.getPassword() != null && !attendant.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(attendant.getPassword()));
        }

        return attendantRepository.save(user);
    }

    public Optional<Attendant> getUserByEmail(String email) {
        return attendantRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return attendantRepository.existsByEmail(email);
    }
}