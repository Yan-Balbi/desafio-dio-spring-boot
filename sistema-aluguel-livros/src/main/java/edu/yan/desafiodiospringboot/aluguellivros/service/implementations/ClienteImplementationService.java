package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;
import edu.yan.desafiodiospringboot.aluguellivros.repository.ClienteRepository;
import edu.yan.desafiodiospringboot.aluguellivros.service.interfaces.IClienteService;

@Service
public class ClienteImplementationService implements IClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	
	/**
	 * Insere um cliente no banco de dados
	 * @param Cliente cliente
	 * @return void
	 */
	@Override
	public void inserir(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.save(cliente);
	}

	/**
	 * Atualiza os dados de um cliente inserido no banco de dados
	 * @param Cliente cliente
	 * @return void
	 */
	@Override
	public void atualizar(Cliente cliente) {
		// TODO Auto-generated method stub
		Cliente clienteBd = clienteRepository.findById(cliente.getId()).orElseThrow(() -> 
			new RuntimeException("Cliente não encontrado")
		);
		clienteBd.setCpf(cliente.getCpf());
		clienteBd.setNomeUsuario(cliente.getNomeUsuario());
		clienteBd.setSenha(cliente.getSenha());
		
		clienteRepository.save(clienteBd);
	}

	/**
	 * Remove um cliente do banco de dados caso ele não esteja com nenhum livro alugado.
	 * @param Long clienteId
	 * @return void
	 */
	@Override
	public void deletar(Long clienteId) {
		//verificar se o usuário tem algum livro alugado
		//se não tiver, pode alugar
		if(clienteRepository.buscarQuantidadeLivrosAlugados(clienteId) == 0) {
			Optional<Cliente> cliente = clienteRepository.findById(clienteId);
			clienteRepository.delete(cliente.get());
		}
	}

	/**
	 * Retorna o cliente dado o respectivo cpf informado.
	 * @param String cpf
	 * @return {@link Optional}<Cliente}>
	 */
	@Override
	public Optional<Cliente> buscarPorCpf(String cpf) {
		// TODO Auto-generated method stub
		return clienteRepository.findByCpf(cpf);
	}

	/**
	 * Retorna uma lista de clientes para um dado nome informado.
	 * @param String nome
	 * @return {@link List}<Cliente>
	 */
	@Override
	public Iterable<Cliente> buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return clienteRepository.findByNomeUsuario(nome);
	}

	/**
	 * Retorna uma lista contendo todos clientes cadastrados.
	 * @param String nome
	 * @return {@link List}<Cliente>
	 */
	@Override
	public Iterable<Cliente> buscarTodos() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

}
