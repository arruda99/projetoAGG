package com.projetoSpring.projetoAGG2.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetoSpring.projetoAGG2.model.Contrato;
import com.projetoSpring.projetoAGG2.model.PServico;
import com.projetoSpring.projetoAGG2.model.Servico;
import com.projetoSpring.projetoAGG2.model.Usuario;
import com.projetoSpring.projetoAGG2.repository.ContratoRepository;
import com.projetoSpring.projetoAGG2.repository.ServicoRepository;
import com.projetoSpring.projetoAGG2.repository.UsuarioRepository;
import com.projetoSpring.projetoAGG2.service.PServicoService;
import com.projetoSpring.projetoAGG2.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ServicoRepository svRep;
	@Autowired
	private PServicoService psServ;
	@Autowired
	private ContratoRepository contratoRepo;
	
	@GetMapping("/")
	public ModelAndView montarLogin() {
		ModelAndView mv =  new ModelAndView("usuario/usuario");
		Usuario usuario = new Usuario();
		
		mv.addObject("usuario",usuario);
		return mv;
	}
	
	@GetMapping("/cadastro")
	public ModelAndView cadastro( ) {
		
		ModelAndView mv = new ModelAndView("usuario/formUsuario");
		Usuario u1 = new Usuario();
		mv.addObject("usuario", u1);
		return mv;
	}
	
	@PostMapping("/")
	public ModelAndView salvar(Usuario usuario,Model model) {
		ModelAndView mv = new ModelAndView("/usuario/formUsuario");
		try {
			service.validacao(usuario);
			repository.saveAndFlush(usuario);

		} catch (Exception E) {
			model.addAttribute("usuarioInvalido", true);
			return cadastro();
		}
		
		return montarLogin();
		
	}
	@PostMapping("/login")
	public ModelAndView login(Usuario usuario,Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("usuario/menu");
		Usuario usuario2 = repository.findByCpf(usuario.getCpf());
		mv.addObject("servicos", svRep.findAll());
		session.setAttribute("usuario", usuario2);
		if(service.logn(usuario.getCpf(), usuario.getSenha())!=null) { 
			
			return  mv;
			
		}else {
			model.addAttribute("loginInvalido", true);
			return montarLogin();
			
		}
	}
	
	@GetMapping("/alt")
	public ModelAndView montarAlt(Model model,HttpSession session) {
		ModelAndView mv = new ModelAndView("usuario/altUsuario");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		mv.addObject("usuario", usuario);
		
		return mv;
	}
	@PostMapping("/alterar")
	public ModelAndView alterar(Usuario usuario, HttpSession session,Model model) {
		try {
			ModelAndView mv = new ModelAndView("usuario/menu");
			Usuario usuario2 = (Usuario) session.getAttribute("usuario");
			usuario.setId(usuario2.getId());
		    
			session.setAttribute("usuario",usuario );
			mv.addObject("servicos", svRep.findAll());
			service.validacao2(usuario);
			service.save(usuario);
			return mv;

		} catch (Exception e) {
			model.addAttribute("usuarioInvalido",true);
			return montarAlt(model,session);
			// TODO: handle exception
		}
		

	}
	
	/*@GetMapping("/menu")
	public ResponseEntity<List<Servico>> list(){
		List<Servico> servicos = new ArrayList();
		servicos = svRep.findAll();
	try {
		return new  ResponseEntity<>(servicos, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
	}*/
		
	@RequestMapping("/buscar")
	public ModelAndView buscarPS(Servico servico,Model model) {
		
		
		ModelAndView mv = new ModelAndView("usuario/buscarPS");
		mv.addObject("pservicos", psServ.findByIdServico(servico.getId()));
		return mv;
	}
	@RequestMapping("/contratar")
	public ModelAndView contratar(HttpSession session,PServico pservico) {
		ModelAndView mv = new ModelAndView("usuario/menu");
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Contrato con = new Contrato();
		con.setPservico(pservico);
		con.setUsuario(usuario);
		contratoRepo.save(con);
		mv.addObject("pservicos", svRep.findAll());

		return mv;
		
	}
		
	}

