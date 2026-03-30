package com.mentorplatform.dto;

import lombok.Data;
import com.mentorplatform.model.enums.Role;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
}