package com.osapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.osapi.domain.model.OrdemDeServico;
import com.osapi.domain.repository.OrdemServicoRepository;
import com.osapi.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService service;

	@Autowired
	private OrdemServicoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemDeServico cadastrar(@Valid @RequestBody OrdemDeServico ordemDeServico) {
		return service.criar(ordemDeServico);
	}

	@GetMapping
	public List<OrdemDeServico> listar(){
		return repository.findAll();
	}

	@GetMapping("/{codOs}")
	public ResponseEntity<OrdemDeServico> buscarPorId(@PathVariable Long codOs){
		Optional<OrdemDeServico> os =  repository.findById(codOs);
		if(os.isPresent()) {
			return ResponseEntity.ok(os.get());
		}
		return ResponseEntity.notFound().build();
	}
}



















