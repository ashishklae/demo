package com.ensemble.shifu.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ensemble.shifu.exception.smartphoneNotFoundException;

@ControllerAdvice
public class RestExceptionProcessor {
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(smartphoneNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public com.ensemble.shifu.exception.helper.ErrorInfo smartphoneNotFound(HttpServletRequest req, smartphoneNotFoundException ex) {
		
		String errorMessage = localizeErrorMessage("error.no.smartphone.id");
		
		errorMessage += ex.getSmartphoneId();
		String errorURL = req.getRequestURL().toString();
		
		return new com.ensemble.shifu.exception.helper.ErrorInfo(errorURL, errorMessage);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public com.ensemble.shifu.exception.helper.ErrorFormInfo handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {
		
		String errorMessage = localizeErrorMessage("error.bad.arguments");
		String errorURL = req.getRequestURL().toString();
		
		com.ensemble.shifu.exception.helper.ErrorFormInfo errorInfo = new com.ensemble.shifu.exception.helper.ErrorFormInfo(errorURL, errorMessage);
		
		BindingResult result = ex.getBindingResult();		
		List<FieldError> fieldErrors = result.getFieldErrors();
		
		errorInfo.getFieldErrors().addAll(populateFieldErrors(fieldErrors));
		
		return errorInfo;
	}
	public List<com.ensemble.shifu.exception.helper.FieldErrorDTO> populateFieldErrors(List<FieldError> fieldErrorList) {
		List<com.ensemble.shifu.exception.helper.FieldErrorDTO> fieldErrors = new ArrayList<com.ensemble.shifu.exception.helper.FieldErrorDTO>();
		StringBuilder errorMessage = new StringBuilder("");
		
		for (FieldError fieldError : fieldErrorList) {
			
			errorMessage.append(fieldError.getCode()).append(".");
			errorMessage.append(fieldError.getObjectName()).append(".");
			errorMessage.append(fieldError.getField());
			
			String localizedErrorMsg = localizeErrorMessage(errorMessage.toString());
			
			fieldErrors.add(new com.ensemble.shifu.exception.helper.FieldErrorDTO(fieldError.getField(), localizedErrorMsg));
			errorMessage.delete(0, errorMessage.capacity());
		}
		return fieldErrors;
	}
	
	public String localizeErrorMessage(String errorCode) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage(errorCode, null, locale);
		return errorMessage;
	}
	
		
		
	}
	

