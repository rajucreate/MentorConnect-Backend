package com.mentorplatform.service;

import java.util.List;
import com.mentorplatform.dto.MatchingResponseDTO;
import com.mentorplatform.dto.MentorshipMatchDTO;

public interface MatchingService {

    List<MatchingResponseDTO> findMentors();  // ✅ REQUIRED

    void createMatch(Long mentorId, String menteeEmail);
    List<MentorshipMatchDTO> getMyMatches(String email);
}