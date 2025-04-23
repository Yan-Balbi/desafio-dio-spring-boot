package edu.yan.desafiodiospringboot.aluguellivros.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 13, nullable = false)
	private String isbn;
	@Column(length = 100, nullable = false)
	private String titulo;
	@Column(length = 20, nullable = false)
	private String editora;
	@Column(length = 50, nullable = false)
	private String categoria;
	@Column(length = 50, nullable = true)
	private Double score;
    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Exemplar> exemplares = new ArrayList<>();
    

	public Livro(String isbn, String titulo, String editora, String categoria) {
		this.isbn = isbn;
		this.titulo = titulo;
		this.editora = editora;
		this.categoria = categoria;
	}
	
	public Livro() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Double getScore() {
		return score;
	}
	
	public void adicionarExemplar(Exemplar exemplar) {
        exemplar.setLivro(this);
        exemplares.add(exemplar);
    }
	
	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public void setScore(Double score) {
		this.score = score;
	}
	
}
