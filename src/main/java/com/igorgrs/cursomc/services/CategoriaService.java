package com.igorgrs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Categoria;
import com.igorgrs.cursomc.dto.CategoriaDto;
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

	public List<Categoria> findAll() {
		return objRepository.findAll();
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return objRepository.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return objRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			objRepository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("This record has children, unable to delete it.");
		}
	}
	
	public List<Categoria> findAll() {
		return objRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return objRepository.findAll(pageRequest);
	}
	
	public Categoria fromDto(CategoriaDto dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
}
