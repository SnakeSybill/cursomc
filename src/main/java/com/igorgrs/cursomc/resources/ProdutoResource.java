package com.igorgrs.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.services.ProdutoService;

@RestController 
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable(value = "id") Integer id) {
		
		Produto produto = produtoService.buscar(id);
		return ResponseEntity.ok().body(produto);
	}
}
