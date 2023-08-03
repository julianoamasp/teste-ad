package com.example.admissao.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admissao.Model.Produto;
import com.example.admissao.Repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> getAll() {
		return produtoRepository.findAll();
	}

	public List<Produto> findByNomeAndCategoriaIdAndSituacao(String nome, Long categoriaId, String situacao) {
		return produtoRepository.findByNomeAndCategoriaIdAndSituacao(nome, categoriaId, situacao);
	}

	public Produto createProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto updateProduto(Long id, Produto produto) {
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		if (produtoExistente != null) {
			produto.setId(id);
			return produtoRepository.save(produto);
		}
		return null;
	}

	public boolean deleteProduto(Long id) {
		Produto produtoExistente = produtoRepository.findById(id).orElse(null);
		if (produtoExistente != null) {
			produtoRepository.delete(produtoExistente);
			return true;
		}
		return false;
	}
}
