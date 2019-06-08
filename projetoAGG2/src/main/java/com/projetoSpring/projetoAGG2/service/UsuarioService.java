package com.projetoSpring.projetoAGG2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoSpring.projetoAGG2.model.Usuario;
import com.projetoSpring.projetoAGG2.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario save(Usuario usuario) {
		
		return repository.saveAndFlush(usuario);
	}
	
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	public Usuario logn(Long cpf , String nome) {
		return repository.findByCpfAndSenha(cpf, nome);
	} 
	public Usuario login(Long cpf) {
		return repository.findByCpf(cpf);
	}
	
	public Usuario validacao(Usuario usuario) throws Exception{
		
		if(usuario==null){
			throw new Exception("Usuario invalido");
		}
		if(usuario.getCpf()==null)
			throw new Exception("Usuario invalido");
		if(repository.findByCpf(usuario.getCpf())!= null){
			throw new Exception("Usuario invalido");
		}
		if(usuario.getNome().equals(""))
			throw new Exception("Usuario invalido");
		if(usuario.getEmail().equals(""))
			throw new Exception("Usuario invalido");
		return usuario;
		
	}
public Usuario validacao2(Usuario usuario) throws Exception{
		
		if(usuario==null){
			throw new Exception("Usuario invalido");
		}
		if(usuario.getCpf()==null)
			throw new Exception("Usuario invalido");
		
		if(usuario.getNome().equals(""))
			throw new Exception("Usuario invalido");
		if(usuario.getEmail().equals(""))
			throw new Exception("Usuario invalido");
		return usuario;
		
	}
	
	
	 
}

