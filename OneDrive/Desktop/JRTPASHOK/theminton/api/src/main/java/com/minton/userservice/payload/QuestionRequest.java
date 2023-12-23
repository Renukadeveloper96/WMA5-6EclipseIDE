package com.minton.userservice.payload;

import lombok.Data;

@Data
public class QuestionRequest {

	private String question;

	private String choice1;

	private String choice2;

	private String choice3;
	
	private Long correctChoice;

	private String tidbitLink;

	private String tidbitText;

	private String tags;

}
