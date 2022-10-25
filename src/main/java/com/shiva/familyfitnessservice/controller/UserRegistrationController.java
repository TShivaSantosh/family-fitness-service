package com.shiva.familyfitnessservice.controller;

import com.shiva.familyfitnessservice.dto.UserDto;
import com.shiva.familyfitnessservice.service.FamilyFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UserRegistrationController {

    @Autowired
    private FamilyFitnessService familyFitnessService;

    @PostMapping("familyfitness/user/registration")
    public ResponseEntity<Object> registerUser(@RequestBody UserDto userInfoDto) {
        try {
            familyFitnessService.registerUser(userInfoDto);
            return ResponseEntity.ok(null);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

}
