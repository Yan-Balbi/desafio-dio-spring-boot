package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class LivroNaoEncontrado extends RuntimeException{
	public LivroNaoEncontrado() {
		super("O livro informado não existe.");
	}
}
