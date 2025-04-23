package edu.yan.desafiodiospringboot.aluguellivros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
	@Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.cliente.id = :clienteId")
	long buscarQuantidadeLivrosAlugados(@Param("clienteId") Long clienteId);

	Optional<Cliente> findByCpf(String cpf);

	Iterable<Cliente> findByNomeUsuario(String nome);
	
}
