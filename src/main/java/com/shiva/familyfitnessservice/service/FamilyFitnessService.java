package com.shiva.familyfitnessservice.service;

import com.shiva.familyfitnessservice.dto.*;
import com.shiva.familyfitnessservice.model.AvailableTrackersInfoEntity;
import com.shiva.familyfitnessservice.model.ManageTrackersInfoEntity;
import com.shiva.familyfitnessservice.model.UserInfoEntity;
import com.shiva.familyfitnessservice.repository.AvailableTrackersInfoRepository;
import com.shiva.familyfitnessservice.repository.ManageTrackersInfoRepository;
import com.shiva.familyfitnessservice.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyFitnessService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private AvailableTrackersInfoRepository availableTrackersInfoRepository;

    @Autowired
    private ManageTrackersInfoRepository manageTrackersInfoRepository;

    public void registerUser(UserDto userInfoDto) {
        UserInfoEntity userInfoEntity = UserInfoEntity
                .builder()
                .userId(userInfoDto.getUserId())
                .emailId(userInfoDto.getEmailId())
                .imageUrl(userInfoDto.getImageUrl())
                .build();
        userInfoRepository.save(userInfoEntity);
    }

    public AvailableTrackersDto availableTrackers() {
        List<AvailableTrackersInfoEntity> availableTrackersInfoEntitiesList = availableTrackersInfoRepository.findAll();
        AvailableTrackersDto availableTrackersDto = new AvailableTrackersDto();
        List<AvailableTrackerDto> availableTrackerList = new ArrayList<AvailableTrackerDto>();

        for (AvailableTrackersInfoEntity availableTrackersInfoEntity: availableTrackersInfoEntitiesList) {
            AvailableTrackerDto availableTrackerDto = AvailableTrackerDto
                    .builder()
                    .trackerName(availableTrackersInfoEntity.getName())
                    .trackerId(availableTrackersInfoEntity.getTrackerId())
                    .trackerImageUrl(availableTrackersInfoEntity.getImageUrl())
                    .build();
            availableTrackerList.add(availableTrackerDto);
        }
        availableTrackersDto.setTrackers(availableTrackerList);
        return  availableTrackersDto;
    }

    public ManageTrackersDto manageTrackers(UserDto userInfoDto) {
        List<ManageTrackersInfoEntity> manageTrackersInfoEntities = manageTrackersInfoRepository.findByUserId(userInfoDto.getUserId());
        ManageTrackersDto manageTrackersDto = new ManageTrackersDto();
        List<ManageTrackerDto> manageTrackerDtoList = new ArrayList<ManageTrackerDto>();

        for (ManageTrackersInfoEntity manageTrackersInfoEntity: manageTrackersInfoEntities) {
            ManageTrackerDto manageTrackerDto = ManageTrackerDto
                    .builder()
                    .trackerName(manageTrackersInfoEntity.getName())
                    .trackerId(manageTrackersInfoEntity.getTrackerId())
                    .trackerImageUrl(manageTrackersInfoEntity.getImageUrl())
                    .userId(manageTrackersInfoEntity.getUserId())
                    .build();
            manageTrackerDtoList.add(manageTrackerDto);
        }
        manageTrackersDto.setTrackers(manageTrackerDtoList);
        return manageTrackersDto;
    }
}
