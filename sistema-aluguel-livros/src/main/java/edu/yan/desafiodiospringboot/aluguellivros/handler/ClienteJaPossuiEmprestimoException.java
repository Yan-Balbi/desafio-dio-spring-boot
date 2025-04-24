package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class ClienteJaPossuiEmprestimoException extends RuntimeException{
	public ClienteJaPossuiEmprestimoException(String mensagem) {
		super(mensagem);
	}
}
