package com.shiva.familyfitnessservice.service;

import com.shiva.familyfitnessservice.dto.*;
import com.shiva.familyfitnessservice.model.*;
import com.shiva.familyfitnessservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private TrackerDataInfoRepository trackerDataInfoRepository;

    @Autowired
    private TrackerDataAccessInfoRepository trackerDataAccessInfoRepository;

    public void registerUser(UserDto userInfoDto) {
        UserInfoEntity userInfoEntity = userInfoRepository.findByUserId(userInfoDto.getUserId());
        if (userInfoEntity == null){
            userInfoEntity = new UserInfoEntity();
        }
        userInfoEntity.setUserId(userInfoDto.getUserId());
        userInfoEntity.setEmailId(userInfoDto.getEmailId());
        userInfoEntity.setImageUrl(userInfoDto.getImageUrl());
        userInfoEntity.setUserName(userInfoDto.getUserName());
        userInfoRepository.save(userInfoEntity);
    }

    public AvailableTrackersDto availableTrackers() {
        List<AvailableTrackersInfoEntity> availableTrackersInfoEntitiesList = availableTrackersInfoRepository.findAll();
        AvailableTrackersDto availableTrackersDto = new AvailableTrackersDto();
        List<AvailableTrackerDto> availableTrackerList = new ArrayList<AvailableTrackerDto>();

        for (AvailableTrackersInfoEntity availableTrackersInfoEntity : availableTrackersInfoEntitiesList) {
            AvailableTrackerDto availableTrackerDto = AvailableTrackerDto
                    .builder()
                    .trackerName(availableTrackersInfoEntity.getName())
                    .trackerId(availableTrackersInfoEntity.getTrackerId())
                    .trackerImageUrl(availableTrackersInfoEntity.getImageUrl())
                    .build();
            availableTrackerList.add(availableTrackerDto);
        }
        availableTrackersDto.setTrackers(availableTrackerList);
        return availableTrackersDto;
    }

    public ManageTrackersDto manageTrackers(String userId) {
        List<ManageTrackersInfoEntity> manageTrackersInfoEntities = manageTrackersInfoRepository.findByUserId(userId);
        ManageTrackersDto manageTrackersDto = new ManageTrackersDto();
        List<ManageTrackerDto> manageTrackerDtoList = new ArrayList<ManageTrackerDto>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (ManageTrackersInfoEntity manageTrackersInfoEntity : manageTrackersInfoEntities) {
            String dateTime = manageTrackersInfoEntity.getUpdatedAt().format(formatter);
            ManageTrackerDto manageTrackerDto = ManageTrackerDto
                    .builder()
                    .trackerName(manageTrackersInfoEntity.getName())
                    .trackerId(manageTrackersInfoEntity.getTrackerId())
                    .trackerImageUrl(manageTrackersInfoEntity.getImageUrl())
                    .updatedAt(dateTime)
                    .build();
            manageTrackerDtoList.add(manageTrackerDto);
        }
        manageTrackersDto.setTrackers(manageTrackerDtoList);
        return manageTrackersDto;
    }

    public void linkTracker(ManageTrackerDto linkTrackerDto, String userId) {
        List<ManageTrackersInfoEntity> manageTrackersInfoEntityList = manageTrackersInfoRepository.
                findByUserIdAndTrackerId(linkTrackerDto.getTrackerId(), userId);
        ManageTrackersInfoEntity manageTrackersInfoEntity = new ManageTrackersInfoEntity();
        if (manageTrackersInfoEntityList.size() > 0) {
            manageTrackersInfoEntity = manageTrackersInfoEntityList.get(0);
        }
        manageTrackersInfoEntity.setUserId(userId);
        manageTrackersInfoEntity.setTrackerId(linkTrackerDto.getTrackerId());
        manageTrackersInfoEntity.setName(linkTrackerDto.getTrackerName());
        manageTrackersInfoEntity.setImageUrl(linkTrackerDto.getTrackerImageUrl());
        manageTrackersInfoRepository.save(manageTrackersInfoEntity);
    }

    public List<TrackerDataDto> getTrackerData(Integer trackerId, String userId) {
        Pageable pageable = PageRequest.of(0, 7 , Sort.by("date").descending());
        List<TrackerDataInfoEntity> trackerDataInfoEntityList = trackerDataInfoRepository.findByUserIdAndTrackerId(userId, trackerId, pageable).getContent();
        List<TrackerDataDto> trackerDataDtoList = new ArrayList<TrackerDataDto>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (TrackerDataInfoEntity trackerDataInfoEntity: trackerDataInfoEntityList
             ) {
            String dateTime = trackerDataInfoEntity.getDate().format(formatter);
            TrackerDataDto trackerDataDto = TrackerDataDto
                    .builder()
                    .steps(trackerDataInfoEntity.getSteps())
                    .sleepInSeconds(trackerDataInfoEntity.getSleepInSeconds())
                    .food(trackerDataInfoEntity.getFood())
                    .date(dateTime)
                    .build();
            trackerDataDtoList.add(trackerDataDto);
        }
        return trackerDataDtoList;
    }

    public void saveTrackerData(TrackerDataDto trackerDataDto, Integer trackerId, String userId) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateTime = LocalDate.parse(trackerDataDto.getDate(), formatter);
        List<TrackerDataInfoEntity> trackerDataInfoEntityList = trackerDataInfoRepository.getByUserIdAndTrackerId(userId, trackerId);

        TrackerDataInfoEntity trackerDataInfoEntity = new TrackerDataInfoEntity();;
        if(trackerDataInfoEntityList.size() > 0) {
            for (TrackerDataInfoEntity savedtrackerDataInfoEntity: trackerDataInfoEntityList) {
                if (savedtrackerDataInfoEntity.getDate().toLocalDate().toString().equals(dateTime.toString())) {
                    trackerDataInfoEntity = savedtrackerDataInfoEntity;
                    break;
                }
            }
        }
        trackerDataInfoEntity.setUserId(userId);
        trackerDataInfoEntity.setSteps(trackerDataDto.getSteps());
        trackerDataInfoEntity.setFood(trackerDataDto.getFood());
        trackerDataInfoEntity.setSleepInSeconds(trackerDataDto.getSleepInSeconds());
        trackerDataInfoEntity.setDate(dateTime.atStartOfDay());
        trackerDataInfoEntity.setTrackerId(trackerId);
        trackerDataInfoRepository.save(trackerDataInfoEntity);
    }

    public Integer requestTrackerDataAccess(TrackerDataAccessDto trackerDataAccessDto,
                                            String userId) {
        UserInfoEntity dependantInfoEntity = userInfoRepository.findByEmailId(trackerDataAccessDto.getDependantEmail());
        if (dependantInfoEntity != null) {
            TrackerDataAccessInfoEntity trackerDataAccessInfoEntity = trackerDataAccessInfoRepository
                    .findByDependantEmail(trackerDataAccessDto.getDependantEmail());
            if (trackerDataAccessInfoEntity == null) {
                trackerDataAccessInfoEntity = new TrackerDataAccessInfoEntity();
            }
            trackerDataAccessInfoEntity.setDependantId(dependantInfoEntity.getUserId());
            trackerDataAccessInfoEntity.setDependantEmail(trackerDataAccessDto.getDependantEmail());
            trackerDataAccessInfoEntity.setUserId(userId);
            trackerDataAccessInfoEntity.setImageUrl(dependantInfoEntity.getImageUrl());
            trackerDataAccessInfoEntity.setRelationship(trackerDataAccessDto.getRelationship());
            trackerDataAccessInfoEntity.setStatus(2);
            trackerDataAccessInfoRepository.save(trackerDataAccessInfoEntity);
            return 1;
        } else {
            return 0;
        }

    }

    public List<Notification> getNotifications(String userId) {
       List<TrackerDataAccessInfoEntity> trackerDataAccessInfoEntities = trackerDataAccessInfoRepository
               .findByDependantId(userId);
       List<Notification> notificationsList = new ArrayList<Notification>();
       for (TrackerDataAccessInfoEntity trackerDataAccessInfoEntity: trackerDataAccessInfoEntities) {
           UserInfoEntity userInfoEntity = userInfoRepository.findByUserId(trackerDataAccessInfoEntity.getUserId());
           Notification notification = Notification
                   .builder()
                   .userName(userInfoEntity.getUserName())
                   .relationship(trackerDataAccessInfoEntity.getRelationship())
                   .status(trackerDataAccessInfoEntity.getStatus())
                   .userId(userInfoEntity.getUserId())
                   .imageUrl(userInfoEntity.getImageUrl())
                   .build();
           notificationsList.add(notification);
       }
       return notificationsList;
    }


    public List<Notification> getFamilyMembers(String userId) {
        List<TrackerDataAccessInfoEntity> trackerDataAccessInfoEntities = trackerDataAccessInfoRepository
                .findByUserId(userId);
        List<Notification> notificationsList = new ArrayList<Notification>();
        for (TrackerDataAccessInfoEntity trackerDataAccessInfoEntity: trackerDataAccessInfoEntities) {
            UserInfoEntity dependantInfoEntity = userInfoRepository.findByUserId(trackerDataAccessInfoEntity.getDependantId());
            Notification notification = Notification
                    .builder()
                    .userName(dependantInfoEntity.getUserName())
                    .relationship(trackerDataAccessInfoEntity.getRelationship())
                    .status(trackerDataAccessInfoEntity.getStatus())
                    .userId(dependantInfoEntity.getUserId())
                    .imageUrl(dependantInfoEntity.getImageUrl())
                    .build();
            notificationsList.add(notification);
        }
        return notificationsList;
    }

    public void acceptTrackerDataAccess(Notification notification, String dependantUserId) {
        TrackerDataAccessInfoEntity trackerDataAccessInfoEntity = trackerDataAccessInfoRepository.findByDependantIdAndUserId(dependantUserId, notification.getUserId());
        trackerDataAccessInfoEntity.setStatus(notification.getStatus());
        trackerDataAccessInfoRepository.save(trackerDataAccessInfoEntity);
    }
}


