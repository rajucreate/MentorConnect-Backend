package com.mentorplatform.dto;

import com.mentorplatform.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchingResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}