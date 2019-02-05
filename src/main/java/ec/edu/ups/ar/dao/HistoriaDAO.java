package ec.edu.ups.ar.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.ar.model.Historia;

@Stateless
public class HistoriaDAO {
	@Inject
	private EntityManager em;

	public void insert(Historia historia) {
		em.persist(historia);
	}
	
	public void update(Historia historia) {
		em.merge(historia);
	}
	
	public void remove(String id) {
		em.remove(this.read(id));
	}
	
	public Historia read(String id) {
		Historia historia = em.find(Historia.class, id);
		return historia;
	}
	
	public List<Historia> getHistoria(){
		String jpql = "SELECT h FROM Historia h";
		Query query = em.createQuery(jpql, Historia.class);
		List<Historia> listado = query.getResultList();		
		return listado;
	}
}
