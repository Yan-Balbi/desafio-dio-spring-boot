package edu.yan.desafiodiospringboot.aluguellivros.service.interfaces;

import java.util.Optional;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;
import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;
import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;

public interface IEmprestimoService {
	/*
	-Registrar empréstimo: void
	-Listar todos empréstimos: List<Emprestimos>
	-Obter emprestimo por isbn: List<Emprestimos>
	-Obter emprestimo por cpf: List<Emprestimos>
	-Obter emprestimos atrasados: List<Emprestimos>
	*/
	
	Optional<Emprestimo> registrarEmprestimo(Long clienteId, Long exemplarId);
	
	void concluirEmprestimo(Long idEmprestimo);
	
	Iterable<Emprestimo> buscarTodos();
	
	Optional<Emprestimo> buscarPorIsbn(String isbn);
	
	Optional<Emprestimo> buscarPorCpf(String cpf);
	
	Iterable<Emprestimo> buscarEmprestimosConcluidos();
	
	Iterable<Emprestimo> buscarEmprestimosAtrasados();

}
