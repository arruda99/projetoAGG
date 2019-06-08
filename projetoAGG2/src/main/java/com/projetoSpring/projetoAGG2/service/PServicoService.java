package com.projetoSpring.projetoAGG2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.model.Usuario;
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
	public PServico login(Long cpf) {
		return PSrepository.findByCpf(cpf);
	}
	 
	public List<PServico> findByIdServico(Long id){
		return PSrepository.findByServicoId(id);
	}
public PServico validacao(PServico pservico) throws Exception{
		
		if(pservico==null){
			throw new Exception("Usuario invalido");
		}
		if(pservico.getCpf()==null)
			throw new Exception("Usuario invalido");
		if(PSrepository.findByCpf(pservico.getCpf())!= null){
			throw new Exception("Usuario invalido");
		}
		if(pservico.getNome().equals(""))
			throw new Exception("Usuario invalido");
		if(pservico.getEmail().equals(""))
			throw new Exception("Usuario invalido");
		return pservico;
		
	}
}
 