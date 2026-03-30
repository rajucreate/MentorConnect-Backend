package com.mentorplatform.service;

import java.util.List;

import com.mentorplatform.dto.ProgressRequestDTO;
import com.mentorplatform.dto.ProgressResponseDTO;

public interface ProgressService {

    ProgressResponseDTO createProgress(ProgressRequestDTO request);

    ProgressResponseDTO updateProgress(Long progressId, ProgressRequestDTO request);

    List<ProgressResponseDTO> getProgressByMatch(Long matchId);
}