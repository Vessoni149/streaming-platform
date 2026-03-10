package com.streaming.user_service.repository;

import com.streaming.user_service.model.Profile;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepo extends JpaRepository<Profile, Long> {
}
