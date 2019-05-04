package com.projetoSpring.projetoAGG2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.repository.PServicoRepository;

@Service
public class PServicoService {

	@Autowired
	private PServicoRepository repository;

	public PServico save(PServico ps) {
		return repository.saveAndFlush(ps);

	}
	public void delete(Long cpf) {
		repository.deleteById(cpf);
	}
	
	public List<PServico> findAll(){
		return repository.findAll();
		
	}
}
