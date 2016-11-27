package br.usjt.falacidadao.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.falacidadao.dao.PostagemDAO;
import br.usjt.falacidadao.model.Area;
import br.usjt.falacidadao.model.Comentario;
import br.usjt.falacidadao.model.Postagem;
import br.usjt.falacidadao.model.Status;
import br.usjt.falacidadao.model.Usuario;

@Service
public class PostagemService {

	private PostagemDAO daoPostagem;
	private ComentarioService serviceComentario;
	
	@Autowired
	public PostagemService(PostagemDAO daoPostagem,ComentarioService serviceComentario) {
		this.daoPostagem = daoPostagem;
		this.serviceComentario = serviceComentario;
	}

	@Transactional
	public Postagem salvar(Postagem postagem){
		return daoPostagem.salvar(postagem);
	}
	
	@Transactional
	public void excluir(Postagem postagem) {
		postagem.setStatus(Status.CANCELADO);
		
		Comentario comentario = new Comentario();
		comentario.setDataComentario(new Date());
		comentario.setDescricao("Cancelado pelo usuario");
		comentario.setPostagem(postagem);
		comentario.setUsuario(postagem.getUsuario());
		serviceComentario.salvar(comentario);
		daoPostagem.salvar(postagem);
	}
	
	public Postagem buscaPorId(Long id) {
		return daoPostagem.buscaPorId(id);
	}

	public List<Postagem> listarPorAreaUsuario(Area area,String status,Usuario usuario) {
		return daoPostagem.listarPorAreaUsuario(area,status,usuario);
	}

	public List<Postagem> listarMural() {
		return daoPostagem.listarMural();
	}
	
	public List<Usuario> listarRanking() {
		return daoPostagem.listarRanking();  
	}

	public List<Postagem> listarqntdsugestao() {
		return daoPostagem.listarQuantidadeSugestao();
	}

	public List<Postagem> listarqntstatusandid(String status, Long id) {
		return daoPostagem.listarqntstatusandid(status,id);
	}

	public List<Postagem> listarporarea(Long id) {
		return daoPostagem.listarporarea(id);
	}

	public List<Postagem> quantidadetotalpostagens() {
		// TODO Auto-generated method stub
		return daoPostagem.listarquantidadetotalpostagens();
	}

	public List<Area> listartudoporarea() {
		return daoPostagem.listartudoporarea();
	}

	public List<Postagem> listarporareaid() {
		return daoPostagem.listarporareaid();
	}

	public List<Postagem> listarQuantidadeArea() {
		return daoPostagem.listarQuantidadeArea();
	}

	public List<Postagem> listarquantidadetotalpostagens() {
		return daoPostagem.listarquantidadetotalpostagens();
	}

	public List<Postagem> pegardevolutivas(Long id) {
		return daoPostagem.pegardevolutivas(id);
	}


}
