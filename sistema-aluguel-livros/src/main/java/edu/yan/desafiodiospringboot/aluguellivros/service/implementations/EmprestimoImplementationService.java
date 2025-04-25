package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteJaPossuiEmprestimoException;
import edu.yan.desafiodiospringboot.aluguellivros.handler.ClienteNaoEncontrado;
import edu.yan.desafiodiospringboot.aluguellivros.handler.LivroNaoEncontrado;
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
	
	/**
	 * Registra um emprestimo caso haja exemplares de livros disponíveis e caso o cliente não esteja com nenhum livro alugado
	 * @param Long clienteId
	 * @param Long livroId
	 * @return Optional<Emprestimo>
	 */
	@Override
	public Optional<Emprestimo> registrarEmprestimo(Long clienteId, Long livroId) {
		if(clienteRepository.findById(clienteId).isEmpty()) {
			throw new ClienteNaoEncontrado();
		}
		
		if(livroRepository.findById(livroId).isEmpty()) {
			throw new LivroNaoEncontrado();
		}
		
		if(!emprestimoRepository.clienteJaPossuiEmprestimoEmAndamento(clienteId)) {
			Optional<Cliente> cliente = clienteRepository.findById(clienteId);
			
			List<Exemplar> exemplares = (List<Exemplar>) livroRepository.buscarExemplaresDeUmLivroPorIsbn(livroId);
			
			Optional<Exemplar> exemplar = exemplares.stream().filter(ex -> ex.isDisponivel()).findFirst();
			
			if(exemplar.isEmpty()) {
				throw new NenhumExemplarDisponivelException();
			}
			exemplar.get().setDisponivel(false);
			Emprestimo emprestimo = new Emprestimo(cliente.get(), exemplar.get(), LocalDate.now());
			emprestimoRepository.save(emprestimo);
			return Optional.of(emprestimo);

		} else {
			throw new ClienteJaPossuiEmprestimoException();
		}
		
	}
	
	@Override
	public void concluirEmprestimo(Long idEmprestimo) {
		Emprestimo emprestimo = emprestimoRepository.findById(idEmprestimo).orElseThrow(() -> new RuntimeException("Emprestimo não encontrado"));
		
		emprestimo.setDataDevolucaoEfetiva(LocalDate.now());
		emprestimo.getExemplar().setDisponivel(true);
		
		emprestimoRepository.save(emprestimo);
	}

	/**
	 * Retorna uma lista de empréstimos cadastrados, concluidos e não concluidos.
	 * @return {@link Iterable}<Emprestimo>
	 */
	@Override
	public Iterable<Emprestimo> buscarTodos() {
		return emprestimoRepository.findAll();
	}

	/**
	 * Retorna uma lista de empréstimos ativos para um dado livro.
	 * @param String isbn
	 * @return {@link Iterable}<Emprestimo>
	 */
	@Override
	public Iterable<Emprestimo> buscarAtivoPorIsbn(String isbn) {
		return emprestimoRepository.findAtivoByIsbn(isbn);
	}

	/**
	 * Retorna uma lista de empréstimos ativos para um dado cpf.
	 * @param String cpf
	 * @return {@link Optional}<Emprestimo>
	 */
	@Override
	public Optional<Emprestimo> buscarPorCpf(String cpf) {
		return emprestimoRepository.findAtivoByCpf(cpf);
	}

	/**
	 * Retorna uma lista de empréstimos concluidos.
	 * @return {@link Optional}<Iterable<Emprestimo>>
	 */
	@Override
	public Iterable<Emprestimo> buscarEmprestimosConcluidos() {
		return emprestimoRepository.findEmpestimosConcluidos();
	}

	/**
	 * Retorna uma lista de empréstimos atrasados.
	 * @return {@link Optional}<Iterable<Emprestimo>>
	 */
	@Override
	public Iterable<Emprestimo> buscarEmprestimosAtrasados() {
		return emprestimoRepository.findEmprestimosAtrasados();
	}

}
