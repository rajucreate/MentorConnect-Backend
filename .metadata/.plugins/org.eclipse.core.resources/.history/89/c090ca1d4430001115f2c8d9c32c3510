package com.mentorplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mentorplatform.dto.MatchingResponseDTO;
import com.mentorplatform.service.MatchingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MatchingController {

    private final MatchingService matchingService;

    @GetMapping("/api/match/mentors")
    public List<MatchingResponseDTO> getMentors() {
        return matchingService.findMentors();
    }
}