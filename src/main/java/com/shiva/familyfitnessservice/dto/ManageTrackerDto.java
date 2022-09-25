package com.shiva.familyfitnessservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ManageTrackerDto {
    private String trackerName;
    private Integer trackerId;
    private String trackerImageUrl;
    private String updatedAt;
}
