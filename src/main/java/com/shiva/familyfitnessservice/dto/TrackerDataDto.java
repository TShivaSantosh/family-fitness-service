package com.shiva.familyfitnessservice.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TrackerDataDto {
    private Integer steps;
    private Integer sleepInSeconds;
    private Integer food;
    private String date;
}
