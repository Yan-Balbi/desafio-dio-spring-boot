package edu.yan.desafiodiospringboot.aluguellivros.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;

public interface ILivroService {
	/*
	-Inserir livro: void
    -Apagar livro: void
	-Obter livro by isbn: Livro
	-Obter livro by nome: List<Livro>
	-Atualizar livro
	-Obter todos os livros: List<Livro>
	 */
	
	void inserirLivroComExemplares(Livro livro, int quantidadeExemplares);
	
	void atualizar(Livro livro);
	
	void deletarLivroComExemplares(String isbn);
	
	Optional<Livro> buscarLivroPorIsbn(String isbn);
	
	Iterable<Livro> buscarLivroPorTitulo(String nome);

	Iterable<Livro> buscarTodosLivros();
}
