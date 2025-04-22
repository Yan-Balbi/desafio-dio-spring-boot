package edu.yan.desafiodiospringboot.aluguellivros.service.interfaces;

import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;

public interface IExemplarService {
	/*
	-Inserir exemplar por IsbnLivro: void
    -Apagar exemplar por IsbnLivro: void
	-Obter exemplar por IsbnLivro: List<Exemplar>
	-Atualizar exemplar por IsbnLivro: void
	*/
	
//	void inserir(Exemplar exemplar, String isbn);
	
//	void apagar(Exemplar exemplar, String isbn);
	
	Iterable<Exemplar> buscarPorIsbn(String isbn);
	
	Iterable<Exemplar> buscarTodos();
	
}
