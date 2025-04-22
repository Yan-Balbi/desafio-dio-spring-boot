package edu.yan.desafiodiospringboot.aluguellivros.repository;

import org.springframework.data.repository.CrudRepository;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	
}
