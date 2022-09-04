package com.shiva.familyfitnessservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManageTrackersDto {
    private List<ManageTrackerDto> trackers;
}
