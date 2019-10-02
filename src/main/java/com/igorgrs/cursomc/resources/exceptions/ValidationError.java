package com.igorgrs.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer Status, String msg, Long timeStamp) {
		super(Status, msg, timeStamp);
	}
	
	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
}
