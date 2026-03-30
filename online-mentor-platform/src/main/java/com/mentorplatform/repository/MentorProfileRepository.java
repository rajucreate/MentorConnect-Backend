package com.mentorplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorplatform.model.MentorProfile;

public interface MentorProfileRepository extends JpaRepository<MentorProfile, Long> {

}
