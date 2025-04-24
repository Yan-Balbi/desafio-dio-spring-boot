package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class NenhumExemplarDisponivelException extends RuntimeException{
	public NenhumExemplarDisponivelException(String menssagem) {
		super(menssagem);
	}
}
