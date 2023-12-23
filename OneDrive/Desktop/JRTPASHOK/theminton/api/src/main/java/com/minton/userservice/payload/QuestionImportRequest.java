package com.minton.userservice.payload;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class QuestionImportRequest {

	@CsvBindByName(column = "Question")
	@CsvBindByPosition(position = 0)
	private String question;

	@CsvBindByName(column = "Choice1")
	@CsvBindByPosition(position = 1)
	private String choice1;

	@CsvBindByName(column = "Choice2")
	@CsvBindByPosition(position = 2)
	private String choice2;

	@CsvBindByName(column = "Choice3")
	@CsvBindByPosition(position = 3)
	private String choice3;

	@CsvBindByName(column = "Correct Choice")
	@CsvBindByPosition(position = 4)
	private Long correctChoice;

	@CsvBindByName(column = "Tidbit Link")
	@CsvBindByPosition(position = 5)
	private String tidbitLink;

	@CsvBindByName(column = "Tidbit Text")
	@CsvBindByPosition(position = 6)
	private String tidbitText;

	@CsvBindByName(column = "Tags")
	@CsvBindByPosition(position = 7)
	private String tags;

}
