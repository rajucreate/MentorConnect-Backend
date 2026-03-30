package com.mentorplatform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchRequestDTO {

    @NotNull
    private Long mentorId;

    @NotNull
    private Long menteeId;
}