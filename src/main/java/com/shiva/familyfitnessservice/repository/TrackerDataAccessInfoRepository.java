package com.shiva.familyfitnessservice.repository;
import com.shiva.familyfitnessservice.model.TrackerDataAccessInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerDataAccessInfoRepository extends JpaRepository<TrackerDataAccessInfoEntity, Long> {
    TrackerDataAccessInfoEntity findByDependantEmail(String emailId);
}
