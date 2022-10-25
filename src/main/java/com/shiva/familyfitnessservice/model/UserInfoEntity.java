package com.shiva.familyfitnessservice.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String emailId;
    private String imageUrl;
    private String userName;
}
