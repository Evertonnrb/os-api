package com.osapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osapi.domain.model.Cliente;
import com.osapi.domain.model.OrdemDeServico;
import com.osapi.domain.model.StatusOrdemServico;
import com.osapi.domain.repository.ClienteRepository;
import com.osapi.domain.repository.OrdemServicoRepository;
import com.osapi.execption.NegocioException;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public OrdemDeServico criar(OrdemDeServico ordemDeServico) {
		Cliente cliente = clienteRepository.findById(ordemDeServico.getCliente().getId())
				.orElseThrow( ()-> new NegocioException("Cliente não encontrado"));
		ordemDeServico.setCliente(cliente);
		ordemDeServico.setStatusOrdemServico(StatusOrdemServico.ABERTA);
		ordemDeServico.setDataAbertura(OffsetDateTime.now());
		return ordemServicoRepository.save(ordemDeServico);
	}
}
