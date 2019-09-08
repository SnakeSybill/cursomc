package com.igorgrs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Categoria;
import com.igorgrs.cursomc.exceptions.DataIntegrityException;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository objRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = objRepository.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Object not found");
		}

		return obj.get();
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return objRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
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
}
