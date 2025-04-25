package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class NenhumExemplarDisponivelException extends RuntimeException{
	public NenhumExemplarDisponivelException() {
		super("Falha ao realizar empréstimo: Não há exemplares disponíveis para este livro no momento.");
	}
}
