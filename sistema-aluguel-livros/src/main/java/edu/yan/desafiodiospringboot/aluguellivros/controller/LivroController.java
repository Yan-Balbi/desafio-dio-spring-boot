package edu.yan.desafiodiospringboot.aluguellivros.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.yan.desafiodiospringboot.aluguellivros.dto.LivroScoreDTO;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;
import edu.yan.desafiodiospringboot.aluguellivros.service.implementations.LivroImplementationService;

@RestController
@RequestMapping("livros")
public class LivroController {
	
	@Autowired
	private LivroImplementationService livroService;
	
	@PostMapping
	public ResponseEntity<Livro> inserir(@RequestBody LivroScoreDTO request) {
		Livro livroCriado = livroService.inserirLivroComExemplares(request.getLivro(), request.getQuantidadeExemplares());
		
		URI localLivroCriado = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livroCriado.getId()).toUri();
		
		return ResponseEntity.created(localLivroCriado).body(livroCriado);
	}
	
	@PutMapping
	public ResponseEntity<Livro> atualizar(@RequestBody Livro livro) {
		livroService.atualizar(livro);;
		return ResponseEntity.ok(livro);
	}
	
	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<Livro> deletarLivroComExemplares(@PathVariable String isbn) {
		livroService.deletarLivroComExemplares(isbn);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Livro>> buscarTodos() {
		return ResponseEntity.ok(livroService.buscarTodosLivros());
	}

	@GetMapping("/isbn/{isbn}")
	public ResponseEntity<Optional<Livro>> buscarPorIsbn(@PathVariable String isbn) {
		return ResponseEntity.ok(livroService.buscarLivroPorIsbn(isbn));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Iterable<Livro>> buscarPorTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(livroService.buscarLivroPorTitulo(titulo));
	}
}
