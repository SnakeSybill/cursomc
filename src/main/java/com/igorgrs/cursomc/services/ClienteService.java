package com.igorgrs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Cliente;
import com.igorgrs.cursomc.dto.ClienteDto;
import com.igorgrs.cursomc.exceptions.DataIntegrityException;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository objRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = objRepository.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Object not found");
		}

		return obj.get();
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return objRepository.save(obj);
	}

	public Cliente update(Cliente obj) {
		find(obj.getId());
		return objRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			objRepository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("This record has children, unable to delete it.");
		}
	}
	
	public List<Cliente> findAll() {
		return objRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return objRepository.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDto dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
}
