package com.projetoSpring.projetoAGG2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.repository.PServicoRepository;

@Service
public class PServicoService   {

	@Autowired
	private PServicoRepository PSrepository;

	public PServico save(PServico ps) {
		return PSrepository.saveAndFlush(ps);

	}
	public void delete(Long cpf) {
		PSrepository.deleteById(cpf);
	}
	
	public List<PServico> findAll(){
		return PSrepository.findAll();
		
	}
	
	public PServico logn(Long cpf , String nome) {
		return PSrepository.findByCpfAndSenha(cpf, nome);
	} 
	 
}
 