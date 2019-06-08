package com.projetoSpring.projetoAGG2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.model.Servico;
import com.projetoSpring.projetoAGG2.repository.PServicoRepository;
import com.projetoSpring.projetoAGG2.repository.ServicoRepository;
import com.projetoSpring.projetoAGG2.service.PServicoService;

@Controller
@RequestMapping("/pservico")
public class PsController {

	private PServicoRepository repo;
	@Autowired
	private PServicoService service;
	@Autowired
	private ServicoRepository svRepository;
	
	@GetMapping("/")
	public ModelAndView montarLogin() {
		ModelAndView mv = new  ModelAndView("pServico/pServico");
		
		PServico ps = new PServico();
		//ps.setServico(new Servico());
		mv.addObject("pservico", ps);
		
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView montarCadastro() {
		ModelAndView mv = new ModelAndView("pServico/formPS");
		PServico ps = new PServico();
		ps.setServico(new Servico());
		mv.addObject("pservico", ps);
		mv.addObject("servicos", svRepository.findAll());
		
		return mv;
	}
	
	@PostMapping("/adcionar")
	public ModelAndView salvar(PServico pservico,Model model) {
		
		try {
			service.validacao(pservico);
			service.save(pservico);

		} catch (Exception E) {
			model.addAttribute("usuarioInvalido", true);
			return montarCadastro();
		}
		
		return montarLogin();
		
	}
	
	@PostMapping("/login")
	public ModelAndView login(PServico pservico,Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("pServico/menu");
		PServico ps = service.login(pservico.getCpf());
		mv.addObject("servicos", svRepository.findAll());
		
		session.setAttribute("teste", ps);
		if(service.logn(pservico.getCpf(), pservico.getSenha())!=null) { 
			
			return  mv;
			
		}else {
			model.addAttribute("loginInvalido", true);
			return montarLogin();
			
		}
		
		
		
	}
	@GetMapping("/alt")
	public ModelAndView montarAlt(Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("pServico/altPs");
		PServico ps = (PServico	)session.getAttribute("teste");
		mv.addObject("pservico", ps);
		mv.addObject("servicos", svRepository.findAll());
		
		return mv;
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(PServico pservico, HttpSession session) {
		//pservico.setCpf(pservico.getCpf());
		PServico ps = (PServico) session.getAttribute("teste");
		//ModelAndView mv = new ModelAndView();
		pservico.setId(ps.getId());
		pservico.setServico(ps.getServico());
		service.save(pservico);
		session.setAttribute("teste",pservico );
		return new ModelAndView("pServico/menu");
	}
	
	
}
