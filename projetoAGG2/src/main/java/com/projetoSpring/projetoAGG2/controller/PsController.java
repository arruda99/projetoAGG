package com.projetoSpring.projetoAGG2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.model.Servico;
import com.projetoSpring.projetoAGG2.repository.PServicoRepository;
import com.projetoSpring.projetoAGG2.repository.ServicoRepository;

@Controller
@RequestMapping("/pservico")
public class PsController {

	
	@Autowired
	private PServicoRepository repository;
	@Autowired
	private ServicoRepository svRepository;
	
	@GetMapping("/login")
	public ModelAndView log() {
		return new ModelAndView("pServico/pServico");
		
		
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mv = new ModelAndView("pServico/formPS");
		PServico ps = new PServico();
		ps.setServico(new Servico());
		mv.addObject("pservico", ps);
		mv.addObject("servicos", svRepository.findAll());
		
		return mv;
	}
	
	@PostMapping("/adcionar")
	public String salvar(PServico pservico) {
		repository.saveAndFlush(pservico);
		
		return "pServico/pServico";
		
	}
	
	
}
