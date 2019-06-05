package com.projetoSpring.projetoAGG2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.Servico;
import com.projetoSpring.projetoAGG2.model.Usuario;
import com.projetoSpring.projetoAGG2.repository.ServicoRepository;
import com.projetoSpring.projetoAGG2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private ServicoRepository svRep;
	
	
	@GetMapping("/login")
	public ModelAndView log() {
		
		return new ModelAndView("usuario/usuario");
		
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario) {
		
		ModelAndView mv = new ModelAndView("usuario/formUsuario");
		Usuario u1 = new Usuario();
		mv.addObject("usuario", u1);
		return mv;
	}
	
	@PostMapping("/adcionar")
	public String salvar(Usuario usuario) {
		repository.saveAndFlush(usuario);
		
		return "redirect:/login";
		
	}
	
	@GetMapping("/menu")
	public ResponseEntity<List<Servico>> list(){
		List<Servico> servicos = new ArrayList();
		servicos = svRep.findAll();
	try {
		return new  ResponseEntity<>(servicos, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}
		
		
	}
}
