package com.ensemble.shifu.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ensemble.shifu.model.smartphone;
import com.ensemble.shifu.service.smartphoneservice;




@Controller
@RequestMapping(value="/smartphones")
public class smartphonecontroller {
	@Autowired
	private smartphoneservice smartphoneService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value="/create", method=RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public smartphone createSmartphone(@RequestBody @Valid smartphone smartphone) {
		System.out.println(smartphone.getModel());
		System.out.println(smartphone.getProducer());
		System.out.println(smartphone.getPrice());
		return smartphoneService.create(smartphone);
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.PUT, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public smartphone editSmartphone(@PathVariable int id, 
			@Valid @RequestBody smartphone smartphone) {
		smartphone.setId(id);
		return smartphoneService.update(smartphone);
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public smartphone deleteSmartphone(@PathVariable int id) {
		return smartphoneService.delete(id);
	}
	
	
	@RequestMapping(value="getall", method=RequestMethod.GET)
			
	@ResponseBody
	public List<smartphone> allPhones() {
		return smartphoneService.getAll();
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
			
	@ResponseBody
	public smartphone GetSmartphone(@PathVariable int id) {
		
		return smartphoneService.get(id);
	}
	
	
	
	
	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody
	public com.ensemble.shifu.exception.helper.ErrorInfo handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage("error.bad.smartphone.id", null, locale);
		
		errorMessage += ex.getValue();
		String errorURL = req.getRequestURL().toString();
		
		return new com.ensemble.shifu.exception.helper.ErrorInfo(errorURL, errorMessage);
	}
	
	
}
