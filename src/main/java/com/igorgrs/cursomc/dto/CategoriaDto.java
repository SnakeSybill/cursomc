package com.igorgrs.cursomc.dto;

import com.igorgrs.cursomc.domain.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDto {

	private Integer id;
	private String nome;

	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
}
