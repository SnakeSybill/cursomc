package com.igorgrs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Cliente;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(!cliente.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
			
		return cliente.get();
	}
}
