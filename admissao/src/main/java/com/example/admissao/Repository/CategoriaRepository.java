package com.example.admissao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.admissao.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	public List<Categoria> findByNome(String nome);
	public List<Categoria> findBySituacao(String situacao);

	@Query(value = "SELECT * FROM categoria WHERE nome LIKE :nome AND situacao LIKE :situacao", nativeQuery = true)
    public List<Categoria> findBynomeAndSituacao(@Param("nome") String nome, @Param("situacao") String situacao);
   
}
