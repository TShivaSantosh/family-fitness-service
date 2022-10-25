package com.shiva.familyfitnessservice.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracker_data_access")
public class TrackerDataAccessInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dependantId;
    private String dependantEmail;
    private String relationship;
    /*
    * PENDING - 0
    * ACCEPTED - 1
    * REJECTED - 2
    * */
    private Integer status;
    private String userId;
    private String imageUrl;
}
