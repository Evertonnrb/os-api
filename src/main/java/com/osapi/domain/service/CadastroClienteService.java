package com.osapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osapi.domain.model.Cliente;
import com.osapi.domain.repository.ClienteRepository;
import com.osapi.execption.NegocioException;

@Service
public class CadastroClienteService {
	
	@Autowired
	private ClienteRepository  clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteExistente != null && clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
}
