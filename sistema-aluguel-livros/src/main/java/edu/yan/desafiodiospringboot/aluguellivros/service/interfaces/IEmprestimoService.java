package edu.yan.desafiodiospringboot.aluguellivros.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
	
	void registrarEmprestimo(Cliente cliente, Exemplar exemplar);
	
	Iterable<Emprestimo> buscarTodos();
	
	Iterable<Emprestimo> buscarPorIbn(String isbn);
	
	Iterable<Emprestimo> buscarPorCpf(String cpf);
	
	Iterable<Emprestimo> buscarEmprestimosConcluidos();
	
	Iterable<Emprestimo> buscarEmprestimosAtrasados();

}
