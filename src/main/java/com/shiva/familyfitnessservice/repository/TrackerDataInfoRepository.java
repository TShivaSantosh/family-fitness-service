package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.TrackerDataInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrackerDataInfoRepository extends JpaRepository<TrackerDataInfoEntity, Long> {

    @Query(value = "SELECT tdie FROM TrackerDataInfoEntity tdie WHERE tdie.userId=:userId AND tdie.trackerId=:trackerId")
    List<TrackerDataInfoEntity> trackerDataByUserIdAndTrackerId(@Param("userId") String userId, @Param("trackerId") Integer trackerId);
}
