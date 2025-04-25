package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class ClienteNaoEncontrado extends RuntimeException{
	public ClienteNaoEncontrado() {
		super("O cliente informado não existe");
	}
}
