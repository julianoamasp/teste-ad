package com.example.admissao.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.admissao.Model.Categoria;
import com.example.admissao.Service.CategoriaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	public static List<Categoria> categorias = new ArrayList<>();
	
	@Autowired
	CategoriaService categoriasService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> categorias = categoriasService.getAll();
		return ResponseEntity.ok().body(categorias);
	}
	@GetMapping("/filtro")
	public ResponseEntity<List<Categoria>> findBynomeAndSituacao(String nome, String situacao){
		if (nome == null || situacao == null) {
			return ResponseEntity.badRequest().build();
		}
		List<Categoria> categorias = categoriasService.findBynomeAndSituacao(nome, situacao);
		return ResponseEntity.ok().body(categorias);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> createCategoria(@Valid @RequestBody Categoria categoria){
		if (categoria == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasService.createCategoria(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> updateCategoria(@Valid @RequestBody Categoria categoria){
		if (categoria == null) {
			return ResponseEntity.badRequest().build();
		}
		Categoria categoriaAtualizado = categoriasService.updateCategoria(categoria.getId(), categoria);
		return ResponseEntity.ok().body(categoriaAtualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategoria(@PathVariable Long id){
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		boolean categoriaExcluida = categoriasService.deleteCategoria(id);
		if (categoriaExcluida) {
			return ResponseEntity.ok().build();
		} 
		return ResponseEntity.notFound().build();
	}
}
