package com.tarcio4lmeida.barclick.resources.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final String fieldName;
	private final String message;

}
