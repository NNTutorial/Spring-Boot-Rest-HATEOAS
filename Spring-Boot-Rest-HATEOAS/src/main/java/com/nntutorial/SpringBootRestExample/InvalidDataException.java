package com.nntutorial.SpringBootRestExample;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidDataException extends Exception{

	public InvalidDataException() {
		super("Invalid Data Please enter valid data");
	}

}
