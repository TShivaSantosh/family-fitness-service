package com.shiva.familyfitnessservice.service;

import com.shiva.familyfitnessservice.dto.UserInfoDto;
import com.shiva.familyfitnessservice.model.UserInfoEntity;
import com.shiva.familyfitnessservice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamilyFitnessService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public void registerUser(UserInfoDto userInfoDto) {
        UserInfoEntity userInfoEntity = UserInfoEntity
                .builder()
                .userId(userInfoDto.getUserId())
                .emailId(userInfoDto.getEmailId())
                .imageUrl(userInfoDto.getImageUrl())
                .build();
        userInfoRepository.save(userInfoEntity);
    }
}
