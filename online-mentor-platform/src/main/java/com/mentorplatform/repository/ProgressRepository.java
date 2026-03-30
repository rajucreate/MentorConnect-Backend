package com.mentorplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorplatform.model.MentorshipMatch;
import com.mentorplatform.model.Progress;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
	
	List<Progress> findByMatch(MentorshipMatch match);
}
