package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.TrackerDataInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrackerDataInfoRepository extends JpaRepository<TrackerDataInfoEntity, Long> {

    @Query(value = "SELECT tdie FROM TrackerDataInfoEntity tdie WHERE tdie.userId=:userId AND tdie.trackerId=:trackerId")
    Page<TrackerDataInfoEntity> findByUserIdAndTrackerId(@Param("userId") String userId, @Param("trackerId") Integer trackerId, Pageable pageable);

    @Query(value = "SELECT tdie FROM TrackerDataInfoEntity tdie WHERE tdie.userId=:userId AND tdie.trackerId=:trackerId")
    List<TrackerDataInfoEntity> getByUserIdAndTrackerId(@Param("userId") String userId, @Param("trackerId") Integer trackerId);
}
