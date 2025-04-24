package edu.yan.desafiodiospringboot.aluguellivros.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {
	
	@Query("SELECT e FROM Emprestimo e "
			+ "JOIN Exemplar ex "
			+ "JOIN ex.livro l "
			+ "WHERE l.isbn = :isbnLivro")
	Optional<Emprestimo> findByIsbn(@Param("isbnLivro") String isbn);
	
	@Query("SELECT e FROM Emprestimo e "
			+ "JOIN Cliente c "
			+ "WHERE c.cpf = :cpfCliente")
	Optional<Emprestimo> findByCpf(@Param("cpfCliente") String cpf);

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

	@Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucaoPrevista > CURRENT_DATE AND e.dataDevolucaoEfetiva IS NULL")
	Iterable<Emprestimo> findEmprestimosAtrasados();

}
