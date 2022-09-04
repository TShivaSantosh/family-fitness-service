package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.ManageTrackersInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageTrackersInfoRepository extends JpaRepository<ManageTrackersInfoEntity, Long> {
    List<ManageTrackersInfoEntity> findByUserId(Long userId);
}
