package com.shiva.familyfitnessservice.controller;

import com.shiva.familyfitnessservice.dto.AvailableTrackersDto;
import com.shiva.familyfitnessservice.service.FamilyFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AvailableTrackersController {
    @Autowired
    private FamilyFitnessService familyFitnessService;

    @GetMapping("familyfitness/availabletrackers")
    public ResponseEntity<AvailableTrackersDto> availableTrackers() {
        try {
            return ResponseEntity.ok(familyFitnessService.availableTrackers());
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

}
