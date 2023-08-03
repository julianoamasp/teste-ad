package com.example.admissao.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@jakarta.validation.constraints.NotBlank(message = "O nome não pode estar em branco")
	private String nome;
	private String descricao;
	private Double preco;
	private String situacao;
	private Long categoria_id;
	
	/*
	@ManyToOne()
	@JoinColumn(name = "categoria_id") // Correção do nome do atributo aqui
	private Categoria categoria;


	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}*/
		
	
	public Long getId() {
		return id;
	}
	public Long getCategoria_id() {
		return categoria_id;
	}
	public void setCategoria_id(Long categoria_id) {
		this.categoria_id = categoria_id;
	}
	public void setId(Long ind) {
		this.id = ind;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
