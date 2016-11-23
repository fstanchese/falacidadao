package br.usjt.falacidadao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.falacidadao.dao.ComentarioDAO;
import br.usjt.falacidadao.model.Comentario;
import br.usjt.falacidadao.model.Postagem;

@Service
public class ComentarioService {

	@Autowired
	ComentarioDAO dao;

	@Transactional
	public void salvar(Comentario comentario){
		dao.salvar(comentario);
	}
	
	@Transactional
	public void excluir(Comentario comentario) {
		dao.excluir(comentario);
	}
	
	public Comentario buscaPorId(Long id) {
		return dao.buscaPorId(id);
	}

	public List<Comentario> listarPostagem(Postagem postagem) {
		return dao.listarPostagem(postagem);
	}

}