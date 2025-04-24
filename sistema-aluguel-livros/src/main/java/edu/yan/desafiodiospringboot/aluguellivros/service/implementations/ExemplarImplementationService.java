package edu.yan.desafiodiospringboot.aluguellivros.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yan.desafiodiospringboot.aluguellivros.model.Exemplar;
import edu.yan.desafiodiospringboot.aluguellivros.repository.ExemplarRepository;
import edu.yan.desafiodiospringboot.aluguellivros.service.interfaces.IExemplarService;

@Service
public class ExemplarImplementationService implements IExemplarService{

	@Autowired
	ExemplarRepository exemplarRepository;
	
	@Override
	public Iterable<Exemplar> buscarPorIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Exemplar> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
