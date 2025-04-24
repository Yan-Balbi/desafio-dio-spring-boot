package edu.yan.desafiodiospringboot.aluguellivros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteJaPossuiEmprestimoException;
import edu.yan.desafiodiospringboot.aluguellivros.handler.NenhumExemplarDisponivelException;

@RestControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(ClienteJaPossuiEmprestimoException.class)
	public ResponseEntity<String> handleClienteComEmprestimo(ClienteJaPossuiEmprestimoException excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	@ExceptionHandler(NenhumExemplarDisponivelException.class)
	public ResponseEntity<String> handleSemExemplarDisponivel(NenhumExemplarDisponivelException excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExcessaoGenerica(Exception excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	
}
