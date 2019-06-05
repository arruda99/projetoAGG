package com.projetoSpring.projetoAGG2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class inicioController {
	
	
	@RequestMapping("/")
	public ModelAndView Inicio() {
		return new ModelAndView("inicio");
	}
	@RequestMapping("/lul")
	public String lul() {
		
		return "lul";
	}
	@RequestMapping("/teste11")
	public String lal() {
		
		return "cliente/formUsuario";
	}
	
	@RequestMapping("/teste")
	public String lil() {
		
		return "pServico/menu";
	}
}
