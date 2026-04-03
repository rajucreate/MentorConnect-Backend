package com.mentorplatform.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mentorplatform.dto.MatchResponseDTO;
import com.mentorplatform.dto.ProgressResponseDTO;
import com.mentorplatform.dto.SessionResponseDTO;
import com.mentorplatform.model.MentorshipMatch;
import com.mentorplatform.model.Progress;
import com.mentorplatform.model.Session;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true);

        // Explicit User → UserResponseDTO mapping
        mapper.typeMap(
                com.mentorplatform.model.User.class,
                com.mentorplatform.dto.UserResponseDTO.class
        );

        // MentorshipMatch → MatchResponseDTO
        mapper.typeMap(MentorshipMatch.class, MatchResponseDTO.class)
                .addMappings(m -> {
                    m.map(src -> src.getMentor(), MatchResponseDTO::setMentor);
                    m.map(src -> src.getMentee(), MatchResponseDTO::setMentee);
                    m.map(src -> src.getStatus().name(), MatchResponseDTO::setStatus);
                });

        // Session → SessionResponseDTO
        mapper.typeMap(Session.class, SessionResponseDTO.class)
                .addMappings(m -> {
                    m.map(src ->
                                    src.getMatch() != null ? src.getMatch().getId() : null,
                            SessionResponseDTO::setMatchId);
                    m.map(src -> src.getStatus().name(), SessionResponseDTO::setStatus);
                });
        
        mapper.typeMap(Progress.class, ProgressResponseDTO.class)
        .addMappings(m -> {
            m.map(src -> 
                src.getMatch() != null ? src.getMatch().getId() : null,
                ProgressResponseDTO::setMatchId
            );
        });

        return mapper;
    }
}