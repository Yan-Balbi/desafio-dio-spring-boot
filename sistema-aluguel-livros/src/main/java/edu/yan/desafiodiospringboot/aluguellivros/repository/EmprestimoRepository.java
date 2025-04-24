package edu.yan.desafiodiospringboot.aluguellivros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	
	@Query("SELECT e FROM Emprestimo e "
			+ "JOIN e.exemplar ex "
			+ "JOIN ex.livro l "
			+ "WHERE l.isbn = :isbnLivro and e.dataDevolucaoEfetiva IS NULL")
	Iterable<Emprestimo> findAtivoByIsbn(@Param("isbnLivro") String isbn);
	
	@Query("SELECT e FROM Emprestimo e "
			+ "JOIN e.cliente c "
			+ "WHERE c.cpf = :cpfCliente and e.dataDevolucaoEfetiva IS NULL")
	Optional<Emprestimo> findAtivoByCpf(@Param("cpfCliente") String cpf);

	@Query("SELECT e FROM Emprestimo e "
			+ "JOIN cliente c "
			+ "WHERE c.id = :clienteId")
	boolean clientePossuiEmprestimosAtrasados(@Param("clienteId") Long clienteId);
	
	@Query("SELECT CASE WHEN "
			+ "	COUNT(e) > 0 THEN true "
			+ "	ELSE false "
			+ "	END "
			+ "FROM Emprestimo e WHERE e.cliente.id = :clienteId AND e.dataDevolucaoEfetiva IS NULL")
	boolean clienteJaPossuiEmprestimoEmAndamento(@Param("clienteId") Long clienteId);
	
	@Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucaoEfetiva IS NULL")
	Iterable<Emprestimo> findEmpestimosConcluidos();

	@Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucaoPrevista < CURRENT_DATE AND e.dataDevolucaoEfetiva IS NULL")
	Iterable<Emprestimo> findEmprestimosAtrasados();

}
