package com.shiva.familyfitnessservice.controller;

import com.shiva.familyfitnessservice.dto.*;
import com.shiva.familyfitnessservice.service.FamilyFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ManageTrackersController {
    @Autowired
    private FamilyFitnessService familyFitnessService;

    @GetMapping("familyfitness/managetrackers")
    public ResponseEntity<ManageTrackersDto> manageTrackers(@RequestHeader(name = "user_id") String userId) {
        try {
            return ResponseEntity.ok(familyFitnessService.manageTrackers(userId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("familyfitness/managetrackers/{trackerId}/trackerdata")
    public ResponseEntity<List<TrackerDataDto>> getTrackerData(@PathVariable(name = "trackerId") Integer trackerId,
                                                         @RequestHeader(name = "user_id") String userId) {
        try {
            return ResponseEntity.ok(familyFitnessService.getTrackerData(trackerId, userId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("familyfitness/managetrackers/{trackerId}/trackerdata")
    public ResponseEntity<TrackerDataDto> saveTrackerData(@RequestBody List<TrackerDataDto> trackersDataDto,
                                                          @PathVariable(name = "trackerId") Integer trackerId,
                                                          @RequestHeader(name = "user_id") String userId) {
        try {
            familyFitnessService.deleteTrackerData(trackerId, userId);
            for (TrackerDataDto trackerDataDto: trackersDataDto) {
                familyFitnessService.saveTrackerData(trackerDataDto, trackerId, userId);
            }
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }


    @PostMapping("familyfitness/linktracker")
    public ResponseEntity<ManageTrackersDto> linkTracker(@RequestBody ManageTrackerDto linkTrackerDto,
                                                         @RequestHeader(name = "user_id") String userId) {
        try {
            familyFitnessService.linkTracker(linkTrackerDto, userId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PostMapping("familyfitness/{trackerId}/unlink")
    public ResponseEntity<ManageTrackersDto> linkTracker(@PathVariable(name = "trackerId") Integer trackerId,
                                                         @RequestHeader(name = "user_id") String userId) {
        try {
            familyFitnessService.unlinkTracker(userId, trackerId);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
