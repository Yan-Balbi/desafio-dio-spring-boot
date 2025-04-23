package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;
import edu.yan.desafiodiospringboot.aluguellivros.repository.ExemplarRepository;
import edu.yan.desafiodiospringboot.aluguellivros.repository.LivroRepository;
import edu.yan.desafiodiospringboot.aluguellivros.service.interfaces.ILivroService;
import jakarta.transaction.Transactional;

@Service
public class LivroImplementationService implements ILivroService{
	
	@Autowired
	ExemplarRepository exemplarRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Override
	public void inserirLivroComExemplares(Livro livro, int quantidadeExemplares) {
		for(int i = 0; i < quantidadeExemplares; i++ ) {
			livro.adicionarExemplar(new Exemplar());
		}
		livroRepository.save(livro);
	}

	@Override
	public void atualizar(Livro livro) {
		Livro livroBd = livroRepository.findById(livro.getId()).orElseThrow(() -> 
			new RuntimeException("livro não encontrado")
		);
		
		livroBd.setCategoria(livro.getCategoria());
		livroBd.setEditora(livro.getEditora());
		livroBd.setIsbn(livro.getIsbn());
		livroBd.setTitulo(livro.getTitulo());
			
		livroRepository.save(livroBd);
	}
	
	@Transactional //Precisa do transactional para fazer remoção em cascade
	@Override
	public void deletarLivroComExemplares(String isbn) throws RuntimeException {
		
		Long livroId = livroRepository.findByIsbn(isbn).get().getId();
		List<Exemplar> exemplares = (List<Exemplar>) buscarExemplaresDeUmLivro(livroId);
		
		boolean permissaoApagarLivro = exemplares.stream().allMatch(ex -> ex.isDisponivel());
		
		if(permissaoApagarLivro) {
			livroRepository.deleteByIsbn(isbn);
		} else {
			throw new RuntimeException("Remoção falhou: Há exemplares deste livro em aluguel");
		}
	}


	private Iterable<Exemplar> buscarExemplaresDeUmLivro(Long livroId) {
		//TODO
		return livroRepository.buscarExemplaresDeUmLivroPorIsbn(livroId);
	}
	
	@Override
	public Optional<Livro> buscarLivroPorIsbn(String isbn) {
		return livroRepository.findByIsbn(isbn);
	}

	@Override
	public Iterable<Livro> buscarLivroPorTitulo(String titulo) {
		return livroRepository.findByTitulo(titulo);
	}

	@Override
	public Iterable<Livro> buscarTodosLivros() {
		return livroRepository.findAll();
	}

}
