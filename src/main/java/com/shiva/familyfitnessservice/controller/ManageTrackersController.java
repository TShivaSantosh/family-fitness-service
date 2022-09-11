package com.shiva.familyfitnessservice.controller;

import com.shiva.familyfitnessservice.dto.ManageTrackerDto;
import com.shiva.familyfitnessservice.dto.ManageTrackersDto;
import com.shiva.familyfitnessservice.dto.UserDto;
import com.shiva.familyfitnessservice.service.FamilyFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ManageTrackersController {
    @Autowired
    private FamilyFitnessService familyFitnessService;

    @GetMapping("familyfitness/managetrackers")
    public ResponseEntity<ManageTrackersDto> manageTrackers(@RequestBody UserDto userInfoDto) {
        try {
            return ResponseEntity.ok(familyFitnessService.manageTrackers(userInfoDto));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("familyfitness/linktracker")
    public ResponseEntity<ManageTrackersDto> linkTracker(@RequestBody ManageTrackerDto linkTrackerDto) {
        try {
            familyFitnessService.linkTracker(linkTrackerDto);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
