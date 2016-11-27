package br.usjt.falacidadao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.falacidadao.dao.UsuarioDAO;
import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.service.PostagemService;

@Controller
public class LoginController {

	private UsuarioDAO daoUsuario;
	private PostagemService servicePostagem;
	
	@Autowired
	public LoginController(UsuarioDAO daoUsuario,PostagemService servicePostagem) {
		this.daoUsuario = daoUsuario;
		this.servicePostagem = servicePostagem;
	}

	@RequestMapping(value={"index","home","/"})
	public String index() {
		return "menuPrincipal";
	}
	
	@RequestMapping(value={"loginForm"})
	public String loginForm() {
		return "formlogin";
	}

	@RequestMapping("menu")
	public String menuForm(HttpSession session,Model model) {
		model.addAttribute("usuarioLogado", session.getAttribute("usuarioLogado"));
		model.addAttribute("postagens", servicePostagem.listarMural());
		return "menuPrincipal";
	}
	
	@RequestMapping("efetuaLogin")
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		Usuario login = daoUsuario.buscaLoginUsuario(usuario);
		if (login.getId() != null) {
			session.setAttribute("usuarioLogado", login);
			return "redirect:menu";
		}
		return "redirect:loginForm";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:menu";
	}
}
