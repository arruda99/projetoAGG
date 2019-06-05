package com.projetoSpring.projetoAGG2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.model.Servico;
import com.projetoSpring.projetoAGG2.repository.ServicoRepository;
import com.projetoSpring.projetoAGG2.service.PServicoService;

@Controller
@RequestMapping("/pservico")
public class PsController {

	
	@Autowired
	private PServicoService service;
	@Autowired
	private ServicoRepository svRepository;
	
	@GetMapping("/login")
	public ModelAndView log() {
		ModelAndView mv = new  ModelAndView("pServico/pServico");
		
		PServico ps = new PServico();
		ps.setServico(new Servico());
		mv.addObject("pservico", ps);
		
		return mv;
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
	public ModelAndView salvar(PServico pservico) {
		service.save(pservico);
		
		return log();
		
	}
	
	@PostMapping("/log")
	public ModelAndView login(PServico pservico) {
		ModelAndView mv = new ModelAndView("pServico/menu");
		mv.addObject("pservico", pservico);
		
		if(service.logn(pservico.getCpf(), pservico.getSenha())!=null) { 
				
			return  mv;
			
		}else {
			
			return log();
		}
		
		
	}
	
	
}
