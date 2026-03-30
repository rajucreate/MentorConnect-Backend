package com.mentorplatform.service;

import com.mentorplatform.dto.MatchResponseDTO;
import com.mentorplatform.model.MentorshipMatch;

public interface MentorshipMatchService {
	MatchResponseDTO createMatch(Long mentorId, Long menteeId);
}


