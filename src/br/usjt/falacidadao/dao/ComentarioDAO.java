package br.usjt.falacidadao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.falacidadao.model.Comentario;
import br.usjt.falacidadao.model.Postagem;

@Repository
public class ComentarioDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public Comentario salvar(Comentario comentario) {
		return em.merge(comentario);
	}

	public void excluir(Comentario comentario) {
		em.remove(comentario);
	}

	public Comentario buscaPorId(Long id) {
		return em.find(Comentario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> listarPostagem(Postagem postagem) {
		Query q = em.createQuery("select c from Comentario c where c.postagem=:postagem order by c.dataComentario");
		q.setParameter("postagem", postagem);
		return q.getResultList();
	}

}
