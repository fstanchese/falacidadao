package br.usjt.falacidadao.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.falacidadao.model.Area;
import br.usjt.falacidadao.model.Comentario;
import br.usjt.falacidadao.model.Postagem;
import br.usjt.falacidadao.model.Status;
import br.usjt.falacidadao.model.TipoUsuario;
import br.usjt.falacidadao.model.Usuario;
import br.usjt.falacidadao.properties.AreaPropertyEditor;
import br.usjt.falacidadao.service.AreaService;
import br.usjt.falacidadao.service.ComentarioService;
import br.usjt.falacidadao.service.PostagemService;
import br.usjt.falacidadao.service.UsuarioService;

@Controller
@Transactional
@RequestMapping("/postagens")
public class PostagemController {

	private ComentarioService comentarioService;
	private UsuarioService usuarioService;
	private PostagemService servicePostagem;
	private AreaService serviceArea;
	private AreaPropertyEditor areaPropertyEditor;

	@Autowired
	public PostagemController(PostagemService servicePostagem,AreaService serviceArea,AreaPropertyEditor areaPropertyEditor,
			UsuarioService usuarioService,ComentarioService comentarioService ) {
		this.servicePostagem = servicePostagem;
		this.serviceArea = serviceArea;
		this.areaPropertyEditor = areaPropertyEditor;
		this.usuarioService = usuarioService;
		this.comentarioService = comentarioService;
	}
	
	@RequestMapping
	public String listarTodos(Model model, @RequestParam(defaultValue = "0") Long area_id,
			@RequestParam(defaultValue = "") String status,HttpSession session) {
		Area area = serviceArea.buscaPorId(area_id);
		
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario = usuarioService.buscaPorId(usuario.getId());
		
		model.addAttribute("postagens", servicePostagem.listarPorAreaUsuario(area,status,usuario));
		model.addAttribute("areas", serviceArea.listar());
		model.addAttribute("statuss", Status.values());
		model.addAttribute("areaFiltro", area);
		model.addAttribute("statusFiltro", status);
		model.addAttribute("usuarioLogado", usuario);
		return "postagem/listaPostagem";
	}

	@RequestMapping("/novo")
	public String addpostagem(Model model) {
		model.addAttribute("areas", serviceArea.listar());
		model.addAttribute("postagem", new Postagem());
		return "postagem/crudPostagem";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPostagem(Model model, @PathVariable("id") Long id,HttpSession session) throws IOException {
		Postagem postagem = servicePostagem.buscaPorId(id);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario = usuarioService.buscaPorId(usuario.getId());
		
		model.addAttribute("areas", serviceArea.listar());
		model.addAttribute("comentarios", comentarioService.listarPostagem(postagem));
		model.addAttribute("statuss", Status.values());
		model.addAttribute("postagem", postagem);
		model.addAttribute("usuarioLogado", usuario);
		return "postagem/crudPostagem";
	}
	
	@RequestMapping(value = "/mural/{id}", method = RequestMethod.GET)
	public String muralPostagem(Model model, @PathVariable("id") Long id,HttpSession session) throws IOException {
		Postagem postagem = servicePostagem.buscaPorId(id);
		Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
		usuario = usuarioService.buscaPorId(usuario.getId());
		
		model.addAttribute("areas", serviceArea.listar());
		model.addAttribute("statuss", Status.values());
		model.addAttribute("postagem", postagem);
		model.addAttribute("usuarioLogado", usuario);
		return "postagem/crudMural";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePostagem(Model model, @PathVariable("id") Long id,HttpSession session) throws IOException {
		Postagem postagem = servicePostagem.buscaPorId(id);
		servicePostagem.excluir(postagem);
		return "redirect:/postagens";
	}
	
	@RequestMapping(value = "/crudPostagem", method = RequestMethod.POST)
	public String crudpostagem(@Valid @ModelAttribute("postagem") Postagem postagem, BindingResult result,
			Model model, @RequestParam("fileName") MultipartFile fileName,HttpSession session) throws IOException {
		if (!result.hasErrors()) {
			if (!fileName.isEmpty() && fileName.getSize() <= 20971520) {
				postagem.setImagem(fileName.getBytes());
			}
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			usuario = usuarioService.buscaPorId(usuario.getId());
			if (postagem.getId()==null) {
				postagem.setUsuario(usuario);
				postagem.setStatus(Status.ABERTO);
				postagem.setDataSugestao(new Date());
			} else {
				Postagem postado = servicePostagem.buscaPorId(postagem.getId());
				if (! usuario.getTipoUsuario().equals(TipoUsuario.SUPERVISOR)) {
					postagem.setImagem(postado.getImagem());
					postagem.setDescricao(postado.getDescricao());
				}
				if (usuario.getTipoUsuario().equals(TipoUsuario.AVALIADOR)) {
					if ("on".equals(postagem.getMural())) {
						if (postado.getDataMural() == null) {
							postagem.setDataMural(new Date());
						} else {
							postagem.setDataMural(postado.getDataMural());
						}
					} else {
						postagem.setDataMural(null);
					}
				} else {
					postagem.setDataMural(postado.getDataMural());
				}
				postagem.setUsuario(postado.getUsuario());
				postagem.setDataSugestao(postado.getDataSugestao());
				
				if (!postagem.getComentario().isEmpty()) {
					Comentario comentario = new Comentario();
					comentario.setDataComentario(new Date());
					comentario.setDescricao(postagem.getComentario());
					comentario.setPostagem(postagem);
					comentario.setUsuario(usuario);
					
					comentarioService.salvar(comentario);
				}
			}

			servicePostagem.salvar(postagem);
			return "redirect:/postagens";
		} else {
			Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
			usuario = usuarioService.buscaPorId(usuario.getId());
			model.addAttribute("areas", serviceArea.listar());
			model.addAttribute("comentarios", comentarioService.listarPostagem(postagem));
			model.addAttribute("statuss", Status.values());
			model.addAttribute("usuarioLogado", usuario);
			model.addAttribute("postagem", postagem);
			return "postagem/crudPostagem";
		}
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Long id, HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException {

		if (id > 0) {
			Postagem postagem = servicePostagem.buscaPorId(id);
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(postagem.getImagem());
			response.getOutputStream().close();
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Area.class, areaPropertyEditor);
	}
	
}
