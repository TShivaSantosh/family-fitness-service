package com.shiva.familyfitnessservice.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "manage_trackers")
public class ManageTrackersInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer trackerId;
    private String imageUrl;
    private Long userId;
}
