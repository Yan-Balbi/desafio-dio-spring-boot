package edu.yan.desafiodiospringboot.aluguellivros.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;

public interface IClienteService {
	/*
 	-Cadastrar usuário: void
    -Apagar usuário: void
	-Obter usuário por nome: List<Usuario>
	-Obter usuário por cpf: Usuario
	-Atualizar exemplar por IsbnLivro: void
	*/
	
	Cliente inserir(Cliente cliente);
	
	void atualizar(Cliente cliente);

	void deletar(Long id);

	Optional<Cliente> buscarPorCpf(String cpf);
	
	Iterable<Cliente> buscarPorNome(String cpf);
	
	Iterable<Cliente> buscarTodos();
	
}
