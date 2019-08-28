package com.igorgrs.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscar(Integer id) {
		return produtoRepository.findById(id).get();
	}
}
