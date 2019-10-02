package com.igorgrs.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.igorgrs.cursomc.domain.Cliente;
import com.igorgrs.cursomc.dto.ClienteDto;
import com.igorgrs.cursomc.dto.ClienteNewDto;
import com.igorgrs.cursomc.services.ClienteService;

@RestController 
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable(value = "id") Integer id) {
		
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto clienteDto) {
		Cliente cliente = clienteService.insert(clienteService.fromDto(clienteDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
		Cliente cliente = clienteService.fromDto(clienteDto);
		cliente.setId(id);
		cliente = clienteService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Integer id) {
		
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> cliente = clienteService.findAll().stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<ClienteDto> cliente = clienteService.findPage(page, linesPerPage, orderBy, direction).map(obj -> new ClienteDto(obj));
		return ResponseEntity.ok().body(cliente);
	}
}
