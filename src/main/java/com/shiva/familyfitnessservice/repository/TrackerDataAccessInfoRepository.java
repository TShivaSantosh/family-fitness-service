package com.shiva.familyfitnessservice.repository;
import com.shiva.familyfitnessservice.model.TrackerDataAccessInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerDataAccessInfoRepository extends JpaRepository<TrackerDataAccessInfoEntity, Long> {
    TrackerDataAccessInfoEntity findByDependantEmail(String emailId);
    List<TrackerDataAccessInfoEntity> findByDependantId(String dependantId);
    TrackerDataAccessInfoEntity findByDependantIdAndUserId(String dependantId, String userId);

    List<TrackerDataAccessInfoEntity> findByUserId(String userId);
}
