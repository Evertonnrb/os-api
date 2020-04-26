package com.osapi.execptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.osapi.execption.NegocioException;
import com.osapi.execptionhandler.Problema.Campo;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest webReq){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema p = new  Problema();
		p.setStatus(status.value());
		p.setTitulo(ex.getMessage());
		p.setDataHora(LocalDateTime.now());
		
		return handleExceptionInternal(ex, p, new HttpHeaders(), status, webReq);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			
		HttpHeaders headers, HttpStatus status, WebRequest request) {
		Problema problema = new Problema();
		ArrayList<Campo> campos = new ArrayList<Problema.Campo>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String nome = error.getObjectName();
			campos.add(new Problema.Campo(nome, mensagem));
		}
		problema.setStatus(status.value());
		problema.setTitulo("Atenção aos campos informados");
		problema.setDataHora(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}
