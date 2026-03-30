package com.mentorplatform.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressRequestDTO {

    @NotNull(message = "Match ID is required")
    private Long matchId;

    @NotBlank(message = "Goal is required")
    private String goal;

    private Boolean completed;

    private String mentorNotes;
    private String menteeNotes;
}