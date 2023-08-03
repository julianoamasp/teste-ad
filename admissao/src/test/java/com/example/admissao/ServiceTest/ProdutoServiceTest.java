package com.example.admissao.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.admissao.Model.Produto;
import com.example.admissao.Repository.ProdutoRepository;
import com.example.admissao.Service.ProdutoService;

@SpringBootTest
public class ProdutoServiceTest {
	 @InjectMocks
	    private ProdutoService produtoService;

	    @Mock
	    private ProdutoRepository produtoRepository;

	    @BeforeEach
	    public void init() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetAll() {
	        List<Produto> produtos = new ArrayList<>();
	        produtos.add(new Produto());
	        when(produtoRepository.findAll()).thenReturn(produtos);
	        List<Produto> result = produtoService.getAll();
	        assertEquals(produtos, result);
	    }

	    @Test
	    public void testFindByNomeAndCategoriaIdAndSituacao() {
	        List<Produto> produtos = new ArrayList<>();
	        produtos.add(new Produto());
	        when(produtoRepository.findByNomeAndCategoriaIdAndSituacao("carrinho", 1L, "Ativado")).thenReturn(produtos);
	        List<Produto> result = produtoService.findByNomeAndCategoriaIdAndSituacao("carrinho", 1L, "Ativado");
	        assertEquals(produtos, result);
	    }

	    @Test
	    public void testCreateProduto() {
	        Produto produto = new Produto();
	        when(produtoRepository.save(produto)).thenReturn(produto);
	        Produto result = produtoService.createProduto(produto);
	        assertEquals(produto, result);
	    }

	    @Test
	    public void testUpdateProduto() {
	        Produto produto = new Produto();
	        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
	        when(produtoRepository.save(produto)).thenReturn(produto);
	        Produto result = produtoService.updateProduto(1L, produto);
	        assertEquals(null, result);
	    }

	    @Test
	    public void testDeleteProduto() {
	        Produto produto = new Produto();
	        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
	        boolean result = produtoService.deleteProduto(1L);
	        assertTrue(result);
	    }
}
