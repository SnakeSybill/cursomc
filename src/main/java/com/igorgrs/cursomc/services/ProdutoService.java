package com.igorgrs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto buscar(Integer id) {
		
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		return produto.get();
	}
}
