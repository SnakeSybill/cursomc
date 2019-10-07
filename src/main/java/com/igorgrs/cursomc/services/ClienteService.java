package com.igorgrs.cursomc.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.igorgrs.cursomc.domain.Cidade;
import com.igorgrs.cursomc.domain.Cliente;
import com.igorgrs.cursomc.domain.Endereco;
import com.igorgrs.cursomc.domain.enums.TipoCliente;
import com.igorgrs.cursomc.dto.ClienteDto;
import com.igorgrs.cursomc.dto.ClienteNewDto;
import com.igorgrs.cursomc.exceptions.DataIntegrityException;
import com.igorgrs.cursomc.exceptions.ObjectNotFoundException;
import com.igorgrs.cursomc.repository.ClienteRepository;
import com.igorgrs.cursomc.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository objRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = objRepository.findById(id);

		if (!obj.isPresent()) {
			throw new ObjectNotFoundException("Object not found");
		}

		return obj.get();
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = objRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
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

	public List<Cliente> findAll() {
		return objRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return objRepository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDto dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	public Cliente fromDto(ClienteNewDto dto) {
		Set<String> telefones = new HashSet<>();
		telefones.add(dto.getTelefone1());
		
		if (dto.getTelefone1() != null)
			telefones.add(dto.getTelefone2());

		if (dto.getTelefone3() != null)
			telefones.add(dto.getTelefone3());

		Cliente cliente = new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		cliente.setTelefones(telefones);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(),
				dto.getCep(), cliente, new Cidade(dto.getCidadeId(), null, null));
		cliente.addEndereco(end);
		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
