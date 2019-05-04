package com.projetoSpring.projetoAGG2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.Usuario;
import com.projetoSpring.projetoAGG2.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/login")
	public ModelAndView log() {
		
		return new ModelAndView("usuario/usuario");
		
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/formUsuario");
		
		return mv;
	}
	
	@PostMapping("/adcionar")
	public String salvar(Usuario usuario) {
		repository.saveAndFlush(usuario);
		
		return "redirect:/login";
		
	}
}
