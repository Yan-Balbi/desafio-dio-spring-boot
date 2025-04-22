package edu.yan.desafiodiospringboot.aluguellivros.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>{

	Iterable<Livro> findByTitulo(String titulo);

	Optional<Livro> findByIsbn(String isbn);

}
