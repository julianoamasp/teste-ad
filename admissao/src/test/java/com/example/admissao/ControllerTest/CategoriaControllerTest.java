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

import com.example.admissao.Controller.CategoriaController;
import com.example.admissao.Model.Categoria;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		try {
			CategoriaController.categorias.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testObterCategoriasRetorna200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/categoria"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		/*
		 * MvcResult mvcResult =
		 * mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/categoria")
		 * ) .andExpect(MockMvcResultMatchers.status().isOk()).andReturn(); String
		 * content = mvcResult.getResponse().getContentAsString();
		 */
	}

	@Test
	public void testAdicionarCategoriaRetorna201() throws Exception {
		Categoria categoria = new Categoria();
		categoria.setNome("brinquedo");
		categoria.setSituacao("Ativado");

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/categoria")
						.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(categoria)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String responseString = result.getResponse().getContentAsString();
		Categoria responseCategoria = new ObjectMapper().readValue(responseString, Categoria.class);
		assertNotNull(responseCategoria.getId());
	}

	@Test
	public void testAtualizarCategoriaRetorna200() throws Exception {
		Categoria categoria = new Categoria();
		categoria.setId(2L);
		categoria.setNome("brinquedo editado");
		categoria.setSituacao("Ativado");

		mockMvc.perform(MockMvcRequestBuilders.put("/categoria")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(categoria)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testDeletarCategoriaRetorna200() throws Exception {
		Long categoriaId = 2L;

		mockMvc.perform(MockMvcRequestBuilders.delete("/categoria/{id}", categoriaId))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}