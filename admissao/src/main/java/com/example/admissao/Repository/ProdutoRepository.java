package com.example.admissao.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.admissao.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	public List<Produto> findByNome(String nome);
	  public List<Produto> findBySituacao(String situacao);
	  
	  @Query(value = "SELECT * FROM produto WHERE nome like :nome or categoria_id = :categoriaId or situacao = :situacao", nativeQuery = true)
		public List<Produto> findByNomeAndCategoriaIdAndSituacao(@Param("nome") String nome,@Param("categoriaId") Long categoriaId, @Param("situacao") String situacao);
}
