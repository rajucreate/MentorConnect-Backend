package com.mentorplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorplatform.model.MentorshipMatch;

public interface MentorshipMatchRepository extends JpaRepository<MentorshipMatch, Long>{
	
}
