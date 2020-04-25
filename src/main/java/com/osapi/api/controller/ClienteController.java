package com.osapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.osapi.domain.model.Cliente;
import com.osapi.domain.repository.ClienteRepository;
import com.osapi.domain.service.CadastroClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	/*@PersistenceContext
	private EntityManager manager;*/
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private CadastroClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return repository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = repository.findById(clienteId);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}
	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		
		if(!repository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Cliente> delete(@PathVariable Long idCliente){
		if(!repository.existsById(idCliente)) {
			return ResponseEntity.notFound().build();
		}
		clienteService.excluir(idCliente);
		return ResponseEntity.noContent().build();
	}
}
