package com.projetoSpring.projetoAGG2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	
	@Id
	public Long id;
	public String nome;
	public String endereco;
	public String telefone;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
	
	
}


