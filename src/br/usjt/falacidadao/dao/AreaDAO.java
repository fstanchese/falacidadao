package br.usjt.falacidadao.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.falacidadao.model.Area;

@Repository
public class AreaDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public Area salvar(Area area) {
		return em.merge(area);
	}

	public void excluir(Area area) {
		em.remove(area);
	}

	public Area buscaPorId(Long id) {
		return em.find(Area.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Area> listar() {
		return em.createQuery("select a from Area a order by a.descricao").getResultList();
	}
}
