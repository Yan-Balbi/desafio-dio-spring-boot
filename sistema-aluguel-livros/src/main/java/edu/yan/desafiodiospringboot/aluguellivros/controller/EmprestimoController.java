package edu.yan.desafiodiospringboot.aluguellivros.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;
import edu.yan.desafiodiospringboot.aluguellivros.service.implementations.EmprestimoImplementationService;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoImplementationService emprestimoImplementationService;
	
	@PostMapping
	public ResponseEntity<Emprestimo> inserirEmprestimo(@RequestParam Long clienteId, @RequestParam Long livroId) {
	    Emprestimo emprestimoCriado = emprestimoImplementationService.registrarEmprestimo(clienteId, livroId);
	    
	    URI localEmprestimoCriado = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emprestimoCriado).toUri();
	    
	    return ResponseEntity.created(localEmprestimoCriado).body(emprestimoCriado);
	}
	
	@PutMapping
	public ResponseEntity<Optional<Emprestimo>> concluirEmprestimo(@RequestBody Long emprestimoId) {
		emprestimoImplementationService.concluirEmprestimo(emprestimoId);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Emprestimo>> buscarTodos() {
		return ResponseEntity.ok(emprestimoImplementationService.buscarTodos());
	}
	
	@GetMapping("isbn/{isbn}")
	public ResponseEntity<Iterable<Emprestimo>> buscarEmprestimoAtivoPorIsbn(@PathVariable String isbn){
		return ResponseEntity.ok(emprestimoImplementationService.buscarAtivoPorIsbn(isbn));
	}
	
	@GetMapping("cpf/{cpf}")
	public ResponseEntity<Optional<Emprestimo>> buscarEmprestimoAtivoPorCpf(@PathVariable String cpf){
		return ResponseEntity.ok(emprestimoImplementationService.buscarPorCpf(cpf));
	}
	
	@GetMapping("/concluidos")
	public ResponseEntity<Iterable<Emprestimo>> buscarEmprestimosConcluidos(){
		return ResponseEntity.ok(emprestimoImplementationService.buscarEmprestimosConcluidos());
	}
	
	@GetMapping("/atrasados")
	public ResponseEntity<Iterable<Emprestimo>> buscarEmprestimosAtrasados(){
		return ResponseEntity.ok(emprestimoImplementationService.buscarEmprestimosAtrasados());
	}
	
}
