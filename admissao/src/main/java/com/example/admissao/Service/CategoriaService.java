package com.example.admissao.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admissao.Model.Categoria;
import com.example.admissao.Repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> getAll(){
		return categoriaRepository.findAll();
	}
	
	public List<Categoria> findBynomeAndSituacao(String nome, String situacao){
		return categoriaRepository.findBynomeAndSituacao(nome, situacao);
	}
	
	public Categoria createCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria updateCategoria(Long id, Categoria categoria) {
		Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
		if(categoriaExistente != null) {
			categoria.setId(id);
			categoriaRepository.save(categoria);
		}
		return null;
	}
	
	public boolean deleteCategoria(Long id) {
		Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);
		if(categoriaExistente != null) {
			categoriaRepository.delete(categoriaExistente);
			return true;
		}
		return false;
	}
}
