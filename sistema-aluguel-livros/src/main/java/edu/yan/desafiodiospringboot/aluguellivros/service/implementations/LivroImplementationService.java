package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;
import edu.yan.desafiodiospringboot.aluguellivros.model.Livro;
import edu.yan.desafiodiospringboot.aluguellivros.repository.LivroRepository;
import edu.yan.desafiodiospringboot.aluguellivros.service.interfaces.ILivroService;
import jakarta.transaction.Transactional;

@Service
public class LivroImplementationService implements ILivroService{
	
	@Autowired
	LivroRepository livroRepository;
	
	@Override
	public void inserirLivroComExemplares(Livro livro, int quantidadeExemplares) {
		// TODO Auto-generated method stub
		for(int i = 0; i < quantidadeExemplares; i++ ) {
			livro.adicionarExemplar(new Exemplar());
		}
		livroRepository.save(livro);
	}

	@Override
	public void atualizar(Livro livro) {
		// TODO Auto-generated method stub
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
	public void deletarLivroComExemplares(String isbn) {
		// TODO Auto-generated method stub
		livroRepository.deleteByIsbn(isbn);
	}

	private void buscarExemplaresDeUmLivro() {
		
	}
	
	@Override
	public Optional<Livro> buscarLivroPorIsbn(String isbn) {
		// TODO Auto-generated method stub
		return livroRepository.findByIsbn(isbn);
	}

	@Override
	public Iterable<Livro> buscarLivroPorTitulo(String titulo) {
		// TODO Auto-generated method stub
		return livroRepository.findByTitulo(titulo);
	}

	@Override
	public Iterable<Livro> buscarTodosLivros() {
		// TODO Auto-generated method stub
		return livroRepository.findAll();
	}

}
