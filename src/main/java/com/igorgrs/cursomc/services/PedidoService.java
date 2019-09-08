package com.igorgrs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Pedido;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		
		if(!pedido.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
			
		return pedido.get();
	}
}
