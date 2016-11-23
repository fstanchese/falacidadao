package br.usjt.falacidadao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.falacidadao.model.Area;
import br.usjt.falacidadao.model.Postagem;
import br.usjt.falacidadao.model.Status;
import br.usjt.falacidadao.model.TipoUsuario;
import br.usjt.falacidadao.model.Usuario;

@Repository
public class PostagemDAO {

	@PersistenceContext
	EntityManager em;

	public Postagem salvar(Postagem sugestao) {
		return em.merge(sugestao);
	}

	public void excluir(Postagem sugestao) {
		em.remove(sugestao);
	}

	public Postagem buscaPorId(Long id) {
		return em.find(Postagem.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Postagem> listarPorAreaUsuario(Area area, String status, Usuario usuario) {
		String query;
		Query q = null;
		if (usuario.getTipoUsuario().equals(TipoUsuario.SUPERVISOR)) {
			if (status.equals("")) {
				query = "select p from Postagem p where p.area=:area order by p.dataSugestao";
			} else {
				query = "select p from Postagem p where p.area=:area and p.status=:status order by p.dataSugestao";
			}
			q = em.createQuery(query);
			q.setParameter("area", area);
			if (!status.equals("")) {
				q.setParameter("status", Status.valueOf(status));
			}
		}
		if (usuario.getTipoUsuario().equals(TipoUsuario.AVALIADOR)) {
			if (status.equals("")) {
				query = "select p from Postagem p where p.area=:area and p.status <> 'CANCELADO' order by p.dataSugestao";
			} else {
				query = "select p from Postagem p where p.area=:area and p.status=:status and p.status <> 'CANCELADO' order by p.dataSugestao";
			}
			q = em.createQuery(query);
			q.setParameter("area", usuario.getArea());
			if (!status.equals("")) {
				q.setParameter("status", Status.valueOf(status));
			}
		}
		if (usuario.getTipoUsuario().equals(TipoUsuario.CIDADAO)) {
			query = "select p from Postagem p where p.usuario=:usuario and p.status <> 'CANCELADO' order by p.dataSugestao";
			q = em.createQuery(query);
			q.setParameter("usuario", usuario);
			if (!status.equals("")) {
				q.setParameter("status", Status.valueOf(status));
			}
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Postagem> listarMural() {
		String query = "select p from Postagem p where p.dataMural is not null and p.status <> 'CANCELADO' order by p.dataMural";
		Query q = em.createQuery(query);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Postagem> listarRanking(){
		em.getTransaction();
		
		String query = "select p.usuario from Postagem p group by p.usuario ";
		Query q12 = em.createQuery(query);
		System.out.println(q12.getResultList().toString());
		return q12.getResultList(); 
	}


}
