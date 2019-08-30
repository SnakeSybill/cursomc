package com.igorgrs.cursomc;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.igorgrs.cursomc.domain.Categoria;
import com.igorgrs.cursomc.domain.Cidade;
import com.igorgrs.cursomc.domain.Estado;
import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.repository.CategoriaRepository;
import com.igorgrs.cursomc.repository.CidadeRepository;
import com.igorgrs.cursomc.repository.EstadoRepository;
import com.igorgrs.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository catRep;
	
	@Autowired
	private ProdutoRepository prodRep;

	@Autowired
	private CidadeRepository cidRep;
	
	@Autowired
	private EstadoRepository estRep;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática", new ArrayList<Produto>());
		catRep.save(c1);
		
		Produto p1 = new Produto(null, "Mouse", 20.0, new ArrayList<Categoria>());
		prodRep.save(p1);
		
		c1.getProdutos().add(p1);
		p1.getCategorias().add(c1);
		
		catRep.save(c1);
		prodRep.save(p1);
		
		Estado estado1 = new Estado(null, "Minas Gerais", new ArrayList<Cidade>());
		estRep.save(estado1);
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		cidRep.save(cidade1);
		
		Cidade cidade2 = new Cidade(null, "Unaí", estado1);
		cidRep.save(cidade2);

	}
}
