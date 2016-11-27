package br.usjt.falacidadao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.falacidadao.model.Area;
import br.usjt.falacidadao.model.Postagem;
import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.service.AreaService;
import br.usjt.falacidadao.service.PostagemService;

@Controller
public class RelatorioController {
	@Autowired
	private PostagemService ps;

	@Autowired
	private AreaService as;

	@RequestMapping("relatorio")
	public String relatorios(Model model, HttpSession session) {
		return "relatorio";
	}

	@RequestMapping("relatorioranking")
	public String relatorioranking(Model model, HttpSession session) {
		List<Usuario> listausuario = ps.listarRanking();
		List<Postagem> listarquantidadesugestao = ps.listarqntdsugestao();
		

		for (int i = 0; i < listausuario.size(); i++) {
			// Total de sugestao por area
			listausuario.get(i).setCelular("" + listarquantidadesugestao.get(i));

			// Total de sugestoes abertas pelo usuario
			List<Postagem> listarstatusabertas = ps.listarqntstatusandid("ABERTO", listausuario.get(i).getId());
			if (listarstatusabertas.size() > 0) {
				listausuario.get(i).setLogin("" + listarstatusabertas.get(0));
			} else {
				listausuario.get(i).setLogin("" + 0);
			}

			// Total de sugestoes reprovadas
			List<Postagem> listarstatusreprovados = ps.listarqntstatusandid("BLOQUEADO", listausuario.get(i).getId());
			if (listarstatusreprovados.size() > 0) {
				listausuario.get(i).setEmail("" + listarstatusreprovados.get(0));
			} else {
				listausuario.get(i).setEmail("" + 0);
			}

			// Total de sugestoes fechadas
			List<Postagem> listarstatusfechados = ps.listarqntstatusandid("FECHADO", listausuario.get(i).getId());
			if (listarstatusfechados.size() > 0) {
				listausuario.get(i).setSenha("" + listarstatusfechados.get(0));
			} else {
				listausuario.get(i).setSenha("" + 0);
			}

		}
		System.out.println(listausuario.toString());
		session.setAttribute("listaranking", listausuario);
		return "relatorioranking";
	}

	@RequestMapping("relatoriocategoria")
	public String relatoriocategoria(Model model, HttpSession session) {
		List<Area> listarares = as.listar();
		if (listarares.size() > 0) {
			for (int i = 0; i < listarares.size(); i++) {
				List<Postagem> postagens = ps.listarporarea(listarares.get(i).getId());
				listarares.get(i).setContador("" + postagens.get(0));
			}
		}

		model.addAttribute("listarareas", listarares);

		return "relatoriocategoria";
	}

	@RequestMapping("relatoriodevolutivas")
	public String relatoriodevolutivas(Model model, HttpSession session) {
		List<Area> listausuario = ps.listartudoporarea();
		List<Postagem> listarquantidadesugestao = ps.listarQuantidadeArea();
		List<Postagem> listartodas = ps.listarquantidadetotalpostagens(); 
		
		ArrayList<Postagem> listargeral = new ArrayList<>();
		
		for (int i = 0; i < listausuario.size(); i++) {
			System.out.print(listausuario.get(i) +" e ");
			System.out.print(listarquantidadesugestao.get(i)+ " e ");
			System.out.println(listartodas.get(0));
			
			
			Postagem postagem = new Postagem();
			postagem.setId(Long.valueOf(i+1));
			List<Postagem> listarpegardevolutivas = ps.pegardevolutivas(postagem.getId());
			
			postagem.setDescricao(""+listartodas.get(0));
			postagem.setProtocolo(""+listarquantidadesugestao.get(i));
			postagem.setContador("" + listarpegardevolutivas.get(0));
			//postagem.setId(id);
			
			
		
			System.out.println(postagem.toString());
			listargeral.add(postagem); 
			
		}
		session.setAttribute("listadevolutivas", listargeral);
		
		return "relatoriodevolutivas";
	}
}
