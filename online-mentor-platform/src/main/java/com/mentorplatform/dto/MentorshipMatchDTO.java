package com.mentorplatform.dto;

import com.mentorplatform.model.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MentorshipMatchDTO {

    private Long id;

    // Mentor info
    private Long mentorId;
    private String mentorName;
    private String mentorEmail;

    // Mentee info
    private Long menteeId;
    private String menteeName;

    // Match metadata
    private MatchStatus status;
}