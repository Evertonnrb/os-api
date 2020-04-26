package com.osapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.osapi.domain.model.OrdemDeServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemDeServico, Long>{

}
