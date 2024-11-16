package com.challenge.showrooms.controller;

import com.challenge.showrooms.DTO.UserDTO;
import com.challenge.showrooms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserDTO userDTO) {
        Map<String,String> token = service.register(userDTO);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody UserDTO userDTO) {
        Map<String,String> token = service.authenticate(userDTO);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }


}