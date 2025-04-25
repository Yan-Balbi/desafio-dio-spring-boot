package edu.yan.desafiodiospringboot.aluguellivros.handler;

public class InsercaoClienteRepetido extends RuntimeException{
	public InsercaoClienteRepetido() {
		super("Já existe um cliente cadastrado com esse cpf.");
	}
}
