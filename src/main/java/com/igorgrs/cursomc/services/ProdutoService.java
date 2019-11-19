package com.igorgrs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Categoria;
import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.CategoriaRepository;
import com.igorgrs.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		return produto.get();
	}
	
	public Page<Produto> buscarPorNomeECategoria(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
