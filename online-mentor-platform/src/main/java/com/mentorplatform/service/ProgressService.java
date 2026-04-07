package com.mentorplatform.service;

import java.util.List;

import com.mentorplatform.dto.ProgressRequestDTO;
import com.mentorplatform.dto.ProgressResponseDTO;

public interface ProgressService {

    ProgressResponseDTO createProgress(String email, ProgressRequestDTO request);

    ProgressResponseDTO updateProgress(String email, Long progressId, ProgressRequestDTO request);

    List<ProgressResponseDTO> getProgressByMatch(String email, Long matchId);
}