package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteJaPossuiEmprestimoException;
import edu.yan.desafiodiospringboot.aluguellivros.handler.NenhumExemplarDisponivelException;
import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;
import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;
import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;
import edu.yan.desafiodiospringboot.aluguellivros.repository.ClienteRepository;
import edu.yan.desafiodiospringboot.aluguellivros.repository.EmprestimoRepository;
import edu.yan.desafiodiospringboot.aluguellivros.repository.ExemplarRepository;
import edu.yan.desafiodiospringboot.aluguellivros.repository.LivroRepository;
import edu.yan.desafiodiospringboot.aluguellivros.service.interfaces.IEmprestimoService;

@Service
public class EmprestimoImplementationService implements IEmprestimoService{

	@Autowired
	ExemplarRepository exemplarRepository;

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Override
	public Optional<Emprestimo> registrarEmprestimo(Long clienteId, Long livroId) {
		if(!emprestimoRepository.clienteJaPossuiEmprestimo(clienteId)) {
			Optional<Cliente> cliente = clienteRepository.findById(clienteId);
			
			List<Exemplar> exemplares = (List<Exemplar>) livroRepository.buscarExemplaresDeUmLivroPorIsbn(livroId);
			
			Optional<Exemplar> exemplar = exemplares.stream().filter(ex -> ex.isDisponivel()).findFirst();
			
			if(exemplar.isEmpty()) {
				throw new NenhumExemplarDisponivelException("Falha ao realizar empréstimo: Não há exemplares disponíveis para este livro no momento.");
			}
			exemplar.get().setDisponivel(false);
			Emprestimo emprestimo = new Emprestimo(cliente.get(), exemplar.get(), LocalDate.now());
			emprestimoRepository.save(emprestimo);
			return Optional.of(emprestimo);

		} else {
			throw new ClienteJaPossuiEmprestimoException("Falha ao realizar empréstimo: O usuário á possui um livro alugado no momento.");
		}
		
	}
	
	@Override
	public void concluirEmprestimo(Long idEmprestimo) {
		Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo).orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));
		
		emprestimo.setDataDevolucaoEfetiva(LocalDate.now());
		
		emprestimoRepository.save(emprestimo);
	}
	
	private boolean haEmprestimoEmAndamento() {
		return false;
	}

	@Override
	public Iterable<Emprestimo> buscarTodos() {
		return emprestimoRepository.findAll();
	}

	@Override
	public Optional<Emprestimo> buscarPorIsbn(String isbn) {
		return emprestimoRepository.findByIsbn(isbn);
	}

	@Override
	public Optional<Emprestimo> buscarPorCpf(String cpf) {
		return emprestimoRepository.findByCpf(cpf);
	}

	@Override
	public Iterable<Emprestimo> buscarEmprestimosConcluidos() {
		return emprestimoRepository.findEmpestimosConcluidos();
	}

	@Override
	public Iterable<Emprestimo> buscarEmprestimosAtrasados() {
		return emprestimoRepository.findEmprestimosAtrasados();
	}

}
