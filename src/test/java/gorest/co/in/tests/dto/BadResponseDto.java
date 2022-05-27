package gorest.co.in.tests.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadResponseDto {

	private String timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	
	private String arguments;
	
	private String path;
}