package com.mentorplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mentorplatform.model.MentorshipMatch;
import com.mentorplatform.model.User;

public interface MentorshipMatchRepository extends JpaRepository<MentorshipMatch, Long>{

	List<MentorshipMatch> findByMentee(User mentee);
	List<MentorshipMatch> findByMentor(User mentor);
}
