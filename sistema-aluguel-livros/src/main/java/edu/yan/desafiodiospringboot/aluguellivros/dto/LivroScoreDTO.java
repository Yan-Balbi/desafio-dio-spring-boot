package edu.yan.desafiodiospringboot.aluguellivros.dto;

import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;

public class LivroScoreDTO {
	private Livro livro;
	private int quantidadeExemplares;
	
	public LivroScoreDTO(Livro livro, int quantidadeExemplares) {
		this.livro = livro;
		this.quantidadeExemplares = quantidadeExemplares;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public int getQuantidadeExemplares() {
		return quantidadeExemplares;
	}

	public void setQuantidadeExemplares(int quantidadeExemplares) {
		this.quantidadeExemplares = quantidadeExemplares;
	}
	
}
