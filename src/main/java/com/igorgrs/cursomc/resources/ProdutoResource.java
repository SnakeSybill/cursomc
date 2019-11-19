package com.igorgrs.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.dto.ProdutoDto;
import com.igorgrs.cursomc.services.ProdutoService;
import com.igorgrs.cursomc.util.URL;

@RestController 
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable(value = "id") Integer id) {
		
		Produto produto = produtoService.buscar(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ProdutoDto>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> list = URL.convertToIntList(categorias);
		Page<ProdutoDto> produtos = produtoService.buscarPorNomeECategoria(nomeDecoded, list, page, linesPerPage, orderBy, direction).map(obj -> new ProdutoDto(obj));
		return ResponseEntity.ok().body(produtos);
	}
}
