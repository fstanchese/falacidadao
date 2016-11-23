package br.usjt.falacidadao.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.service.PostagemService;

@Controller
public class RelatorioController {
	@Autowired
	private PostagemService ps;
	
	@RequestMapping("relatorio")
	public String relatorios(Model model,HttpSession session){
		return "relatorio";		
	}
	
	
	@RequestMapping("relatorioranking")
	public String relatorioranking(Model model,HttpSession session){
		List<Usuario> listausuario = null; 
		//TODO
		session.setAttribute("lista", listausuario);
		
		return "relatorioranking";		
	}
	
	
	@RequestMapping("relatoriocategoria")
	public String relatoriocategoria(Model model,HttpSession session){
		return "relatoriocategoria";		
	}
	
	@RequestMapping("relatoriodevolutivas")
	public String relatoriodevolutivas(Model model,HttpSession session){
		return "relatoriodevolutivas";		
	}
}
