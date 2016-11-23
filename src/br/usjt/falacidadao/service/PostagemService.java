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

}
