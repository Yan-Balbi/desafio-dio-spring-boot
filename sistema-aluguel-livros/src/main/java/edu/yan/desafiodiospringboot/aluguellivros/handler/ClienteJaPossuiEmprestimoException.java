package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class ClienteJaPossuiEmprestimoException extends RuntimeException{
	public ClienteJaPossuiEmprestimoException() {
		super("Falha ao realizar empréstimo: O usuário á possui um livro alugado no momento.");
	}
}
