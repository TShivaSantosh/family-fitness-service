package com.shiva.familyfitnessservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AvailableTrackerDto {
    private String trackerName;
    private Integer trackerId;
    private  String trackerImageUrl;
}
