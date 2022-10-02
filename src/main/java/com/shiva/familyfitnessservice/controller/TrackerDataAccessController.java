package com.shiva.familyfitnessservice.controller;

import com.shiva.familyfitnessservice.dto.TrackerDataAccessDto;
import com.shiva.familyfitnessservice.dto.TrackerDataDto;
import com.shiva.familyfitnessservice.service.FamilyFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TrackerDataAccessController {

    @Autowired
    private FamilyFitnessService familyFitnessService;

    @PostMapping("familyfitness/trackerdata/request")
    public ResponseEntity<Integer> requestTrackerDataAccess(@RequestBody TrackerDataAccessDto trackerDataAccessDto,
                                                                   @RequestHeader(name = "user_id") String userId) {
        try {
            Integer requestStatus = familyFitnessService.requestTrackerDataAccess(trackerDataAccessDto, userId);
            /*
            * Status 1 - Success
            * Status 0 - Register the User
            * */
            return ResponseEntity.ok(requestStatus);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
