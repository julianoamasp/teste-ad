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

import com.example.admissao.Model.Produto;
import com.example.admissao.Service.ProdutoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

	public static List<Produto> produtos = new ArrayList<>();

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<Produto> getAll() {
		List<Produto> produtos = produtoService.getAll();
		return produtos;
	}

	@GetMapping("/filtro")
	public ResponseEntity<List<Produto>> getByNomeAndCategoriaIdAndSituacao(String nome, Long categoria,
			String situacao) {
		if (nome == null || categoria == null || situacao == null) {
			return ResponseEntity.badRequest().build();
		}
		List<Produto> produtos = produtoService.findByNomeAndCategoriaIdAndSituacao(nome, categoria, situacao);
		return ResponseEntity.ok().body(produtos);
	}

	@PostMapping()
	public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto) {
		if (produto == null) {
			return ResponseEntity.badRequest().build();
		}
		Produto novoProduto = produtoService.createProduto(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> updateProduto(@Valid @PathVariable Long id, @RequestBody Produto produto) {
		if (produto == null || id == null) {
			return ResponseEntity.badRequest().build();
		}
		Produto produtoAtualizado = produtoService.updateProduto(id, produto);
		if (produtoAtualizado != null) {
			return ResponseEntity.ok().body(produtoAtualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.badRequest().build();
		}
		boolean produtoExcluido = produtoService.deleteProduto(id);
		if (produtoExcluido) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
