package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.AvailableTrackersInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableTrackersInfoRepository extends JpaRepository<AvailableTrackersInfoEntity, Long> {
}
