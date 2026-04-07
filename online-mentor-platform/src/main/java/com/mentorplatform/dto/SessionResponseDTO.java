package com.mentorplatform.dto;

import java.time.LocalDateTime;

import com.mentorplatform.model.enums.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SessionResponseDTO {

    private Long sessionId;
    private String mentorName;
    private String menteeName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String meetingLink;

    private SessionStatus status;
}