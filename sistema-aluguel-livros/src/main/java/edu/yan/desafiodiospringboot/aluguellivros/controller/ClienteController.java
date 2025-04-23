package edu.yan.desafiodiospringboot.aluguellivros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.yan.desafiodiospringboot.aluguellivros.model.Cliente;
import edu.yan.desafiodiospringboot.aluguellivros.service.implementations.ClienteImplementationService;

@RestController
@RequestMapping("cliente")
public class ClienteController {

	@Autowired
	private ClienteImplementationService clienteImplementationService;
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente clienteRequest) {
		clienteImplementationService.inserir(clienteRequest);
		return ResponseEntity.ok(clienteRequest);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente clienteRequest) {
		clienteImplementationService.atualizar(clienteRequest);
		return ResponseEntity.ok(clienteRequest);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Cliente> deletar(@PathVariable Long id){
		clienteImplementationService.deletar(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos() {
		return ResponseEntity.ok(clienteImplementationService.buscarTodos());
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Optional<Cliente>> buscarPorCpf(String cpf) {
		return ResponseEntity.ok(clienteImplementationService.buscarPorCpf(cpf));
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Iterable<Cliente>> buscarPorNome(String nome) {
		return ResponseEntity.ok(clienteImplementationService.buscarPorNome(nome));
	}
	
}
