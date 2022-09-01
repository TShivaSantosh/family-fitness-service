package com.shiva.familyfitnessservice.repository;

import com.shiva.familyfitnessservice.model.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
}
