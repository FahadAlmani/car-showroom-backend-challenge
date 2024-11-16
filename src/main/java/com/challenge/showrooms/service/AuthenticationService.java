package com.challenge.showrooms.service;

import com.challenge.showrooms.DTO.UserDTO;
import com.challenge.showrooms.Repository.UserRepository;
import com.challenge.showrooms.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public Map<String,String> register(UserDTO userDTO) {
        Optional<User> existUser = userRepository.findByUsername(userDTO.getUsername());

        if(existUser.isPresent()){
            throw new IllegalArgumentException("This username is already taken. Please choose a different one");
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return Map.of("token",jwtToken);
    }

    public Map<String,String> authenticate(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );
        User user = userRepository.findByUsername(userDTO.getUsername()).get();
        String jwtToken = jwtService.generateToken(user);

        return Map.of("token",jwtToken);
    }
}
