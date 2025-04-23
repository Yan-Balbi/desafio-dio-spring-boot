package edu.yan.desafiodiospringboot.aluguellivros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>{

	Iterable<Livro> findByTitulo(String titulo);

	Optional<Livro> findByIsbn(String isbn);

	void deleteByIsbn(String isbn);

	@Query("SELECT e FROM Exemplar e WHERE e.livro.id = :livroId")
	Iterable<Exemplar> buscarExemplaresDeUmLivroPorIsbn(@Param("livroId") Long livroId);

}
