package com.igorgrs.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igorgrs.cursomc.domain.Pedido;
import com.igorgrs.cursomc.services.PedidoService;

@RestController 
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscar(@PathVariable(value = "id") Integer id) {
		
		Pedido pedido = pedidoService.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}
}
