package com.shiva.familyfitnessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AvailableTrackersDto {
    private List<AvailableTrackerDto> trackers;
}
