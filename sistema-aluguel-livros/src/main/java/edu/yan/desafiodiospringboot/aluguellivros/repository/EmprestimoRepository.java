package edu.yan.desafiodiospringboot.aluguellivros.repository;

import org.springframework.data.repository.CrudRepository;

import edu.yan.desafiodiospringboot.aluguellivros.model.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {

}
