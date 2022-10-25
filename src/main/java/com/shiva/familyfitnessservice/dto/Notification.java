package com.shiva.familyfitnessservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Notification {
    private Integer status;
    private String relationship;
    private String userName;
    private String userId;
    private String imageUrl;
}
