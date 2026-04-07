package com.mentorplatform.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mentorplatform.dto.ProgressRequestDTO;
import com.mentorplatform.dto.ProgressResponseDTO;
import com.mentorplatform.exception.ResourceNotFoundException;
import com.mentorplatform.model.MentorshipMatch;
import com.mentorplatform.model.Progress;
import com.mentorplatform.repository.MentorshipMatchRepository;
import com.mentorplatform.repository.ProgressRepository;
import com.mentorplatform.service.ProgressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository progressRepository;
    private final MentorshipMatchRepository matchRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProgressResponseDTO createProgress(ProgressRequestDTO request) {

        MentorshipMatch match = matchRepository.findById(request.getMatchId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Match not found"));

        Progress progress = new Progress();
        progress.setMatch(match);
        progress.setGoal(request.getGoal());
        progress.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);
        progress.setMentorNotes(request.getMentorNotes());
        progress.setMenteeNotes(request.getMenteeNotes());

        Progress saved = progressRepository.save(progress);

        return modelMapper.map(saved, ProgressResponseDTO.class);
    }

    @Override
    public ProgressResponseDTO updateProgress(Long progressId, ProgressRequestDTO request) {

        Progress progress = progressRepository.findById(progressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Progress not found"));

        progress.setGoal(request.getGoal());
        progress.setCompleted(request.getCompleted());
        progress.setMentorNotes(request.getMentorNotes());
        progress.setMenteeNotes(request.getMenteeNotes());

        Progress updated = progressRepository.save(progress);

        return modelMapper.map(updated, ProgressResponseDTO.class);
    }

    @Override
    public List<ProgressResponseDTO> getProgressByMatch(Long matchId) {

        MentorshipMatch match = matchRepository.findById(matchId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Match not found"));

        return progressRepository.findByMatch(match)
                .stream()
                .map(p -> modelMapper.map(p, ProgressResponseDTO.class))
                .toList();
    }
}