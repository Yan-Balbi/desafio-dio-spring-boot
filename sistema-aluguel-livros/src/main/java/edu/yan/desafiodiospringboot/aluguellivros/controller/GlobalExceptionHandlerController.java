package edu.yan.desafiodiospringboot.aluguellivros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteJaPossuiEmprestimoException;
import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteNaoEncontrado;
import edu.yan.desafiodiospringboot.aluguellivros.handler.InsercaoClienteRepetido;
import edu.yan.desafiodiospringboot.aluguellivros.handler.LivroNaoEncontrado;
import edu.yan.desafiodiospringboot.aluguellivros.handler.NenhumExemplarDisponivelException;

@RestControllerAdvice
public class GlobalExceptionHandlerController {
	@ExceptionHandler(ClienteJaPossuiEmprestimoException.class)
	public ResponseEntity<String> handleClienteComEmprestimo(ClienteJaPossuiEmprestimoException excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	@ExceptionHandler(NenhumExemplarDisponivelException.class)
	public ResponseEntity<String> handleSemExemplarDisponivel(NenhumExemplarDisponivelException excessao){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excessao.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExcessaoGenerica(Exception excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	@ExceptionHandler(InsercaoClienteRepetido.class)
	public ResponseEntity<String> handleInsercaoClienteRepetido(InsercaoClienteRepetido excessao){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(excessao.getMessage());
	}
	
	@ExceptionHandler(ClienteNaoEncontrado.class)
	public ResponseEntity<String> handleClinteNaoEncontrado(ClienteNaoEncontrado excessao){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excessao.getMessage());
	}
	
	@ExceptionHandler(LivroNaoEncontrado.class)
	public ResponseEntity<String> handleLivroNaoEncontrado(LivroNaoEncontrado excessao){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(excessao.getMessage());
	}
	
}
