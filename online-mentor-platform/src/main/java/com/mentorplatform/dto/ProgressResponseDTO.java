	package com.mentorplatform.dto;
	
	import lombok.Data;
	
	@Data
	public class ProgressResponseDTO {
	
	    private Long id;
	    private Long matchId;
	    private String goal;
	    private Boolean completed;
	    private String mentorNotes;
	    private String menteeNotes;
	}