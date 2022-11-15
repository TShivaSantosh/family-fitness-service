package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.ManageTrackersInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ManageTrackersInfoRepository extends JpaRepository<ManageTrackersInfoEntity, Long> {
    List<ManageTrackersInfoEntity> findByUserId(String userId);
    @Query(value = "SELECT mtie FROM ManageTrackersInfoEntity mtie WHERE mtie.userId=:userId AND mtie.trackerId=:trackerId")
    List<ManageTrackersInfoEntity> findByUserIdAndTrackerId(Integer trackerId, String userId);
    @Transactional
    long deleteByUserIdAndTrackerId(String userId, Integer trackerId);
}
