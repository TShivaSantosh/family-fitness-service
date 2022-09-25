package com.shiva.familyfitnessservice.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trackers_data")
public class TrackerDataInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer trackerId;
    private Integer steps;
    private Integer sleepInSeconds;
    private Integer food;
    private String userId;
    private LocalDateTime date;

}
