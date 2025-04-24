package edu.yan.desafiodiospringboot.aluguellivros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;
import edu.yan.desafiodiospringboot.aluguellivros.service.implementations.EmprestimoImplementationService;

@RestController
public class EmprestimoController {
	
	@Autowired
	private EmprestimoImplementationService emprestimoImplementationService;
	
	@PostMapping
	public ResponseEntity<Optional<Emprestimo>> inserirEmprestimo(@RequestParam Long clienteId, @RequestParam Long livroId) {
	    Optional<Emprestimo> emprestimo = emprestimoImplementationService.registrarEmprestimo(clienteId, livroId);
	    return ResponseEntity.ok(emprestimo);
	}
	
	@PutMapping
	public ResponseEntity<Optional<Emprestimo>> concluirEmprestimo(@RequestBody Long emprestimoId) {
		emprestimoImplementationService.concluirEmprestimo(emprestimoId);
		return ResponseEntity.ok().build();
	}
	
	
}
