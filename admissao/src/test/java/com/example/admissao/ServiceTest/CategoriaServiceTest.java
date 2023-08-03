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

import com.example.admissao.Model.Categoria;
import com.example.admissao.Repository.CategoriaRepository;
import com.example.admissao.Service.CategoriaService;

@SpringBootTest
public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria());
        when(categoriaRepository.findAll()).thenReturn(categorias);
        List<Categoria> result = categoriaService.getAll();
        assertEquals(categorias, result);
    }

    @Test
    public void testFindBynomeAndSituacao() {
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria());
        when(categoriaRepository.findBynomeAndSituacao("Brinquedos", "Ativado")).thenReturn(categorias);
        List<Categoria> result = categoriaService.findBynomeAndSituacao("Brinquedos", "Ativado");
        assertEquals(categorias, result);
    }

    @Test
    public void testCreateCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Categoria result = categoriaService.createCategoria(categoria);
        assertEquals(categoria, result);
    }

    @Test
    public void testUpdateCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Categoria result = categoriaService.updateCategoria(1L, categoria);
        assertEquals(null, result);
    }

    @Test
    public void testDeleteCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        boolean result = categoriaService.deleteCategoria(1L);
        assertTrue(result);
    }
}
