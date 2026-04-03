package com.mentorplatform.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mentorplatform.dto.MatchingResponseDTO;
import com.mentorplatform.model.User;
import com.mentorplatform.model.enums.Role;
import com.mentorplatform.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final UserRepository userRepository;

    public List<MatchingResponseDTO> findMentors() {

        List<User> mentors = userRepository.findByRole(Role.MENTOR);

        return mentors.stream()
                .map(user -> new MatchingResponseDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
    }
}