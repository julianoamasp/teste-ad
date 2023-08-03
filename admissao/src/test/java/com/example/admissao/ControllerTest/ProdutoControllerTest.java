package com.example.admissao.ControllerTest;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.admissao.Controller.ProdutoController;
import com.example.admissao.Model.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		try {
			ProdutoController.produtos.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObterProdutoRetorna200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/produto"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		/*
		 * MvcResult mvcResult =
		 * mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/produto"))
		 * .andExpect(MockMvcResultMatchers.status().isOk()).andReturn(); String content
		 * = mvcResult.getResponse().getContentAsString();
		 */
	}

	@Test
	public void testAdicionarProdutoRetorna201() throws Exception {
		Produto produto = new Produto();
		produto.setNome("Carro Controle Remoto");
		produto.setDescricao("Carro Controle Remoto Descrição");
		produto.setPreco(10.00);
		produto.setSituacao("Ativado");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/produto")
						.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(produto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String responseString = result.getResponse().getContentAsString();
		Produto responseProduto = new ObjectMapper().readValue(responseString, Produto.class);
		assertNotNull(responseProduto.getId());
	}

	@Test
	public void testAtualizarProdutoRetorna200() throws Exception {
		Produto produto = new Produto();
		produto.setId(1L);
		produto.setNome("Carro Controle Remoto atualizado");
		produto.setDescricao("Carro Controle Remoto Descrição atualizada");
		produto.setPreco(20.00);
		produto.setSituacao("Desativado");

		mockMvc.perform(MockMvcRequestBuilders.put("/produto/{id}", produto.getId())
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(produto)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDeletarProdutoRetorna200() throws Exception {
		Long produtoId = 2L;

		mockMvc.perform(MockMvcRequestBuilders.delete("/produto/{id}", produtoId))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
