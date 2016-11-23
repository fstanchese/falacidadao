package br.usjt.falacidadao.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.falacidadao.exception.ServiceException;
import br.usjt.falacidadao.model.TipoUsuario;
import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService us;

	@RequestMapping("meusdados")
	public String carregardados(Usuario usuario, HttpSession session, Model model) {
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		model.addAttribute("usuariodados", user);
		return "usuario/alterarUsuario";
	}

	@RequestMapping("alterardados")
	public String alterardados(Usuario usuario, HttpSession session, Model model) throws ServiceException {
		Usuario user = (Usuario) session.getAttribute("usuarioLogado");
		usuario.setId(user.getId());
		usuario.setSenha(user.getSenha());
		usuario.setTipoUsuario(user.getTipoUsuario());

		us.alterar(usuario);
		session.setAttribute("usuarioLogado", usuario);
		model.addAttribute("usuarioLogado", usuario);
		model.addAttribute("usuario", usuario);
		return "menuPrincipal";
	}

	@RequestMapping("cadastrarusuario")
	public String cadastrarusuario(Usuario usuario, HttpSession session) throws ServiceException {
		session.invalidate();
		return "usuario/cadastrar_usuario";
	}
	
	@RequestMapping("finalizarcadastro")
	public String finalizarCadastro(Usuario usuario, Model model, HttpSession session) {
		try {
			usuario.setTipoUsuario(TipoUsuario.CIDADAO);
			usuario = us.salvar(usuario);
			System.out.println(usuario.getId());
			session.setAttribute("usuarioLogado", usuario);
			model.addAttribute("usuarioLogado", usuario);
			model.addAttribute("usuario", usuario);
			return "menuPrincipal";
		}	
		catch (ServiceException e){
			System.out.println(e);
			return "redirect:cadastrarusuario?emailjacadastro";
		}
		catch (Exception e) {
			return "redirect:cadastrarusuario?errorprenchatodososcampos";
		}
	}
}
